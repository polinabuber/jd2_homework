package homework2.task4;


import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ExpensesConnection.getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO expenses (id,paydate,receiver,value) VALUES (14, '2021-01-01', 1, 200)");
            insertStatement.executeUpdate();

            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM expenses");
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date paydate = resultSet.getDate("paydate");
                int receiverId = resultSet.getInt("receiver");
                double value = resultSet.getDouble("value");

                PreparedStatement receiverStatement = connection.prepareStatement("SELECT name FROM receivers WHERE id = " + receiverId);
                ResultSet receiverResultSet = receiverStatement.executeQuery();
                String receiverName = "";
                if (receiverResultSet.next()) {
                    receiverName = receiverResultSet.getString("name");
                }

                System.out.println(id + " " + paydate + " " + receiverName + " " + value);
            }

        } catch (SQLException e) {
            System.err.println("An error occurred while interacting with the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("The JDBC driver was not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
