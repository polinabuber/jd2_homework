package homework5.task2.service;

import homework5.task2.dao.*;
import homework5.task2.entity.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDto implements InitializingBean, DisposableBean {
    private final UserDao userDao;

    public UserDto(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("call afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("call destroy");
    }

}
