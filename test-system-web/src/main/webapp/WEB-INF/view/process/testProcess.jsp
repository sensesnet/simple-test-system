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

<c:if test="${not empty param.errorMessage }">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>
<c:if test="${not empty param.message }">
    <c:out value="${param.message}"></c:out>
</c:if>
<form method="post" action="Controller" name="frmTestProcess">
    <input type="hidden" name="action" value="finish_test"/>
    <center>
        <span id="timer" style="color: #d6d6d6; font-size: 120%;
                        font-weight: bold;">00:15:30</span>

        <table border="0" width="30%" cellpadding="5">
<%--            <div class="links">--%>
<%--                <center>--%>
<%--                    <c:forEach items="${testQuestionList}" var="question">--%>
<%--                        <a href="Controller?action=open_test&questionId=${question.getTestQuestion().getQuestionId()}"><span--%>
<%--                                class="pagination">${testQuestionList.indexOf(question)+1}</span></a>--%>
<%--                    </c:forEach>--%>
<%--                </center>--%>
<%--            </div>--%>
            <c:forEach items="${testQuestionList}" var="question">
                <tr>
                    <td class="col2">
                        <p class="que_title" align="center">Question ${testQuestionList.indexOf(question)+1}</p>
                        <hr align="center" size="1px" width="500px">
                        <span class="test_desc">${question.getTestQuestion().getQuestionDesc()}</span>
                        <br>
                        <c:forEach items="${question.getTestAnswerList()}" var="answer">
                            <p><input name="question_${question.getTestQuestion().getQuestionId()}" type="radio"
                                      value="${answer.getAnswerId()}"> ${answer.getAnswerDescription()}</p>
                        </c:forEach>
                        <hr align="center" size="1px" width="500px">

                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Finish test"/>
        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</form>
</body>
<script>
    function startTimer() {
        var timer = document.getElementById("timer");
        var time = timer.innerHTML;
        var arr = time.split(":");
        var hh = arr[0];
        var mm = arr[1];
        var ss = arr[2];
        if (ss == 0) {
            if (mm == 0) {
                if (hh == 0) {
                    alert("Test has been finished.");
                    var x = document.getElementsByClassName("form");
                    x[0].submit();
                    return;
                }
                hh--;
                mm = 60;
                if (hh < 10)
                    hh = "0" + hh;
            }
            mm--;
            if (mm < 10)
                mm = "0" + mm;
            ss = 59;
        } else ss--;
        if (ss < 10)
            ss = "0" + ss;
        document.getElementById("timer").innerHTML = hh+":"+mm+":"+ss;
        setTimeout(startTimer, 1000);
    }
</script>
</html>
