package fontys.its3.hobbybook.security.payload.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {


    LoginRequest lr = new LoginRequest();
    @Test
    void getUsername() {
        lr.setUsername("user1");
        assertEquals("user1",lr.getUsername());
    }

    @Test
    void setUsername() {
        lr.setUsername("user2");
        assertEquals("user2",lr.getUsername());
    }

    @Test
    void getPassword() {
        lr.setPassword("password");
        assertEquals("password",lr.getPassword());
    }

    @Test
    void setPassword() {
        lr.setPassword("password");
        assertEquals("password",lr.getPassword());
    }
}