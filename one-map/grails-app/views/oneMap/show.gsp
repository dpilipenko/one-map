<%@ page import="com.rosetta.onemap.User" %>
<!doctype html public>
<!--[if lt IE 7]> <html lang="en-us" class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html lang="en-us" class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html lang="en-us" class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-us"> <!--<![endif]-->
<head>
  <meta charset="utf-8">

  <title>ONEMAP - Login</title>

  <meta name="viewport" content="width=device-width">

  <link href="${resource(dir: 'css', file: 'main.css')}" rel="stylesheet">

</head>

<body>

<sec:ifLoggedIn>
   <div class="main">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
  <div class="main login">
</sec:ifNotLoggedIn>
  

  	<div class="fractals"></div>

	   <div class="header">

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
              <g:link controller="logout">LOGOUT</g:link>
            </div>

					<sec:ifLoggedIn>
						
	  					<g:set var="userObject" value="${User.findByUsername(sec.loggedInUserInfo(field:'username'))}"/>
	  					<div class="welcome">Hey ${userObject.firstName}&nbsp;&nbsp;&nbsp;&nbsp;|</div>
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

  	<div id="results" class="collapsed">
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
              <a href="#" class="arrow-left"></a>
              <div class="members">
                <div class="member">
                  <div class="name">Dave Fagan</div>
                  <div class="position">Senior Associate, Creative Engineer</div>
                  <div class="phone">216.325.6080</div>
                  <a class="email" href="mailto:dave.fagan@rosetta.com">dave.fagan@rosetta.com</a>
                </div>
              </div>
              <a href="#" class="arrow-right"></a>
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
    <script src="${resource(dir: 'js', file: 'classie.js')}"></script>
    <script src="${resource(dir: 'js', file: 'modernizr.custom.js')}"></script>
	<script src="${resource(dir: 'js', file: 'main.js')}"></script>

  <script src="${resource(dir: 'js', file: 'lean-slider.js')}"></script>
	
	<script type="text/javascript">
		RosettaMap.login.submitURL = '${postUrl}';
	</script>

</body>
</html>
