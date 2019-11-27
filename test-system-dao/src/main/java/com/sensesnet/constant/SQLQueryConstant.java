package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * SQL queries constant
 */
public class SQLQueryConstant
{
    /**
     * SQL queries for DB (test_system.user;)
     */
    public final String INSERT_NEW_USER = "INSERT INTO test_system.user (user_login, user_password, role_id, info_id) VALUES ( ?, ?, ?, ?)";
    public final String DELETE_USER_BY_ID = "DELETE FROM test_system.user WHERE user_id=?";
    public final String UPDATE_USER = "UPDATE test_system.user SET user_login = ?, user_password = ? WHERE user_id = ? AND role_id = ? AND info_id = ?";
    public final String SELECT_ALL_USER = "SELECT * FROM test_system.user";
    public final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM test_system.user WHERE user_login = ? AND user_password = ?";
    public final String SELECT_USER_BY_ID = "SELECT * FROM test_system.user WHERE user_id = ?";

    /**
     * SQL queries for DB (test_system.user_info;)
     */
    public final String INSERT_NEW_USER_INFO = "INSERT INTO test_system.user_info (info_id, name, surname, address, birthday, phone) VALUES (?, ?, ?, ?, ?)";
    public final String DELETE_USER_INFO_BY_ID = "DELETE FROM test_system.user_info WHERE info_id = ?";
    public final String UPDATE_USER_INFO = "UPDATE test_system.user_info SET name = ?, surname = ?, address = ?, birthday = ?, phone = ? WHERE info_id = ?";
    public final String SELECT_ALL_USER_INFO = "SELECT * FROM test_system.user_info";
    public final String SELECT_USER_INFO_BY_NAME = "SELECT * FROM test_system.user_info WHERE name = ? AND surname = ?";
    public final String SELECT_USER_INFO_BY_ID = "SELECT * FROM test_system.user_info WHERE info_id = ?";

    /**
     * SQL queries for DB (test_system.user_role)
     */
    public final String INSERT_NEW_USER_ROLE = "INSERT INTO test_system.user_role (role_id, role_name, role_description) VALUES (?, ?)";
    public final String DELETE_USER_ROLE_BY_ID = "DELETE FROM test_system.user_role WHERE role_id = ?";
    public final String UPDATE_USER_ROLE = "UPDATE test_system.user_role SET role_name = ?, role_description = ? WHERE role_id = ?";
    public final String SELECT_ALL_USER_ROLE = "SELECT * FROM test_system.user_role";
    public final String SELECT_USER_ROLE_BY_NAME = "SELECT * FROM test_system.user_role WHERE role_name = ?";
    public final String SELECT_USER_ROLE_BY_ID = "SELECT * FROM test_system.user_role WHERE role_id = ?";
    /**
     * SQL queries for DB (test_system.test;)
     */
    public static final String INSERT_NEW_TEST = "INSERT INTO test_system.test (test_name, test_description, test_value, test_time) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_TEST = "UPDATE test_system.test SET test_name = ?, test_value = ?, test_time = ? WHERE test_id = ?";
    public static final String DELETE_TEST_BY_ID = "DELETE FROM test_system.test WHERE test_id = ?";
    public static final String SELECT_ALL_TEST = "SELECT * FROM test_system.test";
    public static final String SELECT_TEST_BY_ID = "SELECT * FROM test_system.test WHERE test_id = ?";

    /**
     * SQL queries for DB (test_system.test_process)
     */
    public static final String INSERT_NEW_TEST_PROCESS = "INSERT INTO test_system.test_process (test_process_date, user_id, test_id, main_result_value, is_completed) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_TEST_PROCESS = "UPDATE test_system.test SET test_process_date = ?, user_id = ?, test_id = ?, main_result_value = ?, is_completed = ? WHERE test_process_id = ?";
    public static final String DELETE_TEST_PROCESS_BY_ID = "DELETE FROM test_system.test_process WHERE test_process_id = ?";
    public static final String SELECT_ALL_TEST_PROCESS = "SELECT * FROM test_system.test_process";
    public static final String SELECT_TEST_PROCESS_BY_ID = "SELECT * FROM test_system.test_process WHERE test_process_id = ?";

    /**
     * SQL queries for DB (test_system.question;)
     */
    public final String INSERT_NEW_QUESTION = "INSERT INTO test_system.question (question_description, question_value, test_id, answer_id, question_clarification) VALUES (?, ?, ?, ?, ?)";
    public final String UPDATE_QUESTION = "UPDATE test_system.question SET question_description = ?, question_value = ?, test_id = ?, answer_id = ?, question_clarification = ? WHERE question_id = ?";
    public final String DELETE_QUESTION_BY_ID = "DELETE FROM test_system.question WHERE question_id = ?";
    public final String SELECT_ALL_QUESTION = "SELECT * FROM test_system.question";
    public final String SELECT_QUESTION_BY_ID = "SELECT * FROM test_system.question WHERE question_id = ?";

    /**
     * SQL queries for DB (test_system.answer;)
     */
    public final String INSERT_NEW_ANSWER = "INSERT INTO test_system.answer (answer_description, question_id) VALUES (?, ?)";
    public final String UPDATE_ANSWER = "UPDATE test_system.answer SET answer_description = ?, question_id = ? WHERE answer_id = ?";
    public final String DELETE_ANSWER_BY_ID = "DELETE FROM test_system.answer WHERE answer_id = ?";
    public final String SELECT_ALL_ANSWER = "SELECT * FROM test_system.answer";
    public final String SELECT_ANSWER_BY_ID = "SELECT * FROM test_system.answer WHERE answer_id = ?";

    /**
     * SQL queries for DB (test_system.result;)
     */
    public final String INSERT_RESULT = "INSERT INTO test_system.result (test_process_id, question_id, answer_id) VALUES (?, ?, ?)";
    public final String DELETE_RESULT_BY_ID = "DELETE FROM test_system.result WHERE result_id = ?";
    public final String SELECT_ALL_RESULT = "SELECT * FROM test_system.result";
    public final String SELECT_RESULT_BY_USER_ID = "SELECT * FROM test_system.result WHERE result_id = ?";

}
