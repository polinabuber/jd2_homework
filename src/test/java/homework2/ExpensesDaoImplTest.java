package homework2;

import homework2.task7.*;
import org.junit.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExpensesDaoImplTest {

    private static Dao dao;

    @BeforeClass
    public static void setup() throws Exception {
        dao = new DaoImpl();
        Connection connection = TestExpensesConnection.getConnection();
        connection.createStatement().executeUpdate("TRUNCATE TABLE expenses;");
    }

    @After
    public void teardown() throws Exception {
        dao = null;
        Connection connection = TestExpensesConnection.getConnection();
        connection.createStatement().executeUpdate("TRUNCATE TABLE expenses;");
    }

    @Test
    public void getExpenses() throws SQLException, ClassNotFoundException {
        Connection connection = TestExpensesConnection.getConnection();
        ArrayList<Expenses> expenses = dao.getExpenses();
        assertNotNull(expenses);

        Expenses expense1 = expenses.get(0);
        assertEquals(1, expense1.getId());
        assertEquals("2021-07-27", expense1.getPaydate());
        assertEquals(2, expense1.getReceiver());
        assertEquals(250, expense1.getValue(), 0.0);
    }
}
