<%-- 
    Document   : Login
    Created on : May 21, 2015, 2:26:39 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
    </head>
    <body>
        
        <%@include file="./InternalizForLoginIndexSingup.jsp" %>
        
    <center>
        
        <form action="./library" method="Post">
            <input type="hidden" name="action" value="login"/>
            <fieldset>
                <legend><fmt:message key="text.login"/>:</legend>
                <fmt:message key="label.email"/>:<br/>
                <input type="text" name="userEmail" value="${userEmail}"/><br/>
                <fmt:message key="label.password"/>:<br/>
                <input type="password" name="userPassword" value="${userPassword}"/><br/>                
            </fieldset>
            <br/>
            <fmt:message key="text.login" var="buttonLogin" />
            <input type="submit" value="${buttonLogin}" style="height:50px; width:100px"/><br/>            
        </form>
        ${loginMessage}
    </center>            

</body>
</html>
