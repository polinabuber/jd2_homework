package homework5.task2.dao;

import homework2.task4.*;
import homework5.task2.entity.*;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDaoImpl implements UserDao {


    @Override
    public User getUserById(String id) {
        User user = null;
        String sql = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = ExpensesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getString("id"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return user;
    }


    public void initMethod() {
        System.out.println("call init in UserDaoImpl");
    }

    public void destroyMethod() {
        System.out.println("call destroy in UserDaoImpl");
    }

}

