<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact form</title>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <%--<script type="text/javascript" src="http://www.datejs.com/build/date.js"></script>--%>
    <script type="text/javascript">
        $(document).ready(function() {
            /*var d = $("#birthday").val();
            var dout = Date.parse(d);
            var d = new Date(});
            var curr_date = d.getDate();
            var curr_month = d.getMonth() + 1; //Months are zero based
            var curr_year = d.getFullYear();
            $("#birthday").val(dout.toString('yyyy-MM-dd'));
            $("#birthday").val(curr_year + "-" + curr_month + "-" + curr_date);*/


        });
        function send() {
            var person = {
                "personName": $("#personName").val(),
                "personSurname": $("#personSurname").val(),
                "personPatronymic": $("#personPatronymic").val(),
                "birthday": $("#birthday").val()
            }

            $('#target').html('sending..');

            $.ajax({
                url: 'persistContact',
                type: 'post',
                contentType: 'application/json',
//                dataType: 'json',
                success: function (data) {
                    $('#target').html(data.msg);
                },
                data: JSON.stringify({
                    "personName": $("#personName").val(),
                    "personSurname": $("#personSurname").val(),
                    "personPatronymic": $("#personPatronymic").val(),
                    "birthday": $("#birthday").val()
                })
            });
            return false;
        }
    </script>
</head>
<body>
Contact
<form onsubmit="return send()" name="contact" id="contact">
    <table>
    <tr><td><input hidden type="text" value="${model.person.personId}" name="personId" id="personId"></td></tr>
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
            <td><label for id="addressValue">Адрес</label> </td>
            <td><input type="text" value="${model.address.addressValue}" name="addressValue" id="addressValue"></td>
        </tr>
        <tr>
            <td><label for id="addressDefault">По умолчанию</label> </td>
            <td><input type="checkbox" value="true" name="addressDefault" id="addressDefault"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>
</body>
</html>

