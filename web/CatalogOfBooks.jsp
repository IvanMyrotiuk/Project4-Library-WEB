<%-- 
    Document   : CatalogOfBooks
    Created on : May 23, 2015, 6:08:38 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<c:import url="./header.jsp">
    <c:param name="titel" value="Catalog of books"/>
</c:import> 

<center>
    <br/>
    <c:import url="./additionForLibrarian.jsp"></c:import>
        <br/>
    </center>


    <table id="t01" >
        <tr>
            <th id="t01"><fmt:message key="label.book.name"/></th>
            <th id="t01"><fmt:message key="label.author"/></th>
            <th id="t01"><fmt:message key="label.birthday.of.author"/></th>
            <th id="t01"><fmt:message key="label.quantity"/></th>
        </tr>
    <c:forEach var="catalog" items="${catalog}">
        <c:if test="${catalog.bookQuantity > 0}" >
            <tr>
                <td id="t01">${catalog.bookName}</td>
                <td id="t01">${catalog.author.authorName}</td>
                <td id="t01">${catalog.author.authorBirthday}</td>
                <td id="t01">${catalog.bookQuantity}</td>
            </tr>
        </c:if>
    </c:forEach>    
</table>
<br/><br/><br/>

<center>

    <form action="./library" method="Post">
        <input type="hidden" name="action" value="addbook"/>
        <fieldset>
            <legend><fmt:message key="text.add.book"/>:</legend>
            <table>
                <tr>
                    <td> <fmt:message key="label.book.name"/> :</td>
                    <td> <fmt:message key="label.author"/>: </td>
                    <td> <fmt:message key="label.birthday.of.author"/>: </td>
                    <td> <fmt:message key="label.quantity"/>: </td>
                </tr>
                <tr>
                    <td> <input type="text" name="nameOfBook" value="" style="height:33px;width:320px;font-size:14pt;"/></td>
                    <td> <input type="text" name="nameOfAuthor" value="" style="height:33px;width:320px;font-size:14pt;"/> </td>
                    <td> <input type="text" name="birthdayOfAuthor" value="" style="height:33px;width:320px;font-size:14pt;"/></td>
                    <td> <input type="text" name="bookQuantity" value="" style="height:33px;width:320px;font-size:14pt;"/></td>
                </tr>
            </table>
        </fieldset>
        <br/>
        <fmt:message key="text.add.book" var="buttonAddBook"/>
        <input type="submit" value="${buttonAddBook}" style="height:55px; width:210px"/>
    </form>
    <br/>
    ${addBookMessage}
</center>


</body>
</html>
