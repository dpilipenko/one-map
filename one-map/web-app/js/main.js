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
        [].slice.call(document.querySelectorAll( '.floorplan' ) ).forEach( function( el, i ) {
          classie.remove( el, 'flydown' );
          classie.remove( el, 'flyup' );
        });

        classie.remove(document.querySelector( '.ms-wrapper' ), 'showingfloor');
        classie.remove( this, 'showthisfloor' );
        this.setAttribute("data-showing", "false");
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
      }
    }

    [].slice.call(document.querySelectorAll( '.floorplan' ) ).forEach( function( el, i ) {
      el.addEventListener( 'click', showFloor, false );
    });
  }

  init();

})();

var RosettaMap = { 
  utilities: {
    createCookie: function(name,value,days) {
      if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
      }
      else var expires = "";
      document.cookie = name+"="+value+expires+"; path=/";
    },
    readCookie: function(name) {
      var nameEQ = name + "=";
      var ca = document.cookie.split(';');
      for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
      }
      return null;
    },
    eraseCookie: function(name) {
      var scope = this;
      scope.createCookie(name,"",-1);
    },
    ajax: function(url, type, callback){
      if(typeof callback === 'undefined') {
        callback = function() {
          return true;
        }
      }
      var xhr;
         
          if(typeof XMLHttpRequest !== 'undefined'){
            xhr = new XMLHttpRequest();
          } else {
              var versions = ["MSXML2.XmlHttp.5.0", 
                              "MSXML2.XmlHttp.4.0",
                              "MSXML2.XmlHttp.3.0", 
                              "MSXML2.XmlHttp.2.0",
                              "Microsoft.XmlHttp"]
   
               for(var i = 0, len = versions.length; i < len; i++) {
                  try {
                      xhr = new ActiveXObject(versions[i]);
                      break;
                  }
                  catch(e){}
               }
          }

          function ensureReadiness() {
              if(xhr.readyState < 4) {
                  return;
              }
               
              if(xhr.status !== 200) {
                  return;
              }
   
              // all is well  
              if(xhr.readyState === 4) {
                console.log(xhr.responseText);
                callback(xhr);

                var pageTitleStart = xhr.responseText.indexOf("<title>") + 7,
                  pageTitleEnd = xhr.responseText.indexOf("</title>"),
                  pageTitle = xhr.responseText.substring(pageTitleStart, pageTitleEnd);

                var htmlContent = document.getElementById('htmlContent');
                htmlContent.innerHTML = response.html;
            document.title = pageTitle;
            window.history.pushState({"html":xhr.responseText,"pageTitle": pageTitle},"", url);
              }           
          }

          xhr.onreadystatechange = ensureReadiness;
          
          xhr.open(type, url, true);
          xhr.send('');

      /* xhr.send("a=1&b=2&c=3"); -- this was from a post example */
    } 
  }, 
  mapSetup: {
    zoomMultiplier: 0.1,
    stage: null,
    floorplanLayer: null,
    hoverLayer: null,
    popupElement: null,
    popupWidth: 200,
    popupHeight: 200,
    stageScaleX: window.innerWidth/550,
    stageScaleY: window.innerHeight/526,
    stageScale: 1,
    stageWidth: 521,
    stageHeight: 545,
    hotspotFill: '#0F0',
    hotspotHoverFill: '#111',
    hotspotOpacity: 0.5,
    hotspotHoverOpacity: 1,

    initFloorplan: function (id, imgSrc) {
      var scope = this;

      // is container tall or wide
      if(scope.stageScaleX > scope.stageScaleY) {
        scope.stageScale = scope.stageScaleY;
      } else {
        scope.stageScale = scope.stageScaleX;
      }

      // set up kinetic stages and layers
      var stage = new Kinetic.Stage({
        container: id,
        width: scope.stageWidth * scope.stageScale,
        height: scope.stageHeight * scope.stageScale,
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
      
      // display canvas when image is loaded
      floorplanObj.onload = function() {
        var floorplan = new Kinetic.Image({
          x: 0,
          y: 0,
          image: floorplanObj,
          width: stage.width(),
          height: stage.height(),
        });

        // add the image to the layer
        layer.add(floorplan);

        // TODO get hotspots from a file
        for(var key in hotspots[id]) {
          //create hotspot
          var hotspotPath = new Kinetic.Path({
            x: 0,
            y: 283 * (scope.stageScale), // won't need to adjust this once the entire hotspot file is defined.
            width: stage.width(),
            height: stage.height(),
            scale: {x: scope.stageScale, y: scope.stageScale},
            data: hotspots[id][key],
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
      };
    },
  },

  mapInteractions: {
    hasPanned: false,
    startDragOffset: {},
    draggedOffset: {},
    activeHotspot: null,
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
      
      if (true) {
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
              email: "dan.padget@rosetta.com"
            }
          ]
        };

        if($('#user-template').length <= 0) {
          $.get( "js/template-user.html", function( data ) {
            $( "body" ).append( data );
          });
        }
        if($('#room-template').length <= 0) {
          $.get( "js/template-room.html", function( data ) {
            $( "body" ).append( data );
          });
        }
		var content = $('#room-template').html().format(object.name, object.number, object.phone, object.project);
		$(RosettaMap.mapSetup.popupElement).html(content);


      } else { // not

      }

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
    /* // this needs to move to the on click of floor
    var floorplan = $('.floorplan')[0];
    var id = floorplan.getAttribute('id');
    var imgSrc = floorplan.dataset.imgsrc;
    RosettaMap.mapSetup.initFloorplan(id, imgSrc);

    // return to normal init
    var popupElement = document.getElementById('hotspot-wizard');
    RosettaMap.mapSetup.popupElement = popupElement;

    $(document).on('click', '.zoom-button', function () {
      if($(popupElement).css('display') === 'block') {
        $(popupElement).hide();
      }
      RosettaMap.mapInteractions.zoomMap(this.id);
    });

    $(document).on('click', popupElement.id + ' .close', function () {
        $(popupElement).hide();
    });
    $(document).on('click', '#hotspot-wizard input[type="button"]', function () {
        function loadForm (data) {
          $('#hotspot-wizard .type').hide();
          $('#hotspot-wizard .information').html(data);
          $('#hotspot-wizard .information').show();
        };
        switch($('#hotspot-wizard select').val()) {
          case 'desk':
            $.get( "js/template-deskForm.html", function( data ) {
              loadForm(data);                
            });
            break;
          case 'area':
            $.get( "js/template-deskForm.html", function( data ) {
              loadForm(data);               
            });
            break;
          case 'meeting':
            $.get( "js/template-roomForm.html", function( data ) {
              loadForm(data);                
            });
            break;
        }
    }); */
	
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