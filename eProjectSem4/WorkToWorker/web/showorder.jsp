<%-- 
    Document   : showorder
    Created on : Jun 23, 2014, 5:48:55 PM
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
        <title>Work To Worker</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
              rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
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
                            <div class="widget widget-table action-table">
                                <div class="widget-header">
                                    <i class="icon-th-list"></i>
                                    <h3>Projects </h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <table class="table table-striped table-bordered">
                                        <thead>

                                            <tr>
                                                <th>Project Name </th>
                                                <th>Worker</th>
                                                <th>Bids</th>
                                                <th class="td-actions"> </th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <s:iterator value="listOrder">
                                                <tr>
                                                    <td><a href="Profile?id=<s:property value="idAccount.id"></s:property>"></a></td>
                                                    <td><s:property value="idAccount.fullname"></s:property></td>
                                                    <td><a href="agreeOrderAction?idAccount=<s:property value="idAccount.id"></s:property>">Agree</a></td>
                                                    <td class="td-actions"><a href="javascript:;" class="btn btn-small btn-success"><i class="btn-icon-only icon-ok"> </i></a><a href="javascript:;" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>
                                                    </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>

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
        <script language="javascript" type="text/javascript" src="js/full-calendar/fullcalendar.min.js"></script>

        <script src="js/base.js"></script>
        <script>

            var lineChartData = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        data: [65, 59, 90, 81, 56, 55, 40]
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        data: [28, 48, 40, 19, 96, 27, 100]
                    }
                ]

            }

            var myLine = new Chart(document.getElementById("area-chart").getContext("2d")).Line(lineChartData);


            var barChartData = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        data: [65, 59, 90, 81, 56, 55, 40]
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        data: [28, 48, 40, 19, 96, 27, 100]
                    }
                ]

            }

            $(document).ready(function() {
                var date = new Date();
                var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
                var calendar = $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    selectable: true,
                    selectHelper: true,
                    select: function(start, end, allDay) {
                        var title = prompt('Event Title:');
                        if (title) {
                            calendar.fullCalendar('renderEvent',
                                    {
                                        title: title,
                                        start: start,
                                        end: end,
                                        allDay: allDay
                                    },
                            true // make the event "stick"
                                    );
                        }
                        calendar.fullCalendar('unselect');
                    },
                    editable: true,
                    events: [
                        {
                            title: 'All Day Event',
                            start: new Date(y, m, 1)
                        },
                        {
                            title: 'Long Event',
                            start: new Date(y, m, d + 5),
                            end: new Date(y, m, d + 7)
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: new Date(y, m, d - 3, 16, 0),
                            allDay: false
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: new Date(y, m, d + 4, 16, 0),
                            allDay: false
                        },
                        {
                            title: 'Meeting',
                            start: new Date(y, m, d, 10, 30),
                            allDay: false
                        },
                        {
                            title: 'Lunch',
                            start: new Date(y, m, d, 12, 0),
                            end: new Date(y, m, d, 14, 0),
                            allDay: false
                        },
                        {
                            title: 'Birthday Party',
                            start: new Date(y, m, d + 1, 19, 0),
                            end: new Date(y, m, d + 1, 22, 30),
                            allDay: false
                        },
                        {
                            title: 'EGrappler.com',
                            start: new Date(y, m, 28),
                            end: new Date(y, m, 29),
                            url: 'http://EGrappler.com/'
                        }
                    ]
                });
            });
        </script><!-- /Calendar -->
    </body>
</<html>
