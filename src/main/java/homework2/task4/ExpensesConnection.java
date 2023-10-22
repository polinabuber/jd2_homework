package homework2.task4;

import java.sql.*;

public class ExpensesConnection {
    static ExpensesConnection dataSource;

    private ExpensesConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getExpensesConnection() throws ClassNotFoundException, SQLException {
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
