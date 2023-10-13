package dat3.adventureXP.repository;

import dat3.adventureXP.entity.User;
import dat3.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

      UserWithRoles findByUsername(String employee);

}
