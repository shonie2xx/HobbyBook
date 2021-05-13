package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.Hobby;
import fontys.its3.hobbybook.domain.Match;
import fontys.its3.hobbybook.repositories.AccountRepository;
import fontys.its3.hobbybook.repositories.MatchRepository;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    MatchRepository matchRepository;
    @InjectMocks
    MatchServiceImpl matchService;

    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
    Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password",18, EGender.FEMALE);

    @Test
    void getMatches() {

        List<Account> matches = new ArrayList<>();
        matches.add(a1);
        matches.add(a2);



        when(matchRepository.findAllUserMatches(a1.getUsername())).thenReturn(matches);
        List<Account> actual = matchService.getUserMatches(a1.getUsername());


        assertEquals(matches,actual);
    }
}