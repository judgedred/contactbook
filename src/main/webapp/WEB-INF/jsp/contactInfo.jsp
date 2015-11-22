<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Info</title>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "contactInfo?personId=" + ${model.person.personId}/*$("#personId").val()*/,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    if(data != null)
                    {
                        var personHTML = '';
                        personHTML += "<tr><td>Имя</td><td>Фамилия</td><td>Отчество</td><td>Дата рождения</td></tr>" +
                                "<tr><td>" + data.person.personName + "</td>" +
                                "<td>" + data.person.personSurname + "</td>" +
                                "<td>" + data.person.personPatronymic + "</td>" +
                                "<td>" + data.person.birthday + "</td>";
                        $("#personInfo").append(personHTML);
                     /*$("#contactInfo").append("<tr>");
                     $("#contactInfo").append("<td>" + data.person.personName + "</td>");
                     $("#contactInfo").append("<td>" + data.person.personSurname + "</td>");
                     $("#contactInfo").append("<td>" + data.person.personPatronymic + "</td>");
                     $("#contactInfo").append("<td>" + data.person.birthday + "</td>");
                     $("#contactInfo").append("</tr>");*/

                        var addressHTML = '';
                        $.each(data.addressList, function (i, item) {
                            addressHTML += '<tr><td>' + item.addressValue + '</td><td>'
                                    + item.addressDefault + '</td><td>' + '</td><tr>';
                        });
                        $('#addressInfo').append(addressHTML);
                    }
                }
            })
        });
    </script>

</head>
<body>
Contact
<table border="1" id="personInfo">

</table>
<table border="1" id="addressInfo">

</table>
<%--<div id="contactInfo"></div>--%>
</body>
</html>
