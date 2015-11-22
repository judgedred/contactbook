<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>


<html>
<head>
    <title>ContactBook</title>
</head>
<body>
    <p>Contact Book</p>
    <c:if test="${!empty model.contactList}">
        <table border="1">
            <c:forEach items="${model.contactList}" var="contact">
                <tr>
                    <td>${contact.personName}</td>
                    <td>${contact.personSurname}</td>
                    <td>${contact.personPatronymic}</td>
                    <td><a href="contactEdit?personId=${contact.personId}">Редактировать</a> </td>
                    <td><a href="contactView?personId=${contact.personId}">Просмотр</a> </td>
                </tr>
            </c:forEach>
            <tr>
                <td><a href="contactAdd">Добавить контакт</a> </td>
            </tr>
        </table>
    </c:if>

</body>
</html>
