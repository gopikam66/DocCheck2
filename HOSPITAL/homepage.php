<!DOCTYPE HTML>
<html>
<head><meta name="viewport" content="width=device-width"/>
<title>DocCheck</title>
<!-- STYLES & JQUERY 
================================================== -->
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="css/slider.css"/>
<link rel="stylesheet" type="text/css" href="css/skinblue.css"/><!-- change skin color -->
<link rel="stylesheet" type="text/css" href="css/responsive.css"/>
<script src="js/jquery-1.9.0.min.js"></script><!-- the rest of the scripts at the bottom of the document -->
</head>
<body>
<!-- TOP LOGO & MENU
================================================== -->
<div class="grid">
	<div class="row space-bot">
		<!--Logo-->
		<div class="c4">
			<a href="index.php">
			<h2>DocCheck</h2>
			</a>
		</div>
		<!--Menu-->
		<div class="c8">
			<nav id="topNav">
			<ul id="responsivemenu">
				<li class="active"><a href="homepage.php"><i class="icon-home homeicon"></i><span class="showmobile">Home</span></a></li>
                
                <li><a href="#">View</a>
				<ul style="display: none;">
				<li><a href="check doctor.php">Doctors</a></li>
                <li><a href="viewpatient.php">Patients</a></li>
                     <li><a href="viewbooking.php">Bookings</a></li>
                     <li><a href="deptview.php">Departments</a></li>
                     </ul>
				</li>
                     
				<li><a href="#">Add</a>
				<ul style="display: none;">
				<li><a href="add_dept.php">Deptments</a></li>
					    </ul>
				</li>
                    
					 <li><a href="history.php">History</a></li>
				
								
				<li class="last"><a href="logout.php">LogOut</a></li>
				
			</ul>
			</nav>
		</div>
	</div>
</div>
<div class="undermenuarea">
	<div class="boxedshadow">
	</div>
	<!-- SLIDER AREA
	================================================== -->
	<div id="da-slider" class="da-slider">
		<!--Slide 1-->
		<div class="da-slide">
			<h2>Management system</h2>
			<p>
			"Give your hands to Serve and your hearts to Love.."<br>            -Mother Teresa<br>
            
			</p>
			<a href="#" class="da-link" style="width:202px;">OUR MOTTO</a>
			<div class="da-img">
				<img src="images/app1.jpg" alt="">
			</div>
		</div>
		<!--Slide 2-->
		<div class="da-slide">
			<h2>Services</h2>
			<p>
				We Provide Our Best Services.Just To Make Our Customers Happy!
			</p>
			<a href="#" class="da-link" style="width:192px;">Service</a>
			<div class="da-img">
				<img src="images/app3.jpg" alt="">
			</div>
		</div>
		<!--Slide 3-->
		<div class="da-slide">
			<h2> Feel the Comfort </h2>
			<p>
				 We Can't Compromise The Quality Of Our Service!
			</p>
			<a href="#" class="da-link" style="width:224px;">fully supported</a>
			<div class="da-img">
				<img src="images/app2.png" alt="">
			</div>
		</div>
		<nav class="da-arrows">
		<span class="da-arrows-prev"></span>
		<span class="da-arrows-next"></span>
		</nav>
	</div>
</div>
<!-- UNDER SLIDER - BLACK AREA
================================================== -->
<div class="undersliderblack">
	<div class="grid">
		<div class="row space-bot">
			<div class="c12">
				<!--Box 1-->
				<div class="c4 introbox introboxfirst">
					<div class="introboxinner">
						<span class="homeicone">
						<i class="icon-bolt"></i>
						</span> Step In To Our World and Feel Ecstasy
					</div>
				</div>
				<!--Box 2-->
				<div class="c4 introbox introboxmiddle">
					<div class="introboxinner">
						<span class="homeicone">
						<i class="icon-cog"></i>
						</span> Feel The Best Service
					</div>
				</div>
				<!--Box 3-->
				<div class="c4 introbox introboxlast">
					<div class="introboxinner">
						<span class="homeicone">
						<i class="icon-lightbulb"></i>
						</span>
						We Guarentees Complete Satisfaction To Customer
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="shadowunderslider">
</div>
<!-- START content area
================================================== -->
<div class="grid">
	<div class="row space-bot">
		
	</div>
</div><!-- end grid -->

<!-- FOOTER
================================================== -->
<div id="wrapfooter">
	<div class="grid">
		<div class="row" id="footer">
			<!-- to top button  -->
			<p class="back-top floatright">
				<a href="#top"><span></span></a>
			</p>
			<!-- 1st column -->
			<div class="c3">
				<h3>Management Team</h3>
			</div>
			<!-- 2nd column -->
			<div class="c3">
				<h2 class="title"><i class="icon-twitter"></i> Follow us</h2>
				<hr class="footerstress">
				<div id="ticker" class="query">
				</div>
			</div>
			<!-- 3rd column -->
			<div class="c3">
				<h2 class="title"><i class="icon-envelope-alt"></i> Contact</h2>
				<hr class="footerstress">
				<dl>
					  <dd><span>DocCheck</dd>
					<dd><span>Telephone:</span>+91 271 9483</dd>
					<dd>E-mail: <a href="more.html">DocCheckgaas@gmail.com</a></dd>
				</dl>
			
			</div>
			<!-- 4th column -->
			
			<!-- end 4th column -->
		</div>
	</div>
</div>
<!-- copyright area -->
<div class="copyright">
	<div class="grid">
		<div class="row">
			<div class="c6">
				 DocCheck &copy; 2018. All Rights Reserved.
			</div>
			<div class="c6">
				<span class="right">
				A Step To future By Techies </span>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT AREA -->
<!-- JAVASCRIPTS
================================================== -->
<!-- all -->
<script src="js/modernizr-latest.js"></script>

<!-- menu & scroll to top -->
<script src="js/common.js"></script>

<!-- slider -->
<script src="js/jquery.cslider.js"></script>

<!-- cycle -->
<script src="js/jquery.cycle.js"></script>

<!-- carousel items -->
<script src="js/jquery.carouFredSel-6.0.3-packed.js"></script>

<!-- twitter -->
<script src="js/jquery.tweet.js"></script>

<!-- Call Showcase - change 4 from min:4 and max:4 to the number of items you want visible -->
<script type="text/javascript">
$(window).load(function(){			
			$('#recent-projects').carouFredSel({
				responsive: true,
				width: '100%',
				auto: true,
				circular	: true,
				infinite	: false,
				prev : {
					button		: "#car_prev",
					key			: "left",
						},
				next : {
					button		: "#car_next",
					key			: "right",
							},
				swipe: {
					onMouse: true,
					onTouch: true
					},
				scroll : 2000,
				items: {
					visible: {
						min: 4,
						max: 4
					}
				}
			});
		});	
</script>

<!-- Call opacity on hover images from carousel-->
<script type="text/javascript">
$(document).ready(function(){
    $("img.imgOpa").hover(function() {
      $(this).stop().animate({opacity: "0.6"}, 'slow');
    },
    function() {
      $(this).stop().animate({opacity: "1.0"}, 'slow');
    });
  });
</script>
</body>
</html>