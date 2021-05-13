package fontys.its3.hobbybook.security.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {
    String message;
    MessageResponse mr = new MessageResponse(message);
    @Test
    void getMessage() {
        assertEquals(message,mr.getMessage());
    }

    @Test
    void setMessage() {
        mr.setMessage("Hi");
        assertEquals("Hi",mr.getMessage());
    }
}