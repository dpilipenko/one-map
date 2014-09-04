<!doctype html>
<html>
  <head>
    <meta name="layout" content="main" />
  </head>

  <body>
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
</body>
</html>