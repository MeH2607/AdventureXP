package dat3.adventureXP.repository;

import dat3.adventureXP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class UserRepository implements JpaRepository<User, String> {
}
