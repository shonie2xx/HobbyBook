package fontys.its3.hobbybook.repositories;

import fontys.its3.hobbybook.domain.ERole;
import fontys.its3.hobbybook.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

        Optional<Role> findByName(ERole name);

}
