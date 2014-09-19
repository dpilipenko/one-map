var OneMap = {};

OneMap.admin = {
	changeTab: function() {
		document.querySelector('#tabs .active').classList.remove('active');
        var id = this.querySelector('span').classList[1];
        document.querySelector('.tabs-navigation .active').classList.remove('active');
        this.classList.add('active');
        document.getElementById(id).classList.add('active');
	},
	getZoneConflicts: function() {
		// ajax call to get conflicts and add them to the div
		document.getElementById('show-conflicts-listing').classList.add('active');
	},
	init: function() {
        $(document).on('click', '.tabs-navigation li a',OneMap.admin.changeTab);
        $(document).on('click', '#show-conflicts',OneMap.admin.getZoneConflicts);

        $('#tabs').height($(window).height() - 95 - 20 - 135);
    }
};

OneMap.admin.init();