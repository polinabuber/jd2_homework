package homework2;

import homework2.task4.*;
import homework2.task7.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExpensesDaoImplTest {

    private Dao dao;

    @Before
    public void setup() throws Exception {
        dao = new DaoImpl();
        Connection connection = ExpensesConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS expenses_test (id INT NOT NULL, paydate DATE NOT NULL, receiver INT NOT NULL, value DECIMAL(5,2) NOT NULL, PRIMARY KEY (id));");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO expenses_test VALUES (?, ?, ?, ?);");
        preparedStatement.setInt(1, 1);
        preparedStatement.setDate(2, java.sql.Date.valueOf("2020-07-27"));
        preparedStatement.setInt(3, 2);
        preparedStatement.setDouble(4, 250);
        preparedStatement.executeUpdate();
    }

    @After
    public void teardown() throws Exception {
        Connection connection = ExpensesConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS expenses_test");
        preparedStatement.executeUpdate();
        dao = null;
    }

    @Test
    public void testGetExpenses() {
        ArrayList<Expenses> expenses = dao.getExpenses();
        assertNotNull(expenses);
        for (Expenses expense : expenses) {
            assertNotNull(expense);
        }
        Expenses expense1 = expenses.get(0);
        assertEquals(1, expense1.getId());
        assertEquals("2020-07-27", expense1.getPaydate());
        assertEquals(2, expense1.getReceiver());
        assertEquals(250, expense1.getValue(), 0.0);
    }

}