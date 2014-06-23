<%-- 
    Document   : allproject
    Created on : Jun 16, 2014, 12:19:59 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work To Worker</title>
    </head>
    <body>
        <h1>All Project</h1>
        <table>
            <tr><td><b>Name</b></td><td><b>Category</b></td><td><b>Price</b></td><td><b>Start Date</b></td><td><b>End Date</b></td><td><b>Status</b></td><td><b>Details</b></td></tr>
            <s:iterator value="listProject">
            <tr>
                <td><s:property value="name"></s:property></td>
                <td><s:property value="category"></s:property></td>
                <td><s:property value="price"></s:property></td>
                <td><s:property value="startDate"></s:property></td>
                <td><s:property value="endDate"></s:property></td>
                <td><s:property value="status"></s:property></td>
                <td><a href="detailsAction?id=<s:property value="id"></s:property>">Show</a></td>
            </tr>
            </s:iterator>
        </table>
    </body>
</html>
