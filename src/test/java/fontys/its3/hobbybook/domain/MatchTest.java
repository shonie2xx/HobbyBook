package fontys.its3.hobbybook.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 20, EGender.MALE);
    Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password", 18, EGender.FEMALE);
    Account a3 = new Account("user3", "user3name", "user3lastname", "user3@email.com", "user3password", 18, EGender.FEMALE);

    Match m1 = new Match(a1,a2,true);


    @Test
    void getFromUser() {
        assertEquals(a1,m1.getFromUser());
    }

    @Test
    void setFromUser() {
        m1.setFromUser(a3);
        assertEquals(a3,m1.getFromUser());
    }

    @Test
    void getToUser() {
        assertEquals(a2,m1.getToUser());
    }

    @Test
    void setToUser() {
        m1.setToUser(a3);
        assertEquals(a3,m1.getToUser());
    }

    @Test
    void isLike_pass() {
        assertEquals(true,m1.isLike_pass());
    }

    @Test
    void setLike_pass() {
        m1.setLike_pass(false);
        assertEquals(false,m1.isLike_pass());
    }
}