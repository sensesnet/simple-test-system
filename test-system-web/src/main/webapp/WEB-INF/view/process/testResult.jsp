<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/timer.js" type="text/javascript"></script>
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
        color: #a09898;
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
        color: #a09898;
        font-style: normal;
        margin: 0px 0px 7px;
    }

    input[type=password], input[type=button], input[type=text] {

        font-size: 17px;
        line-height: 1.29412;
        font-weight: 400;
        letter-spacing: -.021em;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial, sans-serif;
        display: inline-block;
        box-sizing: border-box;
        vertical-align: top;
        width: 30%;
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

    input[type=button] {
        background-color: rgb(240, 240, 240);
        text-align: center;
        vertical-align: center;
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

    hr {
        border: 0;
        height: 0;
        border-bottom: 1px solid #dedede;
        clear: both;
    }

</style>

<body onload="startTimer()">
<h1>Online Testing Platform</h1>
<div>
    <div class="links">
        <a href="Controller?action=start_test&testId=${testId}"><span class="more nowrap">Restart test</span></a>
        <a href="Controller?action=home"><span class="more nowrap">Main user</span></a>
        <a href="Controller?action=test_view"><span class="more nowrap">List of tests</span></a>
        <a href="Controller?action=close_session"><span class="more nowrap">Sign Out</span></a>
    </div>
</div>
<h2>Test Result</h2>
<h2>${result}/${numberOfQuestion}</h2>
<h2>
    <c:if test="${isCompleted == true}">
        <c:out value="Test has been completed!"></c:out>
    </c:if>
    <c:if test="${isCompleted != true}">
        <c:out value="Test has NOT been completed!"></c:out>
    </c:if>
</h2>
<input type="button" value="Investigate test result" onclick="investigate()">

<c:if test="${not empty param.errorMessage }">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>
<c:if test="${not empty param.message }">
    <c:out value="${param.message}"></c:out>
</c:if>
<hr align="center" size="1px" width="500px">
<p>Online test system</p>
</body>
<script>
    function investigate() {
        document.location.href = "Controller?action=investigate_test&testProcessId=${testProcessId}";
    }
</script>
</html>
