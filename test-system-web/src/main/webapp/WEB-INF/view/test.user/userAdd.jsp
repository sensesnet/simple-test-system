<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    a{
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
        margin-left:30px;
        white-space: nowrap;
    }
</style>

<body>
<form method="POST" action="Controller" name="frmAddUser">
    <p>Online test system</p>
    <input type="hidden" name="action" value="AddUser"/>
    <table border="0" width="30%" cellpadding="5">
        <tr>
            <td><h3>New User Details</h3></td>
        </tr>
        <tr>
            <td><input type="text" name="firstName" placeholder="First Name"/></td>
        </tr>
        <tr>
            <td><input type="text" name="secondName" placeholder="Last Name"/></td>
        </tr>
        <tr>
            <td><input type="text" name="login" placeholder="Login"/></td>
        </tr>
        <tr>
            <td><input type="text" name="password" placeholder="Password"/></td>
        </tr>
        <tr>
            <td><input type="text" name="email" placeholder="Email"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/>
                <br><input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>
