<%-- 
    Document   : details
    Created on : Jun 23, 2014, 1:54:51 PM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Project"%>
<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    Account accLog = (Account) request.getSession().getAttribute("accLog");
    Project project = (Project) request.getSession().getAttribute("project");
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

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
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

                        <div class="widget widget-nopad">
                            <div class="widget-header">
                                <i class="icon-list-alt"></i>
                                <h3> <s:property value="project.name"/></h3>
                            </div>
                            <!-- /widget-header -->
                            <div class="widget-content">
                                <div class="widget big-stats-container">
                                    <div class="widget-content">

                                        <div id="big_stats" class="cf">
                                            <div class="stat"><i class=""> Price</i> <span class="value"><s:property value="project.price"/></span> </div>
                                            <!-- .stat -->
                                            <div class="stat"><i class=""> Start Date</i> <span class="value"><s:property value="project.startDate"/></span> </div>
                                            <!-- .stat -->
                                            <div class="stat"><i class=""> Status</i> <span class="value"><s:property value="project.status"/></span> </div>
                                            <!-- .stat -->
                                            <%
                                                if (project.getIdWorker() != null) {
                                                    out.print("<div class='stat'><i> Worker Designated</i> <span class='value'><a href='Profile?id=" + project.getIdWorker().getId() + "'>" + project.getIdWorker().getFullname() + "</a></span> </div>");
                                                } else {
                                                    out.print("<div class='stat'><i> Worker Designated</i> <span class='value'>None</span></div>");
                                                }
                                            %>
                                            <!-- .stat -->

                                        </div>
                                        <h6 class="bigstats">&nbsp;</h6>
                                        <p class="title_stats">Project Description</p>
                                        <h6 class="bigstats"><s:property value="project.descriptions" escapeHtml="false"/></h6>
                                        <p class="title_stats">Skills Required</p>
                                        <h6 class="bigstats"><section style="color: blue"><s:property value="project.nameSkills"/></section></h6>


                                        <s:if test="%{!messError.isEmpty()}">
                                            <div class="control-group">
                                                <div class="controls">
                                                    <!-- Modal -->
                                                    <div id="myModal" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: block">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                            <h3 id="myModalLabel">Some Error , you need to know</h3>
                                                        </div>
                                                        <div class="modal-body">
                                                            <s:iterator value="messError">
                                                                <label style="color: red;">
                                                                    <p><s:property /></p>
                                                                </label>
                                                                <br>
                                                            </s:iterator>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button class="btn close" data-dismiss="modal" aria-hidden="true">Close</button>
                                                        </div>
                                                    </div>
                                                </div> <!-- /controls -->	
                                            </div>
                                        </s:if>

                                        <%
                                            if (accLog.getRole().equals("Worker") && project.getStatus().equals("Started")) {
                                                out.print("<p class='title_stats'>Action</p>");
                                                out.print("<h6 class='bigstats'>");
                                                out.print("<form action='orderAction'><input type='submit' value='Order'></form>");
                                            }
                                            if (accLog.getId() == project.getIdCustomer().getId() && !project.getStatus().equals("Done")) {
                                                out.print("<p class='title_stats'>Action</p>");
                                                out.print("<h6 class='bigstats'>");
                                                out.print("<form action='doneProjectAction'><input type='submit' value='Done'></form>");
                                            }
                                            if (accLog.getId() == project.getIdCustomer().getId() && project.getStatus().equals("Started")) {
                                                out.print("<form action='showOrderAction'><input type='submit' value='Show Order'></form>");
                                                out.print("</h6>");
                                            }
                                        %>


                                    </div>
                                    <!-- /widget-content -->

                                </div>
                                <!-- /widget-content -->

                            </div>
                        </div>
                    </div>

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



<script src="js/jquery-1.7.2.min.js"></script>

<script src="js/bootstrap.js"></script>
<script src="js/base.js"></script>
<script>

    $(document).ready(function() {
        $('.close').click(function() {
            $('#myModal').css('display', 'none');
        });
    })
</script>
</body>

</html>
