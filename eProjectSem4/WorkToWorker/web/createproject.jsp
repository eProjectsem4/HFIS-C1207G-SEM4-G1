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
                    $("#" + editor.id).valid();
                }
            });
            var count = 0;

            $(document).ready(function() {

                $('.category-work').click(function() {
                    var value = $(this).html();
                    $('.category-label-work').text(value);
                    $('#category-name-work').val(value);
                });

                $('.project-price').click(function() {
                    var value = $(this).html();
                    $('.project-price-label').text(value);
                    $('#project-price').val(value);
                });

//                $('#skills').focusout(function(event) {
//                    var arr = $('#skill').val().split(",");
//                    $.each(arr, function(i, val) {
//                        if ((availableTags.indexOf(arr[i]) <= -1)) {
//                            $("#msg-errors").text("<div class=\"alert alert-success\">Error !!! Some skill incorrect, please type again</div>");
//                            event.preventDefault();
//                        }
//                    });
//
//                });

                $('#fileUpload').change(function() {
                    var filepath = $('#fileUpload').val();
                    
                        // update the file-path text using case-insensitive regex
                        filepath = filepath.replace(/C:\\fakepath\\/i, '');
                    
                    $('#uploadFile').val(filepath);

                });

                $.validator.addMethod('filesize', function(value, element, param) {
                    // param = size (en bytes) 
                    // element = element to validate (<input>)
                    // value = value of the element (file name)
                    return this.optional(element) || (element.files[0].size <= param)
                });

                 var validator =  $('#edit-profile').submit(function() {
			// update underlying textarea before submit validation
			tinyMCE.triggerSave();
		}).validate({
                   ignore: "",
                    rules: {
                        name: {
                            required: true,
                            minlength: 10,
                            maxlength: 50
                        },
                        description: {
                            required: true,
                            minlength: 30,
                            maxlength: 500
                        },
                        skills: {
                            required: true,
                            minlength: 1,
                            maxlength: 50
                        },
                        price: {
                            required: true,
                            number: true,
                            min: 10,
                            max: 10000,

                        },
                        fileUpload: {
                            extension: "rar|zip",
                            filesize: "2097152"
                        }

                    },
                    messages: {
                        name: {
                            required: "Please enter a project name",
                            minlength: "Your project name must consist of at least 10 characters",
                            maxlength: "Your project name maximum 50 characters",
                        },
                        description: {
                            required: "Please enter a project details",
                            minlength: "Your project details must consist of at least 30 characters",
                            maxlength: "Your project details maximum 500 characters",
                        },
                        skills: {
                            required: "Please enter skills for your project ",
                            minlength: "Your project skills must consist of at least 1 skill",
                            maxlength: "Your project skill  maximum 50 characters",
                        },
                         price: {
                            required: "Please enter price for your project ",
                            min: "Your project must be at least $ 10",
                            max: "Your project is only the maximum $ 10,000",
                        },
                        fileUpload: {
                            extension: "File must be RAR or ZIP",
                            filesize: "File must less than 10MB"
                        }
                    }
                });

                validator.focusInvalid = function() {
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

        <script>
            var availableTags = [
                "ActionScript",
                "AppleScript",
                "Asp",
                "BASIC",
                "C",
                "C#",
                "C++",
                "Clojure",
                "COBOL",
                "ColdFusion",
                "Erlang",
                "Fortran",
                "Groovy",
                "Haskell",
                "Java",
                "JavaScript",
                "Lisp",
                "Perl",
                "PHP",
                "Python",
                "Ruby",
                "Scala",
                "Scheme",
                ".NET",
                "HTML",
                
            ];

            $(function() {

                function split(val) {
                    return val.split(/ \s*/);
                }
                function extractLast(term) {
                    return split(term).pop();
                }

                var userrequest = null;

                $("#skills")
                        // don't navigate away from the field on tab when selecting an item
                        .bind("keydown", function(event) {
                            srcO = $('#skills').val();
                            if (event.keyCode === $.ui.keyCode.TAB &&
                                    $(this).data("ui-autocomplete").menu.active) {
                                event.preventDefault();
                            }
                        })
                        .autocomplete({
                            minLength: 0,
                            source: function(request, response) {
                                // delegate back to autocomplete, but extract the last term
                                userrequest = request.term;
                                response($.ui.autocomplete.filter(
                                        availableTags, extractLast(request.term)));
                            },
                            focus: function() {
                                // prevent value inserted on focus
                                return false;
                            },
                            select: function(event, ui) {
                                var src = $('#skills').val();
                                var terms = split(this.value);
                                if ((src.indexOf(ui.item.value)) == -1) {
                                    // remove the current input
                                    terms.pop();
                                    // add the selected item
                                    terms.push(ui.item.value);
                                    // add placeholder to get the comma-and-space at the end
                                    this.value = terms;

                                    return false;
                                } else {
                                    $('#skill').val(src);
                                    return false;
                                }

                            }, response: function(event, ui) {
                                var src = $('#skills').val();
                                if (ui.content.length <= 0) {
                                    $('#skills').val(src.replace(extractLast(userrequest), ""));
                                }

                            }
                        });
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
                                            <form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" method="POST" action="createProjectAction">
                                                <fieldset>

                                                    <div class="control-group" >

                                                        <div class="controls" id="msg-errors">

                                                            <s:iterator value="messCreate">
                                                                <s:if test='<s:property/>.equals("Create success !")' >
                                                                    <div class="alert alert-info">
                                                                    </s:if>
                                                                    <s:else>
                                                                        <div class="alert alert-success">     
                                                                        </s:else>
                                                                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                                        <s:property/>
                                                                    </div>
                                                                </s:iterator>
                                                            </div> <!-- /control-group -->
                                                        </div>

                                                        <div class="control-group">
                                                            <label class="control-label" for="radiobtns">What work do you require ? </label>

                                                            <div class="controls">
                                                                <div class="btn-group">
                                                                    <a class="btn span6 category-label-work" rel="tooltip" title="Please provide your Category of Work"> Select Category of Work ( Option ) </a>
                                                                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                                                                    <ul class="dropdown-menu span6">
                                                                        <li><a class="category-work"> Website </a></li>
                                                                        <li><a class="category-work"> Software</a></li>
                                                                        <li><a class="category-work"> Mobile</a></li>
                                                                        <li><a class="category-work"> Design</a></li>
                                                                        <li><a class="category-work"> Data Entry</a></li>
                                                                        <li><a class="category-work"> Other ( Default )</a></li>
                                                                        <!--<li class="divider"></li>
                                                                        <li><a href="#"><i class="i"></i> Make admin</a></li>-->
                                                                    </ul>
                                                                </div>
                                                            </div>	<!-- /controls -->
                                                            <input type="hidden" name="category" id="category-name-work" value="Other" rel="tooltip" />
                                                        </div> <!-- /control-group -->

                                                        <div class="control-group">
                                                            <label class="control-label" for="projectname">Project Name</label>
                                                            <div class="controls">
                                                                <input type="text" class="span6" id="name" name="name" placeholder="What do you need done" rel="tooltip" title="Please provide your project name ">

                                                            </div> <!-- /controls -->
                                                        </div> <!-- /control-group -->

                                                        <div class="control-group">
                                                            <label class="control-label" for="skill">Some skills that relate to the project : </label>

                                                            <div class="controls">																
                                                                <input type="text" class="span6" id="skills" name="skills" placeholder="Start typing to see list " rel="tooltip" title="Please provide skill of project ">
                                                            </div>
                                                        </div>

                                                        <div class="control-group">
                                                            <label class="control-label" for="projectdetails">Describe your project in detail</label>
                                                            <div class="controls" title="Please provide detail of project ">
                                                                <textarea class="span10" id="description" name="description"  rel="tooltip"></textarea>
                                                            </div> <!-- /controls -->
                                                        </div>
                                                    <!--file upload-->
                                                        <div class="control-group">
                                                            <label class="control-label" for="uploadFile">Attach File </label>

                                                            <div class="controls">
                                                                <input id="uploadFile" placeholder="Choose File" disabled="disabled" />

                                                                <div class="fileUpload btn btn-primary btn-large">
                                                                    <span>Upload</span>
                                                                    <input id="fileUpload" type="file" name="fileUpload" class="upload" />
                                                                </div>
                                                            </div>	
                                                        </div>  

                                                        <div class="control-group">
                                                            <label class="control-label" for="project-price">What your project price? </label>

                                                            <div class="controls">
                                                                <div class="btn-group">
                                                                    <div class="input-prepend input-append">
                                                                        <span class="add-on">$</span>
                                                                        <input class="span2" id="price" name="price" type="text">
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
                                                                <p>By clicking 'Post Project Now', you are indicating that you have read and agree to the<a href="#"> Terms & Conditions</a> and <a href="#">Privacy Policy</a> 
                                                                    </br>
                                                                    <strong>Your project will be reviewed by staff .</strong></p>
                                                            </div>	
                                                        </div>


                                                        <div class="form-actions">
                                                            <input type="submit" class="btn btn-primary btn-large" value="Post Project Now" />
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
