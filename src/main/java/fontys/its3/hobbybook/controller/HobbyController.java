package fontys.its3.hobbybook.controller;


import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.Hobby;

import fontys.its3.hobbybook.service.AccountService;
import fontys.its3.hobbybook.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/hobbybook/hobbies")
public class HobbyController {


    @Autowired
    private HobbyService hbbService;

    @Autowired
    private AccountService accService;

    public HobbyController(HobbyService hbbService) {
        this.hbbService = hbbService;
    }


    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Hobby> getAssignHobbies(@PathVariable String username)
        {
            return hbbService.getHobbies(username);
        }

    @GetMapping(path = "/find/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Hobby> findHobbyById(@PathVariable Long id)
    {
        return hbbService.findHobbyById(id);
    }

    @PostMapping(path = "/add/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveHobby(@PathVariable String username,@RequestBody Hobby hbb)
    {
    return accService.addHobby(username,hbb);
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteHobby(@PathVariable Long id)
    {
       hbbService.deleteHobby(id);
    }

    @PutMapping(path = "/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Hobby> updateHobby(@PathVariable Long id, @RequestBody Hobby h)
    {
        return hbbService.updateHobby(id,h);
    }
}
