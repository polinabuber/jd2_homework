package homework5.task2.service;
import homework5.task2.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
public class UserConfiguration {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Qualifier("userDaoImpl")
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    @Qualifier("userDaoImpl1")
    public UserDao userDao1() {
        return new UserDaoImpl1();
    }

    @Bean
    @Qualifier("userDaoImpl2")
    public UserDao userDao2() {
        return new UserDaoImpl2();
    }

    @Bean
    public UserDto userDto(@Qualifier("userDaoImpl") UserDao userDao) {
        return new UserDto(userDao);
    }

    public static UserDao createUserDao() {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao;
    }
}
