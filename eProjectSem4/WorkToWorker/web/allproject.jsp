<%-- 
    Document   : allproject
    Created on : Jun 16, 2014, 12:19:59 PM
    Author     : Khanh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="wtw.entities.Account"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Work To Worker</title>
        <link rel="icon" type="image/png" href="img/WTW_logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
              rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/pages/dashboard.css" rel="stylesheet">
        <link href="css/datepicker.css" rel="stylesheet" type="text/css"/>
        
        <script src="js/code.jquery.com_jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/code.jquery.com_ui_1.11.0_jquery-ui.js" type="text/javascript"></script>

       <script src="js/jquery.validate.js"></script>
        <script src="js/additional-methods.js"></script>
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
              <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->
        <script>
            $(document).ready(function(){
                $('#searchForm').validate({
                    rule:{
                        price: {
                            number: true,
                        }
                    }
                })
            })
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
                        <li><a href="home.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                        <li class="dropdown active" >
                            <a href="index.html" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i><span>My Project</span> </a>
                            <ul class="dropdown-menu">
                                <li><a href="createproject.jsp">Post Project</a></li>
                                <li><a href="allProjectAction">List Project</a></li>
                            </ul>
                        </li>
                        <li><a href="forumAction"><i class="icon-facetime-video"></i><span>Forums</span> </a></li>
                        <li><a href="charts.jsp"><i class="icon-bar-chart"></i><span>Reports</span> </a> </li>
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
                            <div class="widget widget-table action-table">
                                <div class="widget-header">
                                    <i class="icon-th-list"></i>
                                    <h3>Projects </h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content" style="padding-left: 5px;">
                                    <form id="searchForm" class="navbar-search pull-left" action="searchProjectAction" method="POST">
                                        <input class="span4" type="text" placeholder="Search Project" name="name" id="name" style="font-weight: bold"/>
                                        <input class="span4" type="text" placeholder="Project Price" name="price" id="price" style="font-weight: bold"/>
                                        <input class="span5" type="text" placeholder="Type to filter project by skills" id="skills" name="skills" style="font-weight: bold"/>
                                        <select name="category" class="span3 btn-large">
                                            <option value="" selected disabled="">-Select Category-</option>
                                            <option value="Website">Website</option>
                                            <option value="Software">Software</option>
                                            <option value="Mobile">Mobile</option>
                                            <option value="Design">Design</option>
                                            <option value="Data Entry">Data Entry</option>
                                            <option value="Other">Other</option>
                                        </select>
                                        <input type="submit" class="btn btn-warning btn-large" value="Search"/>
                                    </form>
                                </div>
                                <div class="widget-content">




                                    <display:table id="projectTable" name="listProject" pagesize="10"  requestURI="" defaultsort="4" defaultorder="descending" class="table table-striped table-bordered" >
                                        <td><display:column title="Project Name" sortable="true" sortName="name" ><a href="detailsAction?id=${projectTable.id}">${projectTable.name}</a></display:column></td>
                                        <td><display:column property="category" title="Category" sortable="true" /></td>
                                        <td><display:column title="Price" sortable="true" >${projectTable.price}$</display:column></td> 
                                        <td><display:column property="startDate" title="Start Date" format="{0,date,dd-MM-yyyy}" sortable="true" /></td> q
                                        <td><display:column property="endDate" title="End Date" format="{0,date,dd-MM-yyyy}" sortable="true" /></td> 
                                        <td><display:column property="status" title="Status" sortable="true" /></td>
                                        


                                        <display:setProperty name="paging.banner.no_items_found" value='<span class="pagebanner">No {0} found.</span>' />
                                        <display:setProperty name="paging.banner.one_item_found" value='<span class="pagebanner">One {0} found.</span>' />
                                        <display:setProperty name="paging.banner.all_items_found" value='<span class="pagebanner">{0} {1} found, displaying all {2}.</span>' />
                                        <display:setProperty name="paging.banner.some_items_found" value='<span class="pagebanner">{0} {1} found, displaying {2} to {3}.</span>' />                                            

                                        <display:setProperty name="paging.banner.first" value='<div class="pagination"><ul><li class="prev" disabled><a href="{1}" class="next" title="first">First</a></li><li class="prev"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li><a href="{3}" class="next" title="next">Next</a></li><li class="next"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>'/>
                                        <display:setProperty name="paging.banner.last" value='<div class="pagination"><ul><li class="prev"><a href="{1}" class="next" title="first">First</a></li><li class="prev"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li class="next disabled"><a href="{3}" class="next" title="next">Next</a></li><li class="next disabled"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>'/>
                                        <display:setProperty name="paging.banner.full" value='<div class="pagination"><ul><li class="prev disabled"><a href="{1}" class="next" title="first">First</a></li><li class="prev disabled"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li><a href="{3}" class="next" title="next">Next</a></li><li class="next"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>' />
                                        <display:setProperty name="paging.banner.onepage" value='<div class="pagination"><ul>{0}</ul></div>' />                               

                                        <display:setProperty name="paging.banner.page.selected" value='<a href="{1}" class="page gradient">{0}</a>'/>
                                        <display:setProperty name="paging.banner.page.link" value='<a href="{1}" class="page gradient">{0}</a>'/>
                                        <display:setProperty name="paging.banner.page.separator" value="" />
                                    </display:table>



                                    <!--                                    <div class="pagination">
                                                                            <ul>
                                                                                <li class="prev"><a href="{1}" class="next" title="first"><img src="static/images/first.png" alt="first" /></a></li>
                                                                                <li class="prev"><a href="{2}" class="next" title="previous"><img src="static/images/previous.png" alt="previous" /></a>
                                                                                </li>{0}<li><a href="{3}" class="next" title="next"><img src="static/images/next.png" alt="next" /></a></li>
                                                                                <li class="next"><a href="{4}" class="next" title="lest"><img src="static/images/last.png" alt="last" /></a></li>
                                                                            </ul>
                                                                        </div>-->
                                </div>
                                <!-- /widget-content -->
                            </div>
                            <!-- /widget -->
                        </div>
                        <!-- /span6 -->
                    </div>


                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /main-inner -->
        </div>
        <!-- /main -->
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
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /extra-inner -->
        </div>
        <!-- /extra -->
        <div class="footer">
            <div class="footer-inner">
                <div class="container">
                    <div class="row">
                        <div class="span12"> &copy; 2013 <a href="http://www.egrappler.com/">Bootstrap Responsive Admin Template</a>. </div>
                        <!-- /span12 -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /footer-inner -->
        </div>
        <!-- /footer -->
        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        
        <script src="js/excanvas.min.js"></script>
       
        <script src="js/full-calendar/fullcalendar.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>
    </body>
</html>
