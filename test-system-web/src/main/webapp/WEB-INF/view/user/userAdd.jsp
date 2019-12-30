<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <title>Add New User</title>
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
        font-size: 18px;
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
        margin-left: 30px;
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
<h3>Online test system</h3>
<div>
    <div class="links">
        <a href="Controller?action=home"><span class="headLink">User main page</span></a>
        <a href="Controller?action=close_session"><span class="headLink">Sign Out</span></a>
    </div>
</div>
<form method="POST" action="Controller" name="frmAddUser">
    <input type="hidden" name="action" value="user_new_save"/>
    <center>
        <table border="0" width="30%" cellpadding="5">
            <tbody>
            <tr>
                <td>
                    <h3>New User Details</h3>
                    <c:if test="${not empty param.errorMessage }">
                        <c:out value="${param.errorMessage}"></c:out>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="firstName"
                           value=""
                           placeholder="First name"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="^[a-zA-Z]+$"
                           title="Please, set your first name. Use only later."
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="secondName"
                           value=""
                           placeholder="Second name"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="^[a-zA-Z]+$"
                           title="Please, set your second name. Use only later."
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="birthday"
                           value=""
                           placeholder="Date of birth"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="^\d{4}-\d{2}-\d{2}$"
                           title="'Please, use format like 'yyyy-mm-dd'"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="address"
                           value=""
                           placeholder="Local address"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           title="'Please, set address'"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="phone"
                           value=""
                           placeholder="Phone"
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           pattern="(8 0(25|29|33|34) ([0-9]{3}( [0-9]{2}){2}))"
                           title="like, '8 0xx xxx xx xx'"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input
                            id="textEmail"
                            type="text"
                            name="login"
                            value=""
                            placeholder="Login"
                            readonly
                            onfocus="this.removeAttribute('readonly')"
                            pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                            title="'Please, use valid email.'"
                            required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password"
                           name="password"
                           value=""
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           placeholder="Password"
                           pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$"
                           title="Your password must be at least 8 characters and include the following: 1 uppercase letter, 1 lowercase letter and 1 number."
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password"
                           name="confirmPassword"
                           value=""
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           placeholder="Password again"
                           pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$"
                           title="Your password must be at least 8 characters and include the following: 1 uppercase letter, 1 lowercase letter and 1 number."
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text"
                           name="role"
                           value=""
                           readonly
                           onfocus="this.removeAttribute('readonly')"
                           placeholder="Role"
                           pattern="^(ADMIN|USER)"
                           title="'ADMIN' or 'USER'"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="submit" value="Submit"/>
                    <br><input type="reset" value="Reset"/>
                </td>
            </tr>
            </tbody>
        </table>
    </center>
</form>
<script>
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
