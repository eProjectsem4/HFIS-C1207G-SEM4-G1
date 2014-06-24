<%-- 
    Document   : profile
    Created on : Jun 24, 2014, 7:12:48 AM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    Account accLog = (Account) request.getSession().getAttribute("accLog");
    Account accPro = (Account) request.getSession().getAttribute("accPro");
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <h2>Profile</h2>
        Full Name : <s:property value="accPro.fullname"></s:property>
            <br>
            Phone : <s:property value="accPro.phone"></s:property>
            <br>
            Email : <s:property value="accPro.email"></s:property>
            <br>
            Company : <s:property value="accPro.company"></s:property>
            <br>
            Role : <s:property value="accPro.role"></s:property>
            <br>
            Address : <s:property value="accPro.addresss"></s:property>
            <br>
            Country : <s:property value="accPro.country"></s:property>
        <br>
        <%
            if(accLog.getId() == accPro.getId()){
                out.print("<form action='editProfileAction'><input type='submit' value='Edit'></form>");
            }
        %>
        <hr>
        <h2>Rate</h2>
        Star : <s:property value="star"></s:property>
        <br>
        <% if(!accLog.getRole().equals(accPro.getRole())){out.print("Rate : <a href='rateAction?star=1'>1 star</a>      <a href='rateAction?star=2'>2 star</a>      <a href='rateAction?star=3'>3 star</a>      <a href='rateAction?star=4'>4 star</a>      <a href='rateAction?star=5'>5 star</a>");}%>
        <hr>
        <h2>Feedback</h2>
        <s:iterator value="listFeedCustomer">
            <s:property value="idWorker.fullname"></s:property> : <s:property value="content"></s:property>
            <br>
        </s:iterator>
         <s:iterator value="listFeedWorker">
            <s:property value="idCustomer.fullname"></s:property> : <s:property value="content"></s:property>
            <br>
        </s:iterator>
            <%
                if(!accLog.getRole().equals(accPro.getRole())){
                    out.print("<form action='feedbackAction'><input name='content'><input type='submit' value='Send Feedback'></form>");
                }
            %>
    </body>
</html>
