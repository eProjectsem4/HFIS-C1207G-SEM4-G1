<%-- 
    Document   : profile
    Created on : Jun 24, 2014, 7:12:48 AM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Worker to Worker</title>
<link rel="icon" type="image/png" href="img/WTW_logo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">    

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">


        <link href="js/guidely/guidely.css" rel="stylesheet"> 

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>



    <body>

        <style>
            .profile-title{
                font-weight: bold;
                font-size:  15px;
            }
            
            .money-title{
                border: 1px solid;
                padding: 5px;
                text-align: center;
                font-size:  20px;
            }
        </style>

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


                        <div class="span3">
                            <img src="img/WTW_logo.png" alt="Avatar" style="width: 100%"/>
                            <div class="money-title"><span>Rate</span> : <s:property value="accPro.money"/>$/Hour</div>
                        </div>


                        <div class="span4">
                            <h1><s:property value="accPro.fullname"/></h1>
                            </br>
                            <p><span class="profile-title">Email</span> : <s:property value="accPro.email"/></p>
                            <p><span class="profile-title">Role</span> : <s:property value="accPro.role"/></p>
                            <p><span class="profile-title">Company</span> : <s:property value="accPro.company"></s:property></p>
                            <p><span class="profile-title">Address</span> : <s:property value="accPro.addresss"></s:property></p>
                            <p><span class="profile-title">Country</span> : <s:property value="accPro.country"></s:property></p>
                            <p><span class="profile-title">Phone</span> : <s:property value="accPro.phone"/></p>
                            <p><span class="profile-title">Experience</span> : <s:property value="accPro.experience"/> Years</p>
                            <p><span class="profile-title">Skills</span> : <s:property value="accPro.skills"/></p>

                            <s:if test="%{#session.accLog.getId() == #session.accPro.getId()}">
                                <form action='editProfileAction'><input type='submit' value='Edit Profile'></form>
                            </s:if>
                        </div>

                        <div class="span5">
                            <h1>Reputation</h1>
                            </br>
                            <p style="font-size: 15px;">Star : <s:property value="star"/></p>
                            <s:if test="%{#session.accLog.getRole() != #session.accPro.getRole()}">
                                Rate : 
                                <a class="btn btn-small" href="rateAction?starRate=1&id=<s:property value="#session.accPro.getId()"/>" >1 <i class="icon-star"></i></a>
                                <a class="btn btn-small" href="rateAction?starRate=2&id=<s:property value="#session.accPro.getId()"/>" >2 <i class="icon-star"></i></a>
                                <a class="btn btn-small" href="rateAction?starRate=3&id=<s:property value="#session.accPro.getId()"/>" >3 <i class="icon-star"></i></a>
                                <a class="btn btn-small" href="rateAction?starRate=4&id=<s:property value="#session.accPro.getId()"/>" >4 <i class="icon-star"></i></a>
                                <a class="btn btn-small" href="rateAction?starRate=5&id=<s:property value="#session.accPro.getId()"/>" >5 <i class="icon-star"></i></a>

                                <br/>

                                <form action='feedbackAction' method='post' style="margin-top: 10px;">
                                    <p><textarea name='content' style="width: 100%"></textarea></p>
                                    <input name='id' value="<s:property value="#session.accPro.getId()"/>" style='display: none'>
                                    <input type='submit' value='Send Feedback'>
                                </form>

                                <s:iterator value="messRate">
                                    <label style="color: red;"><s:property></s:property></label>
                                        <br>
                                </s:iterator>

                                <s:iterator value="messFeed">
                                    <label style="color: red;"><s:property></s:property></label>
                                        <br>
                                </s:iterator>
                            </s:if>



                        </div>

                            
                    </div> <!-- /row -->

                    <div class="row">


                        <div class="span3">
                            &nbsp;  
                        </div>
                        <div class="span9">
                            <hr/>
                            <h2>Feedback</h2>
                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#formcontrols" data-toggle="tab">Customer FeedBack</a>
                                    </li>
                                    <li><a href="#jscontrols" data-toggle="tab">Worker FeedBack</a></li>
                                </ul>
                                <br>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="formcontrols">
                                        <ul class="messages_layout">
                                            <s:iterator value="listFeedCustomer">

                                                <li class="from_user left"> <a href="#" class="avatar"><img src="img/message_avatar1.png"/></a>
                                                    <div class="message_wrap" style="width: 90%"> <span class="arrow"></span>
                                                        <div class="info"> <a class="name"><s:property value="idWorker.fullname"/></a></div>
                                                        <div class="text"> <s:property value="content"/> </div>
                                                    </div>
                                                </li>
                                            </s:iterator>

                                        </ul>
                                    </div>
                                    <div class="tab-pane" id="jscontrols">
                                        <ul class="messages_layout">
                                            <s:iterator value="listFeedWorker">
                                                <li class="from_user left"> <a href="#" class="avatar"><img src="img/message_avatar1.png"/></a>
                                                    <div class="message_wrap"> <span class="arrow"></span>
                                                        <div class="info"> <a class="name"><s:property value="idCustomer.fullname"/></a>

                                                        </div>
                                                        <div class="text"> <s:property value="content"/></div>
                                                    </div>
                                                </li>
                                            </s:iterator>

                                        </ul>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
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


        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.7.2.min.js"></script>

        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>

        <script src="js/guidely/guidely.min.js"></script>


    </body>

</html>
