package homework5.task2.service;
import homework5.task2.dao.*;
import org.springframework.context.annotation.*;

@Configuration

public class UserConfiguration {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public UserDto userDto(UserDao userDao) {
        return new UserDto(userDao);
    }

    public static UserDao createUserDao() {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao;
    }

}
