<%-- 
    Document   : showorder
    Created on : Jun 23, 2014, 5:48:55 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>Orders</h1>
        <table>
            <s:iterator value="listOrder">
                <tr><td><s:property value="idAccount.fullname"></s:property></td><td><a href="agreeOrderAction?idAccount=<s:property value="idAccount.id"></s:property>">Agree</a></td></tr>
            </s:iterator>
        </table>
    </body>
</html>
