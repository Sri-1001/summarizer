<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE HTML>
    <html lang="en">
    <head>
    <meta charset="utf-8">
    <title>Search Summarizer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel='stylesheet' id='prettyphoto-css'  href="css/prettyPhoto.css" type='text/css' media='all'>
    <link href="css/fontello.css" type="text/css" rel="stylesheet">
    <!--[if lt IE 7]>
            <link href="css/fontello-ie7.css" type="text/css" rel="stylesheet">  
        <![endif]-->
    <!-- Google Web fonts -->
    <link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Patua+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <style>
    body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
    }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery.js"></script>
    <!-- Load ScrollTo -->
    <script type="text/javascript" src="js/jquery.scrollTo-1.4.2-min.js"></script>
    <!-- Load LocalScroll -->
    <script type="text/javascript" src="js/jquery.localscroll-1.2.7-min.js"></script>
    <!-- prettyPhoto Initialization -->
    <script type="text/javascript" charset="utf-8">
          $(document).ready(function(){
            $("a[rel^='prettyPhoto']").prettyPhoto();
          });
        </script>
    </head>
    <body>
    <!--******************** NAVBAR ********************-->
    <div class="navbar-wrapper">
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
            <h1 class="brand"><a href="#top">Search Summarizer</a></h1>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <nav class="pull-right nav-collapse collapse">
              <ul id="menu-main" class="nav">
                <li><a title="portfolio" href="index.html">Home</a></li>
                <li><a title="services" href="device.html">Device Search</a></li>
                <li><a title="team" href="#team">Team</a></li>
                <li><a title="contact" href="#contact">Contact</a></li>
              </ul>
            </nav>
          </div>
          <!-- /.container -->
        </div>
        <!-- /.navbar-inner -->
      </div>
      <!-- /.navbar -->
    </div>
    <!-- /.navbar-wrapper -->
    <div id="top"></div>
    <!-- ******************** HeaderWrap ********************-->
    <div id="headerwrap">
      <header class="clearfix">
        <h1><span>Search Summarizer</span><br> We make your search  beautiful.</h1>
        <div class="container">
          <div class="row">
            <div class="span12"><form action="SearchHandler" method="get" class="cform-form">
            <input type="text" name="query" placeholder="Search Keyword" class="cform-text" size="40" title="your email">
              <input type="submit" value="Summary" class="cform-submit"></form>
            </div>
          </div>
          <div class="row">
            <div class="span12">
              <ul class="icon">
                <li><a href="#" target="_blank"><i class="icon-pinterest-circled"></i></a></li>
                <li><a href="#" target="_blank"><i class="icon-facebook-circled"></i></a></li>
                <li><a href="#" target="_blank"><i class="icon-twitter-circled"></i></a></li>
                <li><a href="#" target="_blank"><i class="icon-gplus-circled"></i></a></li>
                <li><a href="#" target="_blank"><i class="icon-skype-circled"></i></a></li>
              </ul>
            </div>
          </div>
        </div>
      </header>
    </div>
    
    
    <hr>
    <!--******************** Services Section ********************-->
    
    <hr>
    <!--******************** Team Section ********************-->
    <section id="team" class="single-page scrollblock">
      <div class="container">
       
        <h1>Summary</h1>
        <!-- Five columns -->
        
        <!-- /.row -->
       
           
            <div class="featurette">
              <p><%String name = request.getParameter( "summary" );
                    %>
				<%= name%></p>
					<div id="google_translate_element"></div>
	
	<script type="text/javascript">
		function googleTranslateElementInit() {
			new google.translate.TranslateElement(
				{pageLanguage: 'en'},
				'google_translate_element'
			);
		}
	</script>
	
	<script type="text/javascript" src=
"https://translate.google.com/translate_a/element.js?
		cb=googleTranslateElementInit">
	</script>
            
            <!-- /.featurette -->
            <hr class="featurette-divider">
          </div>
          <!-- .span10 -->
        </div>
        <!-- /.row -->
     
      <!-- /.container -->
    </section>
    <!--******************** Contact Section ********************-->
    
    <hr>
    <div class="footer-wrapper">
      <div class="container">
        <footer>
          <small>&copy; 2013 Inbetwin Network. All rights reserved.</small>
        </footer>
      </div>
      <!-- ./container -->
    </div>
    <!-- Loading the javaScript at the end of the page -->
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="js/site.js"></script>
    
    <!--ANALYTICS CODE-->
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-29231762-1']);
	  _gaq.push(['_setDomainName', 'dzyngiri.com']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
    </body>
    </html>
