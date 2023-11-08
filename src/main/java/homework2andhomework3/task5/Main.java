package homework2andhomework3.task5;

import homework2andhomework3.task4.*;

import java.sql.*;
import java.time.*;
import java.time.format.*;

public class Main {
    public static final String EXPENSES_INSERT = "INSERT INTO expenses (id,paydate,receiver,value) VALUES (?, ?, ?, ?)";
    public static final String EXPENSES_ALL = "SELECT * FROM expenses";
    public static final String RECEIVER_WHERE_ID = "SELECT name FROM receivers WHERE id = ?";
    public static final String IS_EXPENSE_EXIST = "SELECT 1 FROM expenses WHERE id = ?";

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 4) {
            System.err.println("Please enter the values in the format  'id YYYY-MM-D receiverID price'");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            String paydate = args[1];
            LocalDate date = LocalDate.parse(paydate);
            if (date.getYear() < 1900 || date.getYear() > Year.now().getValue()) {
                throw new DateTimeParseException("Year out of range", paydate, 0);
            }
            int receiver = Integer.parseInt(args[2]);
            double value = Double.parseDouble(args[3]);

            try (Connection connection = ExpensesConnection.getConnection()) {
                if (!isExpenseExists(id, connection)) {
                    insertExpense(id, paydate, receiver, value, connection);
                    printAllExpenses(connection);
                } else {
                    System.err.println("Expense with id " + id + " already exists");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    private static boolean isExpenseExists(int id, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(IS_EXPENSE_EXIST)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void printAllExpenses(Connection connection) throws SQLException {
        try (PreparedStatement selectStatement = connection.prepareStatement(EXPENSES_ALL);
             ResultSet resultSet = selectStatement.executeQuery()) {
            while (resultSet.next()) {
                int expenseId = resultSet.getInt("id");
                Date expensePaydate = resultSet.getDate("paydate");
                int expenseReceiverId = resultSet.getInt("receiver");
                double expenseValue = resultSet.getDouble("value");
                String receiverName = getReceiverNameById(expenseReceiverId, connection);
                System.out.println(expenseId + " " + expensePaydate + " " + receiverName + " " + expenseValue);
            }
        }
    }

    private static String getReceiverNameById(int id, Connection connection) throws SQLException {
        try (PreparedStatement receiverStatement = connection.prepareStatement(RECEIVER_WHERE_ID)) {
            receiverStatement.setInt(1, id);
            try (ResultSet receiverResultSet = receiverStatement.executeQuery()) {
                String receiverName = "";
                if (receiverResultSet.next()) {
                    receiverName = receiverResultSet.getString("name");
                }
                return receiverName;
            }
        }
    }

    private static void insertExpense(int id, String paydate, int receiver, double value, Connection connection) throws SQLException {
        try (PreparedStatement insertStatement = connection.prepareStatement(EXPENSES_INSERT)) {
            insertStatement.setInt(1, id);
            insertStatement.setDate(2, Date.valueOf(paydate));
            insertStatement.setInt(3, receiver);
            insertStatement.setDouble(4, value);
            insertStatement.executeUpdate();
        }
    }
}
