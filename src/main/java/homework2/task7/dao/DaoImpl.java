package homework2.task7.dao;

import homework2.task7.pojo.BankSingleTable.*;
import homework2.task7.pojo.Client.*;
import homework2.task7.pojo.Expenses.*;
import homework2.task7.pojo.ProductJoined.*;
import homework2.task7.pojo.Receiver.*;
import homework2.task7.pojo.TransactionPerClass.*;
import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.util.*;

public class DaoImpl implements Dao {

    public static final String EXPENSES_ALL = "FROM Expenses";
    public static final String RECEIVERS_ALL = "FROM Receiver";
    public static final String CLIENTS_ALL = "FROM Client";
    public static final String CLIENT_DETAILS_ALL = "FROM ClientDetails";
    private final SessionFactory sessionFactory;


    public DaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Serializable getIdentifier(Object entity) throws HibernateException {
        if (entity == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            getSession().saveOrUpdate(entity);
            Serializable id = getSession().getIdentifier(entity);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void flushSession() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when flushing session: " + e.getMessage());
        }
    }

    public void clearSession() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.clear();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when clearing session: " + e.getMessage());
        }
    }

    @Override
    public Expenses loadExpense(int num) {
        try (Session session = sessionFactory.openSession()) {
            Expenses expenses = session.load(Expenses.class, num);
            Hibernate.initialize(expenses);
            return expenses;
        } catch (ObjectNotFoundException e) {
            System.out.println("Expenses with id " + num + " not found");
            return null;
        }
    }


    @Override
    public Receiver loadReceiver(int num) {
        try (Session session = sessionFactory.openSession()) {
            Receiver receiver = session.load(Receiver.class, num);
            Hibernate.initialize(receiver);
            return receiver;
        } catch (ObjectNotFoundException e) {
            System.out.println("Receiver with id " + num + " not found");
            return null;
        }
    }


    @Override
    public Transactions getTransactions(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Transactions.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting transaction: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addTransaction(Transactions transactions) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(transactions);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding transaction:" + e.getMessage());
            return -1L;
        }
    }

    @Override
    public BankTransactions getBankTransaction(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(BankTransactions.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting bank transaction: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addBankTransaction(BankTransactions bankTransaction) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(bankTransaction);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding bank transaction:" + e.getMessage());
            return -1L;
        }
    }

    @Override
    public CardTransactions getCardTransaction(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CardTransactions.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting card transaction: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addCardTransaction(CardTransactions cardTransaction) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(cardTransaction);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding card transaction:" + e.getMessage());
            return -1L;
        }
    }

    @Override
    public Product getProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }catch (HibernateException e) {
            System.out.println("Error when getting product: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(product);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding product:" + e.getMessage());
            return -1L;
        }
    }
    @Override
    public Investment getInvestment(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Investment.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting investment: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addInvestment(Investment investment) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(investment);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding investment:" + e.getMessage());
            return -1L;
        }
    }

    @Override
    public Loan getLoan(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Loan.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting loan: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addLoan(Loan loan) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(loan);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding loan:" + e.getMessage());
            return -1L;
        }
    }


    @Override
    public Bank getBank(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Bank.class, id);
        }catch (HibernateException e) {
            System.out.println("Error when getting bank: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addBank(Bank bank) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(bank);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding bank:" + e.getMessage());
            return -1L;
        }
    }
    @Override
    public BankDetails getBankDetails(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(BankDetails.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting bank details: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addBankDetails(BankDetails bankDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(bankDetails);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding bank details:" + e.getMessage());
            return -1L;
        }
    }

    @Override
    public Account getAccount(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Account.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting account: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Long addAccount(Account account) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(account);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding account:" + e.getMessage());
            return -1L;
        }
    }


    @Override
    public ArrayList<Expenses> getExpenses() {
        try (Session session = sessionFactory.openSession()) {
            Query<Expenses> query = session.createQuery(EXPENSES_ALL, Expenses.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error when getting list of expenses: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Expenses getExpense(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Expenses.class, num);
        } catch (HibernateException e) {
            System.out.println("Error when receiving expense: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addExpense(Expenses expenses) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(expenses);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding expense:" + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateExpense(Expenses expenses) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(expenses);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when updating expense: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteExpense(Expenses expenses) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(expenses);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when deleting expense: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Receiver> query = session.createQuery(RECEIVERS_ALL, Receiver.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error getting list of receivers: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Receiver getReceiver(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Receiver.class, num);
        } catch (HibernateException e) {
            System.out.println("Error when receiving receiver: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addReceiver(Receiver receiver) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(receiver);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding receiver: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateReceiver(Receiver receiver) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(receiver);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when updating receiver: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteReceiver(Receiver receiver) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(receiver);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when deleting receiver: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Client> getClients() {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery(CLIENTS_ALL, Client.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error when getting list of clients: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Client getClient(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        }catch (HibernateException e) {
            System.out.println("Error when getting clients: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(client);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding client:" + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when updating client: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when deleting client: " + e.getMessage());
            return false;
        }
    }


    @Override
    public ArrayList<ClientDetails> getClientDetails() {
        try (Session session = sessionFactory.openSession()) {
            Query<ClientDetails> query = session.createQuery(CLIENT_DETAILS_ALL, ClientDetails.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error when getting list of client details: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ClientDetails getClientDetails(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ClientDetails.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when getting client details: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addClientDetails(ClientDetails clientDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(clientDetails);
            transaction.commit();
            return id;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when adding client details:" + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(clientDetails);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when updating client details: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteClientDetails(ClientDetails clientDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(clientDetails);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error when deleting client details: " + e.getMessage());
            return false;
        }
    }
}
