<%-- 
    Document   : createtopic
    Created on : Jun 25, 2014, 6:49:44 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic</title>
    </head>
    <body>
        <h1>Create Topic</h1>
        <form action="createTopicAction" method="post">
            <table>
                <tr><td>Title:</td><td><input name="title"></td></tr>
                <tr><td>Content:</td><td><textarea name="content"></textarea></td></tr>
                <tr><td><input type="submit" value="Create"></td></tr>
            </table>
            <s:iterator value="messCreate">
                <label style="color: red;"><s:property></s:property></label>
                <br>
            </s:iterator>
        </form>
    </body>
</html>
