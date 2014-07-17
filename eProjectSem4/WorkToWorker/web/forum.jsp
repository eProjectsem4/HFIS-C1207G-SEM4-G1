<%-- 
    Document   : forum
    Created on : Jun 25, 2014, 6:37:39 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
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


        <link href="css/pages/faq.css" rel="stylesheet"> 

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <style>
             td > a{
                color: #000;
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
                        <li><a href="home.jsp"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                        <li class="dropdown">
                            <a href="index.html" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i><span>My Project</span> </a>
                            <ul class="dropdown-menu">
                                <li><a href="createproject.jsp">Post Project</a></li>
                                <li><a href="allProjectAction">List Project</a></li>
                            </ul>
                        </li>
                        <li class="active"><a href="forumAction"><i class="icon-facetime-video"></i><span>Forums</span> </a></li>
                        <s:if test="%{#session.accLog.role == 'Admin'}">
                        <li><a href="charts.jsp"><i class="icon-bar-chart"></i><span>Reports</span> </a> </li>
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
                        <div class="span12">

                            <div class="widget widget-plain">

                                <div class="widget-content">

                                    <a href="createtopic.jsp" class="btn btn-large btn-success btn-support-ask">Create New Topic</a>	


                                </div> <!-- /widget-content -->

                            </div> <!-- /widget -->



                        </div> <!-- /span12 -->
                    </div>	

                    <div class="row">

                        <div class="span12">

                            <div class="widget">

                                <div class="widget-header">
                                    <i class="icon-pushpin"></i>
                                    <h3>Welcome to the Forums</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">

                                    <display:table id="projectTable" name="listTopic" pagesize="10" requestURI="" class="table table-striped ">

                                        <td><display:column title="Thread" ><a href="showTopicAction?id=${projectTable.id}">${projectTable.title}</a></display:column></td>
                                        <td><display:column><a href="Profile?id=${projectTable.idAccount.id}">${projectTable.idAccount.fullname}</a></display:column></td> 
                                        <td><display:column property="postDate" title="" format="{0,date,dd-MM-yyyy}"/></td>

                                        <display:setProperty name="paging.banner.no_items_found" value='<span class="pagebanner"></span>' />
                                        <display:setProperty name="paging.banner.one_item_found" value='<span class="pagebanner"></span>' />
                                        <display:setProperty name="paging.banner.all_items_found" value='<span class="pagebanner"></span>' />
                                        <display:setProperty name="paging.banner.some_items_found" value='<span class="pagebanner"></span>' />                                            

                                        <display:setProperty name="paging.banner.first" value='<div class="pagination"><ul><li class="prev" disabled><a href="{1}" class="next" title="first">First</a></li><li class="prev"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li><a href="{3}" class="next" title="next">Next</a></li><li class="next"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>'/>
                                        <display:setProperty name="paging.banner.last" value='<div class="pagination"><ul><li class="prev"><a href="{1}" class="next" title="first">First</a></li><li class="prev"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li class="next disabled"><a href="{3}" class="next" title="next">Next</a></li><li class="next disabled"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>'/>
                                        <display:setProperty name="paging.banner.full" value='<div class="pagination"><ul><li class="prev disabled"><a href="{1}" class="next" title="first">First</a></li><li class="prev disabled"><a href="{2}" class="next" title="previous">Previous</a></li>{0}<li><a href="{3}" class="next" title="next">Next</a></li><li class="next"><a href="{4}" class="next" title="lest">Last</a></li></ul></div>' />
                                        <display:setProperty name="paging.banner.onepage" value='<div class="pagination"><ul>{0}</ul></div>' />                               

                                        <display:setProperty name="paging.banner.page.selected" value='<a href="{1}" class="page gradient">{0}</a>'/>
                                        <display:setProperty name="paging.banner.page.link" value='<a href="{1}" class="page gradient">{0}</a>'/>
                                        <display:setProperty name="paging.banner.page.separator" value="" />
                                    </display:table>

<!--                                    <div class="faq-toc">
                                        <ol>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-1">When I visit the free sample calendar it still says “Check back soon for today's free sample”- Why is that?</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-2">When I went to request the sample it said they were out or that I didn’t qualify to get the sample.</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-3">The free sample site won't work for me.</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-4">I don't have a Facebook account and cannot accept Facebook offers. Why do I have to “Like” a company on Facebook to get the sample?</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-5">I don't want give my phone number to request a sample.</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-6">I don't want to have my email inbox overrun with newsletters, offers and possibly spam.</a></li>
                                            <li><img src="images/thread_hot.gif"/> <a href="#faq-7">I can’t see the free sample calendar.</a></li>
                                        </ol>
                                    </div>-->



                                </div> <!-- /widget-content -->

                            </div> <!-- /widget -->	

                        </div> <!-- /spa12 -->







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


        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.7.2.min.js"></script>

        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>
        <script src="js/faq.js"></script>

        <script>

            $(function() {

                $('.faq-list').goFaq();

            });

        </script>
    </body>

</html>
