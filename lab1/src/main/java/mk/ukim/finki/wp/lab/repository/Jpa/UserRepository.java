package mk.ukim.finki.wp.lab.repository.Jpa;

import mk.ukim.finki.wp.lab.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndPassword(String username, String password);
}
