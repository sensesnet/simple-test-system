<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Simple test system</title>
</head>

<style type="text/css">

    body {
        text-align: center;
        vertical-align: center;
        font-size: 15px;
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

    .answer {
        font-family: "Courier New", Courier, monospace; /* Семейство шрифта */
        /*margin: 0 0 1em; !* Отступы *!*/
        white-space: pre; /* Учитываются все пробелы и переносы */
        font-size: 16px;
        /*width: 50%;*/
        height: 25px;
        overflow-y: hidden;
        margin: 0px 0px 16px;
        width: 480px;
        text-align: left;
        vertical-align: center;
    }

    .que_title {
        font-weight: bold;
    }

    .test_desc {
        width: 70%;
    }
    .clar {
        width: 70%;
        color: rgba(16, 15, 17, 0.73);
    }
</style>

<body>
<h1>Online Testing Platform</h1>
<div>
    <div class="links">
        <a href="Controller?action=result_list"><span class="more nowrap">Save changes</span></a>
        <a href="Controller?action=result_list"><span class="more nowrap">Admin main page</span></a>
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
        <h2>Edit Test</h2>
        <table border="0" width="30%" cellpadding="5">
            <tr>
                <th><a style="text-align:center;" href="Controller?action=remove_test_question&testId=<c:out value ="${test.getTestId()}"/>">
                    <span class="link">Add New Question</span></a></th>
            </tr>
            <c:forEach items="${testQuestionList}" var="question">
                <tr>
                    <td class="col2">
                        <p class="que_title" align="center">Question ${testQuestionList.indexOf(question)+1}</p>
                        <hr align="center" size="1px" width="500px">
                        <br>
                        <label>Edit test description:</label>
                        <br>
                        <a href="Controller?action=edit_question&questionId=<c:out value ="${question.getTestQuestion().getQuestionId()}"/>">
                            <span class="tableLink">Edit test question</span></a>
                        <a href="Controller?action=remove_test_question&questionId=<c:out value ="${question.getTestQuestion().getQuestionId()}"/>">
                            <span style="text-align:center;" class="tableLink">Remove Question</span></a>
                        <br>
                        <textarea class="test_desc" disabled>${question.getTestQuestion().getQuestionDesc()}</textarea>
                        <hr align="center" size="1px" width="500px">
                        <label>Edit test questions:</label>
                        <br>
                        <c:forEach items="${question.getTestAnswerList()}" var="answer">
                            <c:if test="${question.getTestQuestion().getAnswerId() == answer.getAnswerId()}">
                                <p style="color:green;"><input name="${question.getTestQuestion().getQuestionId()}"
                                          type="radio"
                                          value="${answer.getAnswerId()}"
                                          checked
                                          disabled>
                                        ${answer.getAnswerDescription()}
                                </p>
                            </c:if>
                            <c:if test="${question.getTestQuestion().getAnswerId() != answer.getAnswerId()}">
                                <p><input name="${question.getTestQuestion().getQuestionId()}"
                                          type="radio"
                                          value="${answer.getAnswerId()}"
                                          disabled>
                                        ${answer.getAnswerDescription()}
                                </p>
                            </c:if>
                        </c:forEach>
                        <hr align="center" size="1px" width="500px">
                        <label>Edit test clarification:</label>
                        <br>
                        <br>
                        <div class="clar">${question.getTestQuestion().getQuestionClarification()}
                        </div>
                        <hr align="center" size="1px" width="500px">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="Controller?action=close_session"><span class="more nowrap">Admin main page</span></a>
        <a href="Controller?action=result_list"><span class="more nowrap">Tests list</span></a>
        <hr align="center" size="1px" width="500px">
        <p>Online test system</p>
    </center>
</form>
<script type='text/javascript'>
    var tx = document.getElementsByTagName('textarea');
    for (var i = 0; i < tx.length; i++) {
        tx[i].setAttribute('style', 'height:' + (tx[i].scrollHeight + 20) + 'px;overflow-y:hidden;');
        tx[i].addEventListener("input", OnInput, false);
    }

    function OnInput() {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    }
</script>
</body>
</html>
