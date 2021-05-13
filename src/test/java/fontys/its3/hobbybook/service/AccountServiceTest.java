package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.domain.Hobby;
import fontys.its3.hobbybook.repositories.AccountRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    PasswordEncoder encoder;

    Account a1 = new Account("user1", "user1name", "user1lastname", "user1@email.com", "user1password", 18, EGender.MALE);
    Account a2 = new Account("user2", "user2name", "user2lastname", "user2@email.com", "user2password",18, EGender.FEMALE);
    List<Account> users;
    @BeforeEach
    void set()
    {
        users = new ArrayList<>();
        users.add(a1);
        users.add(a2);
    }
    @Test
    void findAccountByIdTest() {
        final long id = 1;

        when(accountRepository.findById(id)).thenReturn(Optional.of(a1));

        Optional<Account> expected = Optional.of(accountService.findAccountById(id));

        assert(expected).isPresent();
    }

    @Test
    void findAccountByUsernameTest() {
        String username = a1.getUsername();
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(a1));

        Optional<Account> expected = accountService.findAccountByUsername(a1.getUsername());

        assertEquals(expected.get(),a1);
    }

    @Test
    void findAllAccountsTest() {

        when(accountRepository.findAll()).thenReturn(users);

       List<Account> expected = accountService.findAllAccounts();

       assertEquals(expected,users);
    }

    @Test
    void createAccountTest() {

        when(accountRepository.save(a1)).thenReturn(a1);

        Account expected = accountService.createAccount(a1);

        assertEquals(expected,a1);
    }

    @Test
    void deleteAccountTest() {

        long id = 1;

        accountService.deleteAccount(id);

        verify(accountRepository).deleteById(id);
    }

    @Test
    void editUserTest() {

        when(accountRepository.save(a1)).thenReturn(a1);
        when(accountRepository.findById(a1.getId())).thenReturn(Optional.ofNullable(a1));

        ResponseEntity<Account> expected = accountService.editUser(a1.getId(),a1);

        assertEquals(expected,ResponseEntity.ok(a1));
    }

//    @Test
//    void editUser_passTest() {
//
//        when(accountRepository.save(a1)).thenReturn(a1);
//        when(accountRepository.findById(a1.getId())).thenReturn(Optional.ofNullable(a1));
//        ResponseEntity<Account> expected = accountService.editUser_pass(a1.getId(),a1);
//
//
//        assertEquals(expected,ResponseEntity.ok(a1));
//
//    }


    @Test
    void addHobbyTest() {
        Hobby newHobby = new Hobby(1L,"BrandNewHobby","Brand new wheel just hoped in");

        when(accountRepository.findByUsername(a1.getUsername())).thenReturn(Optional.ofNullable(a1));
        when(accountRepository.save(any(Account.class))).thenReturn(a1);

        Account expected = accountService.addHobby(a1.getUsername(),newHobby);

        assertEquals(expected,a1);
    }

    @Test
    void likeUserTest() {
        when(accountRepository.findById(a1.getId())).thenReturn(Optional.ofNullable(a1));
        when(accountRepository.findById(a2.getId())).thenReturn(Optional.ofNullable(a2));
        when(accountRepository.save(any(Account.class))).thenReturn(a1);
        Account expected = accountService.likeUser(a1.getId(),a2.getId());

        assertEquals(expected,a1);
    }
    @Test
    void passUserTest() {
        when(accountRepository.findById(a1.getId())).thenReturn(Optional.ofNullable(a1));
        when(accountRepository.findById(a2.getId())).thenReturn(Optional.ofNullable(a2));
        when(accountRepository.save(any(Account.class))).thenReturn(a1);
        Account expected = accountService.passUser(a1.getId(),a2.getId());

        assertEquals(expected,a1);
    }

    @Test
    void findAllWithoutMe(){
        when(accountRepository.findAll()).thenReturn(users);
        when(accountService.findAccountByUsername(a1.getUsername())).thenReturn(Optional.ofNullable(a1));

        List<Account> expected = accountService.findAllWithoutMe(a1.getUsername());

        assertEquals(expected,users);
    }

    @Test
    void availableToMatchTest(){
        when(accountRepository.findAll()).thenReturn(users);
        when(accountService.findAccountByUsername(a1.getUsername())).thenReturn(Optional.ofNullable(a1));

        List<Account> expected = accountService.availableToMatchUsers(a1.getUsername());

        assertEquals(expected,users);
    }
}