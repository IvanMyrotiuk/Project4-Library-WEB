<%-- 
    Document   : LoginSuccess
    Created on : May 21, 2015, 2:55:20 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.titel}</title>

        <style>
            table#t01{
                margin: auto; 
                width:90%
            }

            table#t01, th#t01, td#t01 {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th#t01, td#t01 {
                padding: 7px;
            }
            table#t01 tr:nth-child(even) {
                background-color: #eee;
            }
            table#t01 tr:nth-child(odd) {
                background-color:#fff;
            }

            table#t01 th	{
                background-color: #33FF66;
                color: black;
            }
        </style>

    </head>
    <body>

        <table  style="width:100%">
            <tr>
                <th> <fmt:message key="label.name"/>: ${user.userFirstName} ${user.userLastName} </th>
                <th> <fmt:message key="label.email"/>: ${user.userEmail}</th>
                <th align="right">

                   <c:if test="${not empty param.currentPage}">
                        <c:set var="currentPage" value="${param.currentPage}" scope="page"/>
                   </c:if>
                   
                <form action="${not empty currentPage ? currentPage :not empty command ? './library' : './index'}" method="Post">
                    <c:if test="${not empty command}">
                         <input type="hidden" name="action" value="${command}"/>
                    </c:if>  
                         
                     <c:if test="${not empty currentPage and empty command}">
                        <input type="hidden" name="currentPage" value="${currentPage}"/>
                    </c:if>     
                     
                <h3><fmt:message key="language.label"/>:
                    <select name="language" onchange="submit()" style="width: 200px; height: 40px; font-size:14pt;">
                        <option value="en" ${language == 'en' ? 'selected' :''}><fmt:message key="language.english"/></option>
                        <option value="ru" ${language == 'ru' ? 'selected' :''}><fmt:message key="language.russian"/></option>
                    </select></h3>
            </form>
        </th>
        <th align="right">
        <form action="./library" method="Post">
            <input type="hidden" name="action" value="logout"/>
            <fmt:message key="text.logout" var="buttonLogout"/>
            <input type="submit" value="${buttonLogout}" style="height:30px; width:150px" />
        </form>
    </th>
</tr>
</table>
<hr>