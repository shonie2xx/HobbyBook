package fontys.its3.hobbybook.security.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.ERole;
import fontys.its3.hobbybook.domain.Role;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class UserDetailsImplTest {
    public Collection<? extends GrantedAuthority> authorities;
    UserDetailsImpl userDetails = new UserDetailsImpl(1L,"user1name","user1@email.com","user1password",authorities);
    Account a1;
    Account a2;

    @BeforeEach
    void Set(){
        a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 20, EGender.MALE);
        a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);
    }
    @Test
    void build() {

    }

    @Test
    void getAuthorities() {
        assertEquals(authorities,userDetails.getAuthorities());
    }

    @Test
    void getPassword() {
        assertEquals("user1password",userDetails.getPassword());
    }

    @Test
    void getId() {
        assertEquals(1L,userDetails.getId());
    }

    @Test
    void getEmail() {
        assertEquals("user1@email.com",userDetails.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals("user1name",userDetails.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        assertEquals(true,userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertEquals(true,userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertEquals(true,userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertEquals(true,userDetails.isEnabled());
    }
}