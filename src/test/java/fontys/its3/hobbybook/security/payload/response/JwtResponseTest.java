package fontys.its3.hobbybook.security.payload.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {
    List<String> roles = new ArrayList<>();
    JwtResponse newJwtresponse = new JwtResponse("token",1L,"username","email",roles);

    @Test
    void getAccessToken() {
        assertEquals("token",newJwtresponse.getAccessToken());
    }

    @Test
    void setAccessToken() {
        newJwtresponse.setAccessToken("newtoken");
        assertEquals("newtoken",newJwtresponse.getAccessToken());
    }

    @Test
    void getTokenType() {
        String type = "Bearer";
        assertEquals(type, newJwtresponse.getTokenType());
    }

    @Test
    void setTokenType() {
        String type = "NotBearer";
        newJwtresponse.setTokenType(type);
        assertEquals(type,newJwtresponse.getTokenType());
    }

    @Test
    void getId() {
        assertEquals(1L,newJwtresponse.getId());
    }

    @Test
    void setId() {
        newJwtresponse.setId(2L);
        assertEquals(2L,newJwtresponse.getId());
    }

    @Test
    void getEmail() {
        assertEquals("email",newJwtresponse.getEmail());
    }

    @Test
    void setEmail() {
        newJwtresponse.setEmail("newemail");
        assertEquals("newemail",newJwtresponse.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals("username",newJwtresponse.getUsername());
    }

    @Test
    void setUsername() {
        newJwtresponse.setUsername("newusername");
        assertEquals("newusername",newJwtresponse.getUsername());
    }

    @Test
    void getRoles() {
        assertEquals(roles,newJwtresponse.getRoles());
    }
}