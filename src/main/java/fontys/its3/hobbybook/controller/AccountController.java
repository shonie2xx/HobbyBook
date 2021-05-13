package fontys.its3.hobbybook.controller;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/hobbybook")
public class AccountController {

    @Autowired
    private AccountService accService;

    @RequestMapping(path = "/admin/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(@PathVariable String username){
        return accService.findAllWithoutMe(username);
    }


    @RequestMapping(path = "/admin/users/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@PathVariable Long id)
    {
        accService.deleteAccount(id);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Account getAccById(@PathVariable Long id)
    {
        return accService.findAccountById(id);
    }

    @RequestMapping(path = "/users/ready/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> readyToMatch(@PathVariable String username)
    {
        return accService.availableToMatchUsers(username);
    }

    @PutMapping(path = "/users/update/{id}")
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Account> editUser(@RequestBody Account account,@PathVariable Long id)
    {
        return accService.editUser(id,account);
    }

    @PutMapping(path = "/users/updatepass/{id}")
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Account> editUserpass(@PathVariable Long id,@RequestBody Account account)
    {
        return accService.editUser_pass(id,account);
    }


    @RequestMapping(path = "/users/like/{fromUserId}/{toUserId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account likeUser(@PathVariable Long fromUserId,@PathVariable Long toUserId)
    {
        return accService.likeUser(fromUserId,toUserId);
    }

    @RequestMapping(path = "/users/pass/{fromUserId}/{toUserId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account passUser(@PathVariable Long fromUserId,@PathVariable Long toUserId)
    {
        return accService.passUser(fromUserId,toUserId);
    }

}

