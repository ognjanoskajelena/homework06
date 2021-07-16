package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepository extends JpaRepository<User, Long> {
}