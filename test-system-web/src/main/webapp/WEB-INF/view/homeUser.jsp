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
        font-size: 16px;
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
    table {
        width: 80%;
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
        width: 200px;
        text-align: center;
    }

    .col4 {
        width: 5px;
        text-align: center;
    }

    .col5 {
        width: 10px;
        text-align: left;
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
    table{
        text-align: center;
        vertical-align: center;
        font-size: 13px;
    }
</style>

<body>
<h1>Online Testing Platform</h1>
<c:if test="${not empty param.errorMessage }">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>
<c:if test="${not empty param.message }">
    <c:out value="${param.message}"></c:out>
</c:if>
<div>
    <h3 class="headline">'User' role</h3>
    <div class="links">
        <a href="Controller?action=test_view"><span class="more nowrap">Find test</span></a>
        <a href="Controller?action=assigned_tests_view"><span class="more nowrap">Assigned tests</span></a>
        <a href="Controller?action=change_password"><span class="more nowrap">Change password</span></a>
        <a href="Controller?action=close_session"><span class="more nowrap">Sign Out</span></a>
    </div>
</div>

<p>
    'User' role gives you main capabilities for:
    <br>- Find and complete available & assigned tests
    <br>- Investigate own test results
</p>

<form action="Controller">
    <center>
        <h4>Test Result History</h4>
        <c:if test="${not empty param.message }">
            <c:out value="${param.message}"></c:out>
        </c:if>
        <table border="0" width="30%" cellpadding="5">
            <tr>
                <th>test_name</th>
                <th>test_description</th>
                <th>test_date</th>
                <th>result</th>
                <th>is_completed</th>
                <th>test_action</th>
            </tr>
            <c:forEach items="${resultHistory}" var="test">
                <tr>
                    <td class="col1">${test.getTest().getTestName()}</td>
                    <td class="col2">
                        <hr align="center" size="1px" width="500px">
                            ${test.getTest().getTestDescription()}
                        <hr align="center" size="1px" width="500px">
                    </td>
                    <td class="col3">${test.getTestProcess().getTestProcessDate()}</td>
                    <td class="col4">${test.getTestProcess().getMainResultValue()}/${test.getTest().getTestValue()}</td>
                    <td class="col4">${test.getTestProcess().isCompleted()}</td>
                    <td class="col5">
                        <a href="Controller?action=investigate_test&testProcessId=<c:out value ="${test.getTestProcess().getTestProcessId()}"/>">
                            <span class="tableLink">Investigate</span>
                        </a>
                        <a href="Controller?action=start_test&testId=<c:out value ="${test.getTest().getTestId()}"/>">
                            <span class="tableLink">Try again</span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</form>
<hr align="center" size="1px" width="500px">
<p>Online test system</p>
</body>
</html>
