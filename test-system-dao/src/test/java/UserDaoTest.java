import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

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
    public void userDaoTest() throws ConnectionPoolException, DaoException
    {
        UserDao userDao = new UserDao();

        User user = userDao.getUserByLoginAndPassword("admin@admin.com", "19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd");

        log.info("[Test] Connection pool test completed! User:" + user.toString());
    }

    @Test
    public void userIsNotExist() throws ConnectionPoolException, DaoException
    {
        UserDao userDao = new UserDao();
        User user = userDao.getUserByLoginAndPassword("admin@admin123.com", "19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd");
        log.info("[Test] Connection pool test completed! User not found:" + user);
    }
}
