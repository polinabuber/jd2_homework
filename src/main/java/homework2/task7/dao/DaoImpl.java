package homework2.task7.dao;

import homework2.task7.pojo.*;
import org.hibernate.*;
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
