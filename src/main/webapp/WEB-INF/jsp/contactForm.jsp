<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact form</title>
</head>
<body>
Contact
<form action="persistContact" method="post" name="contact" id="contact">
    <table>
    <tr><td><input type="text" value="${model.person.personId}" name="personId" id="personId"></td></tr>
        <tr>
            <td><label for id="personName">Имя</label> </td>
            <td><input type="text" value="${model.person.personName}" name="personName" id="personName"></td>
        </tr>
        <tr>
            <td><label for id="personSurname">Фамилия</label> </td>
            <td><input type="text" value="${model.person.personSurname}" name="personSurname" id="personSurname"></td>
        </tr>
        <tr>
            <td><label for id="personPatronymic">Отчество</label> </td>
            <td><input type="text" value="${model.person.personPatronymic}" name="personPatronymic" id="personPatronymic"></td>
        </tr>
        <tr>
            <td><label for id="birthday">Дата рождения</label> </td>
            <td><input type="date" value="${model.person.birthday}" name="birthday" id="birthday"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>
</body>
</html>

