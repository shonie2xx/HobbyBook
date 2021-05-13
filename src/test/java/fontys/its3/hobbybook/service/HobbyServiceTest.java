package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.Hobby;
import fontys.its3.hobbybook.repositories.AccountRepository;
import fontys.its3.hobbybook.repositories.HobbyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HobbyServiceTest {

    @InjectMocks
    HobbyServiceImpl hobbyService;

    @Mock
    HobbyRepository hobbyRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    AccountService accountService;

    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
    Hobby h1 = new Hobby(1L,"Running","Run for fun!");
    Hobby h2 = new Hobby(2L,"Cycling", "Cycle in free time!");

    @Test
    void getHobbies() {

        List<Hobby> hobbies = new ArrayList<>();

        when(accountRepository.findByUsername(a1.getUsername())).thenReturn(Optional.ofNullable(a1));

        List<Hobby> actual = hobbyService.getHobbies(a1.getUsername());

        assertThat(a1).isNotNull();
        assertEquals(hobbies,actual);

    }

    @Test
    void findHobbyById() {
        final long id = 1;

        when(hobbyRepository.findById(id)).thenReturn(Optional.of(h1));

        Optional<ResponseEntity<Hobby>> expected = Optional.of(hobbyService.findHobbyById(id));

        assert(expected).isPresent();
    }



    @Test
    void deleteHobby() {

//        long id = h1.getId();
//
//        hobbyService.deleteHobby(id);
//
//        verify(hobbyRepository).deleteById(id);
    }

    @Test
    void updateHobby() {

        when(hobbyRepository.save(h1)).thenReturn(h1);
        when(hobbyRepository.findById(h1.getId())).thenReturn(Optional.ofNullable(h1));

        ResponseEntity<Hobby> expected = hobbyService.updateHobby(h1.getId(),h1);

        assertEquals(expected,ResponseEntity.ok(h1));
    }
}