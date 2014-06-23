<%-- 
    Document   : createproject
    Created on : Jun 16, 2014, 12:37:23 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work To Worker</title>
    </head>
    <body>
        <h1>Hello <% if (accLog != null) {
                out.print(accLog.getFullname());
            }%></h1>
        <h1>Create Project</h1>
        <s:iterator value="messCreate">
            <label style="color: red;">
                <s:property></s:property>
            </label>
            <br>
        </s:iterator>
        <s:form action="createProjectAction" method="post">
            <table>
                <tr><td>Name:</td><td><input name="name"></td></tr>
                <tr><td>Category:</td><td><select name="category"><option value="IT">IT</option><option value="Driver">Driver</option></select></td></tr>
                <tr><td>Skills:</td><td><input name="skills"></td></tr>
                <tr><td>Description:</td><td><textarea name="description"></textarea></td></tr>
                <tr><td>Price:</td><td><select name="price"><option value="100">100</option><option value="200">200</option></select></td></tr>
                <tr><td>Att File:</td></tr>
                <tr><td><input value="Create" type="submit"></td></tr>
            </table>
        </s:form>
    </body>
</html>
