package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Constant: page path
 */
public class PagePath
{


    public final String INDEX_PAGE = "index.jsp";
    public final String SIGN_IN_PAGE = "signIn.jsp";
    public final String SIGN_UP_PAGE = "signUp.jsp";

    /*
    Home admin and user pages
     */
    public final String HOME_USER_PAGE = "/WEB-INF/view/homeUser.jsp";
    public final String HOME_ADMIN_PAGE = "/WEB-INF/view/homeAdmin.jsp";

    /*
    test.user
     */
    public final String USER_ADD_PAGE = "/WEB-INF/view/user/userAdd.jsp";
    public final String USER_EDIT_PAGE = "/WEB-INF/view/user/userEdit.jsp";
    public final String USER_HISTORY_PAGE = "/WEB-INF/view/user/userHistory.jsp";
    public final String USER_LIST_PAGE = "/WEB-INF/view/user/userList.jsp";
    public final String CHANGE_PASS = "/WEB-INF/view/user/changePassword.jsp";;
    /*
    test.process
     */
    public final String TEST_ADD_PAGE = "/WEB-INF/view/process/testAdd.jsp";
    public final String TEST_EDIT_PAGE = "/WEB-INF/view/process/testEdit.jsp";
    public final String TEST_PROCESS_PAGE = "/WEB-INF/view/process/testProcess.jsp";
    public final String TEST_INVESTIGATE_PAGE = "/WEB-INF/view/process/testInvestigate.jsp";
    public final String TEST_RESULT_PAGE = "/WEB-INF/view/process/testResult.jsp";
    public final String TEST_LIST_FOR_USER_PAGE = "/WEB-INF/view/process/testListForUser.jsp";
    public final String TEST_LIST_FOR_ADMIN_PAGE = "/WEB-INF/view/process/testListForAdmin.jsp";
    public final String TEST_EDIT = "/WEB-INF/view/process/testEdit.jsp";

    public final String QUESTION_EDIT_PAGE = "/WEB-INF/view/process/questionEdit.jsp";
    public final String ANSWERS_EDIT_PAGE = "/WEB-INF/view/process/answersEdit.jsp";
    public final String CLARIFICATION_EDIT_PAGE = "/WEB-INF/view/process/clarificationEdit.jsp";
    public final String ADD_NEW_ANSWER_PAGE = "/WEB-INF/view/process/addNewAnswer.jsp";

    /*
    error
     */
    public final String AUTH_ERROR_PAGE = "/WEB-INF/view/error/authError.jsp";
    public final String NOT_FOUND_PAGE = "/WEB-INF/view/error/400.jsp";
}
