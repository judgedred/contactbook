<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>


<html>
<head>
    <title>ContactList</title>
</head>
<body>

    <p>ContactList</p>
    <c:if test="${!empty contactList}">
        <table>
            <c:forEach items="${contactList}" var="contact">
                <tr>
                    <td>${contact}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>
