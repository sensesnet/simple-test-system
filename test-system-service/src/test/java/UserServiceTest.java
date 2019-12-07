import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * User dao test
 */
public class UserServiceTest
{
    private static final Logger log = LogManager.getLogger(UserServiceTest.class);

    @Test
    public void userServiceTest()
    {
        UserService userService = new UserService();
        userService.authorization("admin@admin.com", "Password1");
        log.info("[Test] Connection pool test completed!");
    }

    @Test
    public void userIsNotExist()
    {
        UserService userService = new UserService();
        userService.authorization("admin@admin123.com", "Password2");
        log.info("[Test] Connection pool test completed!");
    }
}
