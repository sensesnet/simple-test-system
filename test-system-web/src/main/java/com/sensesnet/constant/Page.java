package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DaoConstant: page path
 */
public class Page
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
    public final String USER_ADD_PAGE = "/WEB-INF/view/test/user/userAdd.jsp";
    public final String USER_EDIT_PAGE = "/WEB-INF/view/test/user/userEdit.jsp";
    public final String USER_HISTORY_PAGE = "/WEB-INF/view/test/user/userHistory.jsp";
    public final String USER_LIST_PAGE = "/WEB-INF/view/test/user/userList.jsp";

    /*
    test.process
     */
    public final String TEST_ADD_PAGE = "/WEB-INF/view/test/process/testAdd.jsp";
    public final String TEST_EDIT_PAGE = "/WEB-INF/view/test/process/testEdit.jsp";
    public final String TEST_PROCESS_PAGE = "/WEB-INF/view/test/process/testProcess.jsp";
    public final String TEST_RESULT_PAGE = "/WEB-INF/view/test/process/testResult.jsp";
    public final String TEST_SELECT_PAGE = "/WEB-INF/view/test/process/testSelect.jsp";

    /*
    error
     */
    public final String AUTH_ERROR_PAGE = "/WEB-INF/view/error/authError.jsp";
    public final String NOT_FOUND_PAGE = "/WEB-INF/view/error/400.jsp";
}
