package homework5.task2.service;
import homework5.task2.dao.*;
import org.springframework.context.annotation.*;

@Configuration

public class UserConfiguration {
    @Bean
    public UserDao userDao() {
        return createUserDao();
    }

    @Bean
    public UserDto userDto(UserDao userDao) {
        return new UserDto(userDao);
    }




    //TODO
    public UserDao createUserDao() {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.initMethod();
        return userDao;
    }

}
