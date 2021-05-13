package fontys.its3.hobbybook.websocket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBeanTest {

    MessageBean mb = new MessageBean();
    @Test
    void getName() {
        mb.setName("name");
        assertEquals("name",mb.getName());
    }

    @Test
    void setName() {
        mb.setName("name");
        assertEquals("name",mb.getName());
    }

    @Test
    void getMessage() {
        mb.setMessage("message");
        assertEquals("message",mb.getMessage());
    }

    @Test
    void setMessage() {
        mb.setMessage("message");
        assertEquals("message",mb.getMessage());
    }
}