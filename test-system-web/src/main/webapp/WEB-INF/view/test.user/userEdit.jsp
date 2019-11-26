<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Current User</title>
</head>
<body>
<form method="POST" action="Controller" name="frmEditUser">
    <input type="hidden" name="action" value="saveEditUser"/>
    <table>
        <tr>
            <td>User ID</td>
            <td><input type="text" name="userId" readonly="readonly"
                       value="${userID}"></td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="${user.getFirstName()}" /></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="secondName" value="${user.getSecondName()}" /></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value="${user.getLogin()}" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${user.getPassword()}" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${user.getAddress}" /></td>
        </tr>
        <tr>
            <td>Credits</td>
            <td><input type="text" name="credits" value="${user.CREDITS}" /></td>
        </tr>
        <tr>
            <td>Role</td>
            <td><input type="text" name="role" value="${user.getRole()}" /></td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
</body>
</html>
