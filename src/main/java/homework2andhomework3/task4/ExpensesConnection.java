package homework2andhomework3.task4;

import java.sql.*;

public class ExpensesConnection {
    private static ExpensesConnection dataSource;

    protected ExpensesConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    protected Connection getExpensesConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jd2_homework",
                "user",
                "user");

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new ExpensesConnection();
        }
        return dataSource.getExpensesConnection();
    }

}
