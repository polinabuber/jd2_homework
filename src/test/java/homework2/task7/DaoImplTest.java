package homework2.task7;

import homework2.*;
import org.junit.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

public class DaoImplTest  {
    private static Dao dao;

    @Before
    public void setup() throws Exception {
        Connection connection = TestExpensesConnection.getConnection();
        dao = new DaoImpl(connection);
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=0;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE expenses;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE receivers;");
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=1;");
    }

    @After
    public void teardown() throws Exception {
        Connection connection = TestExpensesConnection.getConnection();
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=0;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE expenses;");
        connection.createStatement().executeUpdate("TRUNCATE TABLE receivers;");
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=1;");
        TestExpensesConnection.closeConnection();
        dao = null;
    }

    @Test
    public void testGetExpenses() {
        // Given
        Receiver receiver = new Receiver();
        receiver.setId(1);
        receiver.setName("First Receiver");
        dao.addReceiver(receiver);

        Expenses expenses = new Expenses();
        expenses.setId(1);
        expenses.setPaydate("2021-07-27");
        expenses.setReceiver(receiver.getId());
        expenses.setValue(250);
        dao.addExpense(expenses);

        // When
        ArrayList<Expenses> expensesList = dao.getExpenses();

        // Then
        assertNotNull(expensesList);
        assertFalse(expensesList.isEmpty());
        Expenses expense = expensesList.get(0);
        assertEquals(1, expense.getId());
        assertEquals("2021-07-27", expense.getPaydate());
        assertEquals(1, expense.getReceiver());
        assertEquals(250, expense.getValue(), 0.0);
    }

    @Test
    public void testGetExpense() {
        // Given
        int num = 1;
        Receiver receiver = new Receiver();
        receiver.setId(num);
        receiver.setName("First Receiver");
        dao.addReceiver(receiver);

        Expenses expenses = new Expenses();
        expenses.setId(num);
        expenses.setPaydate("2021-07-27");
        expenses.setReceiver(receiver.getId());
        expenses.setValue(250);
        dao.addExpense(expenses);

        // When
        Expenses expense = dao.getExpense(num);

        // Then
        assertNotNull(expense);
        assertEquals(num, expense.getId());
    }

    @Test
    public void testAddExpense() {
        // Given
        Receiver receiver = new Receiver();
        receiver.setId(2);
        receiver.setName("Second receiver");
        dao.addReceiver(receiver);

        Expenses expenses = new Expenses();
        expenses.setId(2);
        expenses.setPaydate("2022-07-27");
        expenses.setReceiver(receiver.getId());
        expenses.setValue(350);

        // When
        int result = dao.addExpense(expenses);

        // Then
        assertEquals(0, result);
        Expenses addedExpense = dao.getExpense(2);
        assertEquals(expenses.getId(), addedExpense.getId());
        assertEquals(expenses.getPaydate(), addedExpense.getPaydate());
        assertEquals(expenses.getReceiver(), addedExpense.getReceiver());
        assertEquals(expenses.getValue(), addedExpense.getValue(), 0.0);
    }

    @Test
    public void testGetReceivers() {
        // Given
        Receiver receiver = new Receiver();
        receiver.setId(3);
        receiver.setName("Third receiver");
        dao.addReceiver(receiver);

        // When
        ArrayList<Receiver> receivers = dao.getReceivers();

        // Then
        assertNotNull(receivers);
        assertFalse(receivers.isEmpty());
    }

    @Test
    public void testGetReceiver() {
        // Given
        int num = 4;
        Receiver receiver = new Receiver();
        receiver.setId(num);
        receiver.setName("Fourth receiver");
        dao.addReceiver(receiver);

        // When
        Receiver retrievedReceiver = dao.getReceiver(num);

        // Then
        assertNotNull(retrievedReceiver);
        assertEquals(num, retrievedReceiver.getId());
    }

    @Test
    public void testAddReceiver() {
        // Given
        Receiver receiver = new Receiver();
        receiver.setId(5);
        receiver.setName("Fifth receiver");

        // When
        int result = dao.addReceiver(receiver);

        // Then
        assertEquals(0, result);
        Receiver addedReceiver = dao.getReceiver(5);
        assertEquals(receiver.getId(), addedReceiver.getId());
        assertEquals(receiver.getName(), addedReceiver.getName());
    }
}


