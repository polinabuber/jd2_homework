package homework2.task7.dao;

import homework2.task7.pojo.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.*;

public class DaoImpl implements Dao {

    public static final String EXPENSES_ALL = "FROM Expenses";
    public static final String RECEIVERS_ALL = "FROM Receiver";
    private final SessionFactory sessionFactory;


    public DaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
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



}
