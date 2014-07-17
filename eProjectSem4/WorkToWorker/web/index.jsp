<%-- 
    Document   : index
    Created on : Jun 16, 2014, 12:10:08 PM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<% Account accLog = (Account) request.getSession().getAttribute("accLog");
    if (accLog != null) {
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <!--[if IE 7]><html lang="en" class="ie7"><![endif]-->
    <!--[if IE 8]><html lang="en" class="ie8"><![endif]-->
    <!--[if IE 9]><html lang="en" class="ie9"><![endif]-->
    <!--[if (gt IE 9)|!(IE)]><html lang="en"><![endif]-->
    <!--[if !IE]><html lang="en"><![endif]-->
    <head>
        <meta charset="utf-8">

        <title>Work To Worker</title>
<link rel="icon" type="image/png" href="img/WTW_logo.png">
        <meta name="description" content="A free single page responsive portfolio website html template for designers, developers and entrepreneurs.">
        <meta name="keywords" content="responsive, free, html, template">


        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <!-- Stylesheets -->

        <link rel="stylesheet" href="indexcss/styles/essentials.css">
        <link rel="stylesheet" href="indexcss/styles/font-awesome.min.css">
        <link rel="stylesheet" href="indexcss/styles/jquery.bxslider.css">

        <!-- Main stylesheet -->
        <link rel="stylesheet" href="indexcss/styles/style.css">
        <link rel="stylesheet" href="indexcss/styles/color.css">

        <!-- Favicon -->
        <link rel="shortcut icon" href="images/favicon.ico">

        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/pages/signin.css" rel="stylesheet" type="text/css">

        <!-- Javascript -->        
        <script src="js/code.jquery.com_jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/code.jquery.com_ui_1.10.4_jquery-ui.js" type="text/javascript"></script>
        
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <script src="js/additional-methods.js" type="text/javascript"></script>
        <script src="indexjs/js/jquery.fittext.js" type="text/javascript"></script>
        <script src="indexjs/js/jquery.bxslider.min.js" type="text/javascript"></script>
        <script src="indexjs/js/custom.js" type="text/javascript"></script>
        <script>
            $(document).ready(function() {
                $(function() {
                    var tooltips = $("[title]").tooltip();
                });

                $('.section-toggle').click(function() {
                    var $toggled = $(this).attr('href');
                    if (!$($toggled).is(":visible")) {
                        $($toggled).siblings(':visible').hide("slide", {direction: 'down'}, 750);
                        $($toggled).toggle("slide", {direction: 'up'}, 750);
                    }
//                    $('html, body').animate({
//                        scrollTop: $($toggled).offset().top - 500
//                    }, 
//                    2000);
                });

                $('#register-form').validate({
                    rules: {
                        fullName: {
                            required: true,
                            minlength: 10,
                            maxlength: 50
                        },
                        email: {
                            required: true,
                            email: true,
                        },
                        password: {
                            required: true,
                            minlength: 6,
                            maxlength: 20
                        },
                        repassword: {
                            required: true,
                            minlength: 6,
                            maxlength: 20,
                            equalTo: "#pass"
                        },
                        role:{
                            required: true,
                        }
                    },
                    messages: {
                        fullName: {
                            required: "Please enter your name",
                            minlength: "Your project name must consist of at least 10 characters",
                            maxlength: "Your project name maximum 50 characters",
                        },
                        email: {
                            required: "Please enter your email",
                        },
                        password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 6 characters long",
                            maxlength: "Your project details maximum 20 characters",
                        },
                        repassword: {
                            required: "Please provide re-password",
                            equalTo: "Please enter the same password as above"
                        },
                    }
                });

                $('#login-form').validate({
                    rules: {
                        email: {
                            required: true,
                            email: true,
                            maxlength: 100,
                        },
                        password: {
                            required: true,
                            minlength: 1,
                            maxlength: 50
                        },
                    },
                    messages: {
                        email: {
                            required: "Please enter your email",
                            minlength: "Your project details must consist of at least 30 characters",
                            maxlength: "Your project details maximum 100 characters",
                        },
                        password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long",
                            maxlength: "Your project details maximum 50 characters",
                        },
                    }
                });
            });
        </script>   
    </head>

    <body>

        <div id="preloader">
            <div id="status">&nbsp;</div>
        </div>
        <!-- Menu -->
        <div class="main-menu fixedmenu">
            <div class="menu-wrap">




                <h1><a href="#home" class="smoothscroll"></a></h1>



                <input type="checkbox" id="toggle" />
                <label for="toggle" class="toggle"></label>


                <!-- Menu -->
                <ul class="menu">
                    <li><a href="#about"  class="smoothscroll">About</a></li>
                        <s:if test="%{#session.accLog != null}">
                        <li><a href="#about"  class="smoothscroll">Home</a></li>
                        </s:if>
                    <li><a href="#login" id="login-btn" class="smoothscroll section-toggle">Login</a></li>
                    <li><a href="#signup" id="register-btn" class="smoothscroll section-toggle">Register</a></li>
                    <li><a href="#contact" class="">Contact</a></li>
                </ul>


            </div>
        </div>

        <section id="home" class="home-wrap">
            <div class="home-content text-white">


                <h1 class="bigtext letterspace uppercase bold no-margin">Work To Worker</h1>
                <p class="big margin-bottom">Seize The Opportunity</p>
            </div>
        </section>

        <!--  Background IMG -->
        <div style="background-image: url('images/studio.jpg');" class="fullscreen-img"></div>

        <!-- About  -->
        <section class="about">	
            <div id="login" class="row login">
                <div class="account-container">

                    <div class="content clearfix">

                        <form action="loginAction" method="post" id="login-form">

                            <h1>Member Login</h1>		

                            <div class="login-fields">

                                <p>Please provide your details</p>
                                
                                <s:if test="%{messLogin != null}">
                                    <p style="color: red"><s:property value="messLogin"/></p>
                                </s:if>

                                <div class="field">
                                    <label for="username">Email</label>
                                    <input type="text" id="email" name="email" placeholder="Username" class="login username-field" />
                                </div> <!-- /field -->

                                <div class="field">
                                    <label for="password">Password:</label>
                                    <input type="password" id="password" name="password" placeholder="Password" class="login password-field"/>
                                </div> <!-- /password -->

                            </div> <!-- /login-fields -->

                            <div class="login-actions">

                                <span class="login-checkbox">

                                </span>

                                <input type="submit" class="buttonLogin btn btn-success btn-large" value="Sign In"/>

                            </div> <!-- .actions -->



                        </form>

                    </div> <!-- /content -->

                </div> <!-- /account-container -->

            </div>

            <hr/>
            <div id="signup" class="row signup">
                <div class="account-container register">

                    <div class="content clearfix">

                        <form action="registerAction" method="post" id="register-form">

                            <h1>Sign Up for Free Account</h1>			

                            <div class="login-fields">

                                <p>Create your free account:</p>
                                
                                <s:iterator value="messRegister">
                                    <p style="color: red"><s:property/></p>
                                </s:iterator>

                                <div class="field">
                                    <label for="lastname">Full Name:</label>	
                                    <input type="text" id="fullName" name="fullName" value="" placeholder="Full Name" class="login" />
                                </div> <!-- /field -->


                                <div class="field">
                                    <label for="email">Email Address:</label>
                                    <input type="text" id="email" name="email" value="" placeholder="Email" class="login"/>
                                </div> <!-- /field -->

                                <div class="field">
                                    <label for="password">Password:</label>
                                    <input type="password" id="pass" name="password" value="" placeholder="Password" class="login"/>
                                </div> <!-- /field -->

                                <div class="field">
                                    <label for="confirm_password">Confirm Password:</label>
                                    <input type="password" id="repassword" name="repassword" value="" placeholder="Confirm Password" class="login"/>
                                </div> <!-- /field -->

                            </div> <!-- /login-fields -->

                            <div class="login-actions">

                                <span class="login-checkbox">
                                     <label class="choice" for="worker">Worker</label>
                                    <input id="worker" name="role" type="radio" class="field login-checkbox" value="Worker" />
                                   
                                     <label class="choice" for="customer">Customer</label>
                                    <input id="customer" name="role" type="radio" class="field login-checkbox" value="Customer" />
                                   
                                </span>



                            </div> <!-- .actions -->
                            <div class="login-actions">
                                <input type="submit" class="buttonLogin btn-success btn btn-primary btn-large" value="Register"/>

                            </div> <!-- .actions -->
                        </form>

                    </div> <!-- /content -->

                </div> <!-- /account-container -->
            </div>
        </section>


        <section id="quote" class="quote">
            <div class="header medium">
                <div class="header-center">
                    <div class="centerdiv text-white">
                        <ul id="quote-slider" class="bxslider">


                            <!-- Quote 1 -->
                            <li><h1 class="thin"><q>Opportunity is missed by most people because it is dressed in overalls and looks like work.</q></h1>
                                <hr>
                                <h2 class="italic">Thomas A. Edison</h2>
                            </li>


                            <!-- Quote 2 -->
                            <li>
                                <h1 class="thin"><q>It is the working man who is the happy man. It is the idle man who is the miserable man.</q></h1>
                                <hr>
                                <h2 class="italic">Benjamin Franklin</h2>
                            </li>


                            <!-- Quote 3 -->
                            <li>
                                <h1 class="thin"><q>Choose a job you love, and you will never have to work a day in your life.</q></h1>
                                <hr>
                                <h2 class="italic">Confucius</h2>
                            </li>


                        </ul>
                    </div>
                </div>
            </div>
            <a href="javascript:void(0)" id="quote-prev" class="bx-outer-prev"></a>
            <a href="javascript:void(0)" id="quote-next" class="bx-outer-next"></a>
        </section>


        <section class="contact">
            <div id="about" class="row"  >
                <div class="ten columns center">

                    <!-- The title -->
                    <div class="title">
                        <h1>About Us</h1>
                        <hr>
                    </div>


                    <p class="big thin">WtW.com is the freelancing, outsourcing, and crowdsourcing marketplace for small business. You can hire to do your contract work at a fraction of the cost. Whether you need you can outsource jobs within minutes. Browse through hundreds of skills . Are you an entrepreneur just starting a company? Find a quality graphic designer to create a logo to your specifications. Are you looking to grow your business online? Hire an internet marketer to improve traffic from SEO and Facebook. Don't have a website or mobile app? Not a problem, we have thousands of web developers waiting to hear from you. WtW.com accelerates your businesses growth by giving you the talent you need when you need it. Once you get new customers rolling in, you can even hire customer support or a virtual personal assistant to simplify your life without the risky overhead and cost of hiring full-time staff. From beginning to end, WtW.com makes it easy to hire worker and find jobs online. Join the thousands of businesses tapping into marketplace of skilled worker. Post a project now! </p>

                    <hr>

                </div>

            </div>
        </section>

        <!-- Footer -->
        <section id="social-footer">
            <div class="social-footer">
                <div class="row">
                    <div class="twelve columns">
                        <h3 class="text-white margin-bottom">Where you can find us</h3>
                        <p class="no-margin">
                            <a href="javascript:"><i class="fa fa-twitter icon big white fill"></i></a>
                            <a href="javascript:" target="_blank"><i class="fa fa-dribbble icon big white fill"></i></a>
                            <a href="javascript:" target="_blank"><i class="fa fa-linkedin icon big white fill"></i></a>
                            <a href="javascript:"><i class="fa fa-file-o icon big white fill"></i></a>
                        </p>
                    </div>
                </div>
            </div>	
        </section>



    </body>

</html>