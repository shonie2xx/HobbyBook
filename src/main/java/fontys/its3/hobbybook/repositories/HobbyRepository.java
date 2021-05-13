package fontys.its3.hobbybook.repositories;

import fontys.its3.hobbybook.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {

}
