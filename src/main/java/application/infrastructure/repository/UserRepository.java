package application.infrastructure.repository;

import application.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author violet.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
