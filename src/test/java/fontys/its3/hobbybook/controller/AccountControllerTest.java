package fontys.its3.hobbybook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.service.AccountService;
import org.apache.coyote.Response;
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


import java.util.ArrayList;
import java.util.List;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@AutoConfigureTestDatabase
public class AccountControllerTest {

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

        //Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
        //Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);

        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.webAppContextSetup(webAppContext).apply(springSecurity()).build();


        this.userList = new ArrayList<>();

        this.userList.add(a1);
        this.userList.add(a2);
    }

    @WithMockUser(username="admin",roles={"ADMIN"})
    @Test
    void getAllAccounts() throws Exception{

        Mockito.when(accService.findAllWithoutMe(a1.getUsername())).thenReturn(userList);

        RequestBuilder builder = MockMvcRequestBuilders.get("/api/hobbybook/admin/user1");

        MvcResult result = mockmvc.perform(builder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(userList);

        Assert.assertEquals(expected, content);
    }

    @WithMockUser(value = "spring")
    @Test
    public void readyToMatchTest() throws Exception{

        Mockito.when(accService.availableToMatchUsers(a1.getUsername())).thenReturn(userList);

        RequestBuilder builder = MockMvcRequestBuilders.get("/api/hobbybook/users/ready/user1");

        MvcResult result = mockmvc.perform(builder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(userList);

        Assert.assertEquals(expected, content);

    }

    @WithMockUser(value = "spring")
    @Test
    void getAccById() throws Exception{

        Account user = new Account("user3", "user3name", "user3lastname", "user3@email.com", "user3password", 18, EGender.FEMALE);
        Mockito.when(accService.findAccountById(Mockito.anyLong())).thenReturn(user);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/hobbybook/users/0");
        MvcResult result = mockmvc.perform(builder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(user);

        Assert.assertEquals(expected, content);
    }


    @WithMockUser(value = "spring")
    @Test
    void deleteAccount(){

    }
    @WithMockUser(value = "spring")
    @Test
    void updateAccount() throws Exception{

        Mockito.when(accService.editUser(Mockito.anyLong(),Mockito.any(Account.class))).thenReturn(ResponseEntity.ok(a1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/api/hobbybook/users/update/0").accept(MediaType.APPLICATION_JSON).content(exampleAccountJson)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockmvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(a1);

        Assert.assertEquals(expected, content);

    }

    @WithMockUser(value = "spring")
    @Test
    void editUser() throws Exception{

        Mockito.when(accService.editUser_pass(Mockito.anyLong(),Mockito.any(Account.class))).thenReturn(ResponseEntity.ok(a1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/api/hobbybook/users/updatepass/0").accept(MediaType.APPLICATION_JSON).content(exampleAccountJson)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockmvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(a1);

        Assert.assertEquals(expected, content);

    }

    @WithMockUser(value = "spring")
    @Test
    void like() throws Exception{

        Mockito.when(accService.likeUser(Mockito.anyLong(),Mockito.anyLong())).thenReturn(a1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/hobbybook/users/like/0/1");

        MvcResult result = mockmvc.perform(requestBuilder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(a1);

        Assert.assertEquals(expected, content);

    }

    @WithMockUser(value = "spring")
    @Test
    void pass() throws Exception{

        Mockito.when(accService.passUser(Mockito.anyLong(),Mockito.anyLong())).thenReturn(a1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/hobbybook/users/pass/0/1");

        MvcResult result = mockmvc.perform(requestBuilder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(a1);

        Assert.assertEquals(expected, content);

    }
}