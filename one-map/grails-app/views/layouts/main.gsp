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

<g:if test="${view != 'admin'}">
<div class="md-modal md-effect-1" id="modal-1">
  <a class="md-close close"></a>
  <div class="md-content">
  
  </div>
</div>

<div class="md-overlay"></div>
</g:if>

<sec:ifLoggedIn>
<div class="main ${view}">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
<div class="main ${view}">
</sec:ifNotLoggedIn>
  	<div class="fractals"></div>

   	<div class="header login"><!-- add login here -->
		<div class="header-bg">

			<g:if test="${view != 'admin'}">
				<div class="zone-panel">
	               	<div class="form">
	                    <div class="fields">
	                         <div class="messaging">Select the seats you'd like to group into a zone, then fill in the info below</div>
	                         <input id="zone-name" class="zone-name" type="text" placeholder="zone name">
	                         <input id="zone-color" type="text" class="zone-color" placeholder="color (e.g. #00bce4)">
	                    </div>
	                    <div class="actions">
	                         <div class="number-selected"><span id="selected-number">0 seats</span> selected</div>
	                         <a href="#" class="save-zone">SAVE</a>
	                         <a href="#" class="cancel-zone">CANCEL</a>
	                    </div>
	               </div>
	               <div class="response">
	                    <a href="#" class="okay">OK</a>
	                    <div>Your zone was successfully created</div>
	               </div>
	          	</div>
			</g:if>

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

	            <%-- TODO: add this back in after Active director is hooked up
	            <g:if test="${view != 'admin'}"> --%>
		            <a class="skip-login">Skip Login</a>
		        <%-- </g:if> --%>
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
	<script type="text/javascript" src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.2.min.js"></script>
	<script src="${resource(dir: 'js', file: 'map.js')}"></script>
	<script src="${resource(dir: 'js', file: 'lean-slider.js')}"></script>
</g:else>
<script src="${resource(dir: 'js', file: 'login.js')}"></script>

<script type="text/javascript">
	OneMap.login.submitURL = '${postUrl}';
	<sec:ifLoggedIn>
	  OneMap.isLoggedIn = true;
	  OneMap.userIsAdmin = true;
	</sec:ifLoggedIn>
</script>
</body>
</html>