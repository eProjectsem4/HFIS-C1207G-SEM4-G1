<%-- 
    Document   : createproject
    Created on : Jun 16, 2014, 12:37:23 PM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<% Account accLog = (Account) request.getSession().getAttribute("accLog");
    if (accLog == null || !accLog.getRole().equals("Customer")) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Work to Worker</title>
<link rel="icon" type="image/png" href="img/WTW_logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/datepicker.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet">

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/code.jquery.com_ui_1.10.4_jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/additional-methods.js"></script>
        <script>
            $(function() {
                var tooltips = $("[title]").tooltip();
            });
        </script>

        <script>

            $(document).ready(function() {


                var validator = $('#edit-profile').validate({
                    rules: {
                        creaditCard: {
                            required: true,
                            creditcard: true
                        },
                        money: {
                            required: true,
                            number: true,
                            min: 10,
                            max: 10000,
                        }

                    },
                    messages: {
                        creaditCard: {
                            required: "Please enter your creadit card number",
                            creditcard: "Please enter your creadit card number",
                        },
                        money: {
                            required: "Please enter money for your account ",
                            min: "Your project must be at least $ 10",
                            max: "Your project is only the maximum $ 10,000",
                        }
                    }
                });

            });
        </script>

        <style>
            #edit-profile label.error {
                margin-top : 9px;
                color : red;
                max-width: 200px;
                display: inline;
            }

            #edit-profile label.error {
                display: none;

            }

            .fileUpload > label {
                color: white !important;
            }


        </style>
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
                        <li><a href="home.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                        <li class="dropdown active">
                            <a href="index.html" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i><span>My Project</span> </a>
                            <ul class="dropdown-menu">
                                <li><a href="createproject.jsp">Post Project</a></li>
                                <li><a href="allProjectAction">List Project</a></li>
                            </ul>
                        </li>
                        <li><a href="forumAction"><i class="icon-facetime-video"></i><span>Forums</span> </a></li>
                        <li><a href="barchart"><i class="icon-bar-chart"></i><span>Reports</span> </a> </li>
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

                            <div class="widget ">

                                <div class="widget-header">
                                    <i class="icon-user"></i>
                                    <h3>Your project details</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">



                                    <div class="tabbable">
                                        <!--<ul class="nav nav-tabs">
                                            <li>
                                                <a href="#formcontrols" data-toggle="tab">Form Controls</a>
                                            </li>
                                            <li class="active"><a href="#jscontrols" data-toggle="tab">JS Controls</a></li>
                                        </ul>
          
                                        <br>-->

                                        <!--<div class="tab-content">-->
                                        <div class="tab-pane active" id="formcontrols">
                                            <form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" method="POST" action="recharge">
                                                <fieldset>

                                                    <div class="control-group" >

                                                        <div class="controls" id="msg-errors">

                                                            <s:iterator value="mess">
                                                                <div class="alert alert-success">     
                                                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                                    <s:property/>
                                                                </div>
                                                            </s:iterator>
                                                        </div> <!-- /control-group -->
                                                    </div>

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectname">Your Credit Card</label>
                                                        <div class="controls">
                                                            <input type="text" class="span6" id="creaditCard" name="creaditCard" placeholder="Credit Card Number" rel="tooltip" title="Please provide your project name ">

                                                        </div> <!-- /controls -->
                                                    </div> <!-- /control-group -->
 

                                                    <div class="control-group">
                                                        <label class="control-label" for="project-price">What your money? </label>

                                                        <div class="controls">
                                                            <div class="btn-group">
                                                                <div class="input-prepend input-append">
                                                                    <span class="add-on">$</span>
                                                                    <input class="span2" id="money" name="money" type="text">
                                                                </div>
                                                                <!--                                                                    <input type="text"  id="project-price" value="200" rel="tooltip" />-->
                                                            </div>
                                                        </div>	<!-- /controls -->
                                                    </div> <!-- /control-group -->


                                                    <div class="form-actions">
                                                        <input type="submit" class="btn btn-primary btn-large" value="Confirm" />
                                                    </div> <!-- /form-actions -->
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>

                                </div> <!-- /widget-content -->

                            </div> <!-- /widget -->

                        </div> <!-- /span8 -->

                    </div> <!-- /row -->

                </div> <!-- /container -->

            </div> <!-- /main-inner -->

        </div> <!-- /main -->




        <div class="extra">

            <div class="extra-inner">

                <div class="container">

                    <div class="row">
                        <div class="row">
                        <div class="span3">
                            <h4>
                                About Work To Worker</h4>
                            <ul>
                                <li><a href="javascript:;">Web Development Application</a></li>
                                <li><a href="javascript:;">Responsive Web </a></li>
                                <li><a href="javascript:;">Project</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                        <div class="span3">
                            <h4>
                                 Legal</h4>
                            <ul>
                                <li><a href="javascript:;">Read License</a></li>
                                <li><a href="javascript:;">Terms of Use</a></li>
                                <li><a href="javascript:;">Privacy Policy</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
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



        <script src="js/bootstrap.js"></script>

        <script src="js/base.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/less.js/1.7.1/less.js" type="text/javascript"></script>
    </body>

</html>
