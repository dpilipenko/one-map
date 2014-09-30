/* TODO:
 * 1. Hook app into Active Directory
 * 2. Estimations for Admin
 * 3. Styling for Admin section
 * 4. Create Floorplan PDF with all seat IDs (use the cleaned up versions that Liz has)
 * 		file name structure: 'floorplan-<location ID>.pdf'
 * 5. Hook into backend ajax calls for Admin features
 *
 */
function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
    
    var CSV = '';    
    //Set Report title in first row or line
    
    // CSV += ReportTitle + '\r\n\n';  This prints out the title at the top of the file

    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";
        
        //This loop will extract the label from 1st index of on array
        for (var index in arrData[0]) {
            
            //Now convert each value to string and comma-seprated
            row += index + ',';
        }

        row = row.slice(0, -1);
        
        //append Label row with line break
        CSV += row + '\r\n';
    }
    
    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";
        
        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);
        
        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {        
        alert("Invalid data");
        return;
    }   
    
    //Generate a file name
    var fileName = "OneMap_";
    //this will remove the blank-spaces from the title and replace it with an underscore
    fileName += ReportTitle.replace(/ /g,"_");   
    
    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);
    
    // Now the little tricky part.
    // you can use either>> window.open(uri);
    // but this will not work in some browsers
    // or you will not get the correct file extension    
    
    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");    
    link.href = uri;
    
    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";
    
    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

var OneMap = {};

