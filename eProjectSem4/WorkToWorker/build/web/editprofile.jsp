<%-- 
    Document   : editprofile
    Created on : Jun 24, 2014, 10:47:42 AM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
        <h1>Edit Profile</h1>
        <s:iterator value="messEdit">
            <label style="color: red;"><s:property></s:property></label>
            <br>
        </s:iterator>
        <form action="updateProfile">
            Full Name : <input name="fullname" value="<s:property value="accLog.fullname"></s:property>">
            <br>
            Phone : <input name="phone" value="<s:property value="accLog.phone"></s:property>">
            <br>
            Company : <input name="company" value="<s:property value="accLog.company"></s:property>">
            <br>
            Address : <input name="address" value="<s:property value="accLog.addresss"></s:property>">
            <br>
            Country : <input name="country" value="<s:property value="accLog.country"></s:property>">
            <br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
