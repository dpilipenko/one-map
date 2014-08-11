String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};

var Map = {
    setup: {
        zoomMultiplier: 0.1,
        stage: null,
        floorplanLayer: null,
        hoverLayer: null,
        popupElement: $('#popup'),
        popupWidth: 312,
        popupHeight: 114,
        stageScale: 1,
        stageWidth: 554,
        stageHeight: 528,
        hotspotFill: '#a6bf3e',
        hotspotHoverFill: '#a6bf3e',
        hotspotOpacity: 0,
        hotspotHoverOpacity: 0.5,
        imageX: 0,
        imageY: 0,
        pinHeight: 36,
        pinWidth: 28,
        pinSrcs: {
            'room': 'images/pin-room.png',
            'desk': 'images/pin-seat.png'
        },
        pinImages: null,
        loadPins: function(sources) {
            var images = {};
            var loadedImages = 0;
            var numImages = 0;
            // get num of sources
            for(var src in sources) {
                numImages++;
            }
            for(var src in sources) {
                images[src] = new Image();
                images[src].onload = function() {
                    if(++loadedImages >= numImages) {
                        Map.setup.pinImages = images;
                    }
                };
                images[src].src = sources[src];
            }
        },
        initFloorplan: function (id, imgSrc) {
            var scope = this,
                isHorizontal = false;

            // TODO: Make all the floorplans the same size so we can use the property defined above (17 is already set up for this)
            var canvas = $('#'+id)[0],
                floorNumber = canvas.getAttribute("data-floor");
            switch (floorNumber) {
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
                default:
                    console.log('not a custom sized floor');
                    break;
            }

            scope.stageScaleX = $('.showthisfloor').width() / scope.stageWidth;
            scope.stageScaleY = $('.showthisfloor').height() / scope.stageHeight;

            // is container tall or wide
            if (scope.stageScaleX > scope.stageScaleY) {
                scope.stageScale = scope.stageScaleY;
                isHorizontal = true;
            } else {
                scope.stageScale = scope.stageScaleX;
            }

            // set up kinetic stages and layers
            var stage = new Kinetic.Stage({
                container: id,
                width: $('.showthisfloor').width(),
                height: $('.showthisfloor').height(),
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
            layer.on('dragstart', Map.interactions.startMove);
            layer.on('dragmove', Map.interactions.moveMap);
            layer.on('dragend', Map.interactions.endMove);

            // add floorplan png and hotspots - hotspots should be set up after the image is loaded
            var floorplanObj = new Image();
            floorplanObj.src = 'images/' + imgSrc + '.png';

            var imageWidth = scope.stageWidth * scope.stageScale,
                imageHeight = scope.stageHeight * scope.stageScale;

            // image centering
            if (isHorizontal) {
                scope.imageX = (stage.width() - imageWidth) / 2;
            } else {
                scope.imageY = (stage.height() - imageHeight) / 2;
            }

            // display canvas when image is loaded
            floorplanObj.onload = function () {
                var floorplan = new Kinetic.Image({
                    x: scope.imageX,
                    y: scope.imageY,
                    image: floorplanObj,
                    width: imageWidth,
                    height: imageHeight,
                });
                layer.add(floorplan);
                
                scope.stage = stage;
                scope.hoverLayer = topLayer;
                scope.floorplanLayer = layer;

                scope.loadHotspots(imageWidth, imageHeight, id);
            };

            //show zoom btns
            $('.zoom-btns').fadeIn();
        },
        loadHotspots: function (imageWidth, imageHeight, canvasID) {
            var scope = Map.setup,
                canvas = $('#'+canvasID)[0],
                floorNumber = canvas.getAttribute("data-floor"), 
                hotspot = canvas.getAttribute("data-hotspot"), 
                pins = canvas.getAttribute("data-pins");

            $.ajax({
                url: "oneMap/getHotspots",
                type: 'GET',
                data: {
                    floor: floorNumber,
                },
                success: function (hotspotsObj) {
                    for (var key in hotspotsObj) {
                        // create hotspot
                        var hotspotPath = new Kinetic.Path({
                            x: scope.imageX,
                            y: scope.imageY,
                            width: imageWidth,
                            height: imageHeight,
                            id: key,
                            scale: {
                                x: scope.stageScale,
                                y: scope.stageScale
                            },
                            data: hotspotsObj[key].path,
                            fill: scope.hotspotFill,
                            opacity: scope.hotspotOpacity,
                            shadowColor: "#000",
                            shadowOffset: {
                                x: 0,
                                y: 3
                            },
                            shadowOpacity: 0.83,
                            shadowBlur: 10
                        });

                        // Set custom properties for each hotspot
                        hotspotPath.areaY = hotspotsObj[key].y;
                        hotspotPath.areaWidth = hotspotsObj[key].width;
                        hotspotPath.areaHeight = hotspotsObj[key].height;
                        hotspotPath.areaX = hotspotsObj[key].x;
                        hotspotPath.areaType = hotspotsObj[key].type;

                        // hotspot mouse events
                        hotspotPath.on('mouseover', Map.interactions.hotspotMouseOver);
                        hotspotPath.on('mouseout', Map.interactions.hotspotMouseOut);
                        hotspotPath.on('mousedown', Map.interactions.hotspotClick);

                        
                        if (pins !== null) {
                            var pinsArray = pins.split(',');
                            if (pinsArray.indexOf(key) > -1) {
                                switch(hotspotPath.areaType) {
                                    case 'room':
                                        pinImage = Map.setup.pinImages.room;
                                        break;
                                    case 'desk':
                                        pinImage = Map.setup.pinImages.desk;
                                        break;                                    
                                }
                                var rect = new Kinetic.Rect({
                                    x: hotspotPath.areaX + scope.imageX + scope.pinWidth/2,
                                    y: hotspotPath.areaY + scope.imageY - scope.pinHeight/2,
                                    width: scope.pinWidth,
                                    height: scope.pinHeight,
                                    fillPatternImage: Map.setup.pinImages.room,
                                    fillPatternScale: {x:1, y:1}
                                });
                                scope.floorplanLayer.add(rect);
                                hotspotPath.isPin = true;
                            }
                        } else {
                            hotspotPath.isPin = false;
                        }

                        scope.floorplanLayer.add(hotspotPath);

                        if (hotspot !== null && key == hotspot) {
                            hotspotPath.fire('mousedown');
                            $('.canvas').removeAttr('data-hotspot');
                        }
                    }

                    scope.stage.add(scope.floorplanLayer);
                    scope.stage.add(scope.hoverLayer);

                    $('.zoom-btns').fadeIn();
                },
                error: function (errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        destroyFloorplan: function () {
            if (Map.setup.stage !== null) {
                Map.setup.stage.destroy();
                $('.zoom-btns').fadeOut();
            }
        }
    },
    interactions: {
        hasPanned: false,
        startDragOffset: {},
        draggedOffset: {},
        activeHotspot: null,
        activeHotspotID: null,
        activeHotspotType: null,
        activeHotspotDimensions: [],
        activeHotspotCenter: [],

        showFloor: function () {
            if (this.getAttribute("data-showing") == "false") {
                $(this).addClass('tag');
                var found = false;
                [].slice.call(document.querySelectorAll('.floorplan')).forEach(function (el, i) {
                    if (!$(el).hasClass('tag') && !found) {
                        $(el).addClass('flydown');
                    } else if (!$(el).hasClass('tag') && found) {
                        $(el).addClass('flyup');
                    } else {
                        found = true;
                    }
                });
                $(this).removeClass('tag');

                $('.ms-wrapper').addClass('showingfloor');
                $(this).addClass('showthisfloor');
                this.setAttribute("data-showing", "true");

                var canvas = $(this).children('.canvas')[0];

                Map.setup.initFloorplan(canvas.id, canvas.getAttribute("data-imgsrc"));
            }
        },

        getHotspotInfo: function () {
            $.ajax({
                url: 'oneMap/getHotspot',
                type: 'GET',
                data: {
                    hotspotID: Map.interactions.activeHotspotID,
                },
                success: function (object) {
                    var innerDiv = $('#popup .inner');
                    
                    switch (object.type) {
                        case "room":
                            $('#popup').removeClass('desk').addClass('room');
                            if (object.project !== undefined) { // WAR room
                                var content = $('#room-template').html().format(object.name, object.number, object.phone, object.project);
                                innerDiv.html(content);

                                if (object.members.length > 0) {
                                    $('.btns-container').html('<a class="btn viewWARmembers">VIEW MEMBERS</a>');
                                }

                                var content = '';
                                for (var i = 0; i < object.members.length; i++) {
                                    content += $('#user-template').html().format(object.members[i].name, object.members[i].level, object.members[i].craft, object.members[i].phone, object.members[i].email);
                                }
                                $('#WARmembers').html(content);

                                if (object.members.length > 0) {
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
                                innerDiv.html(content);
                                $('#popup .btns-container').html('<a class="btn createWAR">THIS MEANS WAR</a>');
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
                                content += '<div class="btns-container clearfix"><a class="btn" href="mailto:' + object.email + '?Subject=ONEMAP Seat Request&Body=Hey Bro, can I have your seat?">REQUEST SEAT</a></div>';
                                innerDiv.html(content);
                            }
                            break;
                        default:
                            console.log(object.type);
                            break;
                    }
                    Map.interactions.openPopup();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        claimHotspot: function () {
            var $this = $('.claimHotspot'),
                isWARRoom = $this.hasClass('addme') ? true : false,
                $popupContent = $this.parents('.inner');

            $popupContent.addClass('loading').html('<img width="13px" height="13px" src="images/loading.gif"> Loading');

            $.ajax({
                url: 'oneMap/claimHotspot',
                type: 'GET',
                data: {
                    hotspotID: Map.interactions.activeHotspotID
                },
                success: function (object) {
                    if (isWARRoom) {
                        $popupContent.html('Done');
                        setTimeout(function () {
                            $popupContent.removeClass('loading');
                            Map.setup.popupElement.hide();
                            Map.interactions.unactivateHotspot();
                        }, 1500);
                    } else { // desk
                        $popupContent.removeClass('loading');
                        object = object.userinformation;
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email);
                        $popupContent.html(content);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        createWarRoom: function () {
            var name = $('.war-name').val();

            $.ajax({
                url: 'oneMap/createWarRoom',
                type: 'GET',
                data: {
                    hotspotID: Map.interactions.activeHotspotID,
                    projectName: name
                },
                success: function (object) {
                    $('#popup .room .btns-container')
                        .before('<div>Project: ' + name + '</div>')
                        .html('<a class="btn claimHotspot addme">ADD ME</a>');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },

        hotspotMouseOver: function () {
            if (!this.isPin) {
                this.setFill(Map.setup.hotspotHoverFill);
                this.opacity(Map.setup.hotspotHoverOpacity);
                this.moveTo(Map.setup.hoverLayer);
                Map.setup.hoverLayer.drawScene();
            }
        },
        hotspotMouseOut: function() {
            if (Map.interactions.activeHotspot !== this) {
                this.setFill(Map.setup.hotspotFill);
                this.opacity(Map.setup.hotspotOpacity);
                this.moveTo(Map.setup.floorplanLayer);
                Map.setup.hoverLayer.draw();
            }
        },
        hotspotClick: function() {
            var interactionsObj = Map.interactions,
                mapObj = Map.setup;

            interactionsObj.unactivateHotspot();
            interactionsObj.activeHotspot = this;
            interactionsObj.activeHotspotID = this.getId();
            interactionsObj.activeHotspotType = this.areaType;
            interactionsObj.activeHotspotDimensions = [this.areaWidth, this.areaHeight];
            interactionsObj.activeHotspotCenter = [this.areaX, this.areaY];

            if (!this.isPin) {
                this.setFill(mapObj.hotspotHoverFill);
                this.opacity(mapObj.hotspotHoverOpacity);
                this.moveTo(Map.setup.hoverLayer);
                Map.setup.hoverLayer.drawScene();
            }
            interactionsObj.getHotspotInfo();   
        },
        unactivateHotspot: function () {
            var scope = this,
                mapObj = Map.setup;

            if (scope.activeHotspot !== null) { // unhighlight old active hotspot
                scope.activeHotspot.setFill(mapObj.hotspotFill);
                scope.activeHotspot.opacity(mapObj.hotspotOpacity);
                scope.activeHotspot.moveTo(mapObj.floorplanLayer);
                mapObj.hoverLayer.draw();
                scope.activeHotspot = null;
            }
        },
        openPopup: function () {
            var setupObj = Map.setup,
                popupElement = Map.setup.popupElement,
                popupWidth = popupElement.width(),
                popupHeight = popupElement.height() + 9, // 9 == height of .notch
                x = ((Map.interactions.activeHotspotCenter[0] * setupObj.stageScale) + setupObj.imageX + $('.ms-perspective').offset().left) - (popupWidth/2),
                y = (Map.interactions.activeHotspotCenter[1] * setupObj.stageScale) + setupObj.imageY;

            if (x < 20) { // pass the left edget
                x = 20;
            } else if ((x + popupWidth) > (window.innerWidth)) { // pass the right edge
                x = window.innerWidth - popupWidth;
            }
            popupElement.css('left', x + 'px');

            if(Map.interactions.activeHotspot.isPin) {
                y -= setupObj.pinHeight;
            }
            if ((y - popupHeight) < 0) {
                popupElement.css('top', y + 'px'); // display it below the center
                popupElement.addClass('moveNotch');
            } else {
                popupElement.css('top', (y - popupHeight) + 'px'); // display above the center
                popupElement.removeClass('moveNotch');
            }

            Map.setup.popupElement.show();
        },
        
        zoomMap: function (zoomType) {
            var scope = this,
                mapObj = Map.setup;

            scope.unactivateHotspot();

            var multiplier = zoomType === 'plus' ? 1 + mapObj.zoomMultiplier : 1 - mapObj.zoomMultiplier,
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

            if (mapObj.floorplanLayer.scaleX() > 1) {
                mapObj.floorplanLayer.draggable(true);
            }

            // determine new offset
            if (mapObj.floorplanLayer.scaleX() < 1) { // map can't be smaller than canvas so reset the floorplan
                offsetX = 0;
                offsetY = 0;
                mapObj.floorplanLayer.scale({
                    x: 1,
                    y: 1
                });
                mapObj.floorplanLayer.draggable(false);
                mapObj.hoverLayer.scale({
                    x: 1,
                    y: 1
                });
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
                offsetX = (width - mapObj.stage.width()) / -2;
                offsetY = (height - mapObj.stage.height()) / -2;
            }

            mapObj.floorplanLayer.setAbsolutePosition({
                x: offsetX,
                y: offsetY
            });
            mapObj.floorplanLayer.draw();

            mapObj.hoverLayer.setAbsolutePosition({
                x: offsetX,
                y: offsetY
            });
            mapObj.hoverLayer.draw();
        },
        startMove: function () {
            Map.interactions.startDragOffset = {
                x: this.getX(),
                y: this.getY()
            };
        },
        moveMap: function () {
            var interactionsObj = Map.interactions,
                mapObj = Map.setup,
                topPos = parseInt(Map.setup.popupElement.css('top')),
                leftPos = parseInt(Map.setup.popupElement.css('left'));

            // find better information about hotspot position
            if (leftPos < 0 || topPos < 0) { // hotspot has moved off the screen
                Map.setup.popupElement.hide();
                Map.interactions.unactivateHotspot();
            } else {
                var leftDistance = Map.interactions.startDragOffset.x - this.getX();
                var topDistance = Map.interactions.startDragOffset.y - this.getY();

                Map.setup.popupElement.css({
                    'top' : topPos - topDistance + 'px',
                    'left' : leftPos - leftDistance + 'px'
                });

                Map.interactions.startDragOffset.x = this.getX();
                Map.interactions.startDragOffset.y = this.getY();
            }

            // move the top layer with the bottom layer
            mapObj.hoverLayer.setAttrs({
                x: this.getX(),
                y: this.getY()
            });
            mapObj.hoverLayer.draw();
        },
        endMove: function () {
            Map.interactions.hasPanned = true;
            Map.interactions.draggedOffset = Map.setup.floorplanLayer.getPosition();
        },        
        backTo3D: function() {
            $('.floorplan').each(function (el, i) {
                $(el).removeClass('flydown').removeClass('flyup');
            });

            $('.ms-wrapper').removeClass('showingfloor');
            $('.floorplan.showthisfloor').attr("data-showing", "false").removeClass('showthisfloor');
            Map.setup.destroyFloorplan();
            Map.setup.popupElement.hide();
        }
    },
    search: {
        submit: function () {
            var query = $('.searchbar').val();
            Map.search.clearResults();
            $.ajax({
                url: "oneMap/runSearch",
                type: 'GET',
                data: {
                    searchquery: query
                },
                success: function (results) {
                    // populate results tab
                    var content = '';
                    var floorArray = [];
                    for (var i = 0; i < results.length; i++) {
                        var isLinkClass = "";
                        if (results[i].hotspotId !== '') {
                            var canvas = $('.canvas[data-floor="' + results[i].floor + '"]'),
                                existingHotspots = canvas.attr('data-pins') == undefined ? '' : canvas.attr('data-pins')
                            isLinkClass = "isLink";
                            floorArray.push(parseInt(results[i].floor));
                            canvas.attr('data-pins', existingHotspots + results[i].hotspotId + ',');
                        }
                        if (results[i].level !== '') { // is user
                            content += $('#userResult-template').html().format(results[i].name, results[i].level, results[i].craft, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId);                        
                        } else { // room
                            content += $('#roomResult-template').html().format(results[i].name, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId);                        
                        }
                    }
                    $('#result-list').html(content);

                    // show results tab
                    Map.search.toggleResults();

                    // update map
                    var counts = {};
                    for (var i = 0; i < floorArray.length; i++) {
                        var num = floorArray[i];
                        counts[num] = counts[num] ? counts[num] + 1 : 1;
                    }
                    for (var key in counts) {
                        var canvas = $('.canvas[data-floor="' + key + '"]');
                        canvas.parents('.floorplan').prepend('<h1>' + counts[key] + '</h1>');
                    }
                }
            });
        },
        toggleResults: function () {
            var $results = $('#results');
            $results.removeClass('cleared')
            if($results.hasClass('collapsed')) {
                $results.removeClass('collapsed');
                Map.interactions.backTo3D();
            } else {
                $results.addClass('collapsed');
            }
        },
        clearResults: function () {
            var $results = $('#results');
            $results.addClass('cleared collapsed');
            $results.find('.results-list').html('');
            $('.canvas').removeAttr('data-pins').siblings('h1').remove();
            Map.interactions.backTo3D();
        },
        clickOnResult: function (self) {
            var floor = $(self).data('floor'),
                hotspot = $(self).data('hotspot'),
                canvas = $('.canvas[data-floor="' + floor + '"]'),
                getData = canvas.attr('data-pins') == undefined ? '' : canvas.attr('data-pins');
            canvas.attr('data-pins', getData + ',' + hotspot);
            canvas.attr('data-hotspot', hotspot);
            $('#results').addClass('collapsed');
            canvas.parent('.floorplan').trigger('click');
        }
    },
    login: {
        submitURL: null,
        submit: function () {
            var username = $('.username').val();
            var password = $('.password').val();
            $.ajax({
                url: Map.login.submitURL,
                type: 'POST',
                data: {
                    j_username: username,
                    j_password: password
                },
                success: function (data, textStatus, jqXHR) {
                    //$('.main').removeClass('login');
                    
                    //setTimeout(function () {
                        $('.header').removeClass('login');
                        setTimeout(function () {
                            $('.ms-wrapper').addClass('ms-view-layers');
                        }, 500); // TODO: verify this is the same amount of time for the login slide up animation
                    //}, 100);
                    
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        }
    },
    init: function () {
        //layout setup
        $('#offices').height($(window).height() - 95 - 20 - 70);
        $(window).resize(function(){
            $('#offices').height($(window).height() - 95 - 20 - 70);
        });

        //  alert('page refreshed');
        // ----- set up initial page view -----
        Map.setup.loadPins(Map.setup.pinSrcs);
        if (isLoggedIn) {
            $('.ms-wrapper').addClass('ms-view-layers');
        }

        // ----- lazy load in JS templates -----
        $.get("js/template-room.html", function (data) {
            $("body").append(data);
        });
        $.get("js/template-user.html", function (data) {
            $("body").append(data);
        });
        $.get("js/template-result.html", function (data) {
            $("body").append(data);
        });

        // ----- loging in events -----
        $(document).on('click', '.submit-login', Map.login.submit);
        $(document).on('keypress', '.password', function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                Map.login.submit();
            }
        });
        $(document).on('click', '.logout', function () {
            // TODO: add actual call to logout?
            $('.header').addClass('login');
        });


        // ----- admin events -----
        $(document).on('click', '.create-zone a', function () {
            if($('.showthisfloor').length > 0){
                //get all freezone seats
                //we actually should highlight these somehow, to avoid user 'click to find out'. generic color.

                $('.zone-panel').addClass('expanded');
                $('#backto3d').fadeOut();
            }
        });

        $(document).on('mouseenter', '.create-zone a', function () {
            if(!$('.showthisfloor').length > 0){
                $('#create-zone-popup').show();
            }
        });
        $(document).on('mouseleave', '.create-zone a', function () {
            $('#create-zone-popup').hide();
        });

        $(document).on('click', '.zone-panel .cancel-zone', function () {
            $('.zone-panel').removeClass('expanded');
            $('#backto3d').fadeIn();
        });

        $(document).on('click', '.zone-panel .save-zone', function () {
            //ajax call to save zone down to DB

            //animations for after ajax success
            $('.zone-panel .form').fadeOut(function(){
                $('.zone-panel .response').fadeIn();
            });
        });

        $(document).on('click', '.zone-panel .okay', function () {
            $('.zone-panel').removeClass('expanded');
            setTimeout(function () {
                $('.zone-panel .form').show();
                $('.zone-panel .response').hide();
            }, 300);
            
        });

        // ----- search interactions -----
        $(document).on('keypress', '.searchbar', function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                Map.search.submit();
            }
        });
        $(document).on('click', '.collapse-results', function (e) {
            e.preventDefault();
            Map.search.toggleResults();
        });
        $(document).on('click', '.clear-results', function (e) {
            e.preventDefault();
            Map.search.clearResults();
        });
        $(document).on('click', '#results .isLink', function() {
            Map.search.clickOnResult(this);
        });


        // ----- map interactions -----
        [].slice.call(document.querySelectorAll('.floorplan')).forEach(function (el, i) {
            el.addEventListener('click', Map.interactions.showFloor, false);
        });
        $(document).on("click", '#backto3d', Map.interactions.backTo3D);
        $(document).on('click', '.zoom', function () {
            // TODO: like to remove this and be able to keep the popup open while zooming
            if ($(Map.setup.popupElement).css('display') === 'block') {
                $(Map.setup.popupElement).hide();
            }
            Map.interactions.zoomMap(this.id);
        });

        // ----- popup interactions -----
        $(document).on('click', '#popup .close', function () {
            Map.setup.popupElement.hide();
            Map.interactions.unactivateHotspot();
        });
        $(document).on('click', '.claimHotspot', Map.interactions.claimHotspot);
        $(document).on('click', '.createWAR', function () {
            $(this).after('<input type="text" class="war-name"><a href="#" class="btn savewarname">SAVE</a>').hide();
        });
        $(document).on('click', '.savewarname', Map.interactions.createWarRoom);
        $(document).on('click', '.viewWARmembers', function () {
            $(this).hide();
            $('#popup').css({
                top: parseInt($('#popup').css('top'))-$('#members-slider').height() + 'px'
            });
            $('#members-slider').show();

        });
    }
};
Map.init();
