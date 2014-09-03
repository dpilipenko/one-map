<%@ page import="com.rosetta.onemap.User" %>
<!doctype html>
<!--[if lt IE 7]> <html lang="en-us" class="ie lt-ie10 lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html lang="en-us" class="ie lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html lang="en-us" class="ie lt-ie10 lt-ie9"> <![endif]-->
<!--[if IE 9]>    <html lang="en-us" class="ie lt-ie10"> <![endif]-->
<!--[if gt IE 9]>    <html lang="en-us" class="ie"> <![endif]-->
<!--[if !IE]><!--> <html lang="en-us"> <!--<![endif]-->
	<head>
		<meta charset="utf-8">
		<title>ONEMAP</title>
		<meta name="viewport" content="width=device-width">
		<link href="${resource(dir: 'css', file: 'main.css')}" rel="stylesheet">
	</head>
<body>

<sec:ifLoggedIn>
<div class="main admin">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
<div class="main admin">
</sec:ifNotLoggedIn>
  	<div class="fractals"></div>

   	<div class="header"><!-- add login here -->

			<div class="header-bg">
				<div class="inner">
					<div class="logo"></div>
				  <div class="divider"></div>
					<div class="name">
						ONE MAP
					</div>

					<div class="utility">
	  				<g:formRemote name="searchForm" update="result-list" url="[action: 'runSearch']">
	          			<input type="submit" class="searchbtn" value="submit" />
	    				<input class="searchbar" type="text" name="searchquery" placeholder="Search">
					</g:formRemote>

		            <div class="logout">
		              <g:link controller="logout">
		                <sec:ifLoggedIn>
		                  LOGOUT
		                </sec:ifLoggedIn>
		                <sec:ifNotLoggedIn>
		                  LOGIN
		                </sec:ifNotLoggedIn>
		              </g:link>
		            </div>

	        		<!-- if admin -->
		            <!-- <div class="create-zone header-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">CREATE NEW ZONE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
		                <div class="generic-popup centered notch-top" id="create-zone-popup">
		                    <div class="inner">
		                        Select a floor first
		                        <div class="notch"></div>
		                    </div>
		                </div>
		            </div> -->
		            <div class="admin-corner header-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/one-map">BACK</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</div>
	        		<!-- end if admin -->

		            <div class="welcome">
		  				<sec:ifLoggedIn>
		  	  				<g:set var="userObject" value="${User.findByUsername(sec.loggedInUserInfo(field:'username'))}"/>
		  	  				Hey ${userObject.firstName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
		    			</sec:ifLoggedIn>
		            </div>
					</div>
				</div>

		        <div class="login-dot">
	            <div class="inner">
	              	<div class="login-form">

		                <div class="login-title">
		                  ONE MAP
		                </div>
		                <label for="username">Email</label>
		                <input class="username" type="text" />
		                <label for="password">Password</label>
		                <input class="password" type="password" />
		                <button type="submit" class="submit-login">SUBMIT</button>
	              	</div>
	              	<div class="browser-compatability">
		                <div class="login-title">
		                  ONE MAP
		                </div>
	                	<div class="upgrade-browser-message">
		                  This site requires modern browsers.<br>
		                  It's time to make the switch.
		                </div>
	              	</div>
	            </div>
	            <a class="skip-login">Skip Login</a>
	        </div>
	    </div>
 	</div><!-- end header -->

    <div id="tabs">
		<div id="zones" class="tab active">
			<form action="">
				Zones
				<input type="submit" value="Submit" />
			</form>
		</div>
		<div id="seats" class="tab">
			<form action="">
				Seat Assignments
				<input type="submit" value="Submit" />
			</form>
		</div>
		<div id="reports" class="tab">
			<form action="">
				Reports
				<input type="submit" value="Submit" />
			</form>
		</div>
        <div id="falloff-shadow"></div>
    </div> <!-- end content container -->
    <div class="tabs-navigation">
    	<ul>
    		<li><span class="icon zones"></span>Zones</li>
    		<li><span class="icon seats"></span>Seat Assignments</li>
    		<li><span class="icon reports"></span>Reports</li>
    	</ul>
    </div>
</div><!-- end main -->

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="${resource(dir: 'js', file: 'admin.js')}"></script>
<script src="${resource(dir: 'js', file: 'login.js')}"></script>

<script type="text/javascript">
	OneMap.login.submitURL = '${postUrl}';
</script>

<script type="text/javascript">
<sec:ifLoggedIn>
  OneMap.isLoggedIn = true;
</sec:ifLoggedIn>
</script>

</body>
</html>