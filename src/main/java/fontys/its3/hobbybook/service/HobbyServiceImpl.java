package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.Hobby;
import fontys.its3.hobbybook.repositories.AccountRepository;
import fontys.its3.hobbybook.repositories.HobbyRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    private final AccountRepository accountRepository;

    public HobbyServiceImpl(HobbyRepository hobbyRepository, AccountRepository accountRepository) {
        this.hobbyRepository = hobbyRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Hobby> getHobbies(String username) {

        Optional<Account> acc = accountRepository.findByUsername(username);

        return acc.map(Account::getHobbies).orElse(null);
    }

    @Override
    public ResponseEntity<Hobby> findHobbyById(Long id) {
    Hobby hobby = hobbyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hobbie is not found with id" + id));

    return ResponseEntity.ok(hobby);
}

    @Override
    public void deleteHobby(Long id) {
        if(findHobbyById(id) != null){
        hobbyRepository.deleteById(id);}
    }

    @Override
    public ResponseEntity<Hobby> updateHobby(Long id, Hobby h) {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hobby is not found with id" + id));

        hobby.setName(h.getName());
        hobby.setDescription(h.getDescription());

        Hobby updateHobby = hobbyRepository.save(hobby);
        return ResponseEntity.ok(updateHobby);
    }
}
