package homework2.task7.dao;

import homework2.task7.pojo.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.*;

public class DaoImpl implements Dao {

    public static final String EXPENSES_ALL = "FROM Expenses";
    public static final String RECEIVERS_ALL = "FROM Receiver";

    private SessionFactory sessionFactory;



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
    public int addExpense(Expenses expenses) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = (int) session.save(expenses);
            transaction.commit();
            return id;
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
    public int addReceiver(Receiver receiver) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(receiver);
            transaction.commit();
        }
        return 0;
    }


}
