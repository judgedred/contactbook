<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Contact form</title>
    <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="resources/js/jquery.toObject.js"></script>
    <script type="text/javascript" src="resources/js/form2js.js"></script>
    <script type="text/javascript" src="resources/js/json2.js"></script>
<%--<script type="text/javascript" src="http://www.datejs.com/build/date.js"></script>--%>
    <%--<script type="text/javascript">
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
    </script>--%>

   <%-- <script type="text/javascript">
        $(document).ready(function() {
         var max_fields      = 10; //maximum input boxes allowed
         var wrapper         = $(".input_fields_wrap"); //Fields wrapper
         var add_button      = $(".add_field_button"); //Add button ID

         var x = 1; //initlal text box count
         $(add_button).click(function(e){ //on add input button click
         e.preventDefault();
         if(x < max_fields){ //max input box allowed
         x++; //text box increment
         var inpName = "mytext[" + x + "]";
         $(wrapper).append('<div><input type="text" name=inpName/><a href="#" class="remove_field">Remove</a></div>'); //add input box
         }
         });

         $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
         e.preventDefault(); $(this).parent('div').remove(); x--;
         })
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
        }
    </script>--%>

    <script type="text/javascript">
        $(document).ready(function() {
            var counterA = -1;
            var counterE = -1;
//            function init() {
                document.getElementById('addAddress').onclick = addAddress;
                addAddress();
//            }
                document.getElementById('addEmail').onclick = addEmail;
                addEmail();

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
                insertHere.parentNode.insertBefore(newFields, insertHere);
            }

//            window.onload = moreFields;

        });
        </script>
</head>
<body>
Contact
<%--<form id="testForm" action="javascript:test()">
    <table>
    &lt;%&ndash;<tr><td><input hidden type="text" value="${model.person.personId}" name="personId" id="personId"></td></tr>&ndash;%&gt;
        <tr>
            <td><label for="person.personName">Имя</label> </td>
            <td><input type="text" value="" name="person.personName" id="person.personName"></td>
        </tr>
        <tr>
            <td><label for="person.personSurname">Фамилия</label> </td>
            <td><input type="text" value="" name="person.personSurname" id="person.personSurname"></td>
        </tr>
        <tr>
            <td><label for="person.personPatronymic">Отчество</label> </td>
            <td><input type="text" value="${model.person.personPatronymic}" name="person.personPatronymic" id="person.personPatronymic"></td>
        </tr>
        <tr>
            <td><label for="person.birthday">Дата рождения</label> </td>
            <td><input type="date" value="${model.person.birthday}" name="person.birthday" id="person.birthday"></td>
        </tr>
        <tr>
        <td><label for="addressList[0].addressValue">Адрес</label> </td>
        <td><input type="text" value="${model.address.addressValue}" name="addressList[0].addressValue" id="addressList[0].addressValue"></td>
    </tr>
        <tr>
            <td><label for="addressList[0].addressDefault">По умолчанию</label> </td>
            <td><input type="checkbox" value="true" name="addressList[0].addressDefault" id="addressList[0].addressDefault"></td>
        </tr>
        <tr>
            <td><label for="addressList[1].addressValue">Адрес</label> </td>
            <td><input type="text" value="${model.address.addressValue}" name="addressList[1].addressValue" id="addressList[1].addressValue"></td>
        </tr>
        <tr>
            <td><label for="addressList[1].addressDefault">По умолчанию</label> </td>
            <td><input type="checkbox" value="true" name="addressList[1].addressDefault" id="addressList[1].addressDefault"></td>
        </tr>

        &lt;%&ndash;<div class="input_fields_wrap">
            <button class="add_field_button">Add More Fields</button>
            <div><input type="text" name="mytext[]"></div>
        </div>&ndash;%&gt;

        <tr>
            <td><input type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>--%>

<%--<h2>JSON</h2>
<pre id="result">
</pre>--%>

<div id="readroot" style="display: none">



        <%--Адресс<br/>--%>
    <tr>
        <td>Адресс</td>
       <td><input type="text" value="" name="addressList[0].addressValue" id="addressList[0].addressValue"></td>
    </tr>
        <tr>
            <td>По умолчанию</td>
        <td><input type="checkbox" value="true" name="addressList[0].addressDefault" id="addressList[0].addressDefault"></td>
        </tr>
            <tr>
                <td><input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" /></td>
            </tr>


        <%--<br />Radio buttons included to test them in Explorer:<br />
    <input type="radio" name="something" value="test1" />Test 1<br />
    <input type="radio" name="something" value="test2" />Test 2--%>

</div>

<form method="post" action="/cgi-bin/show_params.cgi">
<table>
    <span id="writeroot"></span>
    <span id="writeroot2"></span>

    <tr>
        <td><input type="button" id="addAddress" value="Добавить адрес" /></td>
    </tr>
    <tr>
        <td><input type="button" id="addEmail" value="Добавить email" /></td>
    </tr>
    <tr>
        <td><input type="submit" value="Send form" /></td>
    </tr>
</table>
</form>

<div id="readroot2" style="display: none">

    <tr>
        <td>Тип</td>
        <td><input type="text" value="" name="emailList[0].emailType" id="emailList[0].emailType"></td>
    </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" value="" name="emailList[0].emailValue" id="emailList[0].emailValue"></td>
        </tr>
    <tr>
        <td>По умолчанию</td>
        <td><input type="checkbox" value="true" name="emailList[0].emailDefault" id="emailList[0].emailDefault"></td>
    </tr>
    <tr>
        <td><input type="button" value="Убрать" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" /></td>
    </tr>

</div>

<pre><code id="testArea">
</code></pre>
</body>
</html>

