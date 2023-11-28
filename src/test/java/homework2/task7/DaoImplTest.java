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

    private static Client getClients() {
        Client client = new Client();
        ClientInfo clientInfo = new ClientInfo();

        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2023-11-26", 100.0);

        int expensesId = dao.addExpense(expenses);
        expenses = dao.getExpense(expensesId);

        clientInfo.setName("Test Name");
        clientInfo.setPhoneNumber("1234567890");
        clientInfo.setExpenses(expenses);
        client.setClientInfo(clientInfo);
        return client;
    }

    private static ClientDetails getClientsDetails() {
        ClientDetails clientDetails = new ClientDetails();
        ClientDetailsInfo clientDetailsInfo = new ClientDetailsInfo();
        Client client = getClients();
        int clientId = dao.addClient(client);
        clientDetailsInfo.setAddress("Test Address");
        clientDetailsInfo.setRegistrationDate(convertStringToDate("2023-01-01"));
        clientDetailsInfo.setBirthdayDate(convertStringToDate("1990-07-15"));
        clientDetails.setClientDetailsInfo(clientDetailsInfo);
        clientDetails.setClient(client);
        return clientDetails;
    }

    private static void extracted(Expenses expenses, Expenses addedExpense) {
        assertEquals(expenses.getId(), addedExpense.getId());
        assertEquals(expenses.getPaydate(), addedExpense.getPaydate());
        assertEquals(expenses.getReceiver(), addedExpense.getReceiver());
        assertEquals(expenses.getValue(), addedExpense.getValue(), 0.0);
    }

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

    @Before
    public void setup() {
        dao = new DaoImpl(TestSessionFactory.getSessionFactory());
        try (Session session = TestSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE expenses;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE receivers;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE clients;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE client_details;").executeUpdate();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @After
    public void teardown() {
        try (Session session = TestSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
//            session.createSQLQuery("TRUNCATE TABLE expenses;").executeUpdate();
//            session.createSQLQuery("TRUNCATE TABLE receivers;").executeUpdate();
//            session.createSQLQuery("TRUNCATE TABLE clients;").executeUpdate();
//            session.createSQLQuery("TRUNCATE TABLE client_details;").executeUpdate();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            session.getTransaction().commit();
        }
        dao = null;
    }

    @Test
    public void getClientDetailsTest() {
        // Given
        ClientDetails clientDetails1 = getClientsDetails();
        int id1 = dao.addClientDetails(clientDetails1);
        ClientDetails clientDetails2 = getClientsDetails();
        int id2 = dao.addClientDetails(clientDetails2);

        // When
        ArrayList<ClientDetails> clientDetails = dao.getClientDetails();

        // Then
        assertNotNull(clientDetails);
        assertTrue(clientDetails.size() >= 2);
    }


    @Test
    public void getClientDetailsByIdTest() {
        // Given
        ClientDetails clientDetails = getClientsDetails();
        int id = dao.addClientDetails(clientDetails);

        // When
        ClientDetails retrievedClientDetails = dao.getClientDetails(id);

        // Then
        assertNotNull(retrievedClientDetails);
        assertEquals(clientDetails, retrievedClientDetails);
        assertEquals(clientDetails.getClientDetailsInfo().getAddress(), retrievedClientDetails.getClientDetailsInfo().getAddress());
        assertEquals(clientDetails.getClientDetailsInfo().getRegistrationDate(), retrievedClientDetails.getClientDetailsInfo().getRegistrationDate());
        assertEquals(clientDetails.getClientDetailsInfo().getBirthdayDate(), retrievedClientDetails.getClientDetailsInfo().getBirthdayDate());
    }

    @Test
    public void addClientDetailsTest() {
        // Given
        ClientDetails clientDetails = getClientsDetails();

        // When
        int id = dao.addClientDetails(clientDetails);

        // Then
        assertNotEquals(-1, id);
        ClientDetails retrievedClientDetails = dao.getClientDetails(id);
        assertNotNull(retrievedClientDetails);
        assertEquals(clientDetails, retrievedClientDetails);
    }

    @Test
    public void updateClientDetailsTest() {
        // Given
        ClientDetails clientDetails = getClientsDetails();
        int id = dao.addClientDetails(clientDetails);
        clientDetails = dao.getClientDetails(id);
        clientDetails.getClientDetailsInfo().setAddress("New Address");

        // When
        dao.updateClientDetails(clientDetails);
        ClientDetails updatedClientDetails = dao.getClientDetails(id);

        // Then
        assertNotNull(updatedClientDetails);
        assertEquals("New Address", updatedClientDetails.getClientDetailsInfo().getAddress());
        assertEquals(clientDetails.getClientDetailsInfo().getRegistrationDate(), updatedClientDetails.getClientDetailsInfo().getRegistrationDate());
        assertEquals(clientDetails.getClientDetailsInfo().getBirthdayDate(), updatedClientDetails.getClientDetailsInfo().getBirthdayDate());
    }

    @Test
    public void deleteClientDetailsTest() {
        // Given
        ClientDetails clientDetails = getClientsDetails();
        int id = dao.addClientDetails(clientDetails);

        // When
        boolean isDeleted = dao.deleteClientDetails(clientDetails);

        // Then
        assertTrue(isDeleted);
        ClientDetails retrievedClientDetails = dao.getClientDetails(id);
        assertNull(retrievedClientDetails);
    }


    @Test
    public void getClientsTest() {
        // Given
        Client client1 = getClients();
        int id1 = dao.addClient(client1);
        Client client2 = getClients();
        int id2 = dao.addClient(client2);

        // When
        ArrayList<Client> clients = dao.getClients();

        // Then
        assertNotNull(clients);
        assertTrue(clients.size() >= 2);
    }

    @Test
    public void getClient() {
        // Given
        Client client = getClients();
        int id = dao.addClient(client);

        // When
        Client retrievedClient = dao.getClient(id);

        // Then
        assertNotNull(client);
        assertNotNull(retrievedClient);
        assertEquals(client, retrievedClient);
        assertEquals("Test Name", retrievedClient.getClientInfo().getName());
        assertEquals("1234567890", retrievedClient.getClientInfo().getPhoneNumber());
        assertEquals(client.getClientInfo().getExpenses(), retrievedClient.getClientInfo().getExpenses());
    }

    @Test
    public void addClient() {
        // Given
        Client client = getClients();

        // When
        int id = dao.addClient(client);

        // Then
        assertNotEquals(-1, id);
        Client retrievedClient = dao.getClient(id);
        assertNotNull(retrievedClient);
        assertEquals(client, retrievedClient);
    }

    @Test
    public void updateClient() {
        // Given
        Client client = getClients();
        int id = dao.addClient(client);
        client.getClientInfo().setName("New Name");

        // When
        dao.updateClient(client);
        Client updatedClient = dao.getClient(id);

        // Then
        assertNotNull(updatedClient);
        assertEquals("New Name", updatedClient.getClientInfo().getName());
        assertEquals(client.getId(), updatedClient.getId());
        assertEquals(client.getClientInfo().getPhoneNumber(), updatedClient.getClientInfo().getPhoneNumber());
        assertEquals(client.getClientInfo().getExpenses(), updatedClient.getClientInfo().getExpenses());
    }

    @Test
    public void deleteClient() {
        // Given
        Client client = getClients();
        int id = dao.addClient(client);

        // When
        boolean isDeleted = dao.deleteClient(client);

        // Then
        assertTrue(isDeleted);
        assertNull(dao.getClient(id));
    }


    @Test
    public void testLoadExpense() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2022-07-27", 350);
        dao.addExpense(expenses);

        // When
        Expenses loadedExpense;
        loadedExpense = dao.loadExpense(expenses.getId());


        // Then
        assertNotNull(loadedExpense);
        assertEquals(expenses.getId(), loadedExpense.getId());
        assertEquals(expenses.getValue(), loadedExpense.getValue(), 0.0);
        assertEquals(expenses.getReceiver(), loadedExpense.getReceiver());

        //Check for non-existent id
        int nonExistentId = 999;
        Expenses nonExistentExpense = dao.loadExpense(nonExistentId);
        assertNull(nonExistentExpense);
    }


    @Test
    public void testLoadReceiver() {
        // Given
        Receiver receiver = getReceiver();
        dao.addReceiver(receiver);

        // When
        Receiver loadedReceiver;
        loadedReceiver = dao.loadReceiver(receiver.getId());


        // Then
        assertNotNull(loadedReceiver);
        assertEquals(receiver.getId(), loadedReceiver.getId());
        assertEquals(receiver.getName(), loadedReceiver.getName());

        //Check for non-existent id
        int nonExistentId = 999;
        Receiver nonExistentReceiver = dao.loadReceiver(nonExistentId);
        assertNull(nonExistentReceiver);
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
    public void testUpdateExpense() {
        // Given
        Receiver receiver = getReceiver();
        Expenses expenses = getExpenses(receiver, "2022-07-27", 350);
        dao.addExpense(expenses);

        // When
        expenses.setValue(400);
        dao.updateExpense(expenses);
        dao.flushSession();

        // Then
        Expenses updatedExpense = dao.loadExpense(expenses.getId());
        assertNotNull(updatedExpense);
        assertEquals(expenses.getValue(), updatedExpense.getValue(), 0.0);

        // After
        dao.clearSession();
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
    public void testUpdateReceiver() {
        // Given
        Receiver receiver = getReceiver();

        // When
        receiver.setName("Updated Receiver");
        dao.updateReceiver(receiver);
        dao.flushSession();

        // Then
        Receiver updatedReceiver = dao.loadReceiver(receiver.getId());
        assertNotNull(updatedReceiver);
        assertEquals(receiver.getName(), updatedReceiver.getName());

        // After
        dao.clearSession();
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



