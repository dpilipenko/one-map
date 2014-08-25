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



<div class="md-modal md-effect-1" id="modal-1">
  <a class="md-close close"></a>
  <div class="md-content">
  
  </div>
</div>

<div class="md-overlay"></div>

<sec:ifLoggedIn>
   <div class="main">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
  <div class="main">
</sec:ifNotLoggedIn>
  

  	<div class="fractals"></div>

	   <div class="header login"><!-- add login here -->

  		<div class="header-bg">

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
              <g:link controller="logout">LOGOUT</g:link>
            </div>

            <!-- if admin -->
            
              <div class="create-zone">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">CREATE NEW ZONE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
                <div class="generic-popup centered notch-top" id="create-zone-popup">
                      <div class="inner">
                        Select a floor first
                        <div class="notch"></div>
                      </div>
                </div>
              </div>
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
                <a class="skip-login">Skip Login</a>
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

          </div>

      </div>

  	</div><!-- end header -->

  	<div id="results" class="cleared collapsed">
  		<div class="results-header">
  			<span>RESULTS</span>
  			<a href="#" class="collapse-results">
  				<div></div>
  			</a>
  			<div class="divider"></div>
  			<a href="#" class="clear-results"></a>
  		</div>
  		<div id="result-list" class="results-list">
		
  		</div>
  	</div>

    <div class="info-panel login">
      <a class="info-link">i</a>
      <div class="info-title">Info</div>
      <div class="info-content">
        <div class="info-shadow"></div>
          <strong>Support</strong>
          <div class="support">
            Please direct any questions, issues, or feature requests to:
            <a href="mailto:onemapsupport@rosetta.com">onemapsupport@rosetta.com</a>
          </div>
          <strong>Legend</strong>
          <div class="legend">
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div class="desk-pin">= Seat or Office</div></td>
                <td><div class="zone-pin">= Zone Indicator</div></td>
              </tr>
              <tr>
                <td><div class="room-pin">= Room</div></td>
                <td><div class="zone-vacant"><div></div>= Vacant Zoned Seat</div></td>
              </tr>
              <tr>
                <td><div class="warroom-pin">= Warroom</div></td>
                <td><div class="zone-occupied"><div></div>= Occupied Zone Seat</div></td>
              </tr>
            </table>
          </div>
          <strong>Tips</strong>
          <div class="tips">
            <ul>
              <li><span>To find a vacant, unzoned seat, search for "Free Zone", then look for desks/offices that are outlined (not filled) on the map</span></li>
            </ul>
          </div>
      </div>
    </div>

    <div id="offices-container">
      <div id="offices-slider">
        <div id="offices" class="ms-wrapper ms-effect-3">

          <div id="san-luis-obispo" class="office"></div>
          <div id="los-angeles" class="office"></div>
          <div id="san-fransico" class="office"></div>
          <div id="chicago" class="office"></div>
          <div id="cleveland" class="office ms-perspective">
            
            <div id="floor-plans" class="ms-device">
              <div class="ms-screens">
                <div class="ms-screen-1 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="eleven" class="canvas" data-imgsrc="floorplan-eleven" data-floor="11"></div>
                  <div class="ms-label">Floor 11</div>
                </div>
                <div class="ms-screen-2 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="twelve" class="canvas" data-imgsrc="floorplan-twelve" data-floor="12"></div>
                  <div class="ms-label">Floor 12</div>
                </div>
                <div class="ms-screen-3 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="thirteen" class="canvas" data-imgsrc="floorplan-thirteen" data-floor="13"></div>
                  <div class="ms-label">Floor 13</div>
                </div>
                <div class="ms-screen-4 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="fourteen" class="canvas" data-imgsrc="floorplan-fourteen" data-floor="14"></div>
                  <div class="ms-label">Floor 14</div>
                </div>
                <div class="ms-screen-5 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="fifteen" class="canvas" data-imgsrc="floorplan-fifteen" data-floor="15"></div>
                  <div class="ms-label">Floor 15</div>
                </div>
                <div class="ms-screen-6 floorplan" data-showing="false">
                  <div class="corner-results">                    
                    <div class="warrooms"></div>
                    <div class="rooms"></div>
                    <div class="users"></div>
                    <div class="zones"></div>
                  </div>
                	<div id="seventeen" class="canvas" data-imgsrc="floorplan-seventeen" data-floor="17"></div>
                  <div class="ms-label">Floor 17</div>
                </div>
              </div>
            </div>

          </div>
          <div id="new-york-hudson" class="office"></div>
          <div id="new-york-fifth" class="office"></div>
          <div id="princeton" class="office"></div>
          <div id="london" class="office"></div>

        </div>
        <div id="offices-slider-control">
          <div id="falloff-shadow"></div>

          <div class="inner">
            <a href="#" class="arrow-left"></a>
            <span>Cleveland</span>
            <a href="#" class="arrow-right"></a>
          </div>

        </div>
        
      </div>

      <div class="zoom-btns">
        <a href="#" id="plus" class="zoom"></a>
        <a href="#" id="minus" class="zoom disabled"></a>
        <a href="#" id="backto3d">3D</a>
      </div>

      <!-- <div id="popup" class="desk">
        <a href="#" class="close"></a>

        <div class="inner">
          <div class="name">Dave Fagan</div>
          <div class="position">Senior Associate, Creative Engineer</div>
          <div class="phone">216.325.6080</div>
          <div class="email"><a href="mailto:dave.fagan@rosetta.com">dave.fagan@rosetta.com</a></div>
          <div class="btns-container clearfix">
            <a href="mailto:dave.fagan@rosetta.com?Subject=ONEMAP Seat Request&Body=Hey Bro, can I have your seat?" class="btn">Request Seat</a>
            <a href="#" class="btn">View Members</a>
          </div>

          <div id="members-slider">
              
              <div class="members" id="WARmembers">
                <div class="member">
                  <div class="name">Dave Fagan</div>
                  <div class="position">Senior Associate, Creative Engineer</div>
                  <div class="phone">216.325.6080</div>
                  <a class="email" href="mailto:dave.fagan@rosetta.com">dave.fagan@rosetta.com</a>
                </div>
                <div class="member">
                  <div class="name">Brad Fagan</div>
                  <div class="position">Senior Associate, Creative Engineer</div>
                  <div class="phone">216.325.6080</div>
                  <a class="email" href="mailto:dave.fagan@rosetta.com">dave.fagan@rosetta.com</a>
                </div>
              </div>

              <div id="slider-direction-nav"></div>

            </div>
        </div>
        <div class="notch"></div>
      </div>
 -->
    </div> <!-- end offices container -->


  </div><!-- end main -->

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.2.min.js"></script>
	<script src="${resource(dir: 'js', file: 'main.js')}"></script>

  <script src="${resource(dir: 'js', file: 'lean-slider.js')}"></script>
	
	<script type="text/javascript">
		OneMap.login.submitURL = '${postUrl}';
	</script>

  <script type="text/javascript">
    <sec:ifLoggedIn>
      OneMap.isLoggedIn = true;
      OneMap.login.init();
    </sec:ifLoggedIn>
  </script>

</body>
</html>