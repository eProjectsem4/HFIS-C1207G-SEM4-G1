<%-- 
    Document   : showTopic
    Created on : Jun 25, 2014, 7:15:15 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic</title>
    </head>
    <body>
        <h1>Topic</h1>
        <table>
            <tr><td>Title</td><td><s:property value="tp.title"></s:property></td></tr>
            <tr><td>Content</td><td><s:property value="tp.content"></s:property></td></tr>
            <tr><td>Poster</td><td><a href="Profile?id=<s:property value="tp.idAccount.id"></s:property>"><s:property value="tp.idAccount.fullname"></s:property></td></tr>
            <tr><td>Date</td><td><s:property value="tp.postDate"></s:property></td></tr>
        </table>
            <s:iterator value="listComment">
                <a href="Profile?id=<s:property value="idAccount.id"></s:property>"><s:property value="idAccount.fullname"></s:property></a> :  <s:property value="content"></s:property>  : <s:property value="postDate"></s:property>
                <br>
            </s:iterator>
                    <form action="commentAction" method="post">
                        <input name="idTopic" value="<s:property value="tp.id"></s:property>" style="display: none">
                    <textarea name="content"></textarea>
                    <input type="submit" value="Send">
                    </form>
                <s:iterator value="messComment">
                    <label style="color: red;"><s:property></s:property></label>
                    <br>
                </s:iterator>
    </body>
</html>
