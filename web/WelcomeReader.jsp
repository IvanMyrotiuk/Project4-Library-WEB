<%-- 
    Document   : Welcome
    Created on : May 23, 2015, 4:56:27 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<c:import url="./header.jsp" >
    <c:param name="titel" value="Welcome reader"/>
</c:import>        
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<center>

    <h2><fmt:message key="text.default.welcome"/> ${user.userFirstName} ${user.userLastName}. <fmt:message key="test.welcome.reader"/></h2>   
    <br/>
    <c:import url="./additionForReader.jsp"></c:import>

</center>   


</body>
</html>
