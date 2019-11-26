<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        margin-left:5px;
        white-space: nowrap;
    }

    input[type=password], input[type=submit], input[type=text]{

        font-size: 17px;
        line-height: 1.29412;
        font-weight: 400;
        letter-spacing: -.021em;
        font-family: SF Pro Text,SF Pro Icons,Helvetica Neue,Helvetica,Arial,sans-serif;
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

    input[type=submit]{
        background-color: rgb(240, 240, 240);
        text-align: center;
        vertical-align: center;
    }
    input[type=reset]{
        font-size: 17px;
        font-weight: 400;
        font-family: SF Pro Text,SF Pro Icons,Helvetica Neue,Helvetica,Arial;
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

    label{
        text-align: center;
        vertical-align: center;
    }

</style>
<body>

<form method="post" action="Controller">
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
                <td><input type="text" name="login" value="" placeholder="Login"/></td>
            </tr>
            <tr>
                <td><input type="text" name="password" value="" placeholder="Password"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Sign In"/>
                    <br><input type="reset" value="Reset form"/>
                    <br><label colspan="2">Probably you  haven't an account.</label><a href="signUp.jsp">Sign Up</a>
                </td>
            </tr>
            </tbody>
        </table>
    </center>
</form>
</body>
</html>
