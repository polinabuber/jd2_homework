package homework2;

import homework2.task4.*;
import homework2.task7.*;
import org.junit.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReceiversDaoImplTest {
    private Dao dao;

    @Before
    public void setup() throws Exception {
        dao = new DaoImpl();
        Connection connection = ExpensesConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("CREATE TABLE IF NOT EXISTS receivers_test (id INT NOT NULL, name VARCHAR(255) NOT NULL, PRIMARY KEY (id));");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO receivers_test VALUES (?, ?);");
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2,"Test_Receiver");
        preparedStatement.executeUpdate();
    }

    @After
    public void teardown() throws Exception {
        Connection connection = ExpensesConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS receivers_test");
        preparedStatement.executeUpdate();
        dao = null;
    }

    @Test
    public void testGetReceivers() {
        ArrayList<Receiver> receivers = dao.getReceivers();
        assertNotNull(receivers);
        for (Receiver receiver : receivers) {
            assertNotNull(receiver);
        }
        Receiver receiver1 = receivers.get(0);
        assertEquals(1, receiver1.getId());
        assertEquals("Amazon Online Shopping", receiver1.getName());
    }
}
