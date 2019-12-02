import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * User dao test
 */
public class UserDaoTest
{
    private static final Logger log = LogManager.getLogger(UserDaoTest.class);

    @Test
    public void userDaoTest() throws ConnectionPoolException
    {
        UserDao userDao = new UserDao();
        try
        {
            userDao.getUserByLoginAndPassword("admin@admin.com","19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd");
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
        log.info("[Test] Connection pool test completed!");
    }

    @Test
    public void userIsNotExist() throws ConnectionPoolException
    {
        UserDao userDao = new UserDao();
        try
        {
            userDao.getUserByLoginAndPassword("admin@admin123.com","19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd");
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
        log.info("[Test] Connection pool test completed!");
    }
}
