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
				<h2>Export / Import</h2>
				<h3>Export</h3>
				<a href="admin/seatchart">Download CSV file</a>
				</div>
				<h3>Import</h3>
				<g:uploadForm name="import" controller="admin" action="seatChartImport">
					<label for="import">Upload .CSV file</label>
					<input type="file" id="import" name="import" />
					<input type="submit" />
				</g:uploadForm>
				<div>
			</div>
	    </div> <!-- end content container -->
	    <div class="tabs-navigation">
	    	<div id="falloff-shadow"></div>
	    	<ul>
	    		<li><a href="#" class="active"><span class="icon zones"></span><span class="admin-zone-text">Export / Import</span></a></li>
	    	</ul>
	    </div>
	    <div id="navigating-away" class="modal">
	    	You will be leaving this page now to view the map.
	    	<input type="button" onclick="return true;" value="OK" />
	    	<input type="button" onclick="return false;" value="Cancel" />
	    </div>
	</body>
</html>