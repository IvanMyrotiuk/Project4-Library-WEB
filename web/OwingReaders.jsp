<%-- 
    Document   : Debtor
    Created on : May 23, 2015, 6:16:20 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<c:import url="./header.jsp">
    <c:param name="titel" value="Reders that took books"/>
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
            <th id="t01"><fmt:message key="label.book.name"/></th>
            <th id="t01"><fmt:message key="label.author"/></th>
            <th id="t01"><fmt:message key="label.reading.area"/></th>
            <th id="t01"><fmt:message key="label.date.time"/></th>
        </tr>
    <c:forEach var="order" items="${orders}">
        <c:choose>
            <c:when test="${order.user.userType == 0}">
                <c:set var="userType" value="Reader" scope="page"/>
            </c:when>
            <c:when test="${order.user.userType == 1}">
                <c:set var="userType" value="Librarian" scope="page"/>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${order.readingArea == 2}">
                <c:set var="readingArea" value="Reading room" scope="page"/>
            </c:when>
            <c:when test="${order.readingArea == 3}">
                <c:set var="readingArea" value="Delivery desk" scope="page"/>
            </c:when>
        </c:choose>

        <tr>
            <td id="t01">${order.user.userFirstName}</td>
            <td id="t01">${order.user.userLastName}</td>
            <td id="t01">${order.user.userAddress}</td>
            <td id="t01">${order.user.userPhoneNumber}</td>
            <td id="t01">${order.user.userEmail}</td>
            <td id="t01">${userType}</td>
            <td id="t01">${order.book.bookName}</td>
            <td id="t01">${order.book.author.authorName}</td>
            <td id="t01">${readingArea}</td>
            <td id="t01">${order.createdAt}</td>
        </tr>
    </c:forEach> 


</body>
</html>
