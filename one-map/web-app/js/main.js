String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};

var OneMap = {
    userIsAdmin: true,

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
            var isHorizontal = false,
                canvas = $('#'+id)[0];
            
            OneMap.map.stageScaleX = $('.showthisfloor').width() / OneMap.map.stageWidth;
            OneMap.map.stageScaleY = $('.showthisfloor').height() / OneMap.map.stageHeight;

            // is container tall or wide
            if (OneMap.map.stageScaleX > OneMap.map.stageScaleY) {
                OneMap.map.stageScale = OneMap.map.stageScaleY;
                isHorizontal = true;
            } else {
                OneMap.map.stageScale = OneMap.map.stageScaleX;
                isHorizontal = false;
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
                OneMap.map.floorplanY = 0;
                OneMap.map.floorplanX = (stage.width() - imageWidth) / 2;
            } else {
                 OneMap.map.floorplanX = 0;
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

                if(OneMap.zones.isCreating) {
                    layer.setOpacity(0.5);
                }
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
                OneMap.map.draggedOffset = new Object;
                OneMap.map.hasPanned = false;
                OneMap.map.stage.destroy();
                $('.zoom-btns').fadeOut();
            }
        },
        backTo3D: function() {
            $('.ms-wrapper').removeClass('showingfloor');
            $('.floorplan.showthisfloor').attr("data-showing", "false").removeClass('showthisfloor');
            OneMap.map.unloadFloor();

            if(!$.isEmptyObject(OneMap.search.mapPinsTemp)){
                console.info('3D mapPins reset');
                OneMap.search.mapPins = OneMap.search.mapPinsTemp;
                OneMap.search.mapPinsTemp = new Object;
            }

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
                OneMap.map.interactiveLayer.drawScene();
            }
        },
        getInfo: function () {
            $.ajax({
                url: 'oneMap/getHotspot',
                type: 'GET',
                data: {
                    hotspotID: OneMap.hotspots.active.id,
                },
                success: function (object) {
                    if(OneMap.userIsAdmin){
                        $.ajax({
                            url: 'oneMap/getAllZones',
                            type: 'GET',
                            success: function (zones) {
                                OneMap.zones.allZones = zones;
                                OneMap.hotspots.populateModal(object);
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(errorThrown);
                            }
                        });
                    } else {
                        OneMap.hotspots.populateModal(object);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        claim: function () {
            var $this = $('.claimHotspot'),
                isWARRoom = $this.hasClass('addme') ? true : false,
                $modalContent = $this.parents('.md-content');

            $modalContent.addClass('loading').html('<img width="13px" height="13px" src="images/loading.gif"> Loading');

            $.ajax({
                url: 'oneMap/claimHotspot',
                type: 'GET',
                data: {
                    hotspotID: OneMap.hotspots.active.id
                },
                success: function (object) {
                    if (isWARRoom) {
                        $modalContent.html('Done');
                        setTimeout(function () {
                            $modalContent.removeClass('loading');
                            OneMap.hotspots.closeModal();
                            OneMap.hotspots.unactivate();
                        }, 1500);
                    } else { // desk
                        $modalContent.removeClass('loading');
                        object = object.userinformation;
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email);
                        $modalContent.html(content);
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
            console.log(this.areaID);
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
                        //console.log(hotspotsObj[key]);
                        hotspotPath.areaY = hotspotsObj[key].y;
                        hotspotPath.areaX = hotspotsObj[key].x;
                        hotspotPath.areaType = hotspotsObj[key].type;
                        hotspotPath.areaID = hotspotsObj[key].assignedSeatId;
                        hotspotPath.isVacant = hotspotsObj[key].isVacant;
                        hotspotPath.zone = hotspotsObj[key].zone.name;
                        hotspotPath.zoneColor = hotspotsObj[key].zone.color;

                        hotspotPath.on('mousedown', OneMap.hotspots.click);
                        
                        if(OneMap.zones.isCreating) {
                            if($.inArray(key, OneMap.zones.vacantHotspots) > -1) {
                                OneMap.zones.displayFreeZone(hotspotPath);
                            }
                        } else {
                            hotspotPath.on('mouseover', OneMap.hotspots.mouseOver);
                            hotspotPath.on('mouseout', OneMap.hotspots.mouseOut);

                            if(!$.isEmptyObject(OneMap.search.mapPins)) {
                                if($.inArray(key, OneMap.search.mapPins[floorNumber].zone) > -1) {
                                    OneMap.search.displayZone(hotspotPath);
                                }
                                if($.inArray(key, OneMap.search.mapPins[floorNumber].floorIds) > -1) {
                                    OneMap.search.displayPin(hotspotPath);
                                }
                            } else {
                                OneMap.map.floorplanLayer.add(hotspotPath);
                            }
                        }
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
        populateModal: function(object){
            var innerDiv = OneMap.hotspots.modalElement.find('.md-content');
                    
            switch (object.type) {
                case "room":
                    OneMap.hotspots.modalElement.removeClass('desk').addClass('room');
                    if (object.project !== undefined) { // WAR room
                        var content = $('#room-template').html().format(object.name, object.number, object.phone, "Project:&nbsp;" + object.project);
                        innerDiv.html(content);

                        if (object.members.length > 0) {
                            $('.btns-container').html('<a class="btn viewWARmembers">VIEW MEMBERS</a>');
                        }

                        //TODO: figure out if the 'add me' button should be added - <a class="btn claimHotspot addme">ADD ME</a>

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
                        if(OneMap.userIsAdmin){
                            OneMap.hotspots.modalElement.find('.btns-container').html('<a class="btn createWAR">CONVERT TO WARROOM</a>');
                        }
                    }
                    break;
                case "desk":
                    //TODO: discuss with team how to get list of all possible zones
                    var zoneDisplay;
                    if(OneMap.userIsAdmin){
                        zoneDisplay = '<select class="zone-select">';
                        for (var z = 0; z < OneMap.zones.allZones.length; z++){
                            zoneDisplay += '<option value="' + OneMap.zones.allZones[z].id + '" ' + ( (object.zone.id == OneMap.zones.allZones[z].id) ? 'selected="selected"' : '') + '>' + ((OneMap.zones.allZones[z].name.toLowerCase().replace(/ /g,"") === "freezone") ? "None" : OneMap.zones.allZones[z].name) + '</option>';
                        }
                        zoneDisplay += '</select><a id="saveSeatZone">Save</a>';
                    } else {
                        zoneDisplay = (object.zone.name.toLowerCase().replace(/ /g,"") === "freezone") ? "None" : object.zone.name;
                    }
                    
                    if (!object.claimed) {
                        OneMap.hotspots.modalElement.removeClass('room').removeClass('user').addClass('desk');
                        var content = '<div class="md-bg"><div class="seat-info"><div>Seat ID:&nbsp;' + object.assignedSeatId + '</div>Zone:&nbsp;' + zoneDisplay + '</div>';
                        content += '<div class="btns-container clearfix"><a class="btn claimHotspot">CLAIM THIS SEAT</a></div></div>';
                        innerDiv.html(content).data("profile", object);
                    } else if (object.claimed && object.isMine) { // should be done
                        OneMap.hotspots.modalElement.removeClass('room').removeClass('desk').addClass('user');
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email, '1125A', zoneDisplay);
                        innerDiv.html(content);
                    } else { // other user claimed seat
                        OneMap.hotspots.modalElement.removeClass('room').removeClass('desk').addClass('user');
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email, '1125A', zoneDisplay);
                        content += '<div class="btns-container clearfix"><a class="btn" href="mailto:' + object.email + '?Subject=ONEMAP Seat Request&Body=Hey Bro, can I have your seat?">REQUEST SEAT</a></div>';
                        innerDiv.html(content);
                    }
                    break;
                default:
                    console.log("unaccounted for type:" + object.type);
                    break;
            }
            OneMap.hotspots.showModal();
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
                $(this).after('<input type="text" class="war-name" placeholder="project name"><a href="#" class="btn savewarname">SAVE</a>').hide();
            });
            $(document).on('click', '.savewarname', OneMap.hotspots.createWarRoom);
            $(document).on('click', '.viewWARmembers', function () {
                $(this).hide();
                // $('#popup').css({
                //     top: parseInt($('#popup').css('top'))-$('#members-slider').height() + 'px'
                // });
                $('#members-slider').show();

            });
        }
    },
    zones: {
        isCreating: false,
        defaultColor: '#000000',
        defaultOpacity: 0.3,
        selectedOpacity: 1,
        selectedHotspots: [],
        allZones: [],
        vacantHotspots: [],
        cancel: function() {
            $('.zone-panel').removeClass('expanded');
            $('#backto3d').fadeIn();
            OneMap.zones.resetFloor();
        },
        save: function () {
            OneMap.zones.isCreating = false;
            var name = $('#zone-name').val(),
                color = $('#zone-color').val().replace(/#/g,""); //.indexOf('#') > -1 ? $('#zone-color').val().replace('#', '') : $('#zone-color').val();

            //if short format hex, convert to long
            if(color.length == 3){
                color = color + color;
            }

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
            console.log('toggle');
            if(self.zone == 'Free Zone') {
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
            }
        },
        displayFreeZone: function(hotspot) {
            hotspot.setOpacity(OneMap.zones.defaultOpacity);
            if(hotspot.isVacant) {
                hotspot.setStroke(OneMap.zones.defaultColor);
            } else {
                hotspot.setFill(OneMap.zones.defaultColor);
            }
            hotspot.moveTo(OneMap.map.interactiveLayer);
        },
        create: function() {
            OneMap.zones.isCreating = true;
            OneMap.search.clear();
            OneMap.hotspots.unactivate();
            OneMap.map.unloadFloor();
            
            var canvas = $('.showthisfloor .canvas'),
                floorNumber = canvas.data('floor');

            $.ajax({
                url: "oneMap/getFreeZoneHotspots",
                type: 'GET',
                data: {
                    floor: floorNumber
                },
                success: function(data) {
                    //get zones for validation
                    $.ajax({
                        url: 'oneMap/getAllZones',
                        type: 'GET',
                        success: function (zones) {
                            OneMap.zones.allZones = zones;
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(errorThrown);
                        }
                    });

                    //console.log(data);
                    // OneMap.zones.vacantHotspots = data; // nice to have

                    for(var j = 0; j < data.length; j++) {
                        OneMap.zones.vacantHotspots.push(data[j].id);
                    }
                    OneMap.map.loadFloor(canvas.attr('id'), canvas.data('imgsrc'));
                }
            });
            $('.zone-panel').addClass('expanded');
            $('#backto3d').fadeOut();
        },
        resetFloor: function() {
            OneMap.zones.isCreating = false;
            OneMap.map.unloadFloor();
            OneMap.zone.vacantHotspots = [];
            var canvas = $('.showthisfloor .canvas');
            OneMap.map.loadFloor(canvas.attr('id'), canvas.data('imgsrc'));
        },
        updateSeatZone: function(event){
            var $this = $(event.target);
            $this.html('<img width="13px" height="13px" src="images/loading.gif" style="margin-bottom: -2px; margin-left: 6px;">');

            $.ajax({
                url: "oneMap/updateZone",
                type: 'GET',
                data: {
                    hotspotID: OneMap.hotspots.active.id,
                    zoneID: $this.parent().find('select').val()
                },
                success: function(data) {
                    $this.html('<img width="16px" height="13px" src="images/icon-checkmark.png" style="margin-bottom: -2px; margin-left: 3px;">');

                    setTimeout(function(){
                        $this.html('Save').hide();
                    }, 1500);
                }
            });
        },
        init: function() {
            $(document).on('click', '.create-zone a', function () {
                if($('.showthisfloor').length > 0){
                    OneMap.zones.create(); 
                }
            });

            $(document).on('change', '.seat-info select', function(){
                $(this).parent().find('#saveSeatZone').show();
            });

            $(document).on('click', '#saveSeatZone', OneMap.zones.updateSeatZone);

            $(document).on('mouseenter', '.create-zone a', function () {
                if(!$('.showthisfloor').length > 0){
                    $('#create-zone-popup').show();
                }
            });
            $(document).on('mouseleave', '.create-zone a', function () {
                $('#create-zone-popup').hide();
            });

            $(document).on('click', '.zone-panel .cancel-zone', OneMap.zones.cancel);

            $(document).on('click', '.zone-panel .save-zone', function(){
                // do validation, then save

                //reset
                $('.error-wrapper #zone-name').parent().find('.error-text').remove();
                $('.error-wrapper #zone-color').parent().find('.error-text').remove();
                $('.error-wrapper #zone-name').unwrap();
                $('.error-wrapper #zone-color').unwrap();

                var zonename = $('#zone-name').val().toLowerCase().replace(/ /g,"");

                //we should do everthing with the #, then strip it off right before giving to backend
                var zonecolor = $('#zone-color').val(); //.replace(/#/g,"");

                var valid = true;

                if(zonecolor.length > 0 && zonename.length > 0){

                    for (var v = 0; v < OneMap.zones.allZones.length; v++){

                        var nametotest = OneMap.zones.allZones[v].name.toLowerCase().replace(/ /g,"");
                        var colortotest = OneMap.zones.allZones[v].color; //.replace(/#/g,"");

                        if(zonename == nametotest){
                            valid = false;

                            $('#zone-name').wrap('<div class="error-wrapper"></div>');
                            $('#zone-name').parent().append('<div class="error-text">' + 'This zone name already exists' + '</div>');

                            break;
                        }

                        //expecting #
                        if(!( /^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$/.test(zonecolor) )){
                            valid = false;

                            $('#zone-color').wrap('<div class="error-wrapper"></div>');
                            $('#zone-color').parent().append('<div class="error-text">' + 'Invalid HEX code format' + '</div>');

                            break;
                        } else {
                            //if short format, convert to long for duplicate check
                            var validformat = zonecolor;
                            if(zonecolor.length == 4){
                                validformat = zonecolor.replace(/#/g,"") + zonecolor.replace(/#/g,"");
                                validformat = '#' + validformat;
                            }
                            if (validformat == colortotest){
                                valid = false;

                                $('#zone-color').wrap('<div class="error-wrapper"></div>');
                                $('#zone-color').parent().append('<div class="error-text">' + 'This zone color already exists' + '</div>');

                                break;
                            }
                        }
                    }

                    if(valid){
                        OneMap.zones.save();
                    }

                } else {
                    if(! zonecolor.length > 0){
                        $('#zone-color').wrap('<div class="error-wrapper"></div>');
                        $('#zone-color').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
                    }
                    if(! zonename.length > 0){
                        $('#zone-name').wrap('<div class="error-wrapper"></div>');
                        $('#zone-name').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
                    }
                }
                
            });

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
        pinSrcs: {
            'room': 'images/pin-room.png',
            'desk': 'images/pin-seat.png',
            'warroom': 'images/pin-war.png'
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
                    
                    //console.log(results);

                    var currentType = "";
                    var typeChangeCount = 0;
                    
                    for (var i = 0; i < results.length; i++) {
                        //type headers
                        if (results[i].type != currentType){
                            typeChangeCount++;
                            currentType = results[i].type;
                            content += '<div class="results-group">' + currentType + 's</div>';
                        }

                        var isLinkClass = "";
                        if (results[i].hotspotId !== '') {
                            var canvas = $('.canvas[data-floor="' + results[i].floor + '"]');


                            if(typeof OneMap.search.mapPins[results[i].floor] == 'undefined') {
                                OneMap.search.mapPins[results[i].floor] = {};
                                OneMap.search.mapPins[results[i].floor].floorIds = [];
                            }

                            // if the type is room and it has a project, it's a warroom.
                            // if its a warroom, we need to update its type for future parsing
                            if (results[i].type == 'room' && results[i].project !== undefined){
                                results[i].type = 'warroom';
                            }
                            if(results[i].type == 'zone') {
                                for(var j = 0; j < results[i].hotspotIds.length; j++) {
                                    var id = results[i].hotspotIds[j];
                                    // break up hotspots by type
                                    if(typeof OneMap.search.mapPins[results[i].floor].zone == 'undefined') {
                                        OneMap.search.mapPins[results[i].floor].zone = [id];
                                    } else {
                                        OneMap.search.mapPins[results[i].floor].zone.push(id);
                                    }
                                }
                            } else {
                                // break up hotspots by type
                                if(typeof OneMap.search.mapPins[results[i].floor][results[i].type] == 'undefined') {
                                    OneMap.search.mapPins[results[i].floor][results[i].type] = [results[i].hotspotId];
                                } else {
                                    OneMap.search.mapPins[results[i].floor][results[i].type].push(results[i].hotspotId);
                                }
                                
                                // add all ids to floors for when search returns type user
                                OneMap.search.mapPins[results[i].floor].floorIds.push(results[i].hotspotId);
                            }

                            isLinkClass = "isLink";
                            
                            var floor = results[i].floor.toString();
                            switch(results[i].type) {
                                case 'zone':
                                    cornerIcons[0].zones[floor] = parseInt(cornerIcons[0].zones[floor], 10) + 1;
                                    break;
                                case 'user':
                                    cornerIcons[1].users[floor] = parseInt(cornerIcons[1].users[floor], 10) + 1;
                                    break;
                                case 'room':
                                    if (results[i].project !== undefined){
                                        cornerIcons[3].warrooms[floor] = parseInt(cornerIcons[3].warrooms[floor], 10) + 1;
                                    } else {
                                        cornerIcons[2].rooms[floor] = parseInt(cornerIcons[2].rooms[floor], 10) + 1;
                                    }
                                    break;
                                case 'warroom':
                                    cornerIcons[3].warrooms[floor] = parseInt(cornerIcons[3].warrooms[floor], 10) + 1;
                                    break;
                            }
                        }

                        switch(results[i].type) {
                            case 'zone':
                                content += $('#zoneResult-template').html().format(results[i].zoneName, results[i].office.name, results[i].floor, isLinkClass, results[i].floor, results[i].zoneId, "zone", results[i].zoneColor, results[i].hotspotIds);                        
                                break;
                            case 'user':
                                content += $('#userResult-template').html().format(results[i].name, results[i].level, results[i].craft, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId, "desk");
                                break;
                            case 'room':
                                content += $('#roomResult-template').html().format(results[i].name, results[i].number, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId, "room");                        
                                break;
                            case 'desk':
                                content += $('#deskResult-template').html().format(results[i].assignedSeatId, results[i].location, results[i].floor, isLinkClass, results[i].floor, results[i].hotspotId, "desk");                        
                                break;
                            case 'warroom':
                                content += $('#roomResult-template').html().format(results[i].name, results[i].number, results[i].location, isLinkClass, results[i].floor, results[i].hotspotId, "warroom");
                                break;
                        }
                    }
                    $('#result-list').html(content);
                    if(typeChangeCount > 1){
                        $('#result-list').addClass('show-dividers');
                    }

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
                $(this).hide().text("");
            });
            if(!OneMap.zones.isCreating) {
                OneMap.map.backTo3D();
            }
        },
        displayResult: function (self) {
            // console.log(self);
            var floor = $(self).data('floor'),
                canvas = $('.canvas[data-floor="' + floor + '"]');
            if($(self).data('type') == 'zone') {

            } else {
                var hotspot = $(self).data('hotspot');
                OneMap.search.activeResult =  hotspot;
            }
            $('#results').addClass('collapsed');
             canvas.parent('.floorplan').trigger('click');
        },
        displayZone: function(hotspot){
            console.log(hotspot.zone);
            //if(hotspot.zone !== 'Free Zone') {
                if(hotspot.isVacant) {
                    hotspot.setStroke(hotspot.zoneColor);
                } else {
                    hotspot.setFill(hotspot.zoneColor);
                }
            //}

            OneMap.map.floorplanLayer.add(hotspot);
            OneMap.map.floorplanLayer.draw();
        },
        displayPin: function(hotspot) {
            var floorNumber = $('.showthisfloor .canvas').data('floor'),
                selectedHotspot = null,
                pinWidth = 0,
                pinHeight = 0;

            switch(hotspot.areaType) {
                case 'room':
                    if(OneMap.search.mapPins[floorNumber].warroom != undefined && ($.inArray(hotspot.attrs.id, OneMap.search.mapPins[floorNumber].warroom) > -1)) {
                        pinHeight = 40;
                        pinWidth = 31;
                        pinImage = OneMap.search.pinImages.warroom;
                    } else {
                        pinHeight = 36;
                        pinWidth = 28;
                        pinImage = OneMap.search.pinImages.room;
                    }
                    break;
                case 'desk':
                    pinHeight = 30;
                    pinWidth = 20;
                    pinImage = OneMap.search.pinImages.desk;
                    break;   
                default:
                    console.log('pinImage not set');                       
            }

            var rect = new Kinetic.Rect({
                x: (hotspot.areaX*OneMap.map.stageScale) + OneMap.map.floorplanX - pinWidth/2 + 7, // + 7 offset to make it line up with the pin's puncture point
                y: (hotspot.areaY*OneMap.map.stageScale) + OneMap.map.floorplanY - pinHeight,
                width: pinWidth,
                height: pinHeight,
                fillPatternImage: pinImage,
                fillPatternScale: {x:1, y:1}
            });
            rect.hotspot = hotspot;
            rect.on('mousedown', function() {
                this.hotspot.fire('mousedown');
            });
            
            OneMap.map.floorplanLayer.add(rect);

            hotspot.isPin = true;

            if (OneMap.search.activeResult == hotspot.attrs.id) {
                selectedHotspot = hotspot;
                OneMap.search.activeResult = null;
            }

            OneMap.map.floorplanLayer.draw();

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

                if(!$.isEmptyObject(OneMap.search.mapPinsTemp)){
                    console.info('collapse results mapPins reset');
                    OneMap.search.mapPins = OneMap.search.mapPinsTemp;
                    OneMap.search.mapPinsTemp = new Object;
                }

                OneMap.search.toggleDisplay();
            });
            $(document).on('click', '.clear-results', function (e) {
                e.preventDefault();
                OneMap.search.clear();
            });
            $(document).on('click', '#results .isLink', function() {
                OneMap.search.activeResult =  $(this).data('hotspot');

                var floor = $(this).data('floor');
                var hotspot = $(this).data('hotspot');
                var type = $(this).data('type');
                OneMap.search.mapPinsTemp = OneMap.search.mapPins;
                OneMap.search.mapPins = new Object;

                if(typeof OneMap.search.mapPins[floor] == 'undefined') {
                    OneMap.search.mapPins[floor] = {};
                    OneMap.search.mapPins[floor].floorIds = [];
                }

                if(type == 'zone') {
                    var idArray = $(this).data('ids').split(',');
                    for(var j = 0; j < idArray.length; j++) {
                        var id = idArray[j];
                        if(typeof OneMap.search.mapPins[floor].zone == 'undefined') {
                            OneMap.search.mapPins[floor].zone = [id];
                        } else {
                            OneMap.search.mapPins[floor].zone.push(id);
                        }
                    }
                } else {
                    // break up hotspots by type
                    if(typeof OneMap.search.mapPins[floor][type] == 'undefined') {
                        OneMap.search.mapPins[floor][type] = [hotspot];
                    } else {
                        OneMap.search.mapPins[floor][type].push(hotspot);
                    }
                    
                    // add all ids to floors for when search returns type user
                    OneMap.search.mapPins[floor].floorIds.push(hotspot);
                }                

                OneMap.search.displayResult(this);
            });
        }
    },
    login: {
        submitURL: null,
        submit: function () {
            var username = $('.username').val();
            var password = $('.password').val();

            // //reset validation
            // $('.error-wrapper .username').parent().find('.error-text').remove();
            // $('.error-wrapper .password').parent().find('.error-text').remove();
            // $('.error-wrapper .username').unwrap();
            // $('.error-wrapper .password').unwrap();

            // //validation
            // if (username.length > 0 && password.length > 0){
            //     if(!( /[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}/.test(username) )){

            //         $('.username').wrap('<div class="error-wrapper"></div>');
            //         $('.username').parent().append('<div class="error-text">' + 'Invalid email format' + '</div>');

            //     } else {
            //         //all good
                    $.ajax({
                        url: OneMap.login.submitURL,
                        type: 'POST',
                        data: {
                            j_username: username,
                            j_password: password
                        },
                        success: function (data, textStatus, jqXHR) {
                            
                            $('.header').removeClass('login');
                            setTimeout(function () {
                                $('.ms-wrapper').addClass('ms-view-layers');
                            }, 500);
                            
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(errorThrown);
                        }
                    });
            //     }
            // } else {
            //     if(! username.length > 0){
            //         $('.username').wrap('<div class="error-wrapper"></div>');
            //         $('.username').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
            //     }
            //     if(! password.length > 0){
            //         $('.password').wrap('<div class="error-wrapper"></div>');
            //         $('.password').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
            //     }
            // }

            
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