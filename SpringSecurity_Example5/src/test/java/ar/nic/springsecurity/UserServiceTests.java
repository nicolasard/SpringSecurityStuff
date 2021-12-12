package ar.nic.springsecurity;

import ar.nic.springsecurity.entity.User;
import ar.nic.springsecurity.services.EmailSenderService;
import ar.nic.springsecurity.services.UserService;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//This makes Spring to resolve the bean injection. https://www.youtube.com/watch?v=Ekr4jxOIf4c a good video about Junit tests
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //TODO: Sort the run not by name but an explicit order.
public class UserServiceTests {

    @Autowired
    UserService userService;

    @MockBean
    EmailSenderService emailSenderService;
    
    private static final LocalValidatorFactoryBean LOCAL_VALIDATOR_FACTORY_BEAN = createLocalValidatorFactoryBean();
	
    
    private static LocalValidatorFactoryBean createLocalValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    private static BindingResult createBindingResult(Object qrCodeModel) {
        DataBinder dataBinder = new DataBinder(qrCodeModel);
        dataBinder.setValidator(LOCAL_VALIDATOR_FACTORY_BEAN);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }

    private static BindingAwareModelMap createModel() {
        return new BindingAwareModelMap();
    }
    /*
     * Validates signUpUser without validations.
     */	
    @Test
    public void signUpUserTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        Mockito.doNothing().when(emailSenderService).sendEmail(simpleMailMessage);
        User user = new User();
        user.setName("Nicolas");
        user.setSurname("Ardison");
        user.setEmail("user@email.com");
        user.setPassword("12345");
        userService.signUpUser(user);        
    }

    /*
     * Validate that we can signUp an user and do the validations
     */
    @Test
    public void signUpUserValidationsTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        Mockito.doNothing().when(emailSenderService).sendEmail(simpleMailMessage);
        User user = new User();
        user.setName("Nicolas");
        user.setSurname("Ardison");
        user.setEmail("user2@email.com");
        user.setPassword("Password!@#");
        BindingResult bindingResult = createBindingResult(user);
        userService.signUpUserValidation(user,bindingResult);
        assertEquals(0, bindingResult.getErrorCount());        
    }
    
    /*
     * Validates that we have exceptions when the client doesn't complete the form
     */
    @Test
    public void signUpUserValidationsFailsTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        Mockito.doNothing().when(emailSenderService).sendEmail(simpleMailMessage);
        final User user = new User();
        user.setName("");
        user.setSurname("");
        user.setEmail("");
        user.setPassword("");
        BindingResult bindingResult = createBindingResult(user);
        userService.signUpUserValidation(user,bindingResult);
        assertEquals(4, bindingResult.getErrorCount());
        assertEquals("size must be between 2 and 30",bindingResult.getFieldError("name").getDefaultMessage());
        assertEquals("size must be between 2 and 30",bindingResult.getFieldError("surname").getDefaultMessage());
        assertEquals("must not be blank",bindingResult.getFieldError("email").getDefaultMessage());
        assertEquals("size must be between 5 and 100",bindingResult.getFieldError("password").getDefaultMessage());   
    }

    /*
     * Validates that we have exceptions when the client doesn't complete the form
     */
    @Test
    public void signUpUserValidateWrongEmailTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        Mockito.doNothing().when(emailSenderService).sendEmail(simpleMailMessage);
        final User user = new User();
        user.setName("Nicolas");
        user.setSurname("Ardison");
        user.setEmail("email");
        user.setPassword("123456");
        BindingResult bindingResult = createBindingResult(user);
        userService.signUpUserValidation(user,bindingResult);
        assertEquals(1, bindingResult.getErrorCount());
        assertEquals("must be a well-formed email address",bindingResult.getFieldError("email").getDefaultMessage());
    }
    
}
