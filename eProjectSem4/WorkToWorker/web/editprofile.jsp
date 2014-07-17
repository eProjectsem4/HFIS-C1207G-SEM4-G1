<%-- 
    Document   : editprofile
    Created on : Jun 24, 2014, 10:47:42 AM
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
<html>

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

        <link href="css/style.css" rel="stylesheet">


        <link href="css/code.jquery.com_ui_1.10.4_themes_smoothness_jquery-ui.css" rel="stylesheet" type="text/css"/>


        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <script src="js/jquery-1.7.2.min.js"></script>
       <script src="js/code.jquery.com_ui_1.10.4_jquery-ui.js" type="text/javascript"></script>

        <script src="js/tinymce.cachefly.net_4.1_tinymce.min.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/additional-methods.js"></script>
        <script>
            $(function() {
                var tooltips = $("[title]").tooltip();
            });
        </script>

        <script>

            $(document).ready(function() {

                $('#edit-profile').validate({
                    rules: {
                        fullname: {
                            required: true,
                            minlength: 0,
                            maxlength: 50
                        },
                        phone: {
                            number: true,
                            max: 20
                        },
                        company: {
                            maxlength: 20
                        },
                        address: {
                            maxlength: 50,
                        },
                        country: {
                            maxlength: 20,
                        },
                        experience: {
                            maxlength: 100
                        },
                        skills: {
                            maxlength: 100
                        },
                        money:{
                            number: true,
                            min: 10,
                            max: 10000,
                        }

                    }
                });


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
                                    <i class="icon-user"></i> 
                                    <s:if test="%{#session.accLog != null}">
                                        <s:property value="#session.accLog.getFullname()"/>
                                    </s:if>
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="Profile?id=${sessionScope.accLog.id}">Profile</a></li>
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
                        <li><a href="credit.jsp"><i class="icon-comment"></i><span>Credit Card</span> </a> </li>
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
                                    <h3>Your Profile Details</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">



                                    <div class="tabbable">

                                        <!--<div class="tab-content">-->
                                        <div class="tab-pane active" id="formcontrols">
                                            <form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" method="POST" action="updateProfileAction">
                                                <fieldset>

                                                    <div class="control-group" >

                                                        <div class="controls" id="msg-errors">

                                                            <s:iterator value="messEdit">
                                                                <div class="alert alert-success">     

                                                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                                    <s:property/>
                                                                </div>
                                                            </s:iterator>
                                                        </div> <!-- /control-group -->
                                                    </div>

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectname">Your Name</label>
                                                        <div class="controls">
                                                            <input id="fullname" type="text" class="span6" name="fullname" value="<s:property value="accLog.fullname"/>"  rel="tooltip" title="Please provide your name "/>
                                                        </div> <!-- /controls -->
                                                    </div> <!-- /control-group -->

                                                    <div class="control-group">
                                                        <label class="control-label" for="skill">Phone : </label>

                                                        <div class="controls">																
                                                            <input id="phone" name="phone" class="span6" value="<s:property value="accLog.phone"/>" rel="tooltip" title="Please provide phone number "/>
                                                        </div>
                                                    </div>

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectdetails">Company : </label>
                                                        <div class="controls" >
                                                            <input name="company" class="span6" id="company" value="<s:property value="accLog.company"/>" rel="tooltip" title="Please provide your Company "/> 
                                                        </div> <!-- /controls -->
                                                    </div>

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectdetails">Address : </label>
                                                        <div class="controls" >
                                                            <input name="address" class="span6" id="address" value="<s:property value="accLog.addresss"/>" rel="tooltip" title="Please provide your address "/> 
                                                        </div> <!-- /controls -->
                                                    </div>

                                                    <div class="control-group">
                                                        <label class="control-label" for="projectdetails">Country : </label>
                                                        <div class="controls">
                                                            <input name="country" class="span6" id="country" value="<s:property value="accLog.country"/>" rel="tooltip" title="Please provide your country "/> 
                                                        </div> <!-- /controls -->
                                                    </div>

                                                    <s:if test="%{#session.accLog.role == 'Worker'}">
                                                        <div class="control-group">
                                                            <label class="control-label" for="projectdetails">Experience : </label>
                                                            <div class="controls">
                                                                <input name="experience" class="span6" id="experience" value="<s:property value="accLog.experience"/>" rel="tooltip" title="Please provide your experience "/> 
                                                            </div> <!-- /controls -->
                                                        </div>

                                                        <div class="control-group">
                                                            <label class="control-label" for="projectdetails">Skills : </label>
                                                            <div class="controls">
                                                                <input name="skills" class="span6" id="skills" value="<s:property value="accLog.skills"/>" rel="tooltip" title="Please provide your skills "/> 
                                                            </div> <!-- /controls -->
                                                        </div>

                                                        <div class="control-group">
                                                            <label class="control-label" for="project-price">Price : </label>
                                                            <div class="controls">
                                                                <div class="btn-group">
                                                                    <div class="input-prepend input-append">
                                                                        <span class="add-on">$</span>
                                                                        <input class="span2" id="money" name="money" value="<s:property value="accLog.money"/>" type="text" rel="tooltip" title="Please provide your price"/>
                                                                    </div>

                                                                </div>
                                                            </div>	<!-- /controls -->
                                                        </div> 
                                                    </s:if>


                                                    <div class="form-actions">
                                                        <input type="submit" class="btn btn-primary btn-large" value="Update Profile" />
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
