var OneMap = {};

OneMap.admin = {
	changeTab: function() {
		document.querySelector('#tabs .active').classList.remove('active');
        var id = this.querySelector('span').classList[1];
        document.getElementById(id).classList.add('active');
	},
	zones: {
		updatedSeats: [],
		getConflicts: function() {
			if(this.classList.contains('disabled')) return;

			var deleteButton = $('#delete-zone');
			deleteButton.addClass('disabled');
			deleteButton.attr('disabled', true);

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

				conflicts += $('#zoneConflict-template').html().format(
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
		updateOptions: function(select) {
			var self = select !== undefined ? select : this,
				buttons = $('#zones .needsRequired');
			if (self.value !== 'default') {
				buttons.removeClass('disabled');
				buttons.removeAttr('disabled');
			
			} else {
				buttons.addClass('disabled');
				buttons.attr('disabled', true);
			}
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
			OneMap.admin.zones.updateOptions(zoneSelect);
		},
		init: function() {
			$.get("js/template-admin.html", function (data) {
	            $("body").append(data);
	        });
			$(document).on('click', '#show-conflicts', OneMap.admin.zones.getConflicts);
	        $(document).on('click', '#delete-zone', OneMap.admin.zones.delete);
	        $(document).on('change', '#conflicts-listing input', OneMap.admin.zones.updatePrimary);
	        $(document).on('click', '#zones-submit', OneMap.admin.zones.updatePrimary);
	        $(document).on('change', '#zones-select', OneMap.admin.zones.updateOptions);
	        
		}
	},
	
	init: function() {
        $(document).on('click', '.tabs-navigation li', OneMap.admin.changeTab);
        OneMap.admin.zones.init();
    }
};

OneMap.admin.init();