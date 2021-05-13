package fontys.its3.hobbybook.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account a1;
    Account a2;

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
        a1.setUsername("new");
        assertEquals("new",a1.getUsername());
    }

    @Test
    void getFirstName() {
        assertEquals("user1name", a1.getFirstName());
    }

    @Test
    void setFirstName() {
        a1.setFirstName("new");
        assertEquals("new",a1.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("user1lastname", a1.getLastName());
    }

    @Test
    void setLastName() {
        a1.setLastName("new");
        assertEquals("new",a1.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("user1@email.com", a1.getEmail());
    }

    @Test
    void setEmail() {
        a1.setEmail("new");
        assertEquals("new",a1.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("user1password", a1.getPassword());
    }

    @Test
    void setPassword() {
        a1.setPassword("new");
        assertEquals("new", a1.getPassword());
    }

    @Test
    void getAge() {
        assertEquals(20,a1.getAge());
    }

    @Test
    void setAge() {
        a1.setAge(25);
        assertEquals(25,a1.getAge());
    }

    @Test
    void getGender() {
        assertEquals(EGender.MALE,a1.getGender());
    }

    @Test
    void setGender() {
        a1.setGender(EGender.FEMALE);
        assertEquals(EGender.FEMALE,a1.getGender());
    }

    @Test
    void getHobbies() {
        List<Hobby> no = new ArrayList<>();
        assertEquals(no,a1.getHobbies());
    }

    @Test
    void setHobbies() {
        List<Hobby> hbbs = new ArrayList<>();
        hbbs.add(new Hobby(1L,"hobby","hobbydescription"));
        a1.setHobbies(hbbs);
        assertEquals(hbbs,a1.getHobbies());
    }

    @Test
    void getFromUsers() {
        List<Account> no = new ArrayList<>();
        assertEquals(no,a1.getFromUsers());
    }

    @Test
    void setFromUsers() {
        List<Match> match = new ArrayList<>();
        match.add(new Match(a1,a2,true));
        a1.setFromUsers(match);
        assertEquals(match,a1.getFromUsers());
    }

    @Test
    void getToUsers() {
        List<Account> no = new ArrayList<>();
        assertEquals(no,a1.getToUsers());
    }

    @Test
    void setToUsers() {
        List<Match> match = new ArrayList<>();
        match.add(new Match(a1,a2,true));
        a1.setToUsers(match);
        assertEquals(match,a1.getToUsers());
    }

    @Test
    void getRoles() {
        Set<Role> roles = new HashSet<>();
        assertEquals(roles,a1.getRoles());
    }

    @Test
    void setRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_USER));
        a1.setRoles(roles);
        assertEquals(roles,a1.getRoles());
    }
}