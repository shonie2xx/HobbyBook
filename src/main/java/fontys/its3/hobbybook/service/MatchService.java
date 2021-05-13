package fontys.its3.hobbybook.service;

import fontys.its3.hobbybook.domain.Account;

import java.util.List;

public interface MatchService {

        List<Account> getUserMatches(String username);


}
