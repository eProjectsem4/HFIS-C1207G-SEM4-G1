<%-- 
    Document   : home
    Created on : Jun 18, 2014, 11:12:56 AM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Account accLog = (Account) request.getSession().getAttribute("accLog");
    if (accLog == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Work to Worker</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">    

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">


        <link href="css/pages/plans.css" rel="stylesheet"> 

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                    </a><a class="brand" href="home.jsp">Work To Worker </a>
                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="icon-cog"></i>Account<b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:;">Settings</a></li>
                                    <li><a href="javascript:;">Help</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="icon-user"></i> 
                                    <s:if test="%{#session.accLog != null}">
                                        <s:property value="#session.accLog.getFullname()"/>
                                    </s:if>
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:;">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                        <form class="navbar-search pull-right">
                            <input type="text" class="search-query" placeholder="Search">
                        </form>
                    </div>
                    <!--/.nav-collapse -->
                </div>
                <!-- /container -->
            </div>
            <!-- /navbar-inner -->
        </div>
        <!-- /navbar -->
        <div class="subnavbar">
            <div class="subnavbar-inner">
                <div class="container">
                    <ul class="mainnav">
                        <li class="active"><a href="home.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                        <li class="dropdown">
                            <a href="index.html" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i><span>My Project</span> </a>
                            <ul class="dropdown-menu">
                                <li><a href="createproject.jsp">Post Project</a></li>
                                <li><a href="allProjectAction">List Project</a></li>
                            </ul>
                        </li>
                        <li><a href="forumAction"><i class="icon-facetime-video"></i><span>Forums</span> </a></li>
                        <li><a href="#"><i class="icon-bar-chart"></i><span>Reports</span> </a> </li>
                        <li><a href="#"><i class="icon-comment"></i><span>Help</span> </a> </li>
                    </ul>
                </div>
                <!-- /container -->
            </div>
            <!-- /subnavbar-inner -->
        </div>
        <!-- /subnavbar -->

        <div class="main">

            <div class="main-inner">

                <div class="container">

                    <div class="row">

                        <div class="span12">

                            <div class="widget">

                                <div class="widget-header">
                                    <i class="icon-th-large"></i>
                                    <h3>Choose Your Plan</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">
                                    <%
                                        if (accLog.getRole().equals("Customer")) {
                                    %>
                                    <div class="pricing-plans plans-3">

                                        <div class="plan-container">
                                            <div class="plan">
                                                <div class="plan-header">

                                                    <div class="plan-title">
                                                        All Project	        		
                                                    </div> <!-- /plan-title -->

                                                    <!--                                                    <div class="plan-price">
                                                                                                            $0<span class="term">For Life</span>
                                                                                                        </div>  /plan-price -->

                                                </div> <!-- /plan-header -->	        

                                                <div class="plan-features">
                                                    <ul>
                                                        <li>Contains all projects in the system</li>
                                                        <li>Easy to view </li>
                                                        <li>Search what you need</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="allProjectAction" class="btn">Enter</a>				
                                                </div> <!-- /plan-actions -->

                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->

                                        <div class="plan-container">
                                            <div class="plan green">
                                                <div class="plan-header">

                                                    <div class="plan-title">
                                                        My Project	        		
                                                    </div> <!-- /plan-title -->

                                                    <!--                                                    <div class="plan-price">
                                                                                                            $5<span class="term">Per Agent</span>
                                                                                                        </div>  /plan-price -->

                                                </div> <!-- /plan-header -->	          

                                                <div class="plan-features">
                                                    <ul>					
                                                        <li>Store all your projects</li>
                                                        <li>Easy to view </li>
                                                        <li>Your project management</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="myProjectAction" class="btn">Enter</a>				
                                                </div> <!-- /plan-actions -->

                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->

                                        <div class="plan-container">
                                            <div class="plan">
                                                <div class="plan-header">

                                                    <div class="plan-title">
                                                        Create New Project	        		
                                                    </div> <!-- /plan-title -->

                                                    <!--                                                    <div class="plan-price">
                                                                                                            $30<span class="term">Per Month</span>
                                                                                                        </div>  /plan-price -->

                                                </div> <!-- /plan-header -->	       

                                                <div class="plan-features">
                                                    <ul>
                                                        <li>Create your project</li>
                                                        <li>Looking for people to work for you</li>
                                                        <li>Universal inbox and cases</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="createproject.jsp" class="btn">Enter</a>				
                                                </div> <!-- /plan-actions -->

                                            </div> <!-- /plan -->

                                        </div> <!-- /plan-container -->                                    

                                    </div> <!-- /pricing-plans -->
                                    <%
                                        }
                                        if (accLog.getRole().equals("Worker")) {
                                    %>
                                    <div class="pricing-plans plans-2">
                                        <div class="plan-container">
                                            <div class="plan">
                                                <div class="plan-header">

                                                    <div class="plan-title">
                                                        All Project	        		
                                                    </div> <!-- /plan-title -->

                                                    <!--                                                    <div class="plan-price">
                                                                                                            $0<span class="term">For Life</span>
                                                                                                        </div>  /plan-price -->

                                                </div> <!-- /plan-header -->	        

                                                <div class="plan-features">
                                                    <ul>
                                                        <li>Contains all projects in the system</li>
                                                        <li>Easy to view </li>
                                                        <li>Search what you need</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="allProjectAction" class="btn">Enter</a>				
                                                </div> <!-- /plan-actions -->

                                            </div> <!-- /plan -->
                                        </div>
                                    </div>
<div class="pricing-plans plans-2">
                                    <div class="plan-container">
                                        <div class="plan">
                                            <div class="plan-header">

                                                <div class="plan-title">
                                                    My Order	        		
                                                </div> <!-- /plan-title -->

                                                <!--                                                    <div class="plan-price">
                                                                                                        $0<span class="term">For Life</span>
                                                                                                    </div>  /plan-price -->

                                            </div> <!-- /plan-header -->	        

                                            <div class="plan-features">
                                                <ul>
                                                    <li>Contains all my order </li>
                                                    <li>Easy to view </li>
                                                    <li>Search what you need</li>
                                                </ul>
                                            </div> <!-- /plan-features -->

                                            <div class="plan-actions">				
                                                <a href="myOrderAction" class="btn">Enter</a>				
                                            </div> <!-- /plan-actions -->

                                        </div> <!-- /plan -->
                                    </div>
     </div>
                                    <%
                                        }
                                    %>
                                </div> <!-- /widget-content -->

                            </div> <!-- /widget -->					

                        </div> <!-- /span12 -->     	


                    </div> <!-- /row -->

                </div> <!-- /container -->

            </div> <!-- /main-inner -->

        </div> <!-- /main -->



        <div class="extra">

            <div class="extra-inner">

                <div class="container">

                    <div class="row">
                        <div class="span3">
                            <h4>
                                About Free Admin Template</h4>
                            <ul>
                                <li><a href="javascript:;">EGrappler.com</a></li>
                                <li><a href="javascript:;">Web Development Resources</a></li>
                                <li><a href="javascript:;">Responsive HTML5 Portfolio Templates</a></li>
                                <li><a href="javascript:;">Free Resources and Scripts</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                        <div class="span3">
                            <h4>
                                Support</h4>
                            <ul>
                                <li><a href="javascript:;">Frequently Asked Questions</a></li>
                                <li><a href="javascript:;">Ask a Question</a></li>
                                <li><a href="javascript:;">Video Tutorial</a></li>
                                <li><a href="javascript:;">Feedback</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                        <div class="span3">
                            <h4>
                                Something Legal</h4>
                            <ul>
                                <li><a href="javascript:;">Read License</a></li>
                                <li><a href="javascript:;">Terms of Use</a></li>
                                <li><a href="javascript:;">Privacy Policy</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                        <div class="span3">
                            <h4>
                                Open Source jQuery Plugins</h4>
                            <ul>
                                <li><a href="http://www.egrappler.com">Open Source jQuery Plugins</a></li>
                                <li><a href="http://www.egrappler.com;">HTML5 Responsive Tempaltes</a></li>
                                <li><a href="http://www.egrappler.com;">Free Contact Form Plugin</a></li>
                                <li><a href="http://www.egrappler.com;">Flat UI PSD</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                    </div> <!-- /row -->

                </div> <!-- /container -->

            </div> <!-- /extra-inner -->

        </div> <!-- /extra -->




        <div class="footer">

            <div class="footer-inner">

                <div class="container">

                    <div class="row">

                        <div class="span12">
                            &copy; 2013 <a href="http://www.egrappler.com/">Bootstrap Responsive Admin Template</a>.
                        </div> <!-- /span12 -->

                    </div> <!-- /row -->

                </div> <!-- /container -->

            </div> <!-- /footer-inner -->

        </div> <!-- /footer -->


        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.7.2.min.js"></script>

        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>

    </body>

</html>
