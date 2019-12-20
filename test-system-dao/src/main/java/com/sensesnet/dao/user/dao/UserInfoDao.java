package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public UserInfoDao()
    {
        log.info("[UserInfoDao] UserInfoDao has been initialized.");
    }

    @Override
    public UserInfo getByIdentifier(UserInfo entity) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<UserInfo> userInfoList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_INFO_BY_ID))
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
                            .userBirthday(resultSet.getString("birthday"))
                            .userPhone(resultSet.getString("phone")).build());
                }
                if (userInfoList.size() != 1)
                {
                    log.info("[UserDao] Account info has NOT been selected by id: " + entity.getInfoId());
                    return null;
                }
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
        CopyOnWriteArrayList<UserInfo> userInfoList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_USER_INFO))
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
                            .userBirthday(resultSet.getString("birthday"))
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_USER_INFO))
            {
                prepareStatementParams(
                        statement,
                        entity.getUserName(),
                        entity.getUserSurname(),
                        entity.getUserAddress(),
                        entity.getUserBirthday(),
                        entity.getUserPhone()).executeUpdate();
                log.info("[UserDao] Account Info has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[Error] Account's info has NOT updated, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.info("[Step] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[Error] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
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
                .prepareStatement(DaoConstant.query().DELETE_USER_INFO_BY_ID))
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_USER_INFO))
            {
                prepareStatementParams(
                        statement,
                        entity.getUserName(),
                        entity.getUserSurname(),
                        entity.getUserAddress(),
                        entity.getUserBirthday(),
                        entity.getUserPhone(),
                        entity.getInfoId()).execute();
                log.info("[UserDao] Account Info has been updated: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[Error] Account's have NOT updated, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.info("[Step] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[Error] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    /**
     * Get user info by phone number
     *
     * @param phoneNumber
     * @return
     */
    public UserInfo getUserInfoByPhone(String phoneNumber) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<UserInfo> userInfoList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_INFO_BY_PHONE))
        {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userInfoList.add(UserInfo.builder()
                            .infoId(resultSet.getInt("info_id"))
                            .userName(resultSet.getString("name"))
                            .userSurname(resultSet.getString("surname"))
                            .userAddress(resultSet.getString("address"))
                            .userBirthday(resultSet.getString("birthday"))
                            .userPhone(resultSet.getString("phone")).build());
                }
                if (userInfoList.size() != 1)
                {
                    log.info("[UserDao] Account info has NOT been selected by phone: " + phoneNumber);
                    return null;
                }
                log.info("[UserDao] Account info has been selected by phone: " + phoneNumber);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] User Info by phone [" + phoneNumber + "] account is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userInfoList.iterator().next();
    }
}
