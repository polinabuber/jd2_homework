package homework5.task2.dao;

import homework5.task2.service.*;
import org.mockito.*;
import org.springframework.context.annotation.*;

@Configuration
public class TestUserConfiguration {
    @Bean
    @Primary
    public UserDao userDao() {
        return Mockito.mock(UserDao.class);
    }

    @Bean
    public UserDto userDto(UserDao userDao) {
        return new UserDto(userDao);
    }

    @Bean
    public NewBean newBean(UserDao userDao) {
        return new NewBean(userDao);
    }
}
