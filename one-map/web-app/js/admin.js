var OneMap = {};

OneMap.admin = {
	changeTab: function() {
		document.querySelector('#tabs .active').classList.remove('active');
        var id = this.querySelector('span').classList[1];
        OneMap.admin[id].resetTab();
        document.getElementById(id).classList.add('active');
	},
	updateAvailableActions: function(tab, select) {
		var self = select !== undefined ? select : this,
			buttons = $('#'+tab+' .needs-required');
		if (self.value !== 'default') {
			buttons.removeClass('disabled');
			buttons.removeAttr('disabled');
		
		} else {
			buttons.addClass('disabled');
			buttons.attr('disabled', true);
		}
	},
	zones: {
		updatedSeats: [],
		getConflicts: function() {
			if(this.classList.contains('disabled')) return;

			var deleteButton = document.getElementById('delete-zone');
			deleteButton.classList.add('disabled');
			deleteButton.setAttribute('disabled', true);

			var zoneID = document.getElementById('zones-select').value,
				zoneLocation = document.getElementById('zones-location-select').value;
			// ajax call to get conflicts and add them to the div
			// -- pass in the zoneID for the backend to compare against
			// -- pass in location as a filter if the user desires to scope it
			// 
			// Success:
			var returnedData = [
				{
					hotspotID: 'h001',
					name: 'Dave Fagan',
					seatID: '1123',
					location: 'Cleveland',
					floor: 12,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'Kraft',
					zone2ID: '002',
					primary: '002'
				},
				{
					hotspotID: 'h002',
					name: '',
					seatID: '1124',
					location: 'Cleveland',
					floor: 12,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'One Map',
					zone2ID: '003',
					primary: '001'
				},
				{
					hotspotID: 'h003',
					name: 'Dmitriy Pilipenko',
					seatID: '1125',
					location: 'Cleveland',
					floor: 12,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'Grange',
					zone2ID: '004',
					primary: '001'
				},
				{
					hotspotID: 'h004',
					name: 'Dan Padgett',
					seatID: '1123',
					location: 'New York',
					floor: 5,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'Kraft',
					zone2ID: '002',
					primary: '001'
				},
			];

			var conflicts = '';
			for (var i = 0; i < returnedData.length; i++) {
				var onePrimary = '', twoPrimary = '', c = returnedData[i];
				if(c.primary == c.zone1ID) {
					onePrimary = 'checked';
				} else {
					twoPrimary = 'checked';
				}
				var user = c.name == '' ? 'Vacant' : c.name;

				conflicts += document.getElementById('zoneConflict-template').innerHTML.format(
					user, // 0
					c.seatID, // 1
					c.location, // 2
					c.floor, // 3
					c.hotspotID, // 4
					
					c.zone1ID, // 5
					c.zone1name, // 6
					onePrimary, // 7
					c.zone2ID, // 8
					c.zone2name, // 9
					twoPrimary // 10
				);                        
			};

			document.getElementById('conflicts-listing').innerHTML = conflicts;
			document.getElementById('zone-conflicts').classList.add('active');
		},
		delete: function() {
			if(this.classList.contains('disabled')) return;
			var select = document.getElementById('zones-select'),
				zoneID = select.value;
			if(zoneID !== 'default') {
				// ajax call to delete a zone
				// -- pass in the zoneID for the backend to change to Free Zone
				// 
				// Success:
				select.remove(select.selectedIndex);
				OneMap.admin.zones.resetTab();
			}
		},
		updatePrimary: function() {
			// <hotspot ID>:<primary zone ID>
			OneMap.admin.zones.updatedSeats.push(this.value);
			
			var zoneSubmit = document.getElementById('zones-submit');

			if(zoneSubmit.classList.contains('disabled')) {
				zoneSubmit.classList.remove('disabled');
				zoneSubmit.removeAttribute('disabled');
			}
		},
		savePrimary: function() {
			if(this.classList.contains('disabled')) return;
			// ajax call to set new primary zones for each hotspot
			// -- pass OneMap.admin.zones.updatedSeats
			// 
			// Success: -- resets everything back
			OneMap.admin.zones.resetTab();
		},
		resetTab: function() {
			OneMap.admin.zones.updatedSeats = [];
			var zoneSelect = document.getElementById('zones-select'),
				zoneSubmit = document.getElementById('zones-submit');

			document.getElementById('conflicts-listing').innerHTML = '';
			document.getElementById('zone-conflicts').classList.remove('active');
			document.getElementById('zones-location-select').value = 'all';

			zoneSubmit.classList.add('disabled');
			zoneSubmit.setAttribute('disabled', true);
			zoneSelect.value = 'default';
			OneMap.admin.updateAvailableActions('zones', zoneSelect);
		},
		init: function() {
			$.get("js/template-admin.html", function (data) {
	            $("body").append(data);
	        });
			$(document).on('click', '#show-conflicts', OneMap.admin.zones.getConflicts);
	        $(document).on('click', '#delete-zone', OneMap.admin.zones.delete);
	        $(document).on('change', '#conflicts-listing input', OneMap.admin.zones.updatePrimary);
	        $(document).on('click', '#zones-submit', OneMap.admin.zones.savePrimary);
	        $(document).on('change', '#zones-select', function() {
	        	OneMap.admin.updateAvailableActions('zones');
	        });
	        
		}
	},
	seats: {
		userList: [],
		populateAutocomplete: function (request, response) {
			var unclaimedOnly = document.getElementById('unclaimed-only').checked;
			// ajax call 
			//
			//    successs: 
			var data = [
				{
					label: 'Liz Judd',
					id: '1234'
				},
				{
					label: 'Dave Fagan',
					id: '1235'
				},
				{
					label: 'Dan Padgett',
					id: '1236'
				},
				{
					label: 'Becky Horvath',
					id: '1237'
				},
			];
			OneMap.admin.seats.userList = data;
	        response(data);
		},
		selectName: function(event, ui) {
	    	for (var n = 0; n < OneMap.admin.seats.userList.length; n++){
	    		if(OneMap.admin.seats.userList[n].label == ui.item.value){
	    			document.getElementById('user-ID').value = OneMap.admin.seats.userList[n].id;
	    			break;
	    		}
	    	}
		},
		assignSeat: function() {
			if(this.classList.contains('disabled')) return;

		},
		resetTab: function() {

		},
		init: function() {
			$( "#user-name" ).autocomplete({
				source: OneMap.admin.seats.populateAutocomplete,
				select: OneMap.admin.seats.selectName
			});
			$(document).on('click', '#seats-submit', OneMap.admin.seats.assignSeat);
		}
	},
	reports: {
		showFields: function() {
			if(this.value == 'default') {
				OneMap.admin.reports.resetTab();
				return;
			}

			var queryForm = document.getElementById('query-form'),
				hiddenFields = queryForm.getElementsByClassName('hidden-content'),
				reportsSubmit = document.getElementById('reports-submit');

			if (this.options[this.selectedIndex].dataset.hasrequired) {
				reportsSubmit.classList.add('disabled');
				reportsSubmit.setAttribute('disabled', true);
			} else {
				reportsSubmit.classList.remove('disabled');
				reportsSubmit.removeAttribute('disabled');
			}
			queryForm.classList.add('active');
			document.getElementById('reports-location-select').selectedIndex = 0;
			for(var i = 0; i < hiddenFields.length; i++) {
				if(hiddenFields[i].classList.contains(this.value + '-field')) {
					hiddenFields[i].classList.add('active');
					hiddenFields[i].selectedIndex = 0;
				} else {
					hiddenFields[i].classList.remove('active');
				}
			}
		},
		resetTab: function() {
			document.getElementById('query-select').value = 'default';
			var reportsTab = document.getElementById('reports'),
				hiddenFields = reportsTab.getElementsByClassName('hidden-content');
						
			for(var i = 0; i < hiddenFields.length; i++) {
				hiddenFields[i].classList.remove('active');
				if(hiddenFields[i].nodeName == 'SELECT') {
					hiddenFields[i].selectedIndex = 0;
				}
			}

			var reportsSubmit = document.getElementById('reports-submit');
			reportsSubmit.classList.add('disabled');
			reportsSubmit.setAttribute('disabled', true);
		},
		getOccupancy: function() { // need to update the floor dropdown based on the location downdown? 
			var location = document.getElementById('reports-location-select').value,
				floor = document.getElementById('floor-select').value;
			// ajax call
			//
			//	success:
			var data = {};
			OneMap.admin.reports.displayOccupancy(data);
		},
		displayOccupancy: function(data) {
			document.getElementById('report-display').classList.add('active');
		},
		getAnalytics: function() {
			var location = document.getElementById('reports-location-select').value;
			// ajax call
			//
			//	success:
			var data = {};
			OneMap.admin.reports.displayAnalytics(data);
		},
		displayAnalytics: function(data) {
			document.getElementById('report-display').classList.add('active');
		},
		getMoves: function() {
			var location = document.getElementById('reports-location-select').value,
				timeRange = document.getElementById('time-range-select').value;
			// ajax call
			//	
			//	success:
			var data = {};
			OneMap.admin.reports.displayMoves(data);
		},
		displayMoves: function(data) {
			document.getElementById('report-display').classList.add('active');
		},
		submit: function() {
			if(this.classList.contains('disabled')) return;
			var query = document.getElementById('query-select').value;
			OneMap.admin.reports['get'+query.charAt(0).toUpperCase() + query.slice(1)]();
		},

		init: function() {
			$(document).on('change', '#query-select', OneMap.admin.reports.showFields);
			$(document).on('click', '#reports-submit', OneMap.admin.reports.submit);
			$(document).on('change', '#reports .is-required', function() {
				OneMap.admin.updateAvailableActions('reports');
			});
		}
	},
	init: function() {
        $('#tabs').height($(window).height() - 95 - 20 - 135);
        $(document).on('click', '.tabs-navigation li', OneMap.admin.changeTab);
        OneMap.admin.zones.init();
        OneMap.admin.seats.init();
        OneMap.admin.reports.init();
    }
};

OneMap.admin.init();