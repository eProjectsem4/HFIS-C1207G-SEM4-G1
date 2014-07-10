
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

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">


        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">


        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

        <script src="http://tinymce.cachefly.net/4.1/tinymce.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/additional-methods.js"></script>
        <script>
            $(function() {
                var tooltips = $("[title]").tooltip();
            });
        </script>

        <script>
            tinyMCE.init({
                mode: "textareas",
                menubar: false,
                extended_valid_elements: "span[!class]",
                valid_styles: {'*': 'text-decoration'},
                // update validation status on change
                onchange_callback: function(editor) {
                    tinyMCE.triggerSave();
                    $("#content").valid();
                }
            });
            
            $.validator.methods.equal = function(value, element, param) {
		return value == param;
            };

            $(document).ready(function() {

                $.validator.addMethod('filesize', function(value, element, param) {
                    // param = size (en bytes) 
                    // element = element to validate (<input>)
                    // value = value of the element (file name)
                    return this.optional(element) || (element.files[0].size <= param)
                });

                var vali = $('#edit-profile').submit(function() {
			// update underlying textarea before submit validation
			tinyMCE.triggerSave();
		}).validate({
                    rules: {
                        ignore: "",
                        title: {
                            required: true,
                            minlength: 15,
                            maxlength: 100
                        },
                        content: {
                            required: true,
                            minlength: 30,
                            maxlength: 1000
                        },
                        math:{
                            equal: 11
                        }

                    },
                    messages:{
                        math: { 
                           equal: "Please enter the correct result!"
                       }
                    },
			errorPlacement: function(label, element) {
				// position error label after generated textarea
				if (element.is("textarea")) {
					label.insertAfter(element.next());
				} else {
					label.insertAfter(element)
				}
			}
                });

                vali.focusInvalid = function() {
			// put focus on tinymce on submit validation
			if( this.settings.focusInvalid ) {
				try {
					var toFocus = $(this.findLastActive() || this.errorList.length && this.errorList[0].element || []);
					if (toFocus.is("textarea")) {
						tinyMCE.get(toFocus.attr("id")).focus();
					} else {
						toFocus.filter(":visible").focus();
					}
				} catch(e) {
					// ignore IE throwing errors when focusing hidden elements
				}
			}
		}

            });
        </script>
        <style>
            #edit-profile label.error {
                margin-top : 9px;
                color : red;
                width: auto;
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

                            <div class="widget ">

                                <div class="widget-header">
                                    <i class="icon-user"></i>
                                    <h3>Create Topic</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">



                                    <div class="tabbable">

                                        <!--<div class="tab-content">-->
                                        <div class="tab-pane active" id="formcontrols">
                                            <form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" method="POST" action="createTopicAction">
                                                <fieldset>

                                                    <div class="control-group" >

                                                        <div class="controls" id="msg-errors">

                                                            <s:iterator value="messCreate">
                                                                <div class="alert alert-success">     
                                                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                                    <s:property/>
                                                                </div>
                                                            </s:iterator>
                                                        </div> <!-- /control-group -->
                                                    </div>

                                                    

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectname">Topic Title</label>
                                                        <div class="controls">
                                                            <input type="text" class="span6" id="title" name="title"  rel="tooltip" title="Please provide topic title ">

                                                        </div> <!-- /controls -->
                                                    </div> <!-- /control-group -->

                                                    

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectdetails">Content</label>
                                                        <div class="controls" rel="tooltip" title="Please provide content ">
                                                            <textarea class="span10" id="content" name="content"></textarea>
                                                        </div> <!-- /controls -->
                                                    </div>
                                                    <!--file upload-->
                                                     

                                                    <div class="control-group">
                                                        <label class="control-label" for="project-price">Please enter the correct result! </label>

                                                        <div class="controls">
                                                            <div class="btn-group">
                                                                <div class="input-prepend input-append" >
                                                                    <span class="add-on">7 + 4</span>
                                                                    <input class="span2" id="math" name="math" type="text" rel="tooltip" title="Please enter the correct result!">
                                                                </div>
                                                                <!--                                                                    <input type="text"  id="project-price" value="200" rel="tooltip" />-->
                                                            </div>
                                                        </div>	<!-- /controls -->
                                                    </div> <!-- /control-group -->

                                                    <!--   <div class="control-group">
                                                          <label class="control-label" for="radiobtns">Button sizes</label>
             
                                                          <div class="controls">
                                                              <a class="btn btn-large" href="#"><i class="icon-star"></i> Star</a>
                                                              <a class="btn btn-small" href="#"><i class="icon-star"></i> Star</a>
                                                              <a class="btn btn-mini" href="#"><i class="icon-star"></i> Star</a>
                                                          </div>	<!-- /controls
                                                      </div>--> <!-- /control-group -->
                                                    <div class="control-group">

                                                        <div class="controls">
                                                            <p>By clicking 'Post Topic', you are indicating that you have read and agree to the<a href="#"> Terms & Conditions</a> and <a href="#">Privacy Policy</a> 
                                                                </br>
                                                                <strong>Your Topic will be reviewed by staff .</strong></p>
                                                        </div>	
                                                    </div>


                                                    <div class="form-actions">
                                                        <input type="submit" class="btn btn-primary btn-large" value="Post Topic Now" />
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
                        <div class="span3">
                            <h4>
                                About Free Admin Template
                            </h4>
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
                                Support
                            </h4>
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
                                Something Legal
                            </h4>
                            <ul>
                                <li><a href="javascript:;">Read License</a></li>
                                <li><a href="javascript:;">Terms of Use</a></li>
                                <li><a href="javascript:;">Privacy Policy</a></li>
                            </ul>
                        </div>
                        <!-- /span3 -->
                        <div class="span3">
                            <h4>
                                Open Source jQuery Plugins
                            </h4>
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



        <script src="js/bootstrap.js"></script>

        <script src="js/base.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/less.js/1.7.1/less.js" type="text/javascript"></script>
    </body>

</html>
