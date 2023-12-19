package homework5.task2.service;

import homework5.task2.dao.*;
import homework5.task2.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class NewBean {
    private final UserDao userDao;

    public NewBean(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    public int getUserNameLengthById(String id) {
        User user = userDao.getUserById(id);
        return (user.getFirstName() + user.getLastName()).length();
    }
}
