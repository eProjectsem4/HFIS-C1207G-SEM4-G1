<%-- 
    Document   : index
    Created on : Jun 16, 2014, 12:10:08 PM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<% Account accLog = (Account) request.getSession().getAttribute("accLog");
    if (accLog != null) {
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work To Worker</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="loginAction" method="post">
            <label style="color: red"><s:property value="messLogin"></s:property></label>
                <table>
                    <tr><td>Email:</td><td><input name="email"></td></tr>
                    <tr><td>Password:</td><td><input name="password" type="password"></td></tr>
                    <tr><td><input type="submit" value="Login"></td></tr>
                </table>
            </form>
            <h1>Register</h1>
            <form action="registerAction">
            <s:iterator value="messRegister">
                <label style="color: red"><s:property></s:property></label>
                    <br>
            </s:iterator>
            <table>
                <tr><td>Full Name</td><td><input name="fullName"></td></tr>
                <tr><td>Email</td><td><input name="email"></td></tr>
                <tr><td>Password</td><td><input name="password" type="password"></td></tr>
                <tr><td>Re-Password</td><td><input name="repassword" type="password"></td></tr>
                <tr><td>Role</td><td><select name="role"><option value="Customer">Customer</option><option value="Worker">Worker</option></select></td></tr>
                <tr><td><input type="submit" value="Register"></td></tr>
            </table>
        </form>

    </body>
</html>
