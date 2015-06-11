<%-- 
    Document   : BooksTakenByReader
    Created on : May 25, 2015, 12:44:45 AM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<c:import url="./header.jsp">
    <c:param name="titel" value="My taken books"/>
</c:import>

<center>
    <br/>
    <c:import url="./additionForReader.jsp"></c:import>
        <br/>
    </center>
        
<form action="./library" method="Post">
<input type="hidden" name="action" value="givebookback"/>

    <table id="t01" >
        <tr>   
            <th id="t01"><fmt:message key="label.choose.book"/></th>
            <th id="t01"><fmt:message key="label.book.name"/></th>
            <th id="t01"><fmt:message key="label.author"/></th>
            <th id="t01"><fmt:message key="label.reading.area"/></th>
            <th id="t01"><fmt:message key="label.date.time"/></th>
        </tr>
    <c:forEach var="order" items="${takenBooks}">
        <c:choose>
            <c:when test="${order.readingArea == 2}">
                <c:set var="readingArea" value="Reading room" scope="page"/>
            </c:when>
            <c:when test="${order.readingArea == 3}">
                <c:set var="readingArea" value="Delivery desk" scope="page"/>
            </c:when>
        </c:choose>

        <tr>
            <td id="t01"><input type="checkbox" name="givenbookback" value="${order.id}" style=" margin: calc ; height: 30px; width: 30px"/></td>
            <td id="t01">${order.book.bookName}</td>
            <td id="t01">${order.book.author.authorName}</td>
            <td id="t01">${readingArea}</td>
            <td id="t01">${order.createdAt}</td>
        </tr>
    </c:forEach> 
</table>
        
<br/>

<c:if test="${not empty takenBooks }">
<center>
    <fmt:message key="button.give.book.back" var="buttonGiveBookBack" />
<input type="submit" value="${buttonGiveBookBack}" style="height:50px; width:210px"/>
</center>
</c:if>

</form>        
        
        
        <br/><br/><br/>
        
<center>
    <h3>${takenBookMessage}</h3>
</center>
</body>
</html>
