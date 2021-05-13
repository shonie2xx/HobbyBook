package fontys.its3.hobbybook.security.payload.request;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.ERole;
import fontys.its3.hobbybook.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestTest {
    Account a1;
    Account a2;
    SignupRequest sr = new SignupRequest();
    @BeforeEach
    void Set(){
        a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 20, EGender.MALE);
        a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);
    }
    @Test
    void getUsername() {
        assertEquals("user1", a1.getUsername());
    }

    @Test
    void setUsername() {
        sr.setUsername("new");
        assertEquals("new",sr.getUsername());
    }

    @Test
    void getEmail() {
        sr.setEmail("user1@email.com");
        assertEquals("user1@email.com", sr.getEmail());
    }

    @Test
    void setEmail() {
        sr.setEmail("new");
        assertEquals("new",sr.getEmail());
    }

    @Test
    void getPassword() {
        sr.setPassword("user1password");
        assertEquals("user1password", sr.getPassword());
    }

    @Test
    void setPassword() {
        sr.setPassword("new");
        assertEquals("new", sr.getPassword());
    }

    @Test
    void getRole() {
        Set<String> roles = new HashSet<>();
        sr.setRole(roles);
        assertEquals(roles,sr.getRole());
    }

    @Test
    void setRole() {
        Set<String> roles = new HashSet<>();

        sr.setRole(roles);
        assertEquals(roles,sr.getRole());
    }

    @Test
    void getFirstName() {
        sr.setFirstName("user1name");
        assertEquals("user1name", sr.getFirstName());
    }

    @Test
    void setFirstName() {
        sr.setFirstName("new");
        assertEquals("new",sr.getFirstName());
    }

    @Test
    void getLastName() {
        sr.setLastName("user1lastname");
        assertEquals("user1lastname", sr.getLastName());
    }

    @Test
    void setLastName() {
        sr.setLastName("new");
        assertEquals("new",sr.getLastName());
    }

    @Test
    void getAge() {
        sr.setAge(20);
        assertEquals(20,sr.getAge());
    }

    @Test
    void setAge() {
        sr.setAge(25);
        assertEquals(25,sr.getAge());
    }

    @Test
    void getGender() {
        sr.setGender(EGender.FEMALE);
        assertEquals(EGender.FEMALE,sr.getGender());
    }

    @Test
    void setGender() {
        sr.setGender(EGender.FEMALE);
        assertEquals(EGender.FEMALE,sr.getGender());
    }
}