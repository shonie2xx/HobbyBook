package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Hobby;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HobbyService {

    List<Hobby> getHobbies(String username);

    ResponseEntity<Hobby> findHobbyById(Long id);

    void deleteHobby(Long id);

    ResponseEntity<Hobby> updateHobby(Long id, Hobby h);


}
