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
        font-size: 13px;
        line-height: 1.47059;
        font-weight: 400;
        letter-spacing: -0.022em;
        font-family: "SF Pro Text", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
        background-color: white;
        color: #635858;
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
        font-size: 16px;
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

    .tableLink {
        z-index: 4;
        font-size: 13px;
        line-height: 3;
        text-decoration: none;
        text-decoration-style: initial;
        text-decoration-color: initial;
        text-align: left;
        color: -webkit-link;
        cursor: pointer;
        color: #0070c9;
        display: inline-block;
        text-decoration: inherit;
        text-decoration-line: inherit;
        white-space: nowrap;
    }

    hr {
        border: 0;
        height: 0;
        border-bottom: 1px solid #dedede;
        clear: both;
    }

    textarea {
        overflow-y: scroll;
        height: 100px;
        resize: none; /* Remove this if you want the user to resize the textarea */
    }


    table {
        width: 70%;
        text-align: left;
    }

    .col1 {
        width: 200px;
        font-weight: bold;
    }

    .col2 {
        width: 400px;
    }

    .col3 {
        width: 5px;
        text-align: center;
    }

    .col4 {
        width: 5px;
        text-align: center;
    }

    .col5 {
        width: 5px;
        text-align: left;
        line-height: 0.5em;
    }
    .tableLink{
        line-height: 30px;
    }
</style>

<body>
<h1>Online Testing Platform</h1>
<div>
    <div class="links">
        <a href="Controller?action=home"><span class="more nowrap">Admin page</span></a>
        <a href="Controller?action=add_new_test"><span class="more nowrap">Add new test</span></a>
        <a href="Controller?action=close_session"><span class="more nowrap">Sign Out</span></a>
    </div>
</div>
<c:if test="${not empty param.errorMessage }">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>
<c:if test="${not empty param.message }">
    <c:out value="${param.message}"></c:out>
</c:if>
<form action="Controller">
    <center>
        <h2>List of Tests</h2>
        <table border="0" width="30%" cellpadding="5">
            <tr>
                <th>test_name</th>
                <th>test_description</th>
                <th>test_value</th>
                <th>test_time</th>
                <th>test_action</th>
            </tr>
            <c:forEach items="${testList}" var="test">
                <tr>
                    <td colspan="5"><hr size="1px" width="800px"></td>
                </tr>
                <tr>
                    <td class="col1">${test.getTestName()}</td>
                    <td class="col2">
                            ${test.getTestDescription()}
                    </td>
                    <td class="col3">${test.getTestValue()}</td>
                    <td class="col4">${test.getTestTime()}</td>
                    <td class="col5">
                        <a href="Controller?action=start_test&testId=<c:out value ="${test.getTestId()}"/>">
                            <span class="tableLink">Start</span>
                        </a><br>
                        <a href="Controller?action=test_edit&testId=<c:out value ="${test.getTestId()}"/>">
                            <span class="tableLink">Edit</span>
                        </a><br>
                        <a href="Controller?action=remove_test&testId=<c:out value ="${test.getTestId()}"/>">
                            <span class="tableLink">Remove</span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5"><hr size="1px" width="800px"></td>
            </tr>
        </table>
        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</form>
</body>
</html>
