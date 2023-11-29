package homework2.task7.dao;

import homework2.task7.pojo.Bank.*;
import homework2.task7.pojo.Client.*;
import homework2.task7.pojo.Expenses.*;
import homework2.task7.pojo.Product.*;
import homework2.task7.pojo.Receiver.*;

import java.util.*;

public interface Dao {
    Product getProduct(Long id);

    Long addProduct(Product product);

    Bank getBank(Long id);

    Long addBank(Bank bank);

    Receiver getReceiver(int num);

    ArrayList<Receiver> getReceivers();

    Receiver loadReceiver(int num);

    int addReceiver(Receiver receiverDto);

    boolean deleteReceiver(Receiver receiver);

    void updateReceiver(Receiver receiver);

    Expenses getExpense(int num);

    ArrayList<Expenses> getExpenses();

    Expenses loadExpense(int num);

    int addExpense(Expenses expensesDto);

    boolean deleteExpense(Expenses expenses);

    void updateExpense(Expenses expenses);

    ArrayList<Client> getClients();

    int addClient(Client client);

    boolean deleteClient(Client client);

    void updateClient(Client client);

    Client getClient(int id);

    ClientDetails getClientDetails(int id);

    ArrayList<ClientDetails> getClientDetails();

    int addClientDetails(ClientDetails clientDetails);

    boolean deleteClientDetails(ClientDetails clientDetails);

    void updateClientDetails(ClientDetails clientDetails);

    void flushSession();

    void clearSession();
}

