<%-- 
    Document   : InternalizForLoginIndexSingup
    Created on : May 26, 2015, 12:00:02 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>


<table style="width: 100%">
    <tr><td align="right">
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
                    <select name="language" onchange="submit()" style="width: 200px; height: 50px; font-size:11pt;">
                        <option value="en" ${language == 'en' ? 'selected' :''}><fmt:message key="language.english"/></option>
                        <option value="ru" ${language == 'ru' ? 'selected' :''}><fmt:message key="language.russian"/></option>
                    </select></h3>
            </form>
        </td></tr>
</table>
<hr>

