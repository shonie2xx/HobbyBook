package fontys.its3.hobbybook.repositories;

import fontys.its3.hobbybook.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByEmail(String email);

    Optional<Account> findByUsername(String username);

    Boolean existsAccountByUsername(String username);

    Boolean existsAccountByEmail(String username);

    @Query("select a.toUser from Match a where a.fromUser.username = :username")
    List<Account> accLiked_Passed(String username);

}
