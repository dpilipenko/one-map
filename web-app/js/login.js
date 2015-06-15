String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};

OneMap.login = {
    submitURL: null,
    submit: function () {
        var username = $('.username').val();
        var password = $('.password').val();

        //reset validation
        $('.error-wrapper .username').parent().find('.error-text').remove();
        $('.error-wrapper .password').parent().find('.error-text').remove();
        $('.error-wrapper .username').unwrap();
        $('.error-wrapper .password').unwrap();
        $('.login-form').removeClass("password-error");

        //validation
        if (username.length > 0 && password.length > 0){
        	if(false){
//            if(!( /[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}/.test(username) )){
                $('.username').wrap('<div class="error-wrapper"></div>');
                $('.username').parent().append('<div class="error-text">' + 'Invalid email format' + '</div>');
            } else {
                //all good
                $('.login-dot').addClass('loading');
                $.ajax({
                    url: OneMap.login.submitURL,
                    type: 'POST',
                    data: {
                        j_username: username,
                        j_password: password
                    },
                    success: function (data, textStatus, jqXHR) {
                        if(data.success){
                            OneMap.isLoggedIn = true;
                            OneMap.userIsAdmin = data.isAdmin;
                            OneMap.login.userID = data.userID;
                            if (!data.isAdmin) {
                            	$('.create-zone').remove();
                            }
                            $('.welcome').html('Hey ' + data.firstname + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|');
                            $('.logout a').text('LOGOUT');

                            $('.header').removeClass('login');
                            $('.info-panel').removeClass('login');
                            setTimeout(function () {
                                $('.ms-wrapper').addClass('ms-view-layers');
                            }, 500);
                        } else {
                            console.log(data.error);
                            $('.password').wrap('<div class="error-wrapper auth-error"></div>');
                            $('.password').parent().append('<div class="error-text">' + data.error + '</div>');
                            $('.login-form').addClass("password-error");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            }
        } else {
            if(! username.length > 0){
                $('.username').wrap('<div class="error-wrapper"></div>');
                $('.username').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
            }
            if(! password.length > 0){
                $('.password').wrap('<div class="error-wrapper"></div>');
                $('.password').parent().append('<div class="error-text">' + 'This field is required' + '</div>');
                $('.login-form').addClass("password-error");
            }
        }
    },
    skipLogin: function(){
        if(!OneMap.isLoggedIn){
            $('.logout a').text('LOGIN');
        }
        $('.header').removeClass('login');
        $('.info-panel').removeClass('login');
        $('.create-zone').remove();
        setTimeout(function () {
            $('.ms-wrapper').addClass('ms-view-layers');
        }, 500);
    },
    init: function() {
        //TODO: how will this work with AD? Do we need to have them log in every time?
        console.info('here');
        $(document).ready(function() {
            if (OneMap.isLoggedIn) {
                console.info('inside here');
                $('.header').removeClass('login');
                $('.info-panel').removeClass('login');
                setTimeout(function () {
                    $('.ms-wrapper').addClass('ms-view-layers');
                }, 500);
            }
        });
        $(document).on('click', '.submit-login', OneMap.login.submit);
        $(document).on('click', '.skip-login', OneMap.login.skipLogin);
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
};

OneMap.login.init();