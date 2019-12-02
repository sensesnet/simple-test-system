package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.Constant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: user info
 * Standart CRUD operation
 */
public class UserInfoDao extends AbstractDao<UserInfo>
{
    private static final Logger log = LogManager.getLogger(UserInfo.class);

    @Override
    public UserInfo getByIdentifier(UserInfo entity) throws ConnectionPoolException, DaoException
    {
        LinkedList<UserInfo> userInfoList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_USER_INFO_BY_ID))
        {
            statement.setInt(1, entity.getInfoId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userInfoList.add(UserInfo.builder()
                            .infoId(resultSet.getInt("info_id"))
                            .userName(resultSet.getString("name"))
                            .userSurname(resultSet.getString("surname"))
                            .userAddress(resultSet.getString("address"))
                            .userBirthday(resultSet.getDate("birthday"))
                            .userPhone(resultSet.getString("phone")).build());
                }
                assert userInfoList.size() == 1;
                log.info("[UserDao] Account info has been selected by id: " + entity.getInfoId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] User Info [" + entity.getInfoId() + "] account is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userInfoList.iterator().next();
    }

    @Override
    public List<UserInfo> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        List<UserInfo> userInfoList = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_ALL_USER_INFO))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userInfoList.add(UserInfo.builder()
                            .infoId(resultSet.getInt("info_id"))
                            .userName(resultSet.getString("name"))
                            .userSurname(resultSet.getString("surname"))
                            .userAddress(resultSet.getString("address"))
                            .userBirthday(resultSet.getDate("birthday"))
                            .userPhone(resultSet.getString("phone")).build());
                }
                log.info("[UserDao] All account's info has been selected.");
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
        return userInfoList;
    }

    @Override
    public void addEntity(UserInfo entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().INSERT_NEW_USER_INFO))
        {
            prepareStatementParams(
                    statement,
                    entity.getUserName(),
                    entity.getUserSurname(),
                    entity.getUserAddress(),
                    entity.getUserBirthday(),
                    entity.getUserPhone()).executeUpdate();
            log.info("[UserDao] Account Info has been added: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void removeEntity(UserInfo entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().DELETE_USER_INFO_BY_ID))
        {
            statement.setInt(1, entity.getInfoId());
            statement.execute();
            log.info("[UserDao] Account info has been removed: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void editEntity(UserInfo entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().UPDATE_USER_INFO))
        {
            prepareStatementParams(
                    statement,
                    entity.getUserName(),
                    entity.getUserSurname(),
                    entity.getUserAddress(),
                    entity.getUserBirthday(),
                    entity.getUserPhone()).executeQuery();
            log.info("[UserDao] Account Info has been updated: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

}
