package homework2.task4;

import java.sql.*;

public class Main {
    public static final String EXPENSES_FIND_ALL = "SELECT * FROM expenses";
    public static final String RECEIVER_FIND_BY_ID = "SELECT name FROM receivers WHERE id =";

    public static void main(String[] args) throws ClassNotFoundException {

        if (args.length < 4) {
            System.err.println("Please enter the values in the format  'id YYYY-MM-D receiverID price'");
            return;
        }
        int id = Integer.parseInt(args[0]);
        String paydate = args[1];
        int receiver = Integer.parseInt(args[2]);
        double value = Double.parseDouble(args[3]);

        try (Connection connection = ExpensesConnection.getConnection()) {
            insertExpense(id, paydate, receiver, value, connection);
            printAllExpenses(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void printAllExpenses(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(EXPENSES_FIND_ALL);
        while (resultSet.next()) {
            int expenseId = resultSet.getInt("id");
            Date expensePaydate = resultSet.getDate("paydate");
            int expenseReceiverId = resultSet.getInt("receiver");
            double expenseValue = resultSet.getDouble("value");
            String receiverName = getReceiverNameById(expenseReceiverId, connection);
            System.out.println(expenseId + " " + expensePaydate + " " + receiverName + " " + expenseValue);
        }
    }

    private static String getReceiverNameById(int id, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet receiverResultSet = statement.executeQuery(String.format("%s %d", RECEIVER_FIND_BY_ID, id));
        String receiverName = "";
        if (receiverResultSet.next()) {
            receiverName = receiverResultSet.getString("name");
        }
        return receiverName;
    }

    private static void insertExpense(int id, String paydate, int receiver, double value, Connection connection) throws SQLException {
        String sql = String.format("INSERT INTO expenses (id,paydate,receiver,value) VALUES (%d, '%s', %d, %f)", id, paydate, receiver, value);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }


}
