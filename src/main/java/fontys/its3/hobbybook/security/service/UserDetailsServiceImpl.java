package fontys.its3.hobbybook.security.service;



import fontys.its3.hobbybook.domain.EGender;
import fontys.its3.hobbybook.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import fontys.its3.hobbybook.domain.Account;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private AccountRepository accRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           Account user = accRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            return UserDetailsImpl.build(user);
    }

}