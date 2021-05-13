package fontys.its3.hobbybook.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    Role r1 = new Role(ERole.ROLE_USER);

    @Test
    void getName() {
        assertEquals(ERole.ROLE_USER,r1.getName());
    }

    @Test
    void setName() {
        r1.setName(ERole.ROLE_ADMIN);
        assertEquals(ERole.ROLE_ADMIN,r1.getName());
    }
}