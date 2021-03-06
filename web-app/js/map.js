var OneMap = {
    isLoggedIn: false,
    userIsAdmin: false,
    isViewingConflict: false,
    impersonatingID: -1,

    map: {
        // map properties
        stage: null,
        stageScale: 1,
        stageWidth: 554,
        stageHeight: 528,
        orginalBackground: null,
        floorplanLayer: null,        
        floorplanX: 0,
        floorplanY: 0,
        interactiveLayer: null,
        pinsLayer: null,

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

            OneMap.map.pinsLayer.scale({
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
                OneMap.map.pinsLayer.scale({
                    x: 1,
                    y: 1
                });
                OneMap.map.pinsLayer.draggable(false);
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

            //skrink pin images
            var pins = OneMap.map.stage.find('.pin');
            pins.each(function(shape) {

                shape.scale({
                    x: shape.scaleX() / multiplier,
                    y: shape.scaleY() / multiplier
                });
                var shiftY = shape.getHeight() - (shape.getHeight()*shape.scaleY());
                var shiftX = zoomType === 'plus' ? Math.abs(OneMap.map.zoomMultiplier) : -Math.abs(OneMap.map.zoomMultiplier);

                //adjust pin images position. since they are smaller now, they won't shift as much as they need to
                shape.setAttrs({
                    x: shape.getX() + shiftX,
                    y: shape.getAttr('oriY') + shiftY
                });

            });
            


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

            OneMap.map.pinsLayer.setAbsolutePosition({
                x: offsetX,
                y: offsetY
            });
            OneMap.map.pinsLayer.draw();
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

            OneMap.map.pinsLayer.setAttrs({
                x: this.getX(),
                y: this.getY()
            });
            OneMap.map.pinsLayer.draw();
        },
        moveEnd: function() {
            OneMap.map.hasPanned = true;
            OneMap.map.draggedOffset = OneMap.map.floorplanLayer.getPosition();
            OneMap.map.interactiveLayer.draw();
            OneMap.map.pinsLayer.draw();
        },
        showFloor: function () {
            console.log('here');
            $('#results:not(".collapsed")').addClass("collapsed");

            if($('.ms-wrapper').hasClass('showingfloor')){
                OneMap.map.backTo3D();
            }  
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
            OneMap.map.orginalBackground = $('.showthisfloor').css('background');
            $('.showthisfloor').css('background', '#e2e2e2');

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
            var highestLayer = new Kinetic.Layer({
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
                OneMap.map.pinsLayer = highestLayer;

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
                if(OneMap.isViewingConflict) {
                    OneMap.isViewingConflict = false;
                    var backBtn =  document.getElementById('backto3d');
                    backBtn.href = '#';
                    backBtn.innerHTML = '3D';
                }
            }
        },
        backTo3D: function() {
            $('.ms-wrapper').removeClass('showingfloor');
            $('.showthisfloor').css('background', OneMap.map.orginalBackground);
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

            $(document).on('click', '.floorplan:not(".showthisfloor")', OneMap.map.showFloor);
            $(document).on("click", '#backto3d', OneMap.map.backTo3D);
            $(document).on('click', '.zoom', function () {
                if(!$(this).hasClass('disabled')){
                    OneMap.map.zoom(this.id);
                }
            });
        }
    },
    hotspots: {
        // hotspot information
        modalElement: $('.md-modal'),
        
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
                if (this.searchZone !== undefined){
                    this.setFill(this.zoneColor);
                } else {
                    this.setFill(OneMap.hotspots.hoverFill);
                }
                this.setOpacity(OneMap.hotspots.hoverOpacity);
                this.moveTo(OneMap.map.interactiveLayer);
                OneMap.map.interactiveLayer.drawScene();
            } else if (this.isPin && !OneMap.zones.isCreating){
                if (this.searchZone !== undefined){
                    this.setFill(this.zoneColor);
                } else {
                    if(this.areaType == 'desk'){
                        this.setFill('#0C748E');
                    } else if(this.areaType == 'room'){
                        if(this.isVacant){
                            //room
                            this.setFill('#A10850');
                        } else {
                            //warroom
                            this.setFill('#E37C26');
                        }
                    }
                }
                
                this.setOpacity(0.3);
                this.moveTo(OneMap.map.interactiveLayer);
                OneMap.map.interactiveLayer.drawScene();
            } else if (OneMap.zones.isCreating){
                if(this.zone == 'Free Zone') {
                    if(this.opacity() != OneMap.zones.selectedOpacity) {
                        this.setOpacity(OneMap.zones.hoverOpacity);
                    }
                    OneMap.map.interactiveLayer.drawScene();
                }
            }
        },
        mouseOut: function() {
            if (!OneMap.zones.isCreating && OneMap.hotspots.active.hotspot !== this && !OneMap.zones.isCreating) {
                this.setFill(OneMap.hotspots.defaultFill);
                this.setOpacity(OneMap.hotspots.defaultOpacity);
                this.moveTo(OneMap.map.floorplanLayer);
                OneMap.map.interactiveLayer.drawScene();
            } else if (OneMap.zones.isCreating){
                if(this.zone == 'Free Zone') {
                    if(this.opacity() != OneMap.zones.selectedOpacity) {
                        this.setOpacity(OneMap.zones.defaultOpacity);
                    }
                    OneMap.map.interactiveLayer.drawScene();
                }
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
                    console.log(object);
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
                        // setTimeout(function () {
                        //     $modalContent.removeClass('loading');
                        //     OneMap.hotspots.closeModal();
                        //     OneMap.hotspots.unactivate();
                        // }, 1500);
                    } else { // desk
                        $modalContent.removeClass('loading');
                        var user = object.userinformation;
                       
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
                        
                        var content = $('#user-template').html().format(user.name, user.level, user.craft, user.phone, user.email, object.assignedSeatId, zoneDisplay);
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
                    if (object.success){
                        OneMap.hotspots.modalElement.find('.room .btns-container')
                            .before('<div>Project: ' + name + '</div>')
                            .html('<a class="btn claimHotspot addme">ADD ME</a>');
                    } else {
                        OneMap.hotspots.modalElement.find('.war-name').wrap('<div class="error-wrapper"></div>');
                        OneMap.hotspots.modalElement.find('.war-name').parent().append('<div class="error-text">' + name + ' is already assigned to a warroom' + '</div>');
                    } 
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
                    if (this.searchZone !== undefined){
                        this.setFill(this.zoneColor);
                    } else {
                        this.setFill(OneMap.hotspots.hoverFill);
                    }
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
                            // console.info(hotspotsObj[key].type);
                            if(($.inArray(key, OneMap.zones.vacantHotspots) > -1) && (hotspotsObj[key].type != 'room')) {
                                OneMap.zones.displayFreeZone(hotspotPath);
                                hotspotPath.on('mouseover', OneMap.hotspots.mouseOver);
                                hotspotPath.on('mouseout', OneMap.hotspots.mouseOut);
                            }
                        } else {
                            hotspotPath.on('mouseover', OneMap.hotspots.mouseOver);
                            hotspotPath.on('mouseout', OneMap.hotspots.mouseOut);

                            if(!$.isEmptyObject(OneMap.search.mapPins) && OneMap.search.mapPins[floorNumber] !== undefined) {
                                if($.inArray(key, OneMap.search.mapPins[floorNumber].zone) > -1) {
                                    hotspotPath.searchZone = true;
                                    OneMap.search.displayZone(hotspotPath);
                                }
                                if($.inArray(key, OneMap.search.mapPins[floorNumber].floorIds) > -1) {
                                    OneMap.search.displayPin(hotspotPath);
                                    //so you can hover over the hotspot as well
                                    OneMap.map.floorplanLayer.add(hotspotPath);
                                }
                            } else {
                                OneMap.map.floorplanLayer.add(hotspotPath);
                            }
                        }
                    }

                    OneMap.map.stage.add(OneMap.map.floorplanLayer);
                    OneMap.map.stage.add(OneMap.map.interactiveLayer);
                    OneMap.map.stage.add(OneMap.map.pinsLayer);

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
                        var content = $('#room-template').html().format(object.name, object.number, (object.phone) ? object.phone : "", "Project:&nbsp;" + object.project);
                        innerDiv.html(content);

                        if (object.members.length > 0) {
                            $('.btns-container').html('<a class="btn viewWARmembers">VIEW MEMBERS</a>');
                        }

                        var idArray = [];
                        for(var u = 0; u < object.members.length; u++){
                            idArray.push(object.members[u].userID);
                        }

                        if(OneMap.isLoggedIn && ($.inArray(OneMap.login.userID, idArray) == -1)){
                            $('.btns-container').append('<a class="btn claimHotspot addme">ADD ME</a>');
                        }

                        //TODO: figure out if the 'add me' button should be added - <a class="btn claimHotspot addme">ADD ME</a>

                        var content = '';
                        for (var i = 0; i < object.members.length; i++) {
                            content += $('#warroom-member-template').html().format(object.members[i].name, object.members[i].level, object.members[i].craft, object.members[i].phone, object.members[i].email);
                        }
                        $('#WARmembers').html(content);

                        if (object.members.length > 0) {
                            //init slider
                            var slider = $('#WARmembers').leanSlider({
                                directionNav: '#slider-directional-nav',
                                controlNav: '#slider-dot-nav',
                                pauseTime: 0,
                                prevText: 'Prev',
                                nextText: 'Next'
                            });

                        }
                    } else { // conference room
                        var content = $('#room-template').html().format(object.name, object.number, (object.phone) ? object.phone : "", '');
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
                        if(OneMap.isLoggedIn){
                            content += '<div class="btns-container clearfix"><a class="btn claimHotspot">CLAIM THIS SEAT</a></div></div>';
                        }
                        innerDiv.html(content).data("profile", object);
                    } else if (object.claimed && object.isOwn) { // should be done
                        OneMap.hotspots.modalElement.removeClass('room').removeClass('desk').addClass('user');
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email, object.assignedSeatId, zoneDisplay);
                        innerDiv.html(content);
                    } else { // other user claimed seat
                        OneMap.hotspots.modalElement.removeClass('room').removeClass('desk').addClass('user');
                        var content = $('#user-template').html().format(object.name, object.level, object.craft, object.phone, object.email, object.assignedSeatId, zoneDisplay);
                        content += '<div class="btns-container clearfix"><a class="btn" href="mailto:' + object.email + '?Subject=ONEMAP Seat Request&Body=Hey, can I have your seat?">REQUEST SEAT</a></div>';
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
            $('.md-close').on( 'click', function( ev ) {
                ev.stopPropagation();
                OneMap.hotspots.closeModal();
            });

            $(document).on('click', '.claimHotspot', OneMap.hotspots.claim);
            $(document).on('click', '.createWAR', function () {
                $(this).after('<input type="text" class="war-name" placeholder="project name"><a href="#" class="btn savewarname">SAVE</a>').hide();
            });
            $(document).on('click', '.savewarname', OneMap.hotspots.createWarRoom);
            $(document).on('click', '.viewWARmembers', function () {
                $(this).hide();
                $('#members-slider').show();

            });
        }
    },
    zones: {
        isCreating: false,
        defaultColor: '#000000',
        defaultOpacity: 0.2,
        hoverOpacity: 0.35,
        selectedOpacity: 0.5,
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
            if(self.zone == 'Free Zone') {
                if(self.opacity() != OneMap.zones.selectedOpacity) {
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
            //if(hotspot.isVacant) {
             //   hotspot.setStroke(OneMap.zones.defaultColor);
            //} else {
                hotspot.setFill(OneMap.zones.defaultColor);
            //}

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
            OneMap.zones.vacantHotspots = [];
            OneMap.zones.selectedHotspots = [];

            $('#zone-name').val("");
            $('#zone-color').val("");
            $('.zone-panel .number-selected span').text("0");
            $('.error-wrapper #zone-name').parent().find('.error-text').remove();
            $('.error-wrapper #zone-color').parent().find('.error-text').remove();
            $('.error-wrapper #zone-name').unwrap();
            $('.error-wrapper #zone-color').unwrap();

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

                var allgood = true;
                var goodHEX = true;
                var uniqueName = true;
                var uniqueColor = true;

                if(zonecolor.length > 0 && zonename.length > 0){

                    //validate hex format. expecting #
                    if(!( /^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$/.test(zonecolor) )){
                        allgood = false;
                        goodHEX = false;

                        $('#zone-color').wrap('<div class="error-wrapper"></div>');
                        $('#zone-color').parent().append('<div class="error-text">' + 'Invalid HEX code format' + '</div>');
                    }

                    for (var v = 0; v < OneMap.zones.allZones.length; v++){

                        var nametotest = OneMap.zones.allZones[v].name.toLowerCase().replace(/ /g,"");
                        var colortotest = OneMap.zones.allZones[v].color; //.replace(/#/g,"");

                        if(uniqueName){
                            if(zonename == nametotest){
                                allgood = false;
                                uniqueName = false;

                                $('#zone-name').wrap('<div class="error-wrapper"></div>');
                                $('#zone-name').parent().append('<div class="error-text">' + 'This zone name already exists' + '</div>');

                            }
                        }

                        //only need test for duplicates if its a valid hex format
                        if(goodHEX && uniqueColor){
                            //if short format, convert to long for duplicate check
                            var validformat = zonecolor;
                            if(zonecolor.length == 4){
                                validformat = zonecolor.replace(/#/g,"") + zonecolor.replace(/#/g,"");
                                validformat = '#' + validformat;
                            }
                            if (validformat == colortotest){
                                allgood = false;
                                uniqueColor = false;

                                $('#zone-color').wrap('<div class="error-wrapper"></div>');
                                $('#zone-color').parent().append('<div class="error-text">' + 'This zone color already exists' + '</div>');

                            }
                        }

                        if(!uniqueColor && !uniqueName){
                            break;
                        }
                    }

                    if(allgood){
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
                                case 'desk':
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
                                content += $('#warroomResult-template').html().format(results[i].name, results[i].number, results[i].location, 'Project: ' + results[i].project,isLinkClass, results[i].floor, results[i].hotspotId, "warroom");
                                break;
                        }
                    }

                    //display no results message if length == 0
                    if(results.length == 0){
                        content = '<div class="no-results">No results found.</div>';
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
            // console.log(hotspot);
            if(hotspot.areaType !== 'room') {
                if(hotspot.isVacant) {
                    hotspot.setStroke(hotspot.zoneColor);
                } else {
                    hotspot.setFill(hotspot.zoneColor);
                }
            }

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
                    if(OneMap.search.mapPins[floorNumber] != undefined && OneMap.search.mapPins[floorNumber].warroom != undefined && ($.inArray(hotspot.attrs.id, OneMap.search.mapPins[floorNumber].warroom) > -1)) {
                        pinHeight = 40;
                        pinWidth = 31;
                        pinImage = OneMap.search.pinImages.warroom;
                    } else {
                        if(OneMap.search.mapPins[floorNumber].user && ($.inArray(hotspot.attrs.id, OneMap.search.mapPins[floorNumber].user > -1))){
                            pinHeight = 40;
                            pinWidth = 31;
                            pinImage = OneMap.search.pinImages.warroom;
                        } else {
                            pinHeight = 36;
                            pinWidth = 28;
                            pinImage = OneMap.search.pinImages.room;
                        }
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
                fillPatternScale: {x:1, y:1},
                name: 'pin',
                oriY: (hotspot.areaY*OneMap.map.stageScale) + OneMap.map.floorplanY - pinHeight
            });
            rect.hotspot = hotspot;
            rect.on('mousedown', function() {
                this.hotspot.fire('mousedown');
            });
            rect.on('mouseover', function() {
                this.hotspot.fire('mouseover');
            });
            rect.on('mouseout', function() {
                this.hotspot.fire('mouseout');
            });
            
            OneMap.map.pinsLayer.add(rect);

            hotspot.isPin = true;

            if (OneMap.search.activeResult == hotspot.attrs.id) {
                selectedHotspot = hotspot;
                OneMap.search.activeResult = null;
            }

            OneMap.map.pinsLayer.draw();

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
                $('.searchbar').val("");
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
    init: function() {
        $('#offices').height($(window).height() - 95 - 20 - 70);
        $(window).resize(function(){
            $('#offices').height($(window).height() - 95 - 20 - 70);
            $('.info-panel').css("max-height", $(window).height() - 95);

            if(this.resizeTO) clearTimeout(this.resizeTO);
            this.resizeTO = setTimeout(function() {
                OneMap.map.unloadFloor();
                var canvas = $('.showthisfloor .canvas');
                if(canvas.length > 0) {
                    OneMap.map.loadFloor(canvas.attr('id'), canvas.data('imgsrc'));
                }
            }, 500);
        });

        $('.info-panel').css("max-height", $(window).height() - 95);
        $(document).on('click', '.info-link', function(){
            var parent = $(this).parent();
            if(parent.hasClass("open")){
                parent.removeClass("open");
                parent.find(".info-link").text("i");
            } else {
                parent.addClass("open");
                parent.find(".info-link").text("x");
            }
        });

        OneMap.map.init();
        OneMap.search.init();
        OneMap.zones.init(); // this should be scoped to admin only?

        $(document).ready(function() {
            var mapQuery = window.location.search.slice(1);
            if(OneMap.isLoggedIn && OneMap.userIsAdmin && mapQuery.length > 1) {
                var queryArray = mapQuery.split('&'),
                    parameters = {};

                for(var i = 0; i < queryArray.length; i++) {
                    var p = queryArray[i].split('=');
                    parameters[p[0]] = p[1];
                }

                // console.log(parameters);
                if(parameters.hotspot) {
                    // will need to add in location when that gets implemented
                    OneMap.isViewingConflict = true;
                    var backBtn =  document.getElementById('backto3d');
                    backBtn.href = '/one-map/admin/';
                    backBtn.innerHTML = 'Back';
                    var canvas = $('.canvas[data-floor="' + parameters.floor + '"]');
                    OneMap.search.activeResult =  parameters.hotspot;
                    OneMap.search.mapPins[parameters.floor] = {};
                    OneMap.search.mapPins[parameters.floor].floorIds = [parameters.hotspot];
                    canvas.parent('.floorplan').trigger('click');
                } else if (parameters.impersonate) {
                    // need to update "claim" function to pass in the user ID.
                    OneMap.impersonatingID = parameters.impersonate;
                    var panel = document.getElementById('impersonating-panel'),
                        nameWrapper = document.getElementById('impersonating-name');
                    nameWrapper.innerHTML = decodeURI(parameters.name);
                    document.getElementById('impersonating-done').onclick = function() {
                        OneMap.impersonatingID = -1;
                        nameWrapper.innerHTML = '';
                        panel.classList.remove('expanded');
                    };
                    panel.classList.add('expanded');
                }
            }
        });
    }
};

OneMap.init();