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
			<h2>Zones</h2>
			<form action="">
				<select name="zones-select" id="zones-select">
					<option value="default">Select One</option>
					<option value="001">AHA</option>
					<option value="002">Kraft</option>
					<option value="003">Grange</option>
				</select>
				<select name="zones-location-select" id="zones-location-select">
					<option value="all">All</option>					
					<option value="001">Cleveland</option>
					<option value="002">Princeton</option>
					<option value="003">New York</option>
				</select>
				<input type="button" id="show-conflicts" value="Show Conflicts" />
				<div>- OR -</div>
				<input type="button" id="delete-zone" value="Delete" />
				<div class="show-conflicts-listing">
					<div class="conflict">
						<a href="#">User Name, Seat ID, Location, Floor</a>
						<input type="radio" name="PrimaryZone-UserID001" selected />
						<label for="PrimaryZone001-UserID001">AHA</label>
						<input type="radio" name="PrimaryZone-UserID001" />
						<label for="PrimaryZone001-UserID001">Kraft</label>
					</div>
					<div class="conflict">
						<a href="#">User Name2, Seat ID2, Location2, Floor2</a>
						<input type="radio" name="PrimaryZone-UserID002" selected />
						<label for="PrimaryZone001-UserID002">AHA</label>
						<input type="radio" name="PrimaryZone-UserID002" />
						<label for="PrimaryZone001-UserID002">Grange</label>
					</div>

				</div>
				<input type="button" value="Save" />
			</form>
		</div>
		<div id="seats" class="tab">
			<h2>Seat Assignments</h2>
			<form action="">
				<input type="checkbox" name="is-unclaimed" />
				<label for="is-unclaimed">Get Unclaimed Names Only</label>

				<input type="text" placeholder="Name" />
				<select>
					<option value="default">Select One</option>
					<option value="h001">1123</option>
					<option value="h002">1124</option>
					<option value="h003">1125</option>
					<option value="h004">1126</option>
				</select>
				<input type="button" value="Save" />
				<input type="button" value="Download Floorplans" />
				<div class="divider horizontal"></div>
				<input type="button" value="Browse" />
			</form>
		</div>
		<div id="reports" class="tab">
			<h2>Reports</h2>
			<form action="">
				<select name="query-select" id="query-select">
					<option value="occupancy">Occupancy</option>
					<option value="moves">Recent Moves</option>
					<option value="analytics">Analytics</option>
				</select>
				<div id="query-form">
					<select name="reports-location-select" id="reports-location-select">
						<option value="all">All</option>					
						<option value="001">Cleveland</option>
						<option value="002">Princeton</option>
						<option value="003">New York</option>
					</select>
					<select name="floor-select" id="floor-select" class="occupancy-field">
						<option value="all">All</option>					
						<option value="001">11</option>
						<option value="002">12</option>
						<option value="003">13</option>
					</select>
					<select name="time-range-select" id="time-range-select" class="moves-field">
						<option value="all">Select One</option>					
						<option value="001">1 week</option>
						<option value="002">1 month</option>
						<option value="003">2 months</option>
					</select>
					<input type="button" value="Submit" />
				</div>
	

				<div class="divider vertical"></div>
				<div id="report-display">
					<table>
						<tr>
							<td>Location</td>
							<td>Floor</td>
							<td>% vacant</td>
						</tr>
						<tr>
							<td>Location</td>
							<td>Floor</td>
							<td>% vacant</td>
						</tr>
						<tr>
							<td>Location</td>
							<td>Floor</td>
							<td>% vacant</td>
						</tr>
					</table>
					<input type="button" value="Download Report" />
				</div>
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