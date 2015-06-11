<%-- 
    Document   : Singup
    Created on : May 21, 2015, 2:26:46 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sing up</title>
    </head>
    <body>
        <%@include file="./InternalizForLoginIndexSingup.jsp" %>
    <center>

        <form action="./library" method="Post">
            <input type="hidden" name="action" value="singup"/>
            <fieldset>
                <legend><fmt:message key="text.singup"/>:</legend>
                <fmt:message key="label.first.name"/>:<br/>
                <input type="text" name="userFirstName" value="${userFirstName}"/><br/>
                <fmt:message key="label.last.name"/>:<br/>
                <input type="text" name="userLastName" value="${userLastName}"/><br/>
                <fmt:message key="label.address"/>:<br/>
                <input type="text" name="userAddress" value="${userAddress}"/><br/>
                <fmt:message key="label.phone.number"/>:<br/>
                <input type="tel" name="userPhoneNumber" value="${userPhoneNumber}"/><br/>
                <fmt:message key="label.email"/>:<br/>
                <input type="text" name="userEmail" value="${userEmail}"/><br/>
                <fmt:message key="label.password"/>:<br/>
                <input type="password" name="userPassword" value="${userPassword}"/><br/>  
                <fmt:message key="label.repeat.password"/>:<br/>
                <input type="password" name="userRepeatPassword" value="${userRepeatPassword}"/><br/>  
            </fieldset>
            <br/>
            <fmt:message key="text.singup" var="buttonSingup"/>
            <input type="submit" value="${buttonSingup}" style="height:50px; width:200px"/><br/>            
        </form>
        ${singupMessage}
    </center> 

</body>
</html>
