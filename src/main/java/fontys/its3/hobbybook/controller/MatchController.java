package fontys.its3.hobbybook.controller;

import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(MatchController.BASE_URI)
public class MatchController {

    public static final String BASE_URI ="/api/hobbybook/matches";

    @Autowired
    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }



    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getMatches(@PathVariable String username)
    {
        return matchService.getUserMatches(username);
    }


}
