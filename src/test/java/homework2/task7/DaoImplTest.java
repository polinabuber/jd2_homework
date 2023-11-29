package homework2.task7;

import homework2.*;
import homework2.task7.dao.*;
import homework2.task7.pojo.Bank.*;
import homework2.task7.pojo.Client.*;
import homework2.task7.pojo.Expenses.*;
import homework2.task7.pojo.Product.*;
import homework2.task7.pojo.Receiver.*;
import org.hibernate.*;
import org.junit.*;

import java.text.*;
import java.util.*;

import static org.junit.Assert.*;

public class DaoImplTest {
    private static Dao dao;

    private static Receiver createReceiver() {
        Receiver receiver = new Receiver();
        receiver.setName("Test Receiver");
        dao.addReceiver(receiver);
        return receiver;
    }
    private BankDetails createBankDetails() {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBankName("Test Bank");
        return bankDetails;
    }

    private Account createAccount() {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        return account;
    }
    private Loan createLoan() {
        Loan loan = new Loan();
        loan.setInterestRate(5.0);
        return loan;
    }

    private Investment createInvestment() {
        Investment investment = new Investment();
        investment.setReturnRate(7.0);
        return investment;
    }

    private static Expenses createExpense(Receiver receiver, String date, double value) {
        Expenses expenses = new Expenses();
        expenses.setPaydate(convertStringToDate(date));
        expenses.setReceiver(receiver);
        expenses.setValue(value);
        return expenses;
    }

    private static Client createClient() {
        Client client = new Client();
        ClientInfo clientInfo = new ClientInfo();

        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2023-11-26", 100.0);

        int expensesId = dao.addExpense(expenses);
        expenses = dao.getExpense(expensesId);

        clientInfo.setName("Test Name");
        clientInfo.setPhoneNumber("1234567890");
        clientInfo.setExpenses(expenses);
        client.setClientInfo(clientInfo);
        return client;
    }

    private static ClientDetails createClientsDetails() {
        ClientDetails clientDetails = new ClientDetails();
        ClientDetailsInfo clientDetailsInfo = new ClientDetailsInfo();
        Client client = createClient();
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
            session.createSQLQuery("TRUNCATE TABLE expenses;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE receivers;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE clients;").executeUpdate();
            session.createSQLQuery("TRUNCATE TABLE client_details;").executeUpdate();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            session.getTransaction().commit();
        }
        dao = null;
    }
    @Test
    public void testAddLoan() {
        // Given
        Loan loan = createLoan();

        // When
        Long id = dao.addProduct(loan);

        // Then
        assertNotNull(id);
        assertEquals(loan.getId(), id);
    }

    @Test
    public void testGetLoan() {
        // Given
        Loan loan = createLoan();
        Long id = dao.addProduct(loan);

        // When
        Product retrievedProduct = dao.getProduct(id);

        // Then
        assertNotNull(retrievedProduct);
        assertEquals(loan.getId(), retrievedProduct.getId());
    }

    @Test
    public void testAddInvestment() {
        // Given
        Investment investment = createInvestment();

        // When
        Long id = dao.addProduct(investment);

        // Then
        assertNotNull(id);
        assertEquals(investment.getId(), id);
    }

    @Test
    public void testGetInvestment() {
        // Given
        Investment investment = createInvestment();
        Long id = dao.addProduct(investment);

        // When
        Product retrievedProduct = dao.getProduct(id);

        // Then
        assertNotNull(retrievedProduct);
        assertEquals(investment.getId(), retrievedProduct.getId());
    }
    @Test
    public void testAddBankDetails() {
        // Given
        BankDetails bankDetails = createBankDetails();

        // When
        long id = dao.addBank(bankDetails);

        // Then
        Assert.assertNotEquals(-1, id);
    }

    @Test
    public void testGetBankDetails() {
        // Given
        BankDetails bankDetails = createBankDetails();
        long id = dao.addBank(bankDetails);

        // When
        Bank retrievedBankDetails = dao.getBank(id);

        // Then
        Assert.assertNotNull(retrievedBankDetails);
        Assert.assertEquals("Test Bank", ((BankDetails) retrievedBankDetails).getBankName());
    }

    @Test
    public void testAddAccount() {
        // Given
        Account account = createAccount();

        // When
        long id = dao.addBank(account);

        // Then
        Assert.assertNotEquals(-1, id);
    }

    @Test
    public void testGetAccount() {
        // Given
        Account account = createAccount();
        long id = dao.addBank(account);

        // When
        Bank retrievedAccount = dao.getBank(id);

        // Then
        Assert.assertNotNull(retrievedAccount);
        Assert.assertEquals("1234567890", ((Account) retrievedAccount).getAccountNumber());
    }

    @Test
    public void getClientDetailsTest() {
        // Given
        ClientDetails clientDetails1 = createClientsDetails();
        int id1 = dao.addClientDetails(clientDetails1);
        ClientDetails clientDetails2 = createClientsDetails();
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
        ClientDetails clientDetails = createClientsDetails();
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
        ClientDetails clientDetails = createClientsDetails();

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
        ClientDetails clientDetails = createClientsDetails();
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
        ClientDetails clientDetails = createClientsDetails();
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
        Client client1 = createClient();
        int id1 = dao.addClient(client1);
        Client client2 = createClient();
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
        Client client = createClient();
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
        Client client = createClient();

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
        Client client = createClient();
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
        Client client = createClient();
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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2022-07-27", 350);
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
        Receiver receiver = createReceiver();
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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2021-07-27", 250);
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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2022-07-27", 350);

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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2022-07-27", 350);

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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2022-07-27", 350);
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
        Receiver receiver = createReceiver();
        Expenses expenses = createExpense(receiver, "2022-07-27", 350);
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
        Receiver receiver = createReceiver();

        // When
        ArrayList<Receiver> receivers = dao.getReceivers();

        // Then
        assertNotNull(receivers);
        assertFalse(receivers.isEmpty());
    }

    @Test
    public void testGetReceiver() {
        // Given
        Receiver receiver = createReceiver();
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
        Receiver receiver = createReceiver();

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
        Receiver receiver = createReceiver();

        // When
        boolean isDeleted = dao.deleteReceiver(receiver);

        // Then
        assertTrue(isDeleted);
        Receiver deletedReceiver = dao.getReceiver(receiver.getId());
        assertNull(deletedReceiver);
    }


}



