<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Info</title>
    <link rel="stylesheet" href="resources/css/styleTable.css"/>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "contactInfo?personId=${model.person.personId}",
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
                                "<td>" + data.person.birthday + "</td></tr>";
                        $("#personInfo").append(personHTML);

                        var addressHTML = "<tr><td>Адрес</td><td>По умолчанию</td></tr>";
                        $.each(data.addressList, function(i, item) {
                            addressHTML += "<tr><td>" + item.addressValue + "</td><td>"
                                    + item.addressDefault + "</td></tr>";
                        });
                        $('#addressInfo').append(addressHTML);

                        var emailHTML = "<tr><td>Email</td><td>Тип</td><td>По умолчанию</td></tr>";
                        $.each(data.emailList, function(i, item) {
                            emailHTML += "<tr><td>" + item.emailValue + "</td><td>"
                                    + item.emailType + "</td><td>"
                                    + item.emailDefault + "</td></tr>";
                        });
                        $('#emailInfo').append(emailHTML);

                        var phoneHTML = "<tr><td>Номер</td><td>Тип</td><td>По умолчанию</td></tr>";
                        $.each(data.phoneList, function(i, item) {
                            phoneHTML += "<tr><td>" + item.phoneNumber + "</td><td>"
                                    + item.phoneType + "</td><td>"
                                    + item.phoneDefault + "</td></tr>";
                        });
                        $('#phoneInfo').append(phoneHTML);
                    }
                }
            })
        });
    </script>

</head>
<body>
<a href="contactList">К списку</a><br/><br/>
Contact
<table border="1" id="personInfo"></table>
<table border="1" id="addressInfo"></table>
<table border="1" id="emailInfo"></table>
<table border="1" id="phoneInfo"></table>
</body>
</html>
