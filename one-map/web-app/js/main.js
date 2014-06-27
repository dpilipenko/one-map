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
        
        RosettaMap.mapSetup.initFloorplan(canvas.id, canvas.getAttribute("data-imgsrc"), canvas.getAttribute("data-floor"), canvas.getAttribute("data-hotspot"), canvas.getAttribute("data-pins"));

        
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
    popupWidth: 312,
    popupHeight: 114,
    stageScale: 1,
    stageWidth: 521,
    stageHeight: 545,
    hotspotFill: '#a6bf3e',
    hotspotHoverFill: '#a6bf3e',
    hotspotOpacity: 0,
    hotspotHoverOpacity: 0.5,

    initFloorplan: function (id, imgSrc, floorNumber, hotspot, pins) {
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
        draggable: false
      });

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
      
      var pinImage = new Image();
	  pinImage.src = '/one-map/static/images/pin-seat.png';
		
      
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
                      opacity: scope.hotspotOpacity,
                      shadowColor: "#000",
                      shadowOffset: {x: 0, y: 3},
                      shadowOpacity: 0.83,
                      shadowBlur: 10
                    });

                    // hotspot mouse events
                    hotspotPath.on('mouseover', function() {
	                	if(this.fill() !== '#00BCE4') {
	                		this.setFill(RosettaMap.mapSetup.hotspotHoverFill);
	                        this.opacity(RosettaMap.mapSetup.hotspotHoverOpacity);
	                        this.moveTo(topLayer);
	                        topLayer.drawScene();
	                        //console.log(this.getId());
	                     }
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
                      if(!(this.fill() == '#00BCE4')) {
                    	  this.setFill(mapObj.hotspotHoverFill);
                          this.opacity(mapObj.hotspotHoverOpacity);
                          this.moveTo(topLayer);
                          topLayer.drawScene();
                      }
                      interactionsObj.openPopup(e);
                    });
                    
                    if(pins !== null) {
                    	var pinsArray = pins.split(',');
                    	if (pinsArray.indexOf(key) > -1) {
                    		hotspotPath.setFill('#00BCE4');
                    		//console.log(pinImage);
                    		//hotspotPath.fillPatternImage(pinImage);
                    		hotspotPath.setOpacity(1);
                    	}
                    }

                    layer.add(hotspotPath);
                    
                    if(hotspot !== null && key == hotspot) {
                    	hotspotPath.fire('mousedown');
                    	$('.canvas').removeAttr('data-hotspot');
                    }
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

      //show zoom btns
      $('.zoom-btns').fadeIn();
    },
    destroyFloorplan: function() {
    	if (RosettaMap.mapSetup.stage !== null) {
    		RosettaMap.mapSetup.stage.destroy();
        	$('.zoom-btns').fadeOut();
    	}
    }
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
      var popupElement = $(RosettaMap.mapSetup.popupElement),
        x = 0,
        y = 0;
      
      if(e.evt !== undefined) {
    	  x = e.evt.clientX;
    	  y = e.evt.clientY;
    	  
    	  var leftPos = x - (RosettaMap.mapSetup.popupWidth/2),
          rightPos = x + (RosettaMap.mapSetup.popupWidth/2);

        if (leftPos < 20) {
          x = 20;
          popupElement.css('left', x +'px');
        } else if (rightPos > (window.innerWidth)){
          x = window.innerWidth - RosettaMap.mapSetup.popupWidth;
          popupElement.css('left', x +'px');
        } else {
          popupElement.css('left', leftPos +'px');
        }

        // check to see the wizard is within the boundries on the y axis
        var topPos = y - (RosettaMap.mapSetup.popupHeight * 2);

        if (topPos < 0) {
          popupElement.css('top', y - 90 +'px'); // display it below the click
          $('#popup').addClass('moveNotch');
        } else {
          popupElement.css('top', topPos +'px'); // display above it
          $('#popup').removeClass('moveNotch');
        }
        $('#popup .notch').show();
        
      } else {
    	  var firstDataPoint = RosettaMap.mapInteractions.activeHotspot.getData().split(' ')[0].replace('M', '').split(',');
        //console.log(firstDataPoint);
    	  x = $('.showthisfloor').width() - RosettaMap.mapSetup.popupWidth + "px"; //parseInt(firstDataPoint[0]) + 40 + (RosettaMap.mapSetup.popupWidth/2);
    	  y = $('.showthisfloor').height() - RosettaMap.mapSetup.popupHeight - 20 + "px";//firstDataPoint[1];
    	  console.log(x);
    	  console.log(y);
          popupElement.css('left', x);
          popupElement.css('top', y);
    	  $('#popup .notch').hide();
    	  
      }

	  $.ajax({
        url: '/one-map/oneMap/gethotspotbyid',
        type: 'GET',
        data: {
            hotspotID: RosettaMap.mapInteractions.activeHotspotID,
        },
        success: function(object) {
        	// TODO: add buttons and interactions
        	var innerDiv = $('#popup .inner');
        	switch(object.type) {
	        	case "room":
              $('#popup').removeClass('desk').addClass('room');
	        		if(object.project !== undefined) { // WAR room
	        			var content = $('#room-template').html().format(object.name, object.number, object.phone, object.project);
	  	            	innerDiv.html(content);

                    if(object.members.length > 0){
                      $('.btns-container').html('<a class="btn viewWARmembers">VIEW MEMBERS</a>');
                    }
	  	            	
	  	            	var content = '';
		                for(var i = 0; i < object.members.length; i++) {
		                	content += $('#user-template').html().format(object.members[i].name, object.members[i].level, object.members[i].craft, object.members[i].phone, object.members[i].email);
		                }
		                $('#WARmembers').html(content);

                    if(object.members.length > 0){
                      //init slider
                      var slider = $('#WARmembers').leanSlider({
                          directionNav: '#slider-direction-nav',
                          pauseTime: 0,
                          prevText: 'Prev',
                          nextText: 'Next'
                      });

                    }
	        		} else { // conference room

	        			var content = $('#room-template').html().format(object.name, object.number, object.phone, '');
	  	            	content += '<div class="btns-container clearfix"><a class="btn createWAR">THIS MEANS WAR</a></div>';
	  	            	innerDiv.html(content);
	  	            } 
	        		break;
	        	case "desk":
              $('#popup').removeClass('room').addClass('desk');
	        		if (!object.claimed) {
	        			var content = '<div class="btns-container clearfix"><a class="btn claimHotspot">CLAIM THIS SEAT</a></div>';
	                	innerDiv.html(content).data("profile", object);
	        		} else if (object.claimed && object.isMine) { // should be done
	        			var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email);
	        			innerDiv.html(content);
	        		} else { // other user claimed seat
	        			var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email);
	                	content += '<div class="btns-container clearfix"><a class="btn" href="mailto:dave.fagan@rosetta.com?Subject=ONEMAP Seat Request&Body=Hey Bro, can I have your seat?">REQUEST SEAT</a></div>';
	                	innerDiv.html(content);
	        		}
                	
	        		break;
	        	default:
	        		alert(object.type);
	        		break;
        	}
        	popupElement.show();
        } ,
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log(errorThrown);
        }
      });
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
		 
		 $('.canvas').removeAttr('data-pins');
	     $('#backto3d').trigger('click');

		 
		 $.ajax({
            url: "/one-map/oneMap/runSearch",
            type: 'GET',
            data: {
            	searchquery: query
            },
            success: function(results) {
            	// populate results tab
            	var content = '';
            	var floorArray = [];
                for(var i = 0; i < results.length; i++) {
                	var isLink = "";
                	if(results[i].hotspotId !== '') {
                		isLink = "isLink";
                		floorArray.push(parseInt(results[i].floor));
                		var canvas = $('.canvas[data-floor="'+results[i].floor+'"]');
                		var theData = canvas.attr('data-pins') == undefined ? '' : canvas.attr('data-pins');
                		canvas.attr('data-pins', theData+results[i].hotspotId+',');
                	}
                	content += $('#result-template').html().format(results[i].name, results[i].level, results[i].craft, results[i].location, isLink, results[i].floor, results[i].hotspotId);
                }
                $('#result-list').html(content);
                
            	// show results tab
                $('#results').show().removeClass('cleared').removeClass('collapsed');
            	
            	// update map
                var counts = {};
                for(var i = 0; i< floorArray.length; i++) {
                    var num = floorArray[i];
                    counts[num] = counts[num] ? counts[num]+1 : 1;
                }
                for(var key in counts) {
                	var canvas = $('.canvas[data-floor="'+key+'"]');
                	canvas.parents('.floorplan').prepend('<h1>'+counts[key]+'</h1>');
                }
            	
            	// on click of active user
                $(document).on('click', '#results .isLink', function() {
                	var floor = $(this).data('floor');
                	var hotspot = $(this).data('hotspot');
                	var canvas = $('.canvas[data-floor="'+floor+'"]');
                	var getData = canvas.attr('data-pins') == undefined ? '' : canvas.attr('data-pins');
                	canvas.attr('data-pins', getData+','+hotspot);
                	canvas.attr('data-hotspot', hotspot);
                	$('#results').addClass('collapsed');
                	canvas.parent('.floorplan').trigger('click');
                });
            	
            },
            error: function(data) {
            }
	  	});
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
                $('.main').removeClass('login');
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
    
    // lazy load in JS templates
    $.get( "/one-map/static/js/template-room.html", function( data ) {
  	  $( "body" ).append( data );
	  });
	  $.get( "/one-map/static/js/template-user.html", function( data ) {
	  	$( "body" ).append( data );
	  });
	  $.get( "/one-map/static/js/template-result.html", function( data ) {
	  	$( "body" ).append( data );
	  });
    
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
  	$(document).on('keypress', '.password', function(e) {
  		if(e.keyCode == 13) {
  			e.preventDefault();
  			RosettaMap.login.submit();
  		}
  	});
    $(document).on('click', '.collapse-results', function(e) {
    	e.preventDefault();
    	if($('#results').hasClass('collapsed')) {
    		$('#results').removeClass('collapsed');
        	$('#backto3d').trigger('click');
    	} else {
    		$('#results').addClass('collapsed');
    	}
    });
    $(document).on('click', '.clear-results', function(e) {
    	e.preventDefault();
    	$('#results').addClass('cleared');
    	$('#results .results-list').html('');
    	$('.canvas').removeAttr('data-pins').siblings('h1').remove();
    	$('#backto3d').trigger('click');
    });

    // map interactions
    $(document).on("click", '#backto3d', function(){
        $( '.floorplan' ).each( function( el, i ) {
          $(this).removeClass('flydown').removeClass('flyup');
        });

        $( '.ms-wrapper' ).removeClass('showingfloor');
        $('.floorplan.showthisfloor').attr("data-showing", "false").removeClass('showthisfloor');
    	RosettaMap.mapSetup.destroyFloorplan();
    	$('#popup').hide();
    });
    $(document).on("click", '#popup .close', function(){
        $('#popup').hide();
        RosettaMap.mapInteractions.unactivateHotspot();

    });

    $(document).on("click", '.claimHotspot', function(){

      $this = $(this);

      var bool = $(this).hasClass('addme') ? true : false;
      $('#popup').data("bool", bool);
      $this.parents('.inner').addClass('loading').html('<img width="13px" height="13px" src="images/loading.gif"> Loading');

      $.ajax({
        url: '/one-map/oneMap/claimHotspot',
        type: 'GET',
        data: {
            hotspotID: RosettaMap.mapInteractions.activeHotspotID
        },
        success: function(object) {
          // TODO: add buttons and interactions

          if($('#popup').data("bool")){
              $('#popup .inner').html('Done');
              setTimeout(function(){ 
                  $('#popup').hide();
                  RosettaMap.mapInteractions.unactivateHotspot();
              },2500);
          } else {
            $('#popup .inner').removeClass('loading');
            object = object.userinformation;
            var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email);
            $('#popup .inner').html(content);
          }
          
        } ,
        error: function(jqXHR, textStatus, errorThrown) {
          console.log(errorThrown);
        }
      });
    });

    $(document).on('click', '.viewWARmembers', function(){
      $(this).hide();
      $('#members-slider').show();
    });


    $(document).on("click", '.createWAR', function(){
      $(this).after('<input class="war-name"><a href="#" class="btn savewarname">SAVE</a>');
      $(this).hide();


      $(document).on('click', '.savewarname', function(){
        var name = $('input.war-name').val();

        $.ajax({
          url: '/one-map/oneMap/createWarRoom',
          type: 'GET',
          data: {
              roomID: RosettaMap.mapInteractions.activeHotspotID,
              projectName: name
          },
          success: function(object) {
            // TODO: add buttons and interactions
            $('#popup .btns-container').before('<div>Project: ' + name);
            $('#popup .btns-container').html('<a class="btn claimHotspot addme">ADD ME</a>');
          } ,
          error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
          }
        });

      });

    });
  }
};
RosettaMap.init();
