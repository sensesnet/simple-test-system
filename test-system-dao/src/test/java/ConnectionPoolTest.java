import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Connection pool tests
 */
public class ConnectionPoolTest
{
    private static final Logger log = LogManager.getLogger(ConnectionPoolTest.class);

    @Test
    public void connectionPoolTest() throws ConnectionPoolException, SQLException
    {
        ConnectionPool pool = new ConnectionPool();
        pool.initPoolData();
        pool.takeConnection();
        pool.destroyConnectionPool();
        log.info("[Test] Connection pool test completed!");
    }
}
