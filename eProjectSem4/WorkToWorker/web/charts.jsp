<%-- 
    Document   : charts
    Created on : Jul 8, 2014, 4:30:29 PM
    Author     : Admin
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
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
        <link rel="icon" type="image/png" href="img/WTW_logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="css/datepicker.css" rel="stylesheet" type="text/css"/>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
              rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/code.jquery.com_ui_1.10.4_jquery-ui.js" type="text/javascript"></script>
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script>
            $(document).ready(function() {
                $("#startdate").datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    dateFormat: "yy-mm-dd",
                    onClose: function(selectedDate) {
                        $("#enddate").datepicker("option", "minDate", selectedDate);
                    }
                });

                $("#enddate").datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    dateFormat: "yy-mm-dd",
                    onClose: function(selectedDate) {
                        $("#startdate").datepicker("option", "maxDate", selectedDate);
                    }
                });
            })
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
                        <li ><a href="home.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                        <li class="dropdown">
                            <a href="index.html" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i><span>My Project</span> </a>
                            <ul class="dropdown-menu">
                                <li><a href="createproject.jsp">Post Project</a></li>
                                <li><a href="allProjectAction">List Project</a></li>
                            </ul>
                        </li>
                        <li><a href="forumAction"><i class="icon-facetime-video"></i><span>Forums</span> </a></li>
                        <s:if test="%{#session.accLog.role == 'Admin'}">
                            <li class="active"><a href="charts.jsp"><i class="icon-bar-chart"></i><span>Reports</span> </a> </li>
                        </s:if>
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
                        <div class="span6">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-bar-chart"></i>
                                    <h3>Bar Chart</h3>
                                </div>
                                <!-- /widget-header -->
                                <form action="barchart" style="display: block">
                                    <input type="text" placeholder="StartDate" name="startdate" id="startdate" style="width: 110px;margin-left: 45px"/>
                                    <input type="text" placeholder="EndDate" name="enddate" id="enddate" style="width: 110px;"/>
                                    <input type="submit" class="btn btn-primary btn-large" value="Search" style="margin-bottom: 9px;"/>
                                    <div class="widget-content">
                                        <s:url action="chart" id="url"/>
                                        <img src="<s:property value="url"/>"/>
                                        <!-- /bar-chart -->
                                    </div>
                                </form>
                                <!-- /widget-content -->
                            </div>
                            <!-- /widget -->
                        </div>
                        <div class="span6">
                            <h3><s:property value="titleDate"/></h3>
                            <h4><s:property value="total"/></h4>
                            <h4><s:property value="mobileS"/><s:property value="webS"/></h4>
                            <h4><s:property value="softS"/><s:property value="deS"/></h4>
                            <h4><s:property value="daS"/><s:property value="otS"/></h4>
                            <p style="margin-top: 17px;"><s:property value="listTitle"/></p>
                            <table dir="ltr" width="500" border="1" 
                                   summary="purpose/structure for speech output">
                                <colgroup width="50%" />
                                <colgroup id="colgroup" class="colgroup" align="center" 
                                          valign="middle" title="title" width="1*" 
                                          span="2" style="background:#ddd;" />
                                <thead>
                                    <tr>
                                        <th scope="col"><s:property value="colum1"/></th>
                                        <th scope="col"><s:property value="colum2"/></th>
                                        <th scope="col"><s:property value="colum3"/></th>
                                        <th scope="col"><s:property value="colum4"/></th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <s:iterator value="listProject">
                                        <tr>
                                            <td><s:property value="name"/></td>
                                            <td><s:property value="category"/></td>
                                            <td><s:property value="price"/></td>
                                            <td><s:property value="status"/></td>
                                        </tr>
                                    </s:iterator>
                                </tfoot>
                            </table>
                        </div>          
                    </div>
                    <!-- /span6 -->
                    <a  href="${pageContext.request.contextPath}/exportEmployeeDetails.action" class="btn btn-primary btn-large" style="float: right">Export File</a>
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
                        <div class="span12">
                            &copy; 2013 <a href="http://www.egrappler.com/">Bootstrap Responsive Admin Template</a>.
                        </div>
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
        <script src="js/chart.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>
</body>
</html>
