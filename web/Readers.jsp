<%-- 
    Document   : Readers
    Created on : May 23, 2015, 6:35:16 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<c:import url="./header.jsp">
    <c:param name="titel" value="Reders"/>
</c:import> 
<center>
<br/>
<c:import url="./additionForLibrarian.jsp"></c:import>
<br/>
</center>

    <table id="t01" >
        <tr>
            <th id="t01"><fmt:message key="label.first.name"/></th>
            <th id="t01"><fmt:message key="label.last.name"/></th>		
            <th id="t01"><fmt:message key="label.address"/></th>
            <th id="t01"><fmt:message key="label.phone.number"/></th>
            <th id="t01"><fmt:message key="label.email"/></th>
            <th id="t01"><fmt:message key="label.user.type"/></th>
        </tr>
    <c:forEach var="user" items="${users}">
        <c:choose>
            <c:when test="${user.userType == 0}">
                <c:set var="userType" value="Reader" scope="page"/>
            </c:when>
            <c:when test="${user.userType == 1}">
                <c:set var="userType" value="Librarian" scope="page"/>
            </c:when>
        </c:choose>
        <tr>
            <td id="t01">${user.userFirstName}</td>
            <td id="t01">${user.userLastName}</td>
            <td id="t01">${user.userAddress}</td>
            <td id="t01">${user.userPhoneNumber}</td>
            <td id="t01">${user.userEmail}</td>
            <td id="t01">${userType}</td>
        </tr>
    </c:forEach>    
</table>

</body>
</html>
