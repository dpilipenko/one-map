String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};

var OneMap = {
    map: {
        // map properties
        stage: null,
        stageScale: 1,
        stageWidth: 554,
        stageHeight: 528,
        floorplanLayer: null,        
        floorplanX: 0,
        floorplanY: 0,
        interactiveLayer: null,

        hasPanned: false,
        startDragOffset: {},
        draggedOffset: {},
        zoomMultiplier: 0.1,
        zoom: function (zoomType) {
            OneMap.hotspots.unactivate();

            var multiplier = zoomType === 'plus' ? 1 + OneMap.map.zoomMultiplier : 1 - OneMap.map.zoomMultiplier,
                offsetX = null,
                offsetY = null;

            OneMap.map.floorplanLayer.scale({
                x: OneMap.map.floorplanLayer.scaleX() * multiplier,
                y: OneMap.map.floorplanLayer.scaleY() * multiplier
            });

            OneMap.map.interactiveLayer.scale({
                x: OneMap.map.floorplanLayer.scaleX(),
                y: OneMap.map.floorplanLayer.scaleY()
            });

            if (OneMap.map.floorplanLayer.scaleX() > 1) {
                OneMap.map.floorplanLayer.draggable(true);
            }

            // determine new offset
            if (OneMap.map.floorplanLayer.scaleX() < 1) { // map can't be smaller than canvas so reset the floorplan
                $('#minus').addClass('disabled');
                offsetX = 0;
                offsetY = 0;
                OneMap.map.floorplanLayer.scale({
                    x: 1,
                    y: 1
                });
                OneMap.map.floorplanLayer.draggable(false);
                OneMap.map.interactiveLayer.scale({
                    x: 1,
                    y: 1
                });
                OneMap.map.interactiveLayer.draggable(false);
                OneMap.map.hasPanned = false;
            } else if (OneMap.map.hasPanned) {
                $('#minus').removeClass('disabled');
                offsetX = OneMap.map.draggedOffset.x * multiplier;
                offsetY = OneMap.map.draggedOffset.y * multiplier;

                OneMap.map.draggedOffset.x = offsetX;
                OneMap.map.draggedOffset.y = offsetY;
            } else { // user is just zooming in and out so the map stays centered and the image is bigger than the canvas
                var width = OneMap.map.floorplanLayer.width() * OneMap.map.floorplanLayer.scaleX(),
                    height = OneMap.map.floorplanLayer.height() * OneMap.map.floorplanLayer.scaleY();
                offsetX = (width - OneMap.map.stage.width()) / -2;
                offsetY = (height - OneMap.map.stage.height()) / -2;
                $('#minus').removeClass('disabled');
            }

            OneMap.map.floorplanLayer.setAbsolutePosition({
                x: offsetX,
                y: offsetY
            });
            OneMap.map.floorplanLayer.draw();

            OneMap.map.interactiveLayer.setAbsolutePosition({
                x: offsetX,
                y: offsetY
            });
            OneMap.map.interactiveLayer.draw();
        },
        moveStart: function() {
            OneMap.map.startDragOffset = {
                x: this.getX(),
                y: this.getY()
            };
        },
        move: function() {
            //var topPos = parseInt(OneMap.hotspots.modalElement.css('top')),
            //    leftPos = parseInt(OneMap.hotspots.modalElement.css('left'));

            // find better information about hotspot position
            //if (leftPos < 0 || topPos < 0) { // hotspot has moved off the screen
            //    OneMap.hotspots.modalElement.hide();
            //    OneMap.hotspots.unactivate();
            //} else {
                var leftDistance = OneMap.map.startDragOffset.x - this.getX();
                var topDistance = OneMap.map.startDragOffset.y - this.getY();

                // OneMap.hotspots.modalElement.css({
                //     'top' : topPos - topDistance + 'px',
                //     'left' : leftPos - leftDistance + 'px'
                // });

                OneMap.map.startDragOffset.x = this.getX();
                OneMap.map.startDragOffset.y = this.getY();
            //}

            // move the top layer with the bottom layer
            OneMap.map.interactiveLayer.setAttrs({
                x: this.getX(),
                y: this.getY()
            });
            OneMap.map.interactiveLayer.draw();
        },
        moveEnd: function() {
            OneMap.map.hasPanned = true;
            OneMap.map.draggedOffset = OneMap.map.floorplanLayer.getPosition();
        },
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

                OneMap.map.loadFloor(canvas.id, canvas.getAttribute("data-imgsrc"));
            }
        },
        loadFloor: function (id, imgSrc) {
            var isHorizontal = false;

            // TODO: Make all the floorplans the same size so we can use the property defined above (17 is already set up for this)
            var canvas = $('#'+id)[0],
                floorNumber = canvas.getAttribute("data-floor");
            switch (floorNumber) {
                case "12":
                    OneMap.map.stageWidth = 554;
                    OneMap.map.stageHeight = 528;
                    break;
                case "13":
                    OneMap.map.stageWidth = 552;
                    OneMap.map.stageHeight = 528;
                    break;
                case "14":
                    OneMap.map.stageWidth = 521;
                    OneMap.map.stageHeight = 501;
                    break;
                case "15":
                    OneMap.map.stageWidth = 550;
                    OneMap.map.stageHeight = 528;
                    break;
                default:
                    console.log('not a custom sized floor');
                    break;
            }
            
            OneMap.map.stageScaleX = $('.showthisfloor').width() / OneMap.map.stageWidth;
            OneMap.map.stageScaleY = $('.showthisfloor').height() / OneMap.map.stageHeight;

            // is container tall or wide
            if (OneMap.map.stageScaleX > OneMap.map.stageScaleY) {
                OneMap.map.stageScale = OneMap.map.stageScaleY;
                isHorizontal = true;
            } else {
                OneMap.map.stageScale = OneMap.map.stageScaleX;
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
            layer.on('dragstart', OneMap.map.moveStart);
            layer.on('dragmove', OneMap.map.move);
            layer.on('dragend', OneMap.map.moveEnd);

            // add floorplan png and hotspots - hotspots should be set up after the image is loaded
            var floorplanObj = new Image();
            floorplanObj.src = 'images/' + imgSrc + '.png';

            var imageWidth = OneMap.map.stageWidth * OneMap.map.stageScale,
                imageHeight = OneMap.map.stageHeight * OneMap.map.stageScale;

            // image centering
            if (isHorizontal) {
                OneMap.map.floorplanX = (stage.width() - imageWidth) / 2;
            } else {
                OneMap.map.floorplanY = (stage.height() - imageHeight) / 2;
            }

            // display canvas when image is loaded
            floorplanObj.onload = function () {
                var floorplan = new Kinetic.Image({
                    x: OneMap.map.floorplanX,
                    y: OneMap.map.floorplanY,
                    image: floorplanObj,
                    width: imageWidth,
                    height: imageHeight,
                });
                layer.add(floorplan);
                
                OneMap.map.stage = stage;
                OneMap.map.interactiveLayer = topLayer;
                OneMap.map.floorplanLayer = layer;

                OneMap.hotspots.load(imageWidth, imageHeight, id);
            };

            //show zoom btns
            $('.zoom-btns').fadeIn();
        },
        unloadFloor: function () {
            if (OneMap.map.stage !== null) {
                OneMap.map.stage.destroy();
                $('.zoom-btns').fadeOut();
            }
        },
        backTo3D: function() {
            $('.ms-wrapper').removeClass('showingfloor');
            $('.floorplan.showthisfloor').attr("data-showing", "false").removeClass('showthisfloor');
            OneMap.map.unloadFloor();

            $('.floorplan').each(function() {
                $(this).removeClass('flydown');
                $(this).removeClass('flyup');
            });
        },
        init: function() {
            OneMap.hotspots.init();

            $(document).on('click', '.floorplan', OneMap.map.showFloor);
            $(document).on("click", '#backto3d', OneMap.map.backTo3D);
            $(document).on('click', '.zoom', function () {
                // TODO: like to remove this and be able to keep the popup open while zooming
                // if ($(OneMap.hotspots.modalElement).css('display') === 'block') {
                //     $(OneMap.hotspots.modalElement).hide();
                // }
                OneMap.map.zoom(this.id);
            });
        }
    },
    hotspots: {
        // hotspot information
        modalElement: $('.md-modal'),
        // popupWidth: 312,
        // popupHeight: 114,
        
        // hotspot properties
        defaultFill: '#a6bf3e',
        defaultOpacity: 0,
        hoverFill: '#a6bf3e',
        hoverOpacity: 0.5,

        active: {
            hotspot: null,
            id: null,
            type: null,
            center: []
        },

        mouseOver: function () {
            if (!this.isPin && !OneMap.zones.isCreating) {
                this.setFill(OneMap.hotspots.hoverFill);
                this.setOpacity(OneMap.hotspots.hoverOpacity);
                this.moveTo(OneMap.map.interactiveLayer);
                OneMap.map.interactiveLayer.drawScene();
            }
        },
        mouseOut: function() {
            if (OneMap.hotspots.active.hotspot !== this && !OneMap.zones.isCreating) {
                this.setFill(OneMap.hotspots.defaultFill);
                this.setOpacity(OneMap.hotspots.defaultOpacity);
                this.moveTo(OneMap.map.floorplanLayer);
                OneMap.map.interactiveLayer.draw();
            }
        },

        // TODO: needs to move to modal implementation 
        getInfo: function () {
            $.ajax({
                url: 'oneMap/getHotspot',
                type: 'GET',
                data: {
                    hotspotID: OneMap.hotspots.active.id,
                },
                success: function (object) {
                    var innerDiv = OneMap.hotspots.modalElement.find('.md-content');
                    
                    switch (object.type) {
                        case "room":
                            OneMap.hotspots.modalElement.removeClass('desk').addClass('room');
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
                                OneMap.hotspots.modalElement.find('.btns-container').html('<a class="btn createWAR">THIS MEANS WAR</a>');
                            }
                            break;
                        case "desk":
                            OneMap.hotspots.modalElement.removeClass('room').addClass('desk');
                            
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
                    OneMap.hotspots.showModal();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        claim: function () {
            var $this = $('.claimHotspot'),
                isWARRoom = $this.hasClass('addme') ? true : false,
                $popupContent = $this.parents('.md-content');

            $popupContent.addClass('loading').html('<img width="13px" height="13px" src="images/loading.gif"> Loading');

            $.ajax({
                url: 'oneMap/claimHotspot',
                type: 'GET',
                data: {
                    hotspotID: OneMap.hotspots.active.id
                },
                success: function (object) {
                    if (isWARRoom) {
                        $popupContent.html('Done');
                        setTimeout(function () {
                            $popupContent.removeClass('loading');
                            OneMap.hotspots.closeModal();
                            OneMap.hotspots.unactivate();
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
                    hotspotID: OneMap.hotspots.active.id,
                    projectName: name
                },
                success: function (object) {
                    OneMap.hotspots.modalElement.find('.room .btns-container')
                        .before('<div>Project: ' + name + '</div>')
                        .html('<a class="btn claimHotspot addme">ADD ME</a>');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        click: function() {
            //console.log(this.areaID);
            if(!OneMap.zones.isCreating) {
                OneMap.hotspots.unactivate();
                OneMap.hotspots.active.hotspot = this;
                OneMap.hotspots.active.id = this.getId();
                OneMap.hotspots.active.hotspot.type = this.areaType;
                OneMap.hotspots.active.hotspot.center = [this.areaX, this.areaY];

                if (!this.isPin) {
                    this.setFill(OneMap.hotspots.hoverFill);
                    this.setOpacity(OneMap.hotspots.hoverOpacity);
                    this.moveTo(OneMap.map.interactiveLayer);
                    OneMap.map.interactiveLayer.drawScene();
                }
                
                OneMap.hotspots.getInfo(); 
            } else {
                OneMap.zones.toggleSelected(this);
            }  
        },
        unactivate: function () {
            if (OneMap.hotspots.active.hotspot !== null) { // unhighlight old active hotspot
                OneMap.hotspots.active.hotspot.setFill(OneMap.hotspots.defaultFill);
                OneMap.hotspots.active.hotspot.setOpacity(OneMap.hotspots.defaultOpacity);
                OneMap.hotspots.active.hotspot.moveTo(OneMap.map.floorplanLayer);
                OneMap.map.interactiveLayer.draw();
                OneMap.hotspots.active.hotspot = null;
            }
        },
        load: function (imageWidth, imageHeight, canvasID) {
            var canvas = $('#'+canvasID)[0],
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
                            x: OneMap.map.floorplanX,
                            y: OneMap.map.floorplanY,
                            width: imageWidth,
                            height: imageHeight,
                            id: key,
                            scale: {
                                x: OneMap.map.stageScale,
                                y: OneMap.map.stageScale
                            },
                            data: hotspotsObj[key].path,
                            fill: OneMap.map.defaultFill,
                            opacity: OneMap.map.defaultOpacity,
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
                        hotspotPath.areaX = hotspotsObj[key].x;
                        hotspotPath.areaType = hotspotsObj[key].type;
                        hotspotPath.areaID = hotspotsObj[key].assignedSeatId;

                        // hotspot mouse events
                        hotspotPath.on('mouseover', OneMap.hotspots.mouseOver);
                        hotspotPath.on('mouseout', OneMap.hotspots.mouseOut);
                        hotspotPath.on('mousedown', OneMap.hotspots.click);

                        OneMap.map.floorplanLayer.add(hotspotPath);
                    }

                    if(!$.isEmptyObject(OneMap.search.mapPins)) {
                        OneMap.search.displayPins();
                    }

                    OneMap.map.stage.add(OneMap.map.floorplanLayer);
                    OneMap.map.stage.add(OneMap.map.interactiveLayer);

                    $('.zoom-btns').fadeIn();
                },
                error: function (errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        closeModal: function(){
            OneMap.hotspots.modalElement.removeClass('md-show');
            OneMap.hotspots.unactivate();
        },
        showModal: function(){
            OneMap.hotspots.modalElement.addClass('md-show');
            $('.md-overlay').off( 'click');
            $('.md-overlay').on( 'click' , OneMap.hotspots.closeModal);
        },
        init: function() {
            // ----- popup interactions -----

            $('.md-close').on( 'click', function( ev ) {
                ev.stopPropagation();
                OneMap.hotspots.closeModal();
            });
            // $(document).on('click', '#popup .close', function () {
            //     OneMap.hotspots.modalElement.hide();
            //     OneMap.hotspots.unactivate();
            // });
            $(document).on('click', '.claimHotspot', OneMap.hotspots.claim);
            $(document).on('click', '.createWAR', function () {
                $(this).after('<input type="text" class="war-name"><a href="#" class="btn savewarname">SAVE</a>').hide();
            });
            $(document).on('click', '.savewarname', OneMap.hotspots.createWarRoom);
            $(document).on('click', '.viewWARmembers', function () {
                $(this).hide();
                $('#popup').css({
                    top: parseInt($('#popup').css('top'))-$('#members-slider').height() + 'px'
                });
                $('#members-slider').show();

            });
        }
    },
    zones: {
        isCreating: false,
        defaultColor: '#000000',
        defaultOpacity: 0.2,
        selectedOpacity: 1,
        selectedHotspots: [],
        cancel: function() {
            $('.zone-panel').removeClass('expanded');
            $('#backto3d').fadeIn();
            OneMap.zones.resetFloor();
        },
        save: function () {
            OneMap.zones.isCreating = false;
            var name = $('#zone-name').val(),
                color = $('#zone-color').val().indexOf('#') > -1 ? $('#zone-color').val().replace('#', '') : $('#zone-color').val();
            $.ajax({
                url: "oneMap/createZone",
                type: 'GET',
                data: {
                    zoneName: name,
                    zoneColor: color,
                    hotspotIDs: OneMap.zones.selectedHotspots.join(',')
                },
                success: function(data) {
                    var wasSaved = data.success ? 'Your zone was successfully created' : 'Please Try again'
                    $('.zone-panel .response div').html(wasSaved);
                    $('.zone-panel .form').fadeOut(function(){
                        $('.zone-panel .response').fadeIn();
                    });
                }
            });
        },
        toggleSelected: function (self) {
            if(self.opacity() != 1) {
                self.setOpacity(OneMap.zones.selectedOpacity);
                OneMap.zones.selectedHotspots.push(self.attrs.id);
            } else {
                self.setOpacity(OneMap.zones.defaultOpacity);
                var index = $.inArray(self.attrs.id, OneMap.zones.selectedHotspots);
                OneMap.zones.selectedHotspots.splice(index, 1);
            }
            OneMap.map.interactiveLayer.drawScene();
            var seat = OneMap.zones.selectedHotspots.length == 1 ? 'seat' : 'seats'; 
            $('#selected-number').html(OneMap.zones.selectedHotspots.length +' '+ seat);
        },
        create: function() {
            OneMap.hotspots.unactivate();

            OneMap.zones.isCreating = true;
            var floorNumber = $('.showthisfloor .canvas').data('floor');
            $.ajax({
                url: "oneMap/getFreeZoneHotspots",
                type: 'GET',
                data: {
                    floor: floorNumber
                },
                success: function(data) {
                    console.log(data);
                    data = [{id: 'h1', isVacant: true}, {id: 'h2', isVacant: false} ];
                    OneMap.map.floorplanLayer.setOpacity(0.5);
                    OneMap.map.floorplanLayer.drawScene();

                    var hotspots = OneMap.map.floorplanLayer.children.getChildren();
                    for (var i = 1; i < hotspots.length; i++) {
                        for(var j = 0; j < data.length; j++) {
                            if(hotspots[i].attrs.id == data[j].id) {
                                hotspots[i].setOpacity(OneMap.zones.defaultOpacity);
                                if(data[j].isVacant) {
                                    hotspots[i].setStroke(OneMap.zones.defaultColor);
                                } else {
                                    hotspots[i].setFill(OneMap.zones.defaultColor);
                                }
                                hotspots[i].moveTo(OneMap.map.interactiveLayer);
                                break;
                            }
                        }
                    }
                    OneMap.map.interactiveLayer.drawScene();
                }
            });
            $('.zone-panel').addClass('expanded');
            $('#backto3d').fadeOut();
        },
        resetFloor: function() {
             OneMap.zones.isCreating = false;
            // do i just set them back to normal? can creating zones be triggered with search if so do i need to know pins colors, etc.
        },
        init: function() {
            $(document).on('click', '.create-zone a', function () {
                if($('.showthisfloor').length > 0){
                    OneMap.zones.create(); 
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

            $(document).on('click', '.zone-panel .cancel-zone', OneMap.zones.cancel);

            $(document).on('click', '.zone-panel .save-zone', OneMap.zones.save);

            $(document).on('click', '.zone-panel .okay', function () {
                OneMap.zones.resetFloor();
                $('.zone-panel').removeClass('expanded');
                setTimeout(function () {
                    $('.zone-panel .form').show();
                    $('.zone-panel .response').hide();
                }, 300);
                
            });
        }
    },     
    search: {
        pinHeight: 36,
        pinWidth: 28,
        pinSrcs: {
            'room': 'images/pin-room.png',
            'desk': 'images/pin-seat.png'
        },
        pinImages: null,
        mapPins: {},
        activeResult: null,

        /* Loads the actual image files used to mark a hotspot with a pin */
        loadPinImages: function(sources) {
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
                        OneMap.search.pinImages = images;
                    }
                };
                images[src].src = sources[src];
            }
        },
        submit: function () {
            var query = $('.searchbar').val();
            OneMap.search.clear();
            $.ajax({
                url: "oneMap/runSearch",
                type: 'GET',
                data: {
                    searchquery: query
                },
                success: function (results) {
                    // populate results tab
                    var content = '';
                    var cornerIcons = [{"zones": {"11":0,"12":0,"13":0,"14":0,"15":0,"17":0} }, {"users": {"11":0,"12":0,"13":0,"14":0,"15":0,"17":0}}, {"rooms": {"11":0,"12":0,"13":0,"14":0,"15":0,"17":0}}, {"warrooms": {"11":0,"12":0,"13":0,"14":0,"15":0,"17":0}}];
                    
                    console.log(results);
                    
                    for (var i = 0; i < results.length; i++) {
                        var isLinkClass = "";
                        if (results[i].hotspotId !== '') {
                            var canvas = $('.canvas[data-floor="' + results[i].floor + '"]');


                            if(typeof OneMap.search.mapPins[results[i].floor] == 'undefined') {
                                OneMap.search.mapPins[results[i].floor] = {};
                            }

                            if(typeof OneMap.search.mapPins[results[i].floor][results[i].type] == 'undefined') {
                                OneMap.search.mapPins[results[i].floor][results[i].type] = [results[i].hotspotId];
                            } else {
                                OneMap.search.mapPins[results[i].floor][results[i].type].push(results[i].hotspotId);
                            }

                            isLinkClass = "isLink";
                            
                            //@Dave: seems like this could be even more simplified (use .length of mapPins object)?
                            var floor = results[i].floor.toString();
                            switch(results[i].type) {
                                case 'zone':
                                    cornerIcons[0].zones[floor] = parseInt(cornerIcons[0].zones[floor], 10) + 1;
                                    break;
                                case 'user':
                                    cornerIcons[1].users[floor] = parseInt(cornerIcons[1].users[floor], 10) + 1;
                                    break;
                                case 'room':
                                    cornerIcons[2].rooms[floor] = parseInt(cornerIcons[2].rooms[floor], 10) + 1;
                                    break;
                                case 'warroom':
                                    cornerIcons[3].warrooms[floor] = parseInt(cornerIcons[3].warrooms[floor], 10) + 1;
                                    break;
                            }
                        }

                        switch(results[i].type) {
                            case 'zone':
                                break;
                            case 'user':
                                content += $('#userResult-template').html().format(results[i].name, results[i].level, results[i].craft, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId);                        
                                break;
                            case 'room':
                                content += $('#roomResult-template').html().format(results[i].name, "num", results[i].location, isLinkClass, results[i].floor, results[i].hotspotId);                        
                                break;
                            case 'warroom':
                                break;
                        }
                    }
                    $('#result-list').html(content);

                    // show results tab
                    OneMap.search.toggleDisplay();

                    // update map
                    for (var i = 0; i < cornerIcons.length; i++) {
                        var type = cornerIcons[i];
                        for (var key in type){
                         var obj = type[key];
                            for (var floor in obj){
                                var num = obj[floor];
                                if(num > 0){
                                    var canvas = $('.canvas[data-floor="' + floor + '"]');
                                    canvas.parents('.floorplan').find(".corner-results ." + key).text(num).fadeIn();
                                }
                            }
                        }
                    }
                }
            });
        },
        toggleDisplay: function () {
            var $results = $('#results');
            $results.removeClass('cleared');
            if($results.hasClass('collapsed')) {
                $results.removeClass('collapsed');
                OneMap.map.backTo3D();
            } else {
                $results.addClass('collapsed');
            }
        },
        clear: function () {
            var $results = $('#results');
            $results.addClass('cleared collapsed');
            $results.find('.results-list').html('');
            OneMap.search.activeResult = null;
            OneMap.search.mapPins = new Object;
            $('.corner-results div').each(function(){
                var $this = $(this);
                $this.fadeOut(function(){
                    $this.text("");
                });
            });
            OneMap.map.backTo3D();
        },
        //@Dave - what happens when the user clicks on a "zone"
        displayResult: function (self) {
            var floor = $(self).data('floor'),
                hotspot = $(self).data('hotspot'),
                canvas = $('.canvas[data-floor="' + floor + '"]');
            OneMap.search.activeResult =  hotspot;   
            $('#results').addClass('collapsed');
            canvas.parent('.floorplan').trigger('click');
        },
        // @Dave - when browsing after search shouldn't all of the hotspots that are part of a zone also display a color and a pin?  or do we not show zones during search unless clicked on it.
        displayPins: function() {
            var hotspots = OneMap.map.floorplanLayer.children.getChildren(),
                hotspotsLength = hotspots.length
                floorNumber = $('.showthisfloor .canvas').data('floor'),
                selectedHotspot = null;

            for (var i = 1; i < hotspotsLength; i++) {
                if($.inArray(hotspots[i].attrs.id, OneMap.search.mapPins[floorNumber][hotspots[i].areaType]) > -1) {
                    switch(hotspots[i].areaType) {
                        case 'room':
                            pinImage = OneMap.search.pinImages.room;
                            break;
                        case 'desk':
                            pinImage = OneMap.search.pinImages.desk;
                            break;                                    
                    }

                     var rect = new Kinetic.Rect({
                        x: (hotspots[i].areaX*OneMap.map.stageScale) + OneMap.map.floorplanX - OneMap.search.pinWidth/2,
                        y: (hotspots[i].areaY*OneMap.map.stageScale) + OneMap.map.floorplanY - OneMap.search.pinHeight,
                        width: OneMap.search.pinWidth,
                        height: OneMap.search.pinHeight,
                        fillPatternImage: pinImage,
                        fillPatternScale: {x:1, y:1}
                    });
                    rect.hotspot = hotspots[i];
                    rect.on('mousedown', function() {
                        this.hotspot.fire('mousedown');
                    });

                    OneMap.map.floorplanLayer.add(rect);

                    hotspots[i].isPin = true;
                    
                    if (OneMap.search.activeResult == hotspots[i].attrs.id) {
                        selectedHotspot = hotspots[i];
                        OneMap.search.activeResult = null;
                    }
                } else {
                    hotspots[i].isPin = false;
                }
            }
            OneMap.map.floorplanLayer.drawScene();

            if (selectedHotspot !== null) {
                selectedHotspot.fire('mousedown');
                selectedHotspot = null;
            }
        },
        init: function() {
            OneMap.search.loadPinImages(OneMap.search.pinSrcs);

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

            // ----- search interactions -----
            $(document).on('keypress', '.searchbar', function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    OneMap.search.submit();
                }
            });
            $(document).on('click', '.collapse-results', function (e) {
                e.preventDefault();
                OneMap.search.toggleDisplay();
            });
            $(document).on('click', '.clear-results', function (e) {
                e.preventDefault();
                OneMap.search.clear();
            });
            $(document).on('click', '#results .isLink', function() {
                OneMap.search.displayResult(this);
            });
        }
    },
    login: {
        submitURL: null,
        submit: function () {
            var username = $('.username').val();
            var password = $('.password').val();
            $.ajax({
                url: OneMap.login.submitURL,
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
        },
        init: function() {
            //TODO: how will this work with AD? Do we need to have them log in every time?
            if (isLoggedIn) {
                $('.ms-wrapper').addClass('ms-view-layers');
            }
            $(document).on('click', '.submit-login', OneMap.login.submit);
            $(document).on('keypress', '.password', function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    OneMap.login.submit();
                }
            });
            $(document).on('click', '.logout', function () {
                //TODO: add actual call to logout?
                $('.header').addClass('login');
            });
        }
    },
    init: function() {
        //layout setup -- @Dave when did this happen?  It broke the responsivness of the canvases
        $('#offices').height($(window).height() - 95 - 20 - 70);
        $(window).resize(function(){
            $('#offices').height($(window).height() - 95 - 20 - 70);
        });

        OneMap.map.init();
        OneMap.search.init();
        OneMap.login.init();
        OneMap.zones.init();
    }
};

OneMap.init();