<%-- 
    Document   : home
    Created on : Jun 18, 2014, 11:12:56 AM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Account accLog = (Account)request.getSession().getAttribute("accLog");
    if(accLog == null){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work To Worker</title>
    </head>
    <body>
        <h1>Hello <% if(accLog != null){out.print("<a href='Profile?id="+accLog.getId()+"'>"+accLog.getFullname() +"</a>");} %></h1>
        <a href="allProjectAction"><h1>All Project</h1></a>
        <% 
            if(accLog.getRole().equals("Customer")){
                out.print("<a href='myProjectAction'><h1>My Project</h1></a>");
                out.print("<a href='createproject.jsp'><h1>Create New Project</h1></a>");
            }
            if(accLog.getRole().equals("Worker")){
                out.print("<a href='myOrderAction'><h1>My Order</h1></a>");
            }
        %>
        <a href='forumAction'><h1>Forum</h1></a>
    </body>
</html>
