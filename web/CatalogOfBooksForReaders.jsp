<%-- 
    Document   : CatalogOfBooksForReaders
    Created on : May 25, 2015, 12:44:06 AM
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
<input type="hidden" name="action" value="takebook"/>

<table id="t01" >
        <tr>
            <th id="t01"><fmt:message key="label.choose.book"/></th>
            <th id="t01"><fmt:message key="label.book.name"/></th>
            <th id="t01"><fmt:message key="label.author"/></th>
            <th id="t01"><fmt:message key="label.birthday.of.author"/></th>
            <th id="t01"><fmt:message key="label.quantity"/></th>
        </tr>
     
    <c:forEach var="catalog" items="${catalog}">
        <c:if test="${catalog.bookQuantity > 0}" >
            <tr>
                <td id="t01"><input type="checkbox" name="takenbook" value="${catalog.id}" style=" margin: calc ; height: 30px; width: 30px"/></td>
                <td id="t01">${catalog.bookName}</td>
                <td id="t01">${catalog.author.authorName}</td>
                <td id="t01">${catalog.author.authorBirthday}</td>
                <td id="t01">${catalog.bookQuantity}</td>
            </tr>
        </c:if>
    </c:forEach>    
</table>
<br/>

<center>
    <fmt:message key="button.make.order" var="buttonMakeOrder"/>
<input type="submit" value="${buttonMakeOrder}" style="height:50px; width:210px"/>
</center>

</form>

        <br/><br/><br/>
        <center>
        <h3>${catalogFoReadersMessage}</h3>
        </center>
</body>
</html>
