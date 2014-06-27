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

  <div class="main">

  	<div class="fractals"></div>

<sec:ifLoggedIn>
  	<div class="header">
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
	<div class="header login">
</sec:ifNotLoggedIn>

  		<div class="header-bg">

  			<div class="inner">
  				
  				<div class="logo"></div>
				<div class="divider"></div>
				<div class="name">
					ONE MAP
				</div>

				<div class="utility">
					<input class="searchbar" type="text" placeholder="Search">
					<a href="#" class="logout">LOGOUT</a>
					<div class="welcome">Hey Dave&nbsp;&nbsp;&nbsp;&nbsp;|</div>
				</div>

				<div class="login-dot">
					<div class="inner">
						<div class="login-form">
							
							<div class="login-title">
								ONE MAP
							</div>
								
							<g:form role="form" name='loginForm' id='loginForm'>
			            		<div class="form-group text-center">
				           			<g:if test='${flash.message}'>
					           			<div class="alert alert-danger fade in">	    					
					    					${flash.message}
					  					</div>	                	
				              		</g:if>	
			            		</div>
				        		<div class="form-group">
				        			<input type="email" for="username" id="username" name="j_username" placeholder="Email">
				           		</div>
				             	<div class="form-group">
				              		<input type="password" class="form-control" id="password" name="j_password" placeholder="Password">
				            	</div>
				            	<g:submitToRemote url="[controller: 'login', action: 'auth']" class="submit-login" onSuccess="RosettaMap.login();" value="SUBMIT"/>
				          </g:form>
						</div>
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
  		<div class="results-list">
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  			<div class="result-item">
  				<div class="name">Dave Fagan</div>
  				<div class="position">Senior Associate, Creative Engineer</div>
  				<div class="location">Cleveland</div>
  			</div>
  		</div>
  	</div>

  </div>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.2.min.js"></script>
<script src="${resource(dir: 'js', file: 'main.js')}"></script>

</body>
</html>
