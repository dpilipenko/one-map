<%@ page import="com.rosetta.onemap.User" %>

<!doctype html>
<!--[if lt IE 7]> <html lang="en-us" class="ie lt-ie10 lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html lang="en-us" class="ie lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html lang="en-us" class="ie lt-ie10 lt-ie9"> <![endif]-->
<!--[if IE 9]>    <html lang="en-us" class="ie lt-ie10"> <![endif]-->
<!--[if gt IE 9]>    <html lang="en-us" class="ie"> <![endif]-->
<!--[if !IE]><!--> <html lang="en-us"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width" />

	<title><g:layoutTitle default="ONEMAP" /></title>
	<link href="${resource(dir: 'css', file: 'main.css')}" rel="stylesheet">

	<g:layoutHead />
</head>
<body>

<g:set var="view" value="${pageProperty(name: 'page.view')}"/>

<sec:ifLoggedIn>
<div class="main ${view}">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
<div class="main ${view}">
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

	        		<!-- TODO: test if it's an admin user -->
	        		<g:if test="${view == 'admin'}">
			            <div class="admin-corner header-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/one-map">BACK</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</div>
			        </g:if>
			        <g:else>
			        	<div class="create-zone header-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">CREATE NEW ZONE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
			                <div class="generic-popup centered notch-top" id="create-zone-popup">
			                    <div class="inner">
			                        Select a floor first
			                        <div class="notch"></div>
			                    </div>
			                </div>
			            </div>
			    	</g:else>
	        		<!-- end TODO -->		            

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

	            <g:if test="${view != 'admin'}">
		            <a class="skip-login">Skip Login</a>
		        </g:if>
	        </div>
	    </div>
 	</div><!-- end header -->

 	<g:layoutBody />

</div><!-- end main -->

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<g:if test="${view == 'admin'}">
<script src="${resource(dir: 'js', file: 'admin.js')}"></script>
</g:if>
<g:else>
<script src="${resource(dir: 'js', file: 'main.js')}"></script>
</g:else>
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