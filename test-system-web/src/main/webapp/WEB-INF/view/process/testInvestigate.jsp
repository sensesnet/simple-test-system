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

    input[type=password], input[type=submit], input[type=text] {

        font-size: 17px;
        line-height: 1.29412;
        font-weight: 400;
        letter-spacing: -.021em;
        font-family: SF Pro Text, SF Pro Icons, Helvetica Neue, Helvetica, Arial, sans-serif;
        display: inline-block;
        box-sizing: border-box;
        vertical-align: top;
        width: 50%;
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

    .pagination {
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
        margin-left: 40px;
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
        width: 5px;
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

    .test_desc {
        font-family: "Courier New", Courier, monospace; /* Семейство шрифта */
        margin: 0 0 1em; /* Отступы */
        white-space: pre; /* Учитываются все пробелы и переносы */
        font-size: 16px;
    }

    .que_title {
        font-weight: bold;
    }

    input[type="radio"] {
        -ms-transform: scale(2);
        -moz-transform: scale(2);
        -o-transform: scale(2);
        height: 18px;
        width: 18px
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

<c:if test="${not empty param.errorMessage }">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>
<c:if test="${not empty param.message }">
    <c:out value="${param.message}"></c:out>
</c:if>
<form method="post" action="Controller" name="frmTestProcess">
    <input type="hidden" name="action" value="finish_test"/>
    <center>
        <table border="0" width="30%" cellpadding="5">
            <c:forEach items="${testResultList}" var="result">
                <tr>
                    <td class="col2">
                        <p class="que_title" align="center">Question ${testResultList.indexOf(result)+1}</p>
                        <hr align="center" size="1px" width="500px">
                        <span class="test_desc">${result.getTestQuestion().getQuestionDesc()}</span>
                        <br>
                        <c:forEach items="${result.getTestAnswerList()}" var="answer">
                            <div>
                                Answer Id:${answer.getAnswerId()}
                                Right answer: ${result.getTestQuestion().getAnswerId()}
                                Result: ${result.getTestResult().getAnswerId()}
                            </div>
                            <c:set var="answerId" value='${answer.getAnswerId()}'/>
                            <c:set var="rightAnswerId" value='${result.getTestQuestion().getAnswerId()}'/>
                            <c:set var="selectedAnswerId" value='${result.getTestResult().getAnswerId()}'/>
                            <%-- верный ответ  --%>
                            <c:if test="${rightAnswerId.equals(answerId)}">
                                <%-- выбран  --%>
                                <c:choose>
                                    <c:when test="${selectedAnswerId.equals(rightAnswerId)}">
                                        <div style="color:green;">
                                            <input
                                                    name="question_${result.getTestQuestion().getQuestionId()}"
                                                    type="radio"
                                                    value="${answer.getAnswerId()}"
                                                    checked
                                                    disabled>
                                                ${answer.getAnswerDescription()}
                                        </div>
                                    </c:when>
                                    <%-- не выбран  --%>
                                    <c:when test="${!selectedAnswerId.equals(rightAnswerId)}">
                                        <div style="color:green;">
                                            <input
                                                    name="question_${result.getTestQuestion().getQuestionId()}"
                                                    type="radio"
                                                    value="${answer.getAnswerId()}"
                                                    disabled>
                                                ${answer.getAnswerDescription()}
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <%-- неверный ответ  --%>
                            <c:if test="${!rightAnswerId.equals(answerId)}">
                                <%-- выбран  --%>
                                <c:choose>
                                    <c:when test="${selectedAnswerId.equals(answerId)}">
                                        <div style="color:red;">
                                            <input
                                                    name="question_${result.getTestQuestion().getQuestionId()}"
                                                    type="radio"
                                                    value="${answer.getAnswerId()}"
                                                    checked
                                                    disabled>
                                                ${answer.getAnswerDescription()}
                                        </div>
                                    </c:when>
                                    <%-- не выбран  --%>
                                    <c:when test="${!selectedAnswerId.equals(answerId)}">
                                        <div>
                                            <input
                                                    name="question_${result.getTestQuestion().getQuestionId()}"
                                                    type="radio"
                                                    value="${answer.getAnswerId()}"
                                                    disabled>
                                                ${answer.getAnswerDescription()}
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                        <hr align="center" size="1px" width="500px">
                        <div style="color:green;">
                                ${result.getTestQuestion().getQuestionClarification()}
                        </div>
                        <hr align="center" size="1px" width="500px">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</form>
</body>
</html>
