<%-- 
    Document   : details
    Created on : Jun 23, 2014, 1:54:51 PM
    Author     : Khanh
--%>

<%@page import="wtw.entities.Project"%>
<%@page import="wtw.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<% 
    Account accLog = (Account)request.getSession().getAttribute("accLog");
    Project project = (Project) request.getSession().getAttribute("project");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
    </head>
    <body>
        <h2>Name : <s:property value="project.name"></s:property></h2>
        <h2>Category : <s:property value="project.category"></s:property></h2>
        <h2>Customer : <s:property value="project.idCustomer.fullname"></s:property></h2>
        <%
            if(project.getIdWorker() != null){
                out.print("<h2>Worker : "+project.getIdWorker().getFullname()+"</h2>");
            }else{
                out.print("<h2>Worker : </h2>");
            }
        %>
        <h2>Skills : <s:property value="project.nameSkills"></s:property></h2>
        <h2>Description : <s:property value="project.descriptions"></s:property></h2>
        <h2>AttFile : <s:property value="project.attFile"></s:property></h2>
        <h2>Price : <s:property value="project.price"></s:property></h2>
        <h2>Start Date : <s:property value="project.startDate"></s:property></h2>
        <h2>End Date : <s:property value="project.endDate"></s:property></h2>
        <h2>Status : <s:property value="project.status"></s:property></h2>
        <s:iterator value="messError">
            <label style="color: red;">
                <s:property></s:property>
            </label>
            <br>
        </s:iterator>
        <%
            if(accLog.getRole().equals("Worker") && project.getStatus().equals("Started")){
                out.print("<form action='orderAction'><input type='submit' value='Order'></form>");
            }
            if(accLog.getId() == project.getIdCustomer().getId() && !project.getStatus().equals("Done")){
                out.print("<form action='doneProjectAction'><input type='submit' value='Done'></form>");
            }
            if(accLog.getId() == project.getIdCustomer().getId() && project.getStatus().equals("Started")){
                out.print("<form action='showOrderAction'><input type='submit' value='Show Order'></form>");
            }
        %>
    </body> 
</html>
