<%-- 
    Document   : forum
    Created on : Jun 25, 2014, 6:37:39 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
    </head>
    <body>
        <h1>Forum</h1>
        <a href="createtopic.jsp">Create</a>
        <br>
        <br>
        <h1>Topics</h1>
        <s:iterator value="listTopic">
            <a href="showTopicAction?id=<s:property value="id"></s:property>"><s:property value="title"></s:property></a> : 
            <a href="Profile?id=<s:property value="idAccount.id"></s:property>"><s:property value="idAccount.fullname"></s:property></a> :   
            <s:property value="postDate"></s:property>
        </s:iterator>
    </body>
</html>
