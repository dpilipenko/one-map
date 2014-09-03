var OneMap = {};

OneMap.admin = {
	init: function() {
        $(document).on('click', '.tabs-navigation li', function() {
            document.querySelector('#tabs .active').classList.remove('active');
            var id = this.querySelector('span').classList[1];
            console.log(id);
            document.getElementById(id).classList.add('active');
        });
    }
};

OneMap.admin.init();