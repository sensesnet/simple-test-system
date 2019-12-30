import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.UserServiceImpl;
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
    public void userServiceTest() throws ServiceException, ConnectionPoolException
    {
        UserServiceImpl userService = new UserServiceImpl();
        ConnectionPool.getInstance().initPoolData();
        userService.getUserByEmailAndPassword("admin@admin.com", "Password1");
        log.info("[Test] Connection pool test completed!");
    }

    @Test
    public void userIsNotExist() throws ServiceException
    {
        UserServiceImpl userService = new UserServiceImpl();
        userService.getUserByEmailAndPassword("admin@admin123.com", "Password2");
        log.info("[Test] Connection pool test completed!");
    }
}
