package fontys.its3.hobbybook.repositories;


import fontys.its3.hobbybook.domain.Account;
import fontys.its3.hobbybook.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    @Query("SELECT B.fromUser FROM Match A JOIN Match B ON A.fromUser = B.toUser AND A.toUser = B.fromUser " +
           "WHERE A.like_pass = true AND A.fromUser.username = :username AND B.like_pass = TRUE")
    List<Account> findAllUserMatches(@Param("username") String username);

}
