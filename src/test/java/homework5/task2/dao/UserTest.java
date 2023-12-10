package homework5.task2.dao;

import homework5.task2.entity.*;
import homework5.task2.service.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.assertEquals;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUserConfiguration.class)
public class UserTest {
    @Autowired
    private UserDao userDao;
    private UserDto userDto;

    @Before
    public void setUp() {
        userDto = new UserDto(userDao);
    }

    @Test
    public void testGetUserById() {
        // Given
        User user = new User();
        user.setId("1");
        user.setFirstName("Test");
        user.setLastName("User");
        Mockito.when(userDao.getUserById("1")).thenReturn(user);

        // When
        User result = userDto.getUserById("1");

        // Then
        Mockito.verify(userDao, Mockito.times(1)).getUserById("1");
        assertEquals(user, result);

        System.out.println("User name: " + result.getFirstName() + " " + result.getLastName());
    }
}
