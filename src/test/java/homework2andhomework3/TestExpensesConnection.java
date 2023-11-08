package homework2andhomework3;

import homework2andhomework3.task4.*;

import java.sql.*;

public class TestExpensesConnection extends ExpensesConnection{

    private static TestExpensesConnection dataSource;

    protected TestExpensesConnection() throws ClassNotFoundException {
        super();
    }

    protected Connection getExpensesConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jd2_homework_test",
                "user",
                "user");

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new TestExpensesConnection();
        }
        return dataSource.getExpensesConnection();
    }
    public static void closeConnection() throws SQLException {
        if (dataSource != null) {
            dataSource.getExpensesConnection().close();
            dataSource = null;
        }
    }

}
