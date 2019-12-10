<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <title>Simple test system</title>
</head>
<style type="text/css">

    body {
        text-align: center;
        vertical-align: center;
        font-size: 17px;
        line-height: 1.47059;
        font-weight: 400;
        letter-spacing: -0.022em;
        font-family: "SF Pro Text", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
        background-color: white;
        color: #333333;
        font-style: normal;
        top: 50%;
        left: 50%;
    }

    h1 {
        text-align: center;
        vertical-align: center;
        color: #333333;
        display: block;
        width: 100%;
        margin-left: auto;
        margin-right: auto;
        font-size: 44px;
        line-height: 1.09091;
        font-weight: 600;
        letter-spacing: -.002em;
        font-family: "SF Pro Display", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    }

    h3 {
        text-align: center;
        vertical-align: center;
        font-size: 28px;
        letter-spacing: -0.022em;
        font-family: "SF Pro Display", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
        background-color: white;
        color: #333333;
        font-style: normal;
        margin: 0px 0px 7px;
    }

    a {

        z-index: 4;
        font-size: 20px;
        line-height: 3;
        text-decoration: none;
        text-decoration-line: none;
        text-decoration-style: initial;
        text-decoration-color: initial;
        color: -webkit-link;
        cursor: pointer;
        color: #0070c9;
        display: inline-block;
        text-decoration: inherit;
        text-decoration-line: inherit;
        margin-left: 5px;
        white-space: nowrap;
    }

    input[type=password], input[type=submit], input[type=text] {

        font-size: 17px;
        line-height: 1.29412;
        font-weight: 400;
        letter-spacing: -.021em;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial, sans-serif;
        display: inline-block;
        box-sizing: border-box;
        vertical-align: top;
        width: 100%;
        height: 34px;
        margin-bottom: 14px;
        padding-left: 15px;
        padding-right: 15px;
        color: #333;
        text-align: left;
        border: 1px solid #d6d6d6;
        border-radius: 4px;
        background: #fff;
        background-clip: padding-box;
    }

    input[type=submit] {
        background-color: rgb(240, 240, 240);
        text-align: center;
        vertical-align: center;
    }

    input[type=reset] {
        font-size: 17px;
        font-weight: 400;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial;
        width: 100%;
        height: 34px;
        margin-bottom: 14px;
        padding-left: 15px;
        padding-right: 15px;
        color: #333;
        background: #fff;
        text-align: right;
        vertical-align: center;
        border: 0;
        outline: none;
    }

    label {
        text-align: center;
        vertical-align: center;
    }

    input:invalid {
        border-color: red;
    }

    input,
    input:valid {
        border-color: #ccc;
    }

</style>
<body>

<form method="post" action="LoginFilter" required>
    <center>
        <p>Online test system</p>
        <table border="0" width="30%" cellpadding="3">
            <thead>
            <tr>
                <th colspan="2"><h3>Sign In</h3></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <input id="textEmail"
                           name="login"
                           type="text"
                           value=""
                           placeholder="Login"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                           title="Please, use valid email."
                           required/>
                </td>
            </tr>
            <tr>
                <td><input id="textPassword"
                           name="password"
                           type="password"
                           value=""
                           placeholder="Password"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$"
                           title="Your password must be at least 8 characters and include the following: 1 uppercase letter, 1 lowercase letter and 1 number."
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="submit" value="Submit"/>
                    <br><input type="reset" value="Reset"/>
                    <c:if test="${not empty param.errorMessage }">
                        <c:out value="${param.errorMessage}"></c:out>
                    </c:if>
                    <br><label colspan="2">Probably you haven't an account.</label><a href="signUp.jsp">Sign Up</a>
                </td>
            </tr>
            </tbody>
        </table>
    </center>
</form>
<script>


    // var input = document.getElementById('textEmail');
    // input.oninvalid = function (event) {
    //     event.target.setCustomValidity("");
    //     if (!event.target.validity.valid)
    //         event.target.setCustomValidity('Please, use valid email.');
    // }

    // var password = document.getElementById('textPassword');
    // password.oninvalid = function (event) {
    //     event.target.setCustomValidity('Your password must be at least 8 characters and include the following: 1 uppercase letter, 1 lowercase letter and 1 number.');
    // }

    $("form").submit(function (e) {
        var ref = $(this).find("[required]");
        $(ref).each(function () {
            $(this).focus();
            if ($(this).val() === '') {
                $(this).focus();
                e.preventDefault();
                return false;
            }
        });
        return true;
    });

</script>
</body>
</html>
