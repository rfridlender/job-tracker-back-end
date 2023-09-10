package app.door2door.jobtracker.repository;

import java.util.Optional;
import app.door2door.jobtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}