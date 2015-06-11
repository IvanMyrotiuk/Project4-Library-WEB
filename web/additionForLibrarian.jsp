<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.myrotiuk.i18n.text"/>
<table style="width:70%">
    <tr>
        <td>
            <form action="./library" method="Post">
                <input type="hidden" name="action" value="catalog"/>
                <fmt:message key="button.look.catalog" var="catalogOfBooks" />
                <input type="submit" value="${catalogOfBooks}" style="height:55px; width:210px"/>
            </form>
        </td>
        <td>
            <form action="./library" method="Post">
                <input type="hidden" name="action" value="readers"/>
                <fmt:message key="button.all.readers" var="allReaders" />
                <input type="submit" value="${allReaders}" style="height:55px; width:210px"/>
            </form>
        </td>
        <td>
            <form action="./library" method="Post">
                <input type="hidden" name="action" value="owingreader"/>
                <fmt:message key="button.readers.took.books" var="readersTookBooks" />
                <input type="submit" value="${readersTookBooks}" style="height:55px; width:210px"/>
            </form>
        </td>
    </tr>
</table>