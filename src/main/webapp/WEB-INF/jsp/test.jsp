<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title></title>
    <script type="text/javascript" src="resources/js/form2js.js"></script>
    <script type="text/javascript" src="resources/js/json2.js"></script>
    <script type="text/javascript">
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
    </script>
</head>

<body>

<form id="testForm" action="javascript:test()">
    <dl>
        <dt><label for="person.nameFirstDisabled">Disabled field:</label></dt>
        <dd><input id="person.nameFirstDisabled" disabled="disabled" type="text" name="person.name.firstDisabled" value="test" /></dd>
    </dl>

    <dl>
        <dt><label for="person.nameFirstEmptyName">Field with empty name:</label></dt>
        <dd><input id="person.nameFirstEmptyName" type="text" name="" value="testEmptyName" /></dd>
    </dl>

    <dl>
        <dt><label for="person.nameFirstMissingName">Field with missing name:</label></dt>
        <dd><input id="person.nameFirstMissingName" type="text" value="testMissingName" /></dd>
    </dl>

    <dl>
        <dt><label>Field with empty name missing id:</label></dt>
        <dd><input type="text" name="" value="testEmptyNameMissingId" /></dd>
    </dl>

    <dl>
        <dt><label>Field with missing name missing id:</label></dt>
        <dd><input type="text" value="testMissingNameMissingId" /></dd>
    </dl>

    <dl>
        <dt><label for="nameFirst">First name:</label></dt>
        <dd><input id="nameFirst" type="text" name="person.name.first"/></dd>
    </dl>

    <dl>
        <dt><label for="nameLast">Last name:</label></dt>
        <dd><input id="nameLast" type="text" name="person.name.last"/></dd>
    </dl>

    <dl>
        <dt>Gender:</dt>
        <dd><label><input type="radio" name="person.gender" id="genderMale" value="male" /> Male</label></dd>
        <dd><label><input type="radio" name="person.gender" id="genderFemale" value="female" /> Female</label></dd>
    </dl>

    <dl>
        <dt>City</dt>
        <dd>
            <select name="person.city">
                <optgroup label="Russia">
                    <option value="msk">Moscow</option>
                    <option value="spb">St. Petersburg</option>
                    <option value="nsk">Novosibirsk</option>
                    <option value="ekb">Ekaterinburg</option>
                </optgroup>
                <optgroup label="Europe">
                    <option value="london">London</option>
                    <option value="paris">Paris</option>
                    <option value="madrid">Madrid</option>
                </optgroup>
            </select>
        </dd>
    </dl>

    <dl>
        <dt>Favorite food</dt>
        <dd><label><input type="checkbox" name="person.favFood[]" value="steak"/> Steak</label></dd>
        <dd><label><input type="checkbox" name="person.favFood[]" value="pizza"/> Pizza</label></dd>
        <dd><label><input type="checkbox" name="person.favFood[]" value="chicken"/> Chicken</label></dd>
    </dl>

    <dl>
        <dt>Bool checkbox</dt>
        <dd><label><input type="checkbox" name="person.agreeToKillAllHumanz" value="true"/> I agree to kill all humanz</label></dd>
    </dl>

    <dl>
        <dt>Choose some colors</dt>
        <dd>
            <select name="person.colors" multiple="multiple">
                <optgroup label="Warm">
                    <option value="green">Green</option>
                    <option value="orange">Orange</option>
                    <option value="red">Red</option>
                </optgroup>
                <optgroup label="Cold">
                    <option value="blue">blue</option>
                    <option value="purple">purple</option>
                </optgroup>
            </select>
        </dd>
    </dl>

    <dl>
        <dt>Multiple select (ZendForm syntax)</dt>
        <dd>
            <select name="person.colorsZend[]" multiple="multiple">
                <optgroup label="Warm">
                    <option value="green">Green</option>
                    <option value="orange">Orange</option>
                    <option value="red">Red</option>
                </optgroup>
                <optgroup label="Cold">
                    <option value="blue">blue</option>
                    <option value="purple">purple</option>
                </optgroup>
            </select>
        </dd>
    </dl>

    <dl>
        <dt>Give us your five friends' names and emails</dt>
        <dd>
            <label>Email0 <input type="text" name="person.friends[0].emails[0].email" value="test1" /></label>
            <label>Email1 <input type="text" name="person.friends[0].emails[1].email" value="test2" /></label>
            <label>Name <input type="text" name="person.friends[0].name"/></label>
        </dd>
        <dd>
            <label>Email0 <input type="text" name="person.friends[1].emails[0].email" value="test3" /></label>
            <label>Email1 <input type="text" name="person.friends[1].emails[1].email" value="test4" /></label>
            <label>Name <input type="text" name="person.friends[1].name"/></label>
        </dd>
        <dd>
            <label>Email0 <input type="text" name="person.friends[2].emails[0].email" value="test5" /></label>
            <label>Email1 <input type="text" name="person.friends[2].emails[1].email" value="test6" /></label>
            <label>Name <input type="text" name="person.friends[2].name"/></label>
        </dd>
        <dd>
            <label>Email0 <input type="text" name="person.friends[3].emails[0].email" value="test7" /></label>
            <label>Email1 <input type="text" name="person.friends[3].emails[1].email" value="test8" /></label>
            <label>Name <input type="text" name="person.friends[3].name"/></label>
        </dd>
        <dd>
            <label>Email0 <input type="text" name="person.friends[4].emails[0].email" value="test9" /></label>
            <label>Email1 <input type="text" name="person.friends[4].emails[1].email" value="test0" /></label>
            <label>Name <input type="text" name="person.friends[4].name"/></label>
        </dd>
    </dl>

    <dl>
        <dt>Multiple select inside an array (ZendForm syntax)</dt>
        <dd>
            <select name="person.friends[4].colorsZend[]" multiple="multiple">
                <optgroup label="Warm">
                    <option value="green">Green</option>
                    <option value="orange">Orange</option>
                    <option value="red">Red</option>
                </optgroup>
                <optgroup label="Cold">
                    <option value="blue">blue</option>
                    <option value="purple">purple</option>
                </optgroup>
            </select>
        </dd>
    </dl>

    <dl>
        <dt>Array of arrays test</dt>
        <dd>
            <label>person.array[0][0]<input type="text" name="person.array[0][0]" value="test0-0" /></label>
            <label>person.array[0][1]<input type="text" name="person.array[0][1]" value="test0-1" /></label>
            <label>person.array[0][2]<input type="text" name="person.array[0][2]" value="test0-2" /></label>
        </dd>
        <dd>
            <label>person.array[1][0]<input type="text" name="person.array[1][0]" value="test1-0" /></label>
            <label>person.array[1][1]<input type="text" name="person.array[1][1]" value="test1-1" /></label>
            <label>person.array[1][2]<input type="text" name="person.array[1][2]" value="test1-2" /></label>
        </dd>
    </dl>

    <dl>
        <dt>Ruby-style test</dt>
        <dd>
            <label>person.ruby[field_1][foo]<input type="text" name="person.ruby[field_1][foo_baz]" value="baz" /></label>
            <label>person.ruby[field_1][bar]<input type="text" name="person.ruby[field_1][bar_baz]" value="qux" /></label>
        </dd>
        <dd>
            <label>person.ruby[field2][foo]<input type="text" name="person.ruby[field2][foo]" value="baz" /></label>
            <label>person.ruby[field2][bar]<input type="text" name="person.ruby[field2][bar]" value="qux" /></label>
        </dd>
    </dl>

    <dl>
        <dt>Fieldset test</dt>
        <dd>
            <fieldset><legend>Fieldset</legend>
                <input type="text" name="person.fieldset.foo" value="baz" />
                <input type="text" name="person.fieldset.bar" value="qux" />
            </fieldset>
        </dd>
    </dl>

    <dl>
        <dt>Textarea test</dt>
        <dd>
            <textarea rows="5" cols="20" name="person.bio">Some bio here, just for test.</textarea>
        </dd>
    </dl>

    <dl>
        <dt>Tables test</dt>
        <dd>
            <table>
                <tr>
                    <td>
                        <select name="table.sides[0].player" id="p1" style="width:100px">
                            <option value="spartacus">Spartacus</option>
                            <option value="dinamo">Dinamo</option>
                        </select>
                    </td>
                    <td align="center">
                        vs
                    </td>
                    <td>
                        <select name="table.sides[1].player" id="p2" style="width:100px">
                            <option value="spartacus">Spartacus</option>
                            <option value="dinamo">Dinamo</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <select name="table.sides[0].score" id="s1" style="width:100px">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        </select>
                    </td>
                    <td align="center">
                        -
                    </td>
                    <td>
                        <select name="table.sides[1].score" id="s2" style="width:100px">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        </select>
                    </td>
                </tr>
            </table>

        </dd>
    </dl>

    <dl>
        <dt>Custom callback test:</dt>
        <dd>
            <div id="person.callbackTest">test test test</div>
        </dd>
    </dl>

    <dl>
        <dt></dt>
        <dd><input type="submit" /></dd>
    </dl>
</form>

<pre><code id="testArea">
</code></pre>


</body>
</html>