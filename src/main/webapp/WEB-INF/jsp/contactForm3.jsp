<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Contact form</title>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <%--<script type="text/javascript" src="resources/js/jquery.toObject.js"></script>--%>
    <script type="text/javascript" src="resources/js/form2js.js"></script>
    <script type="text/javascript" src="resources/js/json2.js"></script>
    <script type="text/javascript" src="resources/js/js2form.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

            var counterA = -1;
            var counterE = -1;
            var counterP = -1;

            document.getElementById('addAddress').onclick = addAddress;
            document.getElementById('addEmail').onclick = addEmail;
            document.getElementById('addPhone').onclick = addPhone;

            function addAddress() {
                counterA++;
                var newFields = document.getElementById('readroot').cloneNode(true);
                newFields.id = '';
                newFields.style.display = 'block';
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 12), counterA, theName.slice(13)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById('writeroot');
                insertHere.parentNode.insertBefore(newFields, insertHere);
            }

            function addEmail() {
                counterE++;
                var newFields = document.getElementById('readroot2').cloneNode(true);
                newFields.id = '';
                newFields.style.display = 'block';
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 10), counterE, theName.slice(11)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById('writeroot2');
                insertHere.parentNode.insertBefore(newFields, insertHere.lastElementChild);
            }

            function addPhone() {
                counterP++;
                var newFields = document.getElementById('readroot3').cloneNode(true);
                newFields.id = '';
                newFields.style.display = 'block';
                var newField = newFields.childNodes;
                for (var i = 0; i < newField.length; i++) {
                    var theName = newField[i].name
                    if (theName)
                        newField[i].name = [theName.slice(0, 10), counterP, theName.slice(11)].join('');
                    newField[i].id = newField[i].name;
                }
                var insertHere = document.getElementById('writeroot3');
                insertHere.parentNode.insertBefore(newFields, insertHere.lastElementChild);
            }

            /*function populateForm()
            {*/

            $.ajax({
                url: "contactInfo?personId=${model.person.personId}",
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    //             $('#target').html(data.msg);
                    //                 alert(data);
//                    var data = document.getElementById('src').value;
//                    data = JSON.parse(dataS);
//                document.getElementById('testArea').innerHTML = data.phoneList.length;
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
                        js2form(document.getElementById('testForm'), data);
                    }
                }
            });


//            }
        });

        function test()
        {
            var formData = form2js('testForm', '.', true,
                    function(node)
                    {
                        if (node.id && node.id.match(/callbackTest/))
                        {
                            return { name: node.id, value: node.innerHTML };
                        }
                    });
            document.getElementById('testArea').innerHTML = JSON.stringify(formData, null, '\t');
            return false;
        }

        function send() {

            var formData = form2js('testForm', '.', true);

            //            alert($("#personName").val());
            //            $('#target').html('sending..');

            $.ajax({
                url: 'persistContact',
                type: 'post',
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if(data != null)
                    {
                        alert("Контакт сохранен");
                        location.reload();
                    }
                    else
                    {
                        alert("Ошибки в форме. Контакт не сохранен.");
                    }
                    //             $('#target').html(data.msg);
                    //                 alert(data);
                },
                data: JSON.stringify(formData)
            });
            return false;
        }





    </script>
</head>
<body>

Contact

<form id="testForm" action="" onsubmit="return send()">

        <p><input type="text" value="<%--${model.person.personId}--%>" name="person.personId" id="personId"></p>

            <p>Имя</p>
    <p><input type="text" value="" name="person.personName" id="person.personName"></p>

    <p>Фамилия</p>p
    <p><input type="text" value="" name="person.personSurname" id="person.personSurname"></p>p

    <p>Отчество</p>
    <p><input type="text" value="" name="person.personPatronymic" id="person.personPatronymic"></p>

    <p>Дата рождения</p>
    <p><input type="date" value="" name="person.birthday" id="person.birthday"></p>




        <span id="writeroot"></span>
        <span id="writeroot2"></span>
        <span id="writeroot3"></span>

    <input type="button" id="addAddress" value="Добавить адрес" />

    <input type="button" id="addEmail" value="Добавить email" />

    <input type="button" id="addPhone" value="Добавить телефон" />

    <p><input type="submit" value="Сохранить"></p>
</form>

<div id="readroot" style="display: none">

    Адресс
    <input type="text" value="" name="addressList[0].addressValue" id="addressList[0].addressValue">

    По умолчанию
    <input type="checkbox" value="true" name="addressList[0].addressDefault" id="addressList[0].addressDefault"><br/>

    <input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" /><br/>

</div>

<div id="readroot2" style="display: none">

    <p>Тип</p>
    <p><input type="text" value="" name="emailList[0].emailType" id="emailList[0].emailType"></p>

    <p>Email</p>
    <p><input type="text" value="" name="emailList[0].emailValue" id="emailList[0].emailValue"></p>

    <p>По умолчанию</p>
    <p><input type="checkbox" value="true" name="emailList[0].emailDefault" id="emailList[0].emailDefault"></p>

    <p><input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" /></p>

</div>

<div id="readroot3" style="display: none">

    <p>Тип</p>
    <p><input type="text" value="" name="phoneList[0].phoneType" id="phoneList[0].phoneType"></p>

    <p>Номер</p>
    <p><input type="text" value="" name="phoneList[0].phoneNumber" id="phoneList[0].phoneNumber"></p>

    <p>По умолчанию</p>
    <p><input type="checkbox" value="true" name="phoneList[0].phoneDefault" id="phoneList[0].phoneDefault"></p>

    <p><input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" /></p>

</div>

<%--<pre><code id="testArea">
</code></pre>

<div>
			<textarea id="src" cols="70" rows="20">
{"addressList":[{"addressDefault":true,"addressId":1,"addressValue":"userAddress","person":
{"personId":1,"personName":"user","personSurname":"user","personPatronymic":"user","birthday":"2015-11-11"}}],"emailList":
[{"emailDefault":true,"emailId":1,"emailType":"work","emailValue":"userEmail","person":
{"personId":1,"personName":"user","personSurname":"user","personPatronymic":"user","birthday":"2015-11-11"}}],"person":
{"personId":1,"personName":"user","personSurname":"user","personPatronymic":"user","birthday":"2015-11-11"},"phoneList":
[{"person":{"personId":1,"personName":"user","personSurname":"user","personPatronymic":"user","birthday":"2015-11-11"},"phoneDefault":true,"phoneId":1,"phoneNumber":4443232,"phoneType":"work"}]}
                </textarea>
</div>--%>

</body>
</html>
