<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>


<html>
<head>
    <title>ContactBook</title>
    <link rel="stylesheet" href="resources/css/styleTable.css"/>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "contactListGet",
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    if(data != null)
                    {
                        var trHTML = "";
                        $.each(data, function(i, item) {
                            trHTML += "<tr>";
                            trHTML += "<td>" + item.person.personId + "</td>"
                            + "<td>" + item.person.personName + "</td>"
                            + "<td>" + item.person.personSurname + "</td>"
                            + "<td>" + item.person.personPatronymic + "</td>"
                            + "<td>" + item.person.birthday + "</td>";
                            if(item.defaultAddress != null)
                            {
                                trHTML += "<td>" + item.defaultAddress.addressValue + "</td>";
                            }
                            else
                            {
                                trHTML += "<td></td>"
                            }
                            if(item.defaultEmail != null)
                            {
                                trHTML += "<td>" + item.defaultEmail.emailValue + "</td>";
                            }
                            else
                            {
                                trHTML += "<td></td>"
                            }
                            if(item.defaultPhone != null)
                            {
                                trHTML += "<td>" + item.defaultPhone.phoneNumber + "</td>";
                            }
                            else
                            {
                                trHTML += "<td></td>"
                            }
                            trHTML += "<td><a href='contactEdit?personId=" + item.person.personId + "'>Редактировать</a>" + "</td>"
                            + "<td><a href='contactView?personId=" + item.person.personId + "'>Показать</a>" + "</td>";
                            trHTML += "</tr>"
                            })
                        $("#contactBook").append(trHTML);
                    }
                }
            });
        });
    </script>
</head>
<body>
    <p>Contact Book</p>

    <table border="1" id="contactBook">
        <tr>
            <td>Номер</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Отчество</td>
            <td>Дата рождения</td>
            <td>Адрес</td>
            <td>Email</td>
            <td>Телефон</td>
            <td></td></td><td><a href="contactAdd">Добавить</a></td>
        </tr>
    </table>

</body>
</html>
