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
<%
    Account accLog = (Account) request.getSession().getAttribute("accLog");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Work To Worker</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
              rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/pages/dashboard.css" rel="stylesheet">
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
                                    <i class="icon-user"></i> <% if (accLog != null) {
                                            out.print(accLog.getFullname());
                                        }%> <b class="caret"></b>
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
                        <li><a href="#"><i class="icon-facetime-video"></i><span>Find Worker</span> </a></li>
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
                            <div class="widget widget-table action-table">
                                <div class="widget-header">
                                    <i class="icon-th-list"></i>
                                    <h3>Projects </h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <form class="navbar-search pull-right" action="searchProjectAction">
                                        <input type="text" placeholder="Search Project" name="keyword" style="font-weight: bold">
                                    </form>
                                    
                                    <table id="project-table" class="table table-striped table-bordered">

                                        <tbody>

                                            <display:table id="projectTable" name="listProject" pagesize="10" requestURI="" class="table table-striped table-bordered">
                                            <td><display:column property="name" title="Project Name" sortable="true"/></td>
                                            <td><display:column property="category" title="Category" sortable="true"/></td>
                                            <td><display:column property="price" title="Price" sortable="true"/></td> 
                                            <td><display:column property="startDate" title="Start Date" format="{0,date,dd-MM-yyyy}" sortable="true"/></td> q
                                            <td><display:column property="endDate" title="End Date" format="{0,date,dd-MM-yyyy}" sortable="true"/></td> 
                                            <td><display:column property="status" title="Status" sortable="true"/></td>
                                            <c:set var="id" value="${projectTable.id}"/>
                                            <display:column class="td-actions" ><a href="detailsAction?id=${id}"  class="btn btn-success"><i class="btn-icon-only"> View Detail </i></a></display:column>
                                           

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

                                        </tbody>
                                    </table>

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
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/excanvas.min.js"></script>
        <script src="js/chart.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/full-calendar/fullcalendar.min.js"></script>
        <script src="js/base.js"></script>
    </body>
</html>
