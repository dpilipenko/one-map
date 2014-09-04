<!doctype html>
<html>
	<head>
		<meta name="layout" content="main" />
		<parameter name="view" value="admin" />
	</head>
	<body>
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
					<input type="button" id="show-conflicts" name="show-conflicts" value="Show Conflicts" />
					<div>- OR -</div>
					<input type="button" id="delete-zone" name="delete-zone" value="Delete" />
					<div id="show-conflicts-listing" class="hidden-content">
						<div class="conflict">
							<a href="#">User Name, Seat ID, Location, Floor</a>
							<input type="radio" name="PrimaryZone-UserID001" checked />
							<label for="PrimaryZone001-UserID001">AHA</label>
							<input type="radio" name="PrimaryZone-UserID001" />
							<label for="PrimaryZone001-UserID001">Kraft</label>
						</div>
						<div class="conflict">
							<a href="#">User Name2, Seat ID2, Location2, Floor2</a>
							<input type="radio" name="PrimaryZone-UserID002" checked />
							<label for="PrimaryZone001-UserID002">AHA</label>
							<input type="radio" name="PrimaryZone-UserID002" />
							<label for="PrimaryZone001-UserID002">Grange</label>
						</div>

					</div>
					<input type="button" id="zones-submit" value="Save" />
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
					<input type="button" id="seats-submit" value="Save" />
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
					<div id="query-form" class="hidden-content">
						<select name="reports-location-select" id="reports-location-select">
							<option value="all">All</option>					
							<option value="001">Cleveland</option>
							<option value="002">Princeton</option>
							<option value="003">New York</option>
						</select>
						<select name="floor-select" id="floor-select" class="occupancy-field hidden-content">
							<option value="all">All</option>					
							<option value="001">11</option>
							<option value="002">12</option>
							<option value="003">13</option>
						</select>
						<select name="time-range-select" id="time-range-select" class="moves-field hidden-content">
							<option value="all">Select One</option>					
							<option value="001">1 week</option>
							<option value="002">1 month</option>
							<option value="003">2 months</option>
						</select>
						<input type="button" id="reports-submit" value="Submit" />
					</div>
		

					<div class="divider vertical"></div>
					<div id="report-display" class="hidden-content">
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
	</body>
</html>