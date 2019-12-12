<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        margin-left: 30px;
        white-space: nowrap;
    }
</style>

<body>
<h1>Online Testing Platform</h1>
<p><a href="Controller?action=closeSessionCommand">Log out</a></p>


<form action="Controller">
    <h2>List of User</h2>
    <table border="1">
        <tr>
            <th>id</th>
            <th>first_name</th>
            <th>Last Name</th>
            <th>login</th>
            <th>PASSWORD</th>
            <th>email</th>
            <th>credits</th>
            <th>status</th>

        </tr>
        <c:forEach items="${user}" var="user">
            <tr>
                <td>${user.getUSER_ID()}</td>
                <td>${user.getFIRST_NAME()}</td>
                <td>${user.getSECOND_NAME()}</td>
                <td>${user.getLOGIN()}</td>
                <td>${user.getPASSWORD()}</td>
                <td>${user.getEMAIL()}</td>
                <td>${user.getCREDITS()}</td>
                <td>${user.getSTATUS()}</td>

                <td><a
                        href="Controller?action=editUser&userID=<c:out value ="${user.getUSER_ID()}"/>">Edit</a></td>
                <td><a
                        href="Controller?action=removeUser&userID=<c:out value ="${user.getUSER_ID()}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="Controller?action=addUserFormCommand">Add new User</a></p>
</form>
<p><a href="Controller?action=showOrderCommand">Admin main page</a></p>
</body>
</html>
