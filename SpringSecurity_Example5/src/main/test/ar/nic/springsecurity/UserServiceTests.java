package ar.nic.springsecurity;

import ar.nic.springsecurity.entity.User;
import ar.nic.springsecurity.services.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//This makes Spring to resolve the bean injection. https://www.youtube.com/watch?v=Ekr4jxOIf4c a good video about Junit tests
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //TODO: Sort the run not by name but an explicit order.
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void signUpUserTest() {
        User user = new User();
        user.setName("Nicolas");
        user.setSurname("Ardison");
        user.setEmail("user@email.com");
        user.setPassword("12345");
        userService.signUpUser(user);
    }
}
