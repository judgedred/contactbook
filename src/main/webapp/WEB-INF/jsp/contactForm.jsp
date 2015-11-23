<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Contact form</title>
    <link rel="stylesheet" href="resources/css/styleForm.css"/>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="resources/js/form2js.js"></script>
    <script type="text/javascript" src="resources/js/json2.js"></script>
    <script type="text/javascript" src="resources/js/js2form.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

            var counterA = -1;
            var counterE = -1;
            var counterP = -1;

            document.getElementById("addAddress").onclick = addAddress;
            document.getElementById("addEmail").onclick = addEmail;
            document.getElementById("addPhone").onclick = addPhone;

            function addAddress() {
                counterA++;
                var newFields = document.getElementById("readroot").cloneNode(true);
                newFields.id = '';
                newFields.style.display = "block";
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 12), counterA, theName.slice(13)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById("writeroot");
                insertHere.parentNode.insertBefore(newFields, insertHere);
            }

            function addEmail() {
                counterE++;
                var newFields = document.getElementById("readroot2").cloneNode(true);
                newFields.id = '';
                newFields.style.display = "block";
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 10), counterE, theName.slice(11)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById("writeroot2");
                insertHere.parentNode.insertBefore(newFields, insertHere);
            }

            function addPhone() {
                counterP++;
                var newFields = document.getElementById("readroot3").cloneNode(true);
                newFields.id = '';
                newFields.style.display = "block";
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 10), counterP, theName.slice(11)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById("writeroot3");
                insertHere.parentNode.insertBefore(newFields, insertHere);
            }

            $.ajax({
                url: "contactInfo?personId=${model.person.personId}",
                type: "get",
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        for (i = 0; i < data.addressList.length; i++) {
                            addAddress();
                        }
                        for (i = 0; i < data.emailList.length; i++) {
                            addEmail();
                        }
                        for (i = 0; i < data.phoneList.length; i++) {
                            addPhone();
                        }
                        js2form(document.getElementById("contactForm"), data);
                    }
                }
            });
        });

        function send() {

            var formData = form2js("contactForm", '.', true);
            $.ajax({
                url: "persistContact",
                type: "post",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(formData),
                success: function (data) {
                    if(data != null)
                    {
                        alert("Контакт сохранен.");
                        location.reload();
                    }
                    else
                    {
                        alert("Ошибки в форме. Контакт не сохранен.");
                    }
                }
            });
            return false;
        }
    </script>
</head>
<body>

<a href="contactList">К списку</a><br/><br/>


<form id="contactForm" action="" onsubmit="return send()">
    <table>
        <tr><td><input hidden type="text" value="" name="person.personId" id="personId"></td></tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" value="" name="person.personName" id="person.personName"></td>
        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" value="" name="person.personSurname" id="person.personSurname"></td>
        </tr>
        <tr>
            <td>Отчество</td>
            <td><input type="text" value="" name="person.personPatronymic" id="person.personPatronymic"></td>
        </tr>
        <tr>
            <td>Дата рождения</td>
            <td><input type="date" value="" name="person.birthday" id="person.birthday"></td>
        </tr>
    </table>
    <br/>
    <span id="writeroot"></span>
    <br/>
    <span id="writeroot2"></span>
    <br/>
    <span id="writeroot3"></span>
    <br/>

    <input type="button" id="addAddress" value="Добавить адрес" />
    <input type="button" id="addEmail" value="Добавить email" />
    <input type="button" id="addPhone" value="Добавить телефон" />

    <br/><input type="submit" value="Сохранить">

</form>

<div id="readroot" style="display: none">

    Адресс
    <input type="text" value="" name="addressList[0].addressValue" id="addressList[0].addressValue">
    По умолчанию
    <input type="checkbox" value="true" name="addressList[0].addressDefault" id="addressList[0].addressDefault">
    <input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" />

</div>

<div id="readroot2" style="display: none">

    Email
    <input type="text" value="" name="emailList[0].emailValue" id="emailList[0].emailValue">
    Тип
    <input type="text" value="" name="emailList[0].emailType" id="emailList[0].emailType">
    По умолчанию
    <input type="checkbox" value="true" name="emailList[0].emailDefault" id="emailList[0].emailDefault">
    <input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" />

</div>

<div id="readroot3" style="display: none">

    Номер
    <input type="text" value="" name="phoneList[0].phoneNumber" id="phoneList[0].phoneNumber">
    Тип
    <input type="text" value="" name="phoneList[0].phoneType" id="phoneList[0].phoneType">
    По умолчанию
    <input type="checkbox" value="true" name="phoneList[0].phoneDefault" id="phoneList[0].phoneDefault">
    <input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" />

</div>

</body>
</html>
