package fontys.its3.hobbybook.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyTest {
    Hobby h1 = new Hobby(1L,"Cycling","I cycle");

    @Test
    void getId() {
        assertEquals(1L,h1.getId());
    }

    @Test
    void setId() {
        h1.setId(12L);
        assertEquals(12L,h1.getId());
    }

    @Test
    void getName() {
        assertEquals("Cycling",h1.getName());
    }

    @Test
    void setName() {
        h1.setName("Cycle");
        assertEquals("Cycle",h1.getName());
    }

    @Test
    void getDescription() {
        assertEquals("I cycle",h1.getDescription());
    }

    @Test
    void setDescription() {
        h1.setDescription("Znaesh kak e");
        assertEquals("Znaesh kak e",h1.getDescription());
    }
}