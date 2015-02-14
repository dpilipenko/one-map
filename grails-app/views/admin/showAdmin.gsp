<!doctype html>
<html>
	<head>
		<meta name="layout" content="main" />
		<parameter name="view" value="admin" />
	</head>
	<body>
	    <div id="tabs">
			<div id="zones" class="tab active">
				<div class="watermark"></div>
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
					<input type="button" id="show-conflicts" name="show-conflicts" value="Show Conflicts" class="needs-required disabled" disabled />
					<img id="conflicts-loading" class="loading-icon" width="13px" height="13px" src="/one-map/images/loading.gif" />
					<div>- OR -</div>
					<input type="button" id="delete-zone" name="delete-zone" value="Delete" class="needs-required disabled" disabled />
					<div id="zone-conflicts"  class="hidden-content">
						<div id="conflicts-listing"></div>
						<input type="button" id="zones-submit" value="Save" class="disabled" disabled />
					</div>
				</form>
			</div>
			<div id="seats" class="tab">
				<div class="watermark"></div>
				<h2>Seats</h2>
				
				<input type="button" id="sync-db" name="sync-db" value="Sync with Active Directory" />
				<img id="sync-loading" class="loading-icon" width="13px" height="13px" src="/one-map/images/loading.gif" />
				<form action="">
					<select name="seats-location-select" id="seats-location-select">
						<option value="default">Select One</option>					
						<option value="001">Cleveland</option>
						<option value="002">Princeton</option>
						<option value="003">New York</option>
					</select>

					<input type="checkbox" name="unclaimed-only" id="unclaimed-only" />
					<label for="unclaimed-only">Get Unclaimed Names Only</label>

					<input type="text" placeholder="Name" name="user-name" id="user-name" />
					<input type="hidden" name="user-ID" id="user-ID" value="" />
					<span class="validation" id="name-validation"></span> 
					<select size="3" name="hotspot-ID" id="hotspot-ID"> 
						<option value="default">Select One</option>
					</select>
					<input type="button" id="seats-submit" value="Save" class="disabled" disabled />
					<a id="floorplan-download" href="#" >Download Floorplans</a>
					<div class="divider horizontal"></div>
					<input type="button" value="Browse" id="browse-seats" class="disabled" disabled />
				</form>
			</div>
			<div id="reports" class="tab">
				<div class="watermark"></div>
				<h2>Reports</h2>
				<form action="">
					<select name="query-select" id="query-select">
						<option value="default" selected>Select One</option>
						<option value="occupancy">Occupancy</option>
						<option value="moves" data-hasrequired="true">Recent Moves</option>
						<option value="analytics">Analytics</option>
					</select>
					<div id="query-form" class="hidden-content">
						<select name="reports-location-select" id="reports-location-select">
							<option value="all">All</option>					
							<option value="001">Cleveland</option>
							<option value="002">Princeton</option>
							<option value="003">New York</option>
						</select>
						<select name="floor-select" id="floor-select" class="occupancy-field hidden-content">
							<option value="all">All</option>
						</select>
						<select name="time-range-select" id="time-range-select" class="moves-field hidden-content is-required">
							<option value="all">Select One</option>					
							<option value="001">1 week</option>
							<option value="002">1 month</option>
							<option value="003">2 months</option>
						</select>
						<input type="button" id="reports-submit" value="Submit" class="needs-required disabled" disabled />
						<img id="reports-loading" class="loading-icon" width="13px" height="13px" src="/one-map/images/loading.gif" />
					</div>
		

					<div class="divider vertical"></div>
					<div id="report-display" class="hidden-content"></div>
				</form>
			</div>
	    </div> <!-- end content container -->
	    <div class="tabs-navigation">
	    	<div id="falloff-shadow"></div>
	    	<ul>
	    		<li><a href="#" class="active"><span class="icon zones"></span><span class="admin-zone-text">Zones</span></a></li>
	    		<li class="divide"><div></div></li>
	    		<li><a href="#"><span class="icon seats"></span>Seats</a></li>
	    		<li class="divide"><div></div></li>
	    		<li><a href="#"><span class="icon reports"></span>Reports</a></li>
	    	</ul>
	    </div>
	    <div id="navigating-away" class="modal">
	    	You will be leaving this page now to view the map.
	    	<input type="button" onclick="return true;" value="OK" />
	    	<input type="button" onclick="return false;" value="Cancel" />
	    </div>
	</body>
</html>