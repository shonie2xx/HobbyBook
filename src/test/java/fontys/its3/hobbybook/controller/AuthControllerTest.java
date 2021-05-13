package fontys.its3.hobbybook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.security.payload.response.MessageResponse;
import fontys.its3.hobbybook.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@AutoConfigureTestDatabase
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockmvc;

    @MockBean
    private AccountService accService;


    List<Account> userList;

    ObjectMapper objectMapper = new ObjectMapper();

    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
    Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);
    //Account a23 = new Account("user1","firstname","lastname","email","password",LocalDate.of(2000,10,11),EGender.MALE);
    String exampleAccountJson ="{\"uname\":\"user1\",\"firstName\":\"user1name\",\"lastName\":\"user1lastname\",\"email\":\"user1@email.com\",\"password\":\"user1password\",\"dateOfBirth\":\"2001-01-01\",\"EGender\":\"Male\"}";


    @BeforeEach
    void setMockmvc() {

        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.webAppContextSetup(webAppContext).apply(springSecurity()).build();


        this.userList = new ArrayList<>();

        this.userList.add(a1);
        this.userList.add(a2);
    }

    @WithMockUser(value = "spring")
    @Test
    void signUpTest() throws Exception{

//        Mockito.when(accService.createAccount(Mockito.any(Account.class))).thenReturn();
//
//        RequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup");
//
//        MvcResult result = mockmvc.perform(builder).andReturn();
//
//        String content = result.getResponse().getContentAsString();
//
//        String expected = objectMapper.writeValueAsString(a1);
//
//        Assert.assertEquals(expected, content);

    mockmvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }
}
