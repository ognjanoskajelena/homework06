package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}