OneMap.admin = {
	changeTab: function() {
		document.querySelector('#tabs .active').classList.remove('active');
        var id = this.querySelector('span').classList[1];
        document.querySelector('.tabs-navigation .active').classList.remove('active');
        this.classList.add('active');
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
	navigatingAwayAlert: function(tab, action, button) {
		var modal = document.getElementById('navigating-away'),
			confirmBtn = modal.getElementsByTagName('input')[0],
			denyBtn = modal.getElementsByTagName('input')[1];
		modal.classList.add('active');
		confirmBtn.onclick = function() {
			modal.classList.remove('active');
			OneMap.admin[tab][action](button);
		};
		denyBtn.onclick = function() {
			modal.classList.remove('active');
		};
	},
	zones: {
		updatedSeats: [],
		getConflicts: function() {
			if(this.classList.contains('disabled')) return;

			var loadingIcon = document.getElementById('conflicts-loading');
			var deleteButton = document.getElementById('delete-zone');
			loadingIcon.style.display = 'inline-block';
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
					hotspotID: 'h106',
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
					hotspotID: 'h226',
					name: '',
					seatID: '1124',
					location: 'Cleveland',
					floor: 13,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'One Map',
					zone2ID: '003',
					primary: '001'
				},
				{
					hotspotID: 'h227',
					name: 'Dmitriy Pilipenko',
					seatID: '1125',
					location: 'Cleveland',
					floor: 13,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'Grange',
					zone2ID: '004',
					primary: '001'
				},
				{
					hotspotID: 'h232',
					name: 'Dan Padgett',
					seatID: '1123',
					location: 'New York',
					floor: 15,
					zone1name: 'AHA',
					zone1ID: '001',
					zone2name: 'Kraft',
					zone2ID: '002',
					primary: '001'
				},
			];

			setTimeout(function() { // remove when ajax is actually implemented
				var conflicts = '';
				for (var i = 0; i < returnedData.length; i++) {
					var onePrimary = '', twoPrimary = '', c = returnedData[i];
					if(c.primary == c.zone1ID) {
						onePrimary = 'checked';
					} else {
						twoPrimary = 'checked';
					}
					var user = c.name === '' ? 'Vacant' : c.name;

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
				}

				document.getElementById('conflicts-listing').innerHTML = conflicts;
				loadingIcon.style.display = 'none';
				document.getElementById('zone-conflicts').classList.add('active');
			}, 1000);
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
		viewHotspotOnMap: function(button) {
			var hotspot = button.dataset.hotspot,
				floor = button.dataset.floor,
				location = button.dataset.location,
				url = '/one-map/?hotspot='+hotspot+'&floor='+floor+'&location='+location;

			window.location.href = url; // functionality on map for this scenario needs implemented
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
			$(document).on('click', '.conflict .user', function() {
				OneMap.admin.navigatingAwayAlert('zones', 'viewHotspotOnMap', this);
			});
		}
	},
	seats: {
		userList: [],
		impersonateUser: function() {
			var userID = document.getElementById('user-ID').value,
				url = '/one-map/?impersonate='+userID+'&name='+encodeURI(document.getElementById('user-name').value);
			window.location.href = url; // functionality on map for this scenario needs implemented
		},
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
		getAvailableSeatsByLocation: function() {
			var location = this.value,
				floorSelect = document.getElementById('hotspot-ID'),
				floorplanButton = document.getElementById('floorplan-download'),
				selectOptions = '<option value="default">Select One</option>';
			
			if(location !== 'default') {
				floorplanButton.href = '/one-map/pdf/floorplan-'+ location +'.pdf';
				// ajax call
				// 
				// success:
				var data = [{id: 'h001', label: '1125'}, {id: 'h002', label: '1225'}, {id: 'h003', label: '1325'}, {id: 'h004', label: '1425'}, {id: 'h005', label: '1525'}, {id: 'h006', label: '1725'}];
				

				for(var i = 0; i < data.length; i++) {
					selectOptions += '<option value="'+ data[i].id +'">'+ data[i].label +'</option>';
				}
				floorSelect.innerHTML = selectOptions;
			} else {
				floorplanButton.href = '#';
				floorSelect.innerHTML = selectOptions;
			}
		},
		selectName: function(event, ui) {
			for (var n = 0; n < OneMap.admin.seats.userList.length; n++){
				if(OneMap.admin.seats.userList[n].label == ui.item.value){
					document.getElementById('user-ID').value = OneMap.admin.seats.userList[n].id;
					OneMap.admin.seats.checkRequiredFields();
					var browseBtn = document.getElementById('browse-seats'),
						validationCue = document.getElementById('name-validation');
					browseBtn.classList.remove('disabled');
					browseBtn.removeAttribute('disabled');
					validationCue.classList.remove('error');
					validationCue.classList.add('valid');
					break;
				}
			}
		},
		assignSeat: function() {
			if(this.classList.contains('disabled')) return;

			var user = document.getElementById('user-ID').value,
				hotspot = document.getElementById('hotspot-ID').value;
			// ajax
			// 
			// success:
			var select = document.getElementById('hotspot-ID');
			select.remove(select.selectedIndex);
			OneMap.admin.seats.resetTab();
		},
		resetTab: function() {
			document.getElementById('unclaimed-only').checked = false;
			OneMap.admin.seats.userList = [];
			document.getElementById('user-name').value = '';
			document.getElementById('user-ID').value = '';
			document.getElementById('hotspot-ID').selectedIndex = 0;
			document.getElementById('seats-location-select').selectedIndex = 0;
			$('#seats-location-select').trigger('change');
			var submitButton = document.getElementById('seats-submit');
			submitButton.classList.add('disabled');
			submitButton.setAttribute('disabled', true);
		},
		checkRequiredFields: function() {
			var user = document.getElementById('user-ID').value,
				hotspot = document.getElementById('hotspot-ID').value,
				submitButton = document.getElementById('seats-submit');
			if(user !== '' && hotspot !== 'default') {
				submitButton.classList.remove('disabled');
				submitButton.removeAttribute('disabled');
			} else {
				submitButton.classList.add('disabled');
				submitButton.setAttribute('disabled', true);
			}
		},
		init: function() {
			$( "#user-name" ).autocomplete({
				source: OneMap.admin.seats.populateAutocomplete,
				select: OneMap.admin.seats.selectName,
				search: function() {
					var browseBtn = document.getElementById('browse-seats'),
						validationCue = document.getElementById('name-validation');
					browseBtn.classList.add('disabled');
					browseBtn.setAttribute('disabled', true);
					validationCue.classList.add('error');
					validationCue.classList.remove('valid');
				}
			});
			$(document).on('change', '#hotspot-ID', OneMap.admin.seats.checkRequiredFields);
			$(document).on('click', '#seats-submit', OneMap.admin.seats.assignSeat);
			$(document).on('change', '#seats-location-select', OneMap.admin.seats.getAvailableSeatsByLocation);
			$(document).on('click', '#browse-seats', function() {
				if(this.classList.contains('disabled')) return;
				OneMap.admin.navigatingAwayAlert('seats', 'impersonateUser', this);
			});
			$(document).on('click', '#sync-db', function() {
				var loadingIcon = document.getElementById('sync-loading'),
					form = document.querySelector('#seats form');
				loadingIcon.style.display = 'inline-block';
				form.style.display = 'none';

				// ajax call
				// 
				// success:
				setTimeout(function() { // remove this once the ajax call is actually in
					loadingIcon.style.display = 'none';
					form.style.display = 'block';
				}, 1000);

				
			});
		}
	},
	reports: {
		currentData: '',
		showFields: function() {
			if(this.value == 'default') {
				OneMap.admin.reports.resetTab();
				return;
			}

			var queryForm = document.getElementById('query-form'),
				hiddenFields = queryForm.getElementsByClassName('hidden-content'),
				reportsSubmit = document.getElementById('reports-submit'),
				display = document.getElementById('report-display');

			OneMap.admin.reports.currentData = '';
			display.classList.remove('active');

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

			document.getElementById('reports-location-select').selectedIndex = 0;

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
		generateFile: function() {
			if(OneMap.admin.reports.currentData === '') return;
			var title = document.getElementById('query-select').value;
			JSONToCSVConvertor(OneMap.admin.reports.currentData, title, true);
		},
		displayResults: function(data) {
			var html = 'No Results Found',
				numOfResults = data.length;
			if(numOfResults > 0) {
				OneMap.admin.reports.currentData = data; // this is used to create the CSV

				// Create Table
				html = '<table>';
				for (var i = 0; i < numOfResults; i++) {
					if(i === 0) {
						html += '<tr>';
						for(var key in data[i]) {							
							html += '<th>'+ key +'</th>';
						}
						html += '</tr>';
					}
					html += '<tr>';
					for(var key in data[i]) {							
						html += '<td>'+ data[i][key] +'</td>';
					}
					html += '</tr>';
				}

				html +='</table>';
				html += '<input type="button" value="Download Report" id="generate-report" />'; // update link once we know how to do this
				
			}
			var reportWrapper = document.getElementById('report-display');
			reportWrapper.innerHTML = html;
			reportWrapper.classList.add('active');
			document.getElementById('reports-loading').style.display = 'none';
		},
		getOccupancy: function() {
			var location = document.getElementById('reports-location-select').value,
				floor = document.getElementById('floor-select').value;
			// ajax call
			//
			//	success:
			var data = [ 
				{
					'Location': 'Cleveland',
					'Floor': '11',
					'Vancancy': 24
				},
				{
					'Location': 'Cleveland',
					'Floor': '12',
					'Vancancy': 50
				},
				{
					'Location': 'Cleveland',
					'Floor': '13',
					'Vancancy': 75
				},
				{
					'Location': 'Cleveland',
					'Floor': '14',
					'Vancancy': 12
				},
				{
					'Location': 'Cleveland',
					'Floor': '15',
					'Vancancy': 0
				},
				{
					'Location': 'Cleveland',
					'Floor': '17',
					'Vancancy': 0
				},
				{
					'Location': 'New York',
					'Floor': '5',
					'Vancancy': 1
				}
			];
			setTimeout(function() { // remove when ajax is actually implemented
				OneMap.admin.reports.displayResults(data);
			}, 1000);
		},
		getAnalytics: function() {
			var location = document.getElementById('reports-location-select').value;
			// ajax call
			//
			//	success:
			var data = [];
			setTimeout(function() { // remove when ajax is actually implemented
				OneMap.admin.reports.displayResults(data);
			}, 1000);
		},
		getMoves: function() {
			var location = document.getElementById('reports-location-select').value,
				timeRange = document.getElementById('time-range-select').value;
			// ajax call
			//	
			//	success:
			var data = [
				{
					'Location': 'Cleveland',
					'Employee': 'Dave Fagan',
					'Date': '09/30/14' 
				},
				{
					'Location': 'Cleveland',
					'Employee': 'Liz Judd',
					'Date': '01/15/14' 
				},
				{
					'Location': 'Cleveland',
					'Employee': 'Dmitriy Pilipenko',
					'Date': '05/24/14' 
				},
				{
					'Location': 'Cleveland',
					'Employee': 'Becky Horvath',
					'Date': '03/16/14' 
				},
				{
					'Location': 'New York',
					'Employee': 'Dan Padgett',
					'Date': '08/28/14' 
				}
			];
			setTimeout(function() { // remove when ajax is actually implemented
				OneMap.admin.reports.displayResults(data);
			}, 1000);
		},
		submit: function() {
			if(this.classList.contains('disabled')) return;
			var query = document.getElementById('query-select').value;
			document.getElementById('reports-loading').style.display = 'inline-block';
			OneMap.admin.reports['get'+query.charAt(0).toUpperCase() + query.slice(1)]();
		},
		getFloorsByLocation: function() {
			if (document.getElementById('query-select').value !== 'occupancy') return; // currently floors only displays on occupancy

			var location = this.value,
				floorSelect = document.getElementById('floor-select'),
				selectOptions = '<option value="all">All</option>';
			
			if(location !== 'all') {
				// ajax call
				// 
				// success:
				var data = [{id: 001, label: '11'}, {id: 002, label: '12'}, {id: 003, label: '13'}, {id: 004, label: '14'}, {id: 005, label: '15'}, {id: 006, label: '17'}];
				

				for(var i = 0; i < data.length; i++) {
					selectOptions += '<option value="'+ data[i].id +'">'+ data[i].label +'</option>';
				}
				floorSelect.innerHTML = selectOptions;
			} else {
				floorSelect.innerHTML = selectOptions;
			}			
		},

		init: function() {
			$(document).on('change', '#query-select', OneMap.admin.reports.showFields);
			$(document).on('click', '#reports-submit', OneMap.admin.reports.submit);
			$(document).on('change', '#reports .is-required', function() {
				OneMap.admin.updateAvailableActions('reports');
			});
			$(document).on('change', '#reports-location-select', OneMap.admin.reports.getFloorsByLocation);
			$(document).on('click', '#generate-report', OneMap.admin.reports.generateFile);
		}
	},
	init: function() {
        $('#tabs').height($(window).height() - 95 - 20 - 135);
        $(document).on('click', '.tabs-navigation li a', OneMap.admin.changeTab);
        OneMap.admin.zones.init();
        OneMap.admin.seats.init();
        OneMap.admin.reports.init();
    }
};

OneMap.admin.init();