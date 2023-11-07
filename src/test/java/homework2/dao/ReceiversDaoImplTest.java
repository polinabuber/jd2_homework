package homework2.dao;


import homework2.*;
import homework2.task7.*;
import org.junit.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ReceiversDaoImplTest {




    //The tests run once, after the database is dropped.

// After that, they fail with an error:

    //  getExpenses(homework2.dao.ExpensesDaoImplTest): Cannot add or update a child row: a foreign key constraint fails
    //  (`jd2_homework_test`.`expenses`, CONSTRAINT `expenses_ibfk_1`
    //  FOREIGN KEY (`receiver`) REFERENCES `receivers` (`id`))





    private static Dao dao;


    @BeforeClass
    public static void setup() throws Exception {
        Connection connection = TestExpensesConnection.getConnection();
        dao = new DaoImpl(connection);
        Statement statement = connection.createStatement();
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
        statement.executeUpdate("TRUNCATE TABLE expenses;");
        statement.executeUpdate("TRUNCATE TABLE receivers;");
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
    }

    @After
    public void teardown() throws Exception {
        Connection connection = TestExpensesConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
        statement.executeUpdate("TRUNCATE TABLE expenses;");
        statement.executeUpdate("TRUNCATE TABLE receivers;");
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        dao = null;
        TestExpensesConnection.closeConnection();
    }


    @Test
    public void testGetReceivers() throws SQLException, ClassNotFoundException {
        Connection connection = TestExpensesConnection.getConnection();
        connection.createStatement().executeUpdate
                ("INSERT INTO receivers (id, name) VALUES (1, 'test');");
        connection.createStatement().executeUpdate
                ("INSERT INTO expenses (id, paydate, receiver, value) VALUES (1, '2021-07-27', 1, 250);");

        // When
        ArrayList<Receiver> receivers = dao.getReceivers();

        // Then
        assertNotNull(receivers);
        Receiver receiver1 = receivers.get(0);
        assertEquals(1, receiver1.getId());
        assertEquals("test", receiver1.getName());
    }
}
