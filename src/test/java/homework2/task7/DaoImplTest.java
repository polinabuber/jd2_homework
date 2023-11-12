package homework2.task7;

import homework2.*;
import homework2.task7.dao.*;
import homework2.task7.pojo.*;
import org.hibernate.*;
import org.junit.*;

import java.text.*;
import java.util.*;

import static org.junit.Assert.*;

public class DaoImplTest {
    private static Dao dao;

    public static Date convertStringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private static Receiver getReceiver() {
        Receiver receiver = new Receiver();
        receiver.setName("Test Receiver");
        dao.addReceiver(receiver);
        return receiver;
    }

    private static Expenses getExpenses(Receiver receiver, String date, double value) {
        Expenses expenses = new Expenses();
        expenses.setPaydate(convertStringToDate(date));
        expenses.setReceiver(receiver);
        expenses.setValue(value);
        return expenses;
    }

    private static void extracted(Expenses expenses, Expenses addedExpense) {
        assertEquals(expenses.getId(), addedExpense.getId());
        assertEquals(expenses.getPaydate(), addedExpense.getPaydate());
        assertEquals(expenses.getReceiver(), addedExpense.getReceiver());
        assertEquals(expenses.getValue(), addedExpense.getValue(), 0.0);
    }

    @Before
    public void setup() {
        dao = new DaoImpl(TestSessionFactory.getSessionFactory());
        try (Session session = TestSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE expenses;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE receivers;").executeUpdate();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @After
    public void teardown() {
        try (Session session = TestSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
        }
        dao = null;
    }

    @Test
    public void testGetExpenses() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2021-07-27", 250);
        dao.addExpense(expenses);

        // When
        ArrayList<Expenses> expensesList = dao.getExpenses();

        // Then
        assertNotNull(expensesList);
        assertFalse(expensesList.isEmpty());
        Expenses expense = expensesList.get(0);
        assertEquals(1, expense.getId());
        assertEquals("2021-07-27", convertDateToString(expense.getPaydate()));
        assertEquals(receiver, expense.getReceiver());
        assertEquals(250, expense.getValue(), 0.0);
    }


    @Test
    public void testGetExpense() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2022-07-27", 350);

        // When
        int result = dao.addExpense(expenses);

        // Then
        assertEquals(expenses.getId(), result);
        Expenses addedExpense = dao.getExpense(expenses.getId());
        assertNotNull(addedExpense);
        extracted(expenses, addedExpense);
    }


    @Test
    public void testAddExpense() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2022-07-27", 350);

        // When
        int result = dao.addExpense(expenses);

        // Then
        assertEquals(expenses.getId(), result);
        Expenses addedExpense = dao.getExpense(1);
        assertNotNull(addedExpense);
        extracted(expenses, addedExpense);
    }

    @Test
    public void testGetReceivers() {
        // Given
        Receiver receiver = getReceiver();

        // When
        ArrayList<Receiver> receivers = dao.getReceivers();

        // Then
        assertNotNull(receivers);
        assertFalse(receivers.isEmpty());
    }

    @Test
    public void testGetReceiver() {
        // Given
        Receiver receiver = getReceiver();
        int id = dao.addReceiver(receiver);

        // When
        Receiver retrievedReceiver = dao.getReceiver(id);

        // Then
        assertNotNull(retrievedReceiver);
        assertEquals(id, retrievedReceiver.getId());
        assertEquals(receiver.getName(), retrievedReceiver.getName());
    }



    @Test
    public void testAddReceiver() {
        // Given
        Receiver receiver = new Receiver();
        receiver.setName("Test receiver to addReceiver");

        // When
        int result = dao.addReceiver(receiver);

        // Then
        assertEquals(receiver.getId(), result);
        Receiver addedReceiver = dao.getReceiver(result);
        assertNotNull(addedReceiver);
        assertEquals(receiver.getId(), addedReceiver.getId());
        assertEquals(receiver.getName(), addedReceiver.getName());
    }

    @Test
    public void testDeleteExpense() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2022-07-27", 350);
        dao.addExpense(expenses);

        // When
        boolean isDeleted = dao.deleteExpense(expenses);

        // Then
        assertTrue(isDeleted);
        Expenses deletedExpense = dao.getExpense(expenses.getId());
        assertNull(deletedExpense);
    }
    @Test
    public void testDeleteReceiver() {
        // Given
        Receiver receiver = getReceiver();

        // When
        boolean isDeleted = dao.deleteReceiver(receiver);

        // Then
        assertTrue(isDeleted);
        Receiver deletedReceiver = dao.getReceiver(receiver.getId());
        assertNull(deletedReceiver);
    }




}


