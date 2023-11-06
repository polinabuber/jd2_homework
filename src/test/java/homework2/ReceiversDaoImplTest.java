//package homework2;
//
//import homework2.task4.*;
//import homework2.task7.*;
//import org.junit.*;
//
//import java.sql.*;
//import java.util.*;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class ReceiversDaoImplTest { //IN PROCESS
//
//    private static Dao dao;
//
//    @BeforeClass
//    public static void setup() throws Exception {
//        dao = new DaoImpl();
//        Connection connection = TestExpensesConnection.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement
//                ("CREATE TABLE IF NOT EXISTS receivers_test (id INT NOT NULL, name VARCHAR(255) NOT NULL, PRIMARY KEY (id));");
//        preparedStatement.executeUpdate();
//        preparedStatement = connection.prepareStatement("INSERT INTO receivers_test VALUES (?, ?);");
//        preparedStatement.setInt(1, 1);
//        preparedStatement.setString(2,"Test_Receiver");
//        preparedStatement.executeUpdate();
//    }
//
//    @After
//    public void teardown() throws Exception {
//        Connection connection = TestExpensesConnection.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE receivers_test");
//        preparedStatement.executeUpdate();
//    }
//
//    @Test
//    public void testGetReceivers() {
//        ArrayList<Receiver> receivers = dao.getReceivers();
//        assertNotNull(receivers);
//        for (Receiver receiver : receivers) {
//            assertNotNull(receiver);
//        }
//        Receiver receiver1 = receivers.get(0);
//        assertEquals(1, receiver1.getId());
//        assertEquals("Test_Receiver", receiver1.getName());
//    }
//}
