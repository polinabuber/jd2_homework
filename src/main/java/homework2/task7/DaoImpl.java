package homework2.task7;

import homework2.task4.*;

import java.sql.*;
import java.util.*;

public class DaoImpl implements Dao {

    public static final String EXPENSES_ALL = "SELECT*FROM expenses";
    public static final String EXPENSES_WHERE_ID = "SELECT*FROM expenses WHERE id = ?";
    public static final String EXPENSES_INSERT = "INSERT INTO expenses (id, paydate, receiver, value) VALUES (?, ?, ?, ?)";
    public static final String RECEIVERS_ALL = "SELECT*FROM receivers";
    public static final String RECEIVERS_WHERE_ID = "SELECT*FROM receivers WHERE id = ?";
    public static final String RECEIVERS_INSERT = "INSERT INTO receivers (id, name) VALUES (?, ?)";

    private final Connection connection;

    public DaoImpl(Connection connection) {
        this.connection = connection;
    }

    private static Expenses mapRowExpenses(ResultSet resultSet) throws SQLException {
        Expenses expenses = new Expenses();
        expenses.setId(resultSet.getInt("id"));
        expenses.setPaydate(resultSet.getString("paydate"));
        expenses.setReceiver(resultSet.getInt("receiver"));
        expenses.setValue(resultSet.getDouble("value"));
        return expenses;
    }

    private static Receiver mapRowReceivers(ResultSet resultSet) throws SQLException {
        Receiver receiver = new Receiver();
        receiver.setId(resultSet.getInt("id"));
        receiver.setName(resultSet.getString("name"));
        return receiver;
    }


    @Override
    public ArrayList<Expenses> getExpenses() {
        ArrayList<Expenses> expenses = new ArrayList<>();
        try (Connection connection = ExpensesConnection.getConnection();
             PreparedStatement preparedStatement = this.connection.prepareStatement(EXPENSES_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Expenses result = mapRowExpenses(resultSet);
                expenses.add(result);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return expenses;
    }

    @Override
    public Expenses getExpense(int num) {
        try (Connection connection = ExpensesConnection.getConnection();
             PreparedStatement preparedStatement = this.connection.prepareStatement(EXPENSES_WHERE_ID)) {
            preparedStatement.setInt(1, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return mapRowExpenses(resultSet);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public int addExpense(Expenses expenses) {
        try (Connection connection = ExpensesConnection.getConnection();
             PreparedStatement preparedStatement = this.connection.prepareStatement(EXPENSES_INSERT)) {
            preparedStatement.setInt(1, expenses.getId());
            preparedStatement.setString(2, expenses.getPaydate());
            preparedStatement.setInt(3, expenses.getReceiver());
            preparedStatement.setDouble(4, expenses.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver> receiver = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(RECEIVERS_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Receiver result = mapRowReceivers(resultSet);
                receiver.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receiver;
    }



    @Override
    public Receiver getReceiver(int num) {
        try (Connection connection = ExpensesConnection.getConnection();
             PreparedStatement preparedStatement = this.connection.prepareStatement(RECEIVERS_WHERE_ID)) {
            preparedStatement.setInt(1, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return mapRowReceivers(resultSet);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }


    @Override
    public int addReceiver(Receiver receiver) {
        try (Connection connection = ExpensesConnection.getConnection();
             PreparedStatement preparedStatement = this.connection.prepareStatement(RECEIVERS_INSERT)) {
            preparedStatement.setInt(1, receiver.getId());
            preparedStatement.setString(2, receiver.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


}
