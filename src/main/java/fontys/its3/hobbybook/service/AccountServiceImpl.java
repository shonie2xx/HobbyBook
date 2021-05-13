package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.*;
//import fontys.its3.hobbybook.exception.ResourceNotFoundException;
import fontys.its3.hobbybook.repositories.AccountRepository;
import fontys.its3.hobbybook.repositories.MatchRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accRepository;

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    PasswordEncoder encoder;

    public AccountServiceImpl(AccountRepository accRepository) {
        this.accRepository = accRepository;
    }

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> a = accRepository.findById(id);
        if(a.isPresent())
        return a.get();
        else
            return null;
    }

    @Override
    public Optional<Account> findAccountByUsername(String username) {
        return accRepository.findByUsername(username);
    }



    @Override
    public List<Account> findAllAccounts() {
        return accRepository.findAll();
    }


    @Override
    public Account createAccount(Account account) {
        return accRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Account> editUser(Long id, Account account) {
        Account account1 = accRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account is not found with id" + id));

        account1.setFirstName(account.getFirstName());
        account1.setLastName(account.getLastName());
        account1.setEmail(account.getEmail());
        //account1.setUsername(account.getUsername());

        Account updateAccount = accRepository.save(account1);
        return ResponseEntity.ok(updateAccount);
    }
    @Override
    public ResponseEntity<Account> editUser_pass(Long id, Account account) {
        Account account1 = accRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account is not found with id" + id));

        account1.setFirstName(account.getFirstName());
        account1.setLastName(account.getLastName());
        account1.setEmail(account.getEmail());
        account1.setPassword(encoder.encode(account.getPassword()));
        //account1.setUsername(account.getUsername());

        Account updateAccount = accRepository.save(account1);
        return ResponseEntity.ok(updateAccount);
    }

    @Override
    public Account addHobby(String username, Hobby hobby) {
        Optional<Account> acc = accRepository.findByUsername(username);
        if (acc.isPresent() && acc.get().getHobbies().add(hobby)) {
            accRepository.save(acc.get());
            return acc.get();
        }
        return null;
    }

    @Override
    public Account likeUser(Long baseId, Long targetId)
    {

        Account from_user = findAccountById(baseId);
        Account to_user = findAccountById(targetId);

        if(!accRepository.accLiked_Passed(from_user.getUsername()).contains(to_user))
        {
            from_user.getToUsers().add(new Match(from_user,to_user,true));
            return accRepository.save(from_user);
        }
        return null;
    }

    @Override
    public Account passUser(Long baseId, Long targetId)
    {
        Account from_user = findAccountById(baseId);
        Account to_user = findAccountById(targetId);


        if(!accRepository.accLiked_Passed(from_user.getUsername()).contains(to_user))
        {
            from_user.getToUsers().add(new Match(from_user,to_user,false));
            return accRepository.save(from_user);
        }
        return null;
    }



    @Override
    public List<Account> findAllWithoutMe(String username) {
        List<Account> accs = accRepository.findAll();
        Optional<Account> acc = findAccountByUsername(username);

        if(acc.isPresent())
        {
            accs.remove(acc.get());
            return accs;
        }
        return accs;
    }

    @Override
    public List<Account> availableToMatchUsers(String username) {
        List<Account> allUsers = findAllWithoutMe(username);
        List<Account> interactswith = accRepository.accLiked_Passed(username);

        for (Account acc:
             interactswith) {
            if(allUsers.contains(acc))
            {
                allUsers.remove(acc);
            }
        }

        return allUsers;
    }

}
