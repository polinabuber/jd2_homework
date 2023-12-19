package homework5.task2.dao;

import homework5.task2.entity.*;
import homework5.task2.service.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUserConfiguration.class)
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDto userDto;
    @Autowired
    private NewBean newBean;

    private static User getUser() {
        User user = new User();
        user.setId("1");
        user.setFirstName("Test");
        user.setLastName("User");
        return user;
    }
    @Before
    public void setUp() {
        Mockito.reset(userDao);
    }
    @Test
    public void testGetUserById() {
        // Given
        User user = getUser();
        Mockito.when(userDao.getUserById("1")).thenReturn(user);

        // When
        User result = userDto.getUserById("1");

        // Then
        Mockito.verify(userDao, Mockito.times(1)).getUserById("1");
        assertEquals(user, result);

        System.out.println("User name: " + result.getFirstName() + " " + result.getLastName());
    }

    @Test
    public void testBeansConfiguration() {
        // Given
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // When
        UserDao userDao = context.getBean(UserDao.class);
        UserDto userDto = context.getBean(UserDto.class);

        // Then
        assertNotNull(userDao);
        assertNotNull(userDto);
        assertEquals(userDao, userDto.getUserDao());
    }

    @Test
    public void testGetUserNameLengthById() {
        // Given
        User user = getUser();
        Mockito.when(userDao.getUserById("1")).thenReturn(user);

        // When
        int result = newBean.getUserNameLengthById("1");

        // Then
        Mockito.verify(userDao, Mockito.times(1)).getUserById("1");
        assertEquals((user.getFirstName() + user.getLastName()).length(), result);

        // Output the length to the console
        System.out.println("Length of the full name without spaces: " + result);
    }
    @Test
    public void testGetUserByIdWithUserDaoImpl1() {
        // Given
        User user = getUser();
        Mockito.when(userDao.getUserById("1")).thenReturn(user);

        // When
        User result = userDto.getUserById("1");

        // Then
        Mockito.verify(userDao, Mockito.times(1)).getUserById("1");
        assertEquals(user, result);

        System.out.println("User name: " + result.getFirstName() + " " + result.getLastName());
    }

    @Test
    public void testGetUserByIdWithUserDaoImpl2() {
        // Given
        User user = getUser();
        Mockito.when(userDao.getUserById("1")).thenReturn(user);

        // When
        User result = userDto.getUserById("1");

        // Then
        Mockito.verify(userDao, Mockito.times(1)).getUserById("1");
        assertEquals(user, result);

        System.out.println("User name: " + result.getFirstName() + " " + result.getLastName());
    }



}