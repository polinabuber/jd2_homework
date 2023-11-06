package homework2;

import homework2.task4.*;

import java.sql.*;

public class TestExpensesConnection {
    static TestExpensesConnection dataSource;

    private TestExpensesConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getExpensesConnection() throws ClassNotFoundException, SQLException {
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
}
