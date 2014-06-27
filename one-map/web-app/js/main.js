String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) { 
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

var phoneSlideshow = (function() {

  function init() {


    function showFloor() {
      
      if( this.getAttribute("data-showing") == "true" ) {
    	  return true;
        /* [].slice.call(document.querySelectorAll( '.floorplan' ) ).forEach( function( el, i ) {
          classie.remove( el, 'flydown' );
          classie.remove( el, 'flyup' );
        });

        classie.remove(document.querySelector( '.ms-wrapper' ), 'showingfloor');
        classie.remove( this, 'showthisfloor' );
        this.setAttribute("data-showing", "false"); */
      }
      else {
        classie.add(this, 'tag');
        var found = false;
        [].slice.call(document.querySelectorAll( '.floorplan' ) ).forEach( function( el, i ) {
          if(!classie.hasClass(el, 'tag') && !found) {
            classie.add( el, 'flydown' );
          } else if(!classie.hasClass(el, 'tag') && found){
            classie.add( el, 'flyup' );
          } else {
            found = true;
          }
        });
        classie.remove(this, 'tag');

        classie.add(document.querySelector( '.ms-wrapper' ), 'showingfloor');
        classie.add( this, 'showthisfloor' );
        this.setAttribute("data-showing", "true");
        
        var canvas = $(this).children('.canvas')[0];

        RosettaMap.mapSetup.initFloorplan(canvas.id, canvas.getAttribute("data-imgsrc"), canvas.getAttribute("data-floor"));
      }
    }

    [].slice.call(document.querySelectorAll( '.floorplan' ) ).forEach( function( el, i ) {
      el.addEventListener( 'click', showFloor, false );
    });
  }

  init();
})();

var RosettaMap = { 
  mapSetup: {
    zoomMultiplier: 0.1,
    stage: null,
    floorplanLayer: null,
    hoverLayer: null,
    popupElement: null,
    popupWidth: 200,
    popupHeight: 200,
    stageScale: 1,
    stageWidth: 521,
    stageHeight: 545,
    hotspotFill: '#0F0',
    hotspotHoverFill: '#111',
    hotspotOpacity: 0.5,
    hotspotHoverOpacity: 1,

    initFloorplan: function (id, imgSrc, floorNumber) {
    	var scope = this,
      		isHorizontal = false;
    	
    	switch(floorNumber) {
	    	case "11":
	    		scope.stageWidth = 550;
	    	    scope.stageHeight = 526;
	    		break;
	    	case "12":
	    		scope.stageWidth = 554;
	    	    scope.stageHeight = 528;
	    		break;
	    	case "13":
	    		scope.stageWidth = 552;
	    	    scope.stageHeight = 528;
	    		break;
	    	case "14":
	    		scope.stageWidth = 521;
	    	    scope.stageHeight = 501;
	    		break;
	    	case "15":
	    		scope.stageWidth = 550;
	    	    scope.stageHeight = 528;
	    		break;
	    	case "17":
	    		scope.stageWidth = 520;
	    	    scope.stageHeight = 523;
	    		break;
	    	default:
	    		console.log('not a floor');
	    		break;
    	}
      
      
      scope.stageScaleX = $('.showthisfloor').width()/scope.stageWidth;
      scope.stageScaleY = $('.showthisfloor').height()/scope.stageHeight;
      

      // is container tall or wide
      if(scope.stageScaleX > scope.stageScaleY) {
        scope.stageScale = scope.stageScaleY;
        isHorizontal = true;
      } else {
        scope.stageScale = scope.stageScaleX;
      }

      // set up kinetic stages and layers
      var stage = new Kinetic.Stage({
        container: id,
        width: $('.showthisfloor').width(), //scope.stageWidth * scope.stageScale,
        height: $('.showthisfloor').height(), //scope.stageHeight * scope.stageScale,
        draggable: false,
        fill: "#e2e2e2"
      });
	  //stage.style.background = "#e2e2e2";

      var layer = new Kinetic.Layer({
        x: 0,
        y: 0,
        width: stage.width(),
        height: stage.height(),
        draggable: false
      });
      var topLayer = new Kinetic.Layer({
        x: 0,
        y: 0,
        width: stage.width(),
        height: stage.height(),
        draggable: false
      });

      // event setup for zooming if the user has panned the image
      layer.on('dragstart', RosettaMap.mapInteractions.startMove);
      layer.on('dragmove', RosettaMap.mapInteractions.moveMap);
      layer.on('dragend', RosettaMap.mapInteractions.endMove);

      // add floorplan png and hotspots - hotspots should be set up after the image is loaded
      var floorplanObj = new Image();
      floorplanObj.src = 'images/'+ imgSrc + '.png';
      
      var imageWidth = scope.stageWidth * scope.stageScale,
      	imageHeight = scope.stageHeight * scope.stageScale,
      	imageX = 0,
      	imageY = 0;
      // image centering
      if(isHorizontal) {
    	  imageX = (stage.width() - imageWidth)/2;
      } else {
    	  imageY = (stage.height() - imageHeight)/2;
      }
      // display canvas when image is loaded
      floorplanObj.onload = function() {
        var floorplan = new Kinetic.Image({
          x: imageX,
          y: imageY,
          image: floorplanObj,
          width: imageWidth,
          height: imageHeight,
        });

        // add the image to the layer
        layer.add(floorplan);
        
        $.ajax({
            url: "/one-map/oneMap/gethotspots",
            type: 'GET',
            data: {
                floor: floorNumber,
            },
            success: function(data) {
            	for(var key in data) {
                    //create hotspot
                    var hotspotPath = new Kinetic.Path({
                      x: imageX,
                      y: imageY,
                      width: imageWidth,
                      height: imageHeight,
                      id: key,
                      scale: {x: scope.stageScale, y: scope.stageScale},
                      data: data[key],
                      fill: scope.hotspotFill,
                      opacity: scope.hotspotOpacity
                    });

                    // hotspot mouse events
                    hotspotPath.on('mouseover', function() {
                      this.setFill(RosettaMap.mapSetup.hotspotHoverFill);
                      this.opacity(RosettaMap.mapSetup.hotspotHoverOpacity);
                      this.moveTo(topLayer);
                      topLayer.drawScene();
                    });
                    hotspotPath.on('mouseout', function() {
                      if(RosettaMap.mapInteractions.activeHotspot !== this) {
                        this.setFill(RosettaMap.mapSetup.hotspotFill);
                        this.opacity(RosettaMap.mapSetup.hotspotOpacity);
                        this.moveTo(layer);
                        topLayer.draw();
                      }
                    });
                    hotspotPath.on('mousedown', function(e) {
                      var interactionsObj = RosettaMap.mapInteractions,
                        mapObj = RosettaMap.mapSetup;

                      interactionsObj.unactivateHotspot();
                      var mapObj = RosettaMap.mapSetup;

                      interactionsObj.activeHotspot = this;
                      interactionsObj.activeHotspotID = this.getId();
                      this.setFill(mapObj.hotspotHoverFill);
                      this.opacity(mapObj.hotspotHoverOpacity);
                      this.moveTo(topLayer);
                      topLayer.drawScene();

                      interactionsObj.openPopup(e);
                    });

                    layer.add(hotspotPath);
                  }

                  stage.add(layer);
                  stage.add(topLayer);

                  scope.stage = stage;
                  scope.hoverLayer = topLayer;
                  scope.floorplanLayer = layer;
                  
                  $('.zoom-btns').show();
            },
            error: function(errorThrown) {
            	console.log(errorThrown);
            }
        });        
      };
    },
  },
  mapInteractions: {
    hasPanned: false,
    startDragOffset: {},
    draggedOffset: {},
    activeHotspot: null,
    activeHotspotID: null,
    getHotspot: function(hotspotID) {
      // returns null or {type, info?}
    },
    claimHotspot: function(hotspotID, userID) {
      // post to DB the userID to the desk/WAR (hotspotID)
    },
    unclaimHotspot: function(hotspotID, userID) {
      // post to DB to unassign user to hotspot
    },
    createWarRoom: function(hotspotID, projectName) {
      // post to DB to make conference room to WAR Room
    },

    unactivateHotspot: function() {
      var scope = this,
        mapObj = RosettaMap.mapSetup;

      if(scope.activeHotspot !== null) { // unhighlight old active hotspot
        scope.activeHotspot.setFill(mapObj.hotspotFill);
        scope.activeHotspot.opacity(mapObj.hotspotOpacity);
        scope.activeHotspot.moveTo(mapObj.floorplanLayer);
        mapObj.hoverLayer.draw();
        scope.activeHotspot = null;
      }
    },

    openPopup: function(e) {
      var popupElement = RosettaMap.mapSetup.popupElement,
        x = e.evt.clientX,
        y = e.evt.clientY;
        
      var leftPos = x - (RosettaMap.mapSetup.popupWidth/2),
        rightPos = x + (RosettaMap.mapSetup.popupWidth/2);

      if (leftPos < 20) {
        x = 20;
        popupElement.style.left = x +'px';
      } else if (rightPos > (window.innerWidth)){
        x = window.innerWidth - RosettaMap.mapSetup.popupWidth;
        popupElement.style.left = x + 'px';
      } else {
        popupElement.style.left = leftPos + 'px';
      }

      // check to see the wizard is within the boundries on the y axis
      var topPos = y - (RosettaMap.mapSetup.popupHeight);

      if (topPos < 20) {
        popupElement.style.top = y +'px'; // display it below it
      } else {
        popupElement.style.top = topPos +'px'; // display above it
      }

      // import information in wizard based on return for ajax call (todo in future)
      
	  $.ajax({
        url: '/one-map/oneMap/gethotspotbyid',
        type: 'GET',
        data: {
            hotspotID: RosettaMap.mapInteractions.activeHotspotID,
        },
        success: function(data) {
        	console.log(data);
	    	/* if (true) {
	            // call ajax and get object
	            var object = {
	              type: "room",
	              name: "The Beatles",
	              number: "1728",
	              phone: "216.000.0000",
	              project: "AHA",
	              members: [
	                {
	                  name: "Dave Fagan",
	                  craft: "Creative Engineer",
	                  level: "Senior Associate",
	                  phone: "216.000.1234",
	                  email: "dave.fagan@rosetta.com"
	                },
	                {
	                  name: "Liz Judd",
	                  craft: "Creative Engineer",
	                  level: "Senior Associate",
	                  phone: "216.000.1234",
	                  email: "liz.judd@rosetta.com"
	                },
	                {
	                  name: "Dan Padgett",
	                  craft: "Software Engineer",
	                  level: "Senior Associate",
	                  phone: "216.000.1234",
	                  email: "dan.padget@rosetta.com",
	                }
	              ]
	            };
	
	            
	            if(!($('#room-template').length > 0)) {
	              $.get( "/one-map/static/js/template-room.html", function( data ) {
	            	  $( "body" ).append( data );
	            	  var content = $('#room-template').html().format(object.name, object.number, object.phone, object.project);
	            	  $(RosettaMap.mapSetup.popupElement).html(content);
	            	  
	            	  if(!($('#user-template').length > 0)) {
			              $.get( "/one-map/static/js/template-user.html", function( data ) {
			                $( "body" ).append( data );
			                var content = '';
			                for(var i = 0; i < object.members.length; i++) {
			                	content += $('#user-template').html().format(object.members[i].name, object.members[i].level, object.members[i].craft, object.members[i].phone, object.members[i].email);
			                }
			                $('#WARmembers').html(content);
			                
			              });
			            }
	              });
	            } 
	           
	          } else { // not
	
	          } */
        } ,
        error: function(jqXHR, textStatus, errorThrown) {
        	alert(errorThrown);
        }
      });

      popupElement.style.display = 'block';
    },
    zoomMap: function(zoomType) {
      var scope = this,
        mapObj = RosettaMap.mapSetup;

      scope.unactivateHotspot();

      var multiplier = zoomType === 'plus' ? 1 + mapObj.zoomMultiplier :  1 - mapObj.zoomMultiplier,
        offsetX = null,
        offsetY = null;

      mapObj.floorplanLayer.scale({
        x: mapObj.floorplanLayer.scaleX() * multiplier,
        y: mapObj.floorplanLayer.scaleY() * multiplier
      });

      mapObj.hoverLayer.scale({
        x: mapObj.floorplanLayer.scaleX(),
        y: mapObj.floorplanLayer.scaleY()
      });

      if(mapObj.floorplanLayer.scaleX() > 1) {
        mapObj.floorplanLayer.draggable(true);
      }

      // determine new offset
      if(mapObj.floorplanLayer.scaleX() < 1) { // map can't be smaller than canvas so reset the floorplan
        offsetX = 0;
        offsetY = 0;
        mapObj.floorplanLayer.scale({ x: 1, y: 1 });
        mapObj.floorplanLayer.draggable(false);
        mapObj.hoverLayer.scale({ x: 1, y: 1 });
        mapObj.hoverLayer.draggable(false);
        scope.hasPanned = false;
      } else if (scope.hasPanned) {
          offsetX = scope.draggedOffset.x * multiplier;
          offsetY = scope.draggedOffset.y * multiplier;

          scope.draggedOffset.x = offsetX;
          scope.draggedOffset.y = offsetY;
      } else { // user is just zooming in and out so the map stays centered and the image is bigger than the canvas
        var width = mapObj.floorplanLayer.width() * mapObj.floorplanLayer.scaleX(),
          height = mapObj.floorplanLayer.height() * mapObj.floorplanLayer.scaleY();
        offsetX = (width - mapObj.stage.width())/-2;
        offsetY = (height - mapObj.stage.height())/-2;
      }

      mapObj.floorplanLayer.setAbsolutePosition({ x: offsetX, y: offsetY });
      mapObj.floorplanLayer.draw();

      mapObj.hoverLayer.setAbsolutePosition({ x: offsetX, y: offsetY });
      mapObj.hoverLayer.draw();
    },
    startMove: function() {
      RosettaMap.mapInteractions.startDragOffset = {x:this.getX(), y:this.getY()};
    },
    moveMap: function() {
      var interactionsObj = RosettaMap.mapInteractions,
        mapObj = RosettaMap.mapSetup,
        popupElement = RosettaMap.mapSetup.popupElement,
        topPos = parseInt(popupElement.style.top),
        leftPos = parseInt(popupElement.style.left);

      if (leftPos < 0 || topPos < 0) {
        popupElement.style.display = "none";
        RosettaMap.mapInteractions.unactivateHotspot();
      } else {
        var leftDistance = RosettaMap.mapInteractions.startDragOffset.x - this.getX();
        var topDistance = RosettaMap.mapInteractions.startDragOffset.y - this.getY();

        popupElement.style.top = topPos - topDistance + 'px';
        popupElement.style.left = leftPos - leftDistance + 'px';

        RosettaMap.mapInteractions.startDragOffset.x = this.getX();
        RosettaMap.mapInteractions.startDragOffset.y = this.getY();
      }

      mapObj.hoverLayer.setAttrs({x:this.getX(), y:this.getY()});
      mapObj.hoverLayer.draw();
    },
    endMove: function() {
      RosettaMap.mapInteractions.hasPanned = true;
      RosettaMap.mapInteractions.draggedOffset = RosettaMap.mapSetup.floorplanLayer.getPosition();
    },
    // NOTE: mouse events for hotspots is in set up
  },
  search: {
	  submit: function() {
		 var query = $('.searchbar').val();
		 alert('call backend for search results for: '+query);
	  }
  },
  login: {
	  submitURL: null,
	  submit: function() {
		  var username = $('.username').val();
		  var password = $('.password').val();
		  $.ajax({
            url: RosettaMap.login.submitURL,
            type: 'POST',
            data: {
                j_username: username,
                j_password: password
            },
            success: function(data, textStatus, jqXHR) {
                $('.header').removeClass('login');
                setTimeout(function(){
                  $('.ms-wrapper').addClass('ms-view-layers');
                }, 500);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            }
        });
		  
	  } 
  },
  init: function() {
	//  alert('page refreshed');
    if(isLoggedIn) {
      setTimeout(function(){
        $('.ms-wrapper').addClass('ms-view-layers');
      }, 500);
    }
    
    var popupElement = document.getElementById('popup');
    RosettaMap.mapSetup.popupElement = popupElement;

    $(document).on('click', '.zoom', function () {
      if($(popupElement).css('display') === 'block') {
        $(popupElement).hide();
      }
      RosettaMap.mapInteractions.zoomMap(this.id);
    });
   
	
	 // loging in events
    $(document).on('click', '.submit-login', RosettaMap.login.submit);
  	$(document).on('click', '.logout', function() {
  		$('.header').addClass('login');
  	});
	  
  	// search click results
  	$(document).on('keypress', '.searchbar', function(e) {
  		if(e.keyCode == 13) {
  			e.preventDefault();
  			RosettaMap.search.submit();
  		}
  	});
    $(document).on('click', '.collapse-results', function(e) {
    	e.preventDefault();
    	if($('#results').hasClass('collapsed')) {
    		$('#results').removeClass('collapsed');
    	} else {
    		$('#results').addClass('collapsed');
    	}
    });
    $(document).on('click', '.clear-results', function(e) {
    	e.preventDefault();
    	$('#results').addClass('cleared');
    	$('#results .results-list').html('');
    });
  }
};
RosettaMap.init();
