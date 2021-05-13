package fontys.its3.hobbybook.service;


import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepo;

    public MatchServiceImpl(MatchRepository matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public List<Account> getUserMatches(String username) {
        return matchRepo.findAllUserMatches(username);
    }


}
