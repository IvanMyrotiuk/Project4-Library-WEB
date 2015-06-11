<%-- 
    Document   : Index
    Created on : May 21, 2015, 2:26:29 PM
    Author     : Ivan Myrotiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/newtag_library.tld" prefix="MyrotiukIvan" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        
        <%@include file="./InternalizForLoginIndexSingup.jsp" %>
        
    <center>

        <form action="./library" method="Post">
            
            <input type="hidden" name="action" value="dologin"/>
            <fmt:message key="text.login" var="buttonLogin"/>
            <input type="submit" value="${buttonLogin}" style="height:50px; width:200px"/> 
        </form><br/>
        <form action="./library" method="Post">
            <input type="hidden" name="action" value="dosingup"/>
            <fmt:message key="text.singup" var="buttonSingup"/>
            <input type="submit" value="${buttonSingup}" style="height:50px; width:200px"/> 
        </form><br/>

        ${nullPageMessage}
        
    </center>
    
    <jsp:useBean id="date" class="java.util.Date" scope="page"/> 
        <MyrotiukIvan:FontTagHandler color="gold">
            <MyrotiukIvan:TextTagHandler  value="Ivan Myrotiuk's Corporation"/>
        </MyrotiukIvan:FontTagHandler>
        <MyrotiukIvan:FontTagHandler size="5" color="goldenrod">
            <MyrotiukIvan:TextTagHandler  value="${date}"/>
        </MyrotiukIvan:FontTagHandler>
        <br/><br/><br/>
        <MyrotiukIvan:FontTagHandler color="red">
            <MyrotiukIvan:TextTagHandler  value="All Rights Reserved"/>
        </MyrotiukIvan:FontTagHandler>
    
    
</body>
</html>
