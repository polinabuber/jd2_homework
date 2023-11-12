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
    public ArrayList<Expenses> getExpenses() {
        try (Session session = sessionFactory.openSession()) {
            Query<Expenses> query = session.createQuery(EXPENSES_ALL, Expenses.class);
            return new ArrayList<>(query.list());
        }
    }


    @Override
    public Expenses getExpense(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Expenses.class, num);
        }
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Receiver> query = session.createQuery(RECEIVERS_ALL, Receiver.class);
            return new ArrayList<>(query.list());
        }
    }


    @Override
    public Receiver getReceiver(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Receiver.class, num);
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
            throw e;
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
            throw e;
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
            return false;
        }
    }



}
