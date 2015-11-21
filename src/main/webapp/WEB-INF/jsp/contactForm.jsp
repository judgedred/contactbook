<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
        /*
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return a;
        };
        $(function() {
            $('form').submit(function() {
                $('#result').text(JSON.stringify($('#address'.text).serializeObject()));
                return false;
            });
        });*/

        function send() {
            var person = {
                "personName": $("#personName").val(),
                "personSurname": $("#personSurname").val(),
                "personPatronymic": $("#personPatronymic").val(),
                "birthday": $("#birthday").val()
            }
//            alert($("#personName").val());
//            $('#target').html('sending..');

            $.ajax({
             url: 'persistContact',
             type: 'post',
             contentType: 'application/json; charset=utf-8',
             dataType: 'json',
             success: function (data) {
//             $('#target').html(data.msg);
//                 alert(data);
             },
             data: JSON.stringify(
                     {
                         "person":
                         {
                             "personName" : "test",
                             "personSurname" : "test",
                             "personPatronymic" : "test",
                             "birthday" : "2015-11-21"
                         },

                         "addressList" :
                                 [

                                    {
                                        "addressType" : "work",
                                            "addressValue" : "Chapaev",
                                            "addressDefault" : "false"
                                    },
                                     {
                                         "addressType" : "home",
                                         "addressValue" : "Gagarina",
                                         "addressDefault" : "true"
                                     }
                                ]
                    }

             )
             });
            return false;
        }
    </script>
</head>
<body>
Contact
<form name="contact" id="contact" onsubmit="return send()">
    <table>
    <tr><td><input hidden type="text" value="${model.person.personId}" name="personId" id="personId"></td></tr>
        <tr>
            <td><label for="personName">Имя</label> </td>
            <td><input type="text" value="" name="personName" id="personName"></td>
        </tr>
        <tr>
            <td><label for="personSurname">Фамилия</label> </td>
            <td><input type="text" value="" name="personSurname" id="personSurname"></td>
        </tr>
        <tr>
            <td><label for="personPatronymic">Отчество</label> </td>
            <td><input type="text" value="${model.person.personPatronymic}" name="personPatronymic" id="personPatronymic"></td>
        </tr>
        <tr>
            <td><label for="birthday">Дата рождения</label> </td>
            <td><input type="date" value="${model.person.birthday}" name="birthday" id="birthday"></td>
        </tr>
        <div id="address">
        <tr>
            <td><label for="addressValue">Адрес</label> </td>
            <td><input type="text" value="${model.address.addressValue}" name="addressValue" id="addressValue"></td>
        </tr>
        <tr>
            <td><label for="addressDefault">По умолчанию</label> </td>
            <td><input type="checkbox" value="true" name="addressDefault" id="addressDefault"></td>
        </tr>
        </div>
        <tr>
            <td><input type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>

<h2>JSON</h2>
<pre id="result">
</pre>
</body>
</html>

