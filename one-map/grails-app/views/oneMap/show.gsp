<%@ page import="com.rosetta.onemap.User" %>
<!doctype html public>
<!--[if lt IE 7]> <html lang="en-us" class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html lang="en-us" class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html lang="en-us" class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-us"> <!--<![endif]-->
<head>
  <meta charset="utf-8">

  <title>ONEMAP</title>

  <meta name="viewport" content="width=device-width">

  <link href="${resource(dir: 'css', file: 'main.css')}" rel="stylesheet">

</head>

<body>



<div class="md-modal md-effect-1" id="modal-1">
  <div class="md-content">
    <h3>Modal Dialog</h3>
    <div>
      <p>This is a modal window. You can do the following things with it:</p>
      <ul>
        <li><strong>Read:</strong> modal windows will probably tell you something important so don't forget to read what they say.</li>
        <li><strong>Look:</strong> a modal window enjoys a certain kind of attention; just look at it and appreciate its presence.</li>
        <li><strong>Close:</strong> click on the button below to close the modal.</li>
      </ul>
      <a class="md-close close"></a>
    </div>
  </div>
</div>

<div class="md-overlay"></div>

<button class="md-trigger" data-modal="modal-1">Fade in &amp; Scale</button>

<sec:ifLoggedIn>
   <div class="main">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
  <div class="main">
</sec:ifNotLoggedIn>
  

  	<div class="fractals"></div>

	   <div class="header login">

  		<div class="header-bg">

          <div class="zone-panel">
               <div class="form">
                    <div class="fields">
                         <div class="messaging">Select the seats you'd like to group into a zone, then fill in the info below</div>
                         <input class="zone-name" type="text" placeholder="zone name">
                         <input type="text" class="zone-color" placeholder="color (e.g. #00bce4)">
                    </div>
                    <div class="actions">
                         <div class="number-selected"><span>2 seats</span> selected</div>
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

					  <sec:ifLoggedIn>
						
	  					<g:set var="userObject" value="${User.findByUsername(sec.loggedInUserInfo(field:'username'))}"/>
	  					<div class="welcome">Hey ${userObject.firstName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</div>
  					 </sec:ifLoggedIn>
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
                	<div id="eleven" class="canvas" data-imgsrc="floorplan-eleven" data-floor="11"></div>
                  <div class="ms-label">Floor 11</div>
                </div>
                <div class="ms-screen-2 floorplan" data-showing="false">
                	<div id="twelve" class="canvas" data-imgsrc="floorplan-twelve" data-floor="12"></div>
                  <div class="ms-label">Floor 12</div>
                </div>
                <div class="ms-screen-3 floorplan" data-showing="false">
                	<div id="thirteen" class="canvas" data-imgsrc="floorplan-thirteen" data-floor="13"></div>
                  <div class="ms-label">Floor 13</div>
                </div>
                <div class="ms-screen-4 floorplan" data-showing="false">
                	<div id="fourteen" class="canvas" data-imgsrc="floorplan-fourteen" data-floor="14"></div>
                  <div class="ms-label">Floor 14</div>
                </div>
                <div class="ms-screen-5 floorplan" data-showing="false">
                	<div id="fifteen" class="canvas" data-imgsrc="floorplan-fifteen" data-floor="15"></div>
                  <div class="ms-label">Floor 15</div>
                </div>
                <div class="ms-screen-6 floorplan" data-showing="false">
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
        <a href="#" id="minus" class="zoom"></a>
        <a href="#" id="backto3d">3D</a>
      </div>

      <div id="popup" class="desk">
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

    </div> <!-- end offices container -->


  </div><!-- end main -->

	<script type="text/javascript">
		var isLoggedIn = false;
		<sec:ifLoggedIn>
			isLoggedIn = true;
		</sec:ifLoggedIn>
	</script>

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.2.min.js"></script>
	<script src="${resource(dir: 'js', file: 'main.js')}"></script>

  <script src="${resource(dir: 'js', file: 'lean-slider.js')}"></script>
	
	<script type="text/javascript">
		Map.login.submitURL = '${postUrl}';
	</script>

  <script src="${resource(dir: 'js', file: 'classie.js')}"></script>
  <script src="${resource(dir: 'js', file: 'modalEffects.js')}"></script>

</body>
</html>