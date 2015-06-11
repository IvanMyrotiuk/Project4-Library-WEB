<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>

<table style="width:70%">
        <tr>
            <td>
                <form action="./library" method="Post">
                    <input type="hidden" name="action" value="catalogforreader"/>
                    <fmt:message key="button.look.catalog" var="catalogOfBooks"/>
                    <input type="submit" value="${catalogOfBooks}" style="height:55px; width:210px"/>
                </form>
            </td>
            <td>
                <form action="./library" method="Post">
                    <input type="hidden" name="action" value="takenbooks"/>
                    <fmt:message key="button.your.taken.books" var="takenBooks"/>
                    <input type="submit" value="${takenBooks}" style="height:50px; width:210px"/>
                </form>
            </td>
        </tr>
    </table>