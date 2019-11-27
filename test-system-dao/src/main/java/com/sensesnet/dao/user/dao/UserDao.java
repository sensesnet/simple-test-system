package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.Constant;
import com.sensesnet.constant.SQLQueryConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: User
 * Standart CRUD operation
 */
public class UserDao extends AbstractDao<UserDao>
{

    @Override
    public String getCreateQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getSelectQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getUpdateQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getDeleteQuery() throws DaoException
    {
        return null;
    }

    @Override
    public UserDao getByIdentifier(UserDao entity)
    {
        return null;
    }

    @Override
    public List<UserDao> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(UserDao entity)
    {

    }

    @Override
    public void removeEntity(UserDao entity)
    {

    }

    @Override
    public void editEntity(UserDao entity)
    {

    }

    public User getUserByNameAndPassword(String login, String password) throws DaoException
    {
        LinkedList<User> userList = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(Constant.QUERY().SELECT_USER_BY_LOGIN_AND_PASSWORD))
        {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            while (resultSet.next())
            {
                userList.add(User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .userLogin(resultSet.getString("user_login"))
                        .userPassword(resultSet.getString("user_password"))
                        .userRole(resultSet.getInt("role_id"))
                        .userInfo(resultSet.getInt("info_id")).build());
            }
            assert userList.size() == 1;
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            throw new DaoException("Response Error: Have more one account for [" + login + "," + password + "].", e);
        }
        catch (ConnectionPoolException e) {
            throw new DaoException("Connection pool Error:", e);
        }finally
        {
//          TODO:  close statement result set and return connection
        }
        return userList.iterator().next();
    }
}
