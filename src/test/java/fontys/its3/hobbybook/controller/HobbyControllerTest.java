package fontys.its3.hobbybook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.Hobby;
import fontys.its3.hobbybook.service.AccountService;
import fontys.its3.hobbybook.service.HobbyService;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@AutoConfigureTestDatabase
class HobbyControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockmvc;

    @MockBean
    private HobbyService hbbService;

    @MockBean
    private AccountService accService;


    List<Hobby> hobbylist;
    ObjectMapper objectMapper = new ObjectMapper();

    Hobby h1 = new Hobby(1L,"Cycle","I cycle!");
    Hobby h2 = new Hobby(2L,"Hiking","I hike!");
    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
    Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);
    String exampleAccountJson ="{\"uname\":\"user1\",\"firstName\":\"user1name\",\"lastName\":\"user1lastname\",\"email\":\"user1@email.com\",\"password\":\"user1password\",\"dateOfBirth\":\"2001-01-01\",\"EGender\":\"Male\"}";
    String exampleHobbyJson = "{\"id\":\"1\",\"name\":\"Cycle\",\"description\":\"I cycle\"}";
    @BeforeEach
    void setMockmvc(){

        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.webAppContextSetup(webAppContext).apply(springSecurity()).build();


        this.hobbylist = new ArrayList<>();

        this.hobbylist.add(h1);
        this.hobbylist.add(h1);
    }

    @WithMockUser(value = "spring")
    @Test
    void getAssignHobbies() throws Exception{

        Mockito.when(hbbService.getHobbies(a2.getUsername())).thenReturn(hobbylist);

        RequestBuilder builder = MockMvcRequestBuilders.get("/api/hobbybook/hobbies/user2");

        MvcResult result = mockmvc.perform(builder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(hobbylist);

        Assert.assertEquals(expected, content);
    }

    @WithMockUser(value = "spring")
    @Test
    void findHobbyById() throws Exception{

        Mockito.when(hbbService.findHobbyById(Mockito.anyLong())).thenReturn(ResponseEntity.ok(h1));

        RequestBuilder builder = MockMvcRequestBuilders.get("/api/hobbybook/hobbies/find/0");

        MvcResult result = mockmvc.perform(builder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(h1);

        Assert.assertEquals(expected, content);
    }

    @WithMockUser(value = "spring")
    @Test
    void saveHobby() throws Exception{

        Mockito.when(accService.addHobby(Mockito.anyString(),Mockito.any(Hobby.class))).thenReturn(a1);

        RequestBuilder builder = MockMvcRequestBuilders.post("/api/hobbybook/hobbies/add/user1").accept(MediaType.APPLICATION_JSON).content(exampleAccountJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockmvc.perform(builder).andReturn();

        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(a1);

        Assert.assertEquals(expected, content);
    }

    @Test
    void deleteHobby() {
    }

    @WithMockUser(value = "spring")
    @Test
    void updateHobby() throws Exception{

        Mockito.when(hbbService.updateHobby(Mockito.anyLong(),Mockito.any(Hobby.class))).thenReturn(ResponseEntity.ok(h1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/api/hobbybook/hobbies/update/0").accept(MediaType.APPLICATION_JSON).content(exampleHobbyJson)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockmvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(h1);

        Assert.assertEquals(expected, content);

    }
}