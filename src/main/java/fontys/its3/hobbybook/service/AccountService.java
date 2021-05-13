package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.Hobby;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;


public interface AccountService {

    Account findAccountById(Long id);

    //Account findByEmail(String email);

    Optional<Account> findAccountByUsername(String username);

    List<Account> findAllAccounts();

    Account createAccount(Account account);

    void deleteAccount(Long id);

    ResponseEntity<Account> editUser(Long id, Account account);

    ResponseEntity<Account> editUser_pass(Long id, Account account);

    Account addHobby(String username, Hobby hobby);

    Account likeUser(Long baseId,Long targetId);

    Account passUser(Long baseId, Long targetId);

    List<Account> findAllWithoutMe(String username);

    List<Account> availableToMatchUsers(String username);
}
