<%-- 
    Document   : WelcomeLibrarian
    Created on : May 23, 2015, 5:01:44 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome librarian</title>
        <style></style>
 </head>
    <body>
        
<%@include file="./header.jsp" %>


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>        
<center>

    <h2><fmt:message key="text.default.welcome"/> ${user.userFirstName} ${user.userLastName}. <fmt:message key="test.welcome.librarian"/> </h2>   
    <br/>

    <c:import url="./additionForLibrarian.jsp"></c:import>
    
</center> 



    </body>
</html>
