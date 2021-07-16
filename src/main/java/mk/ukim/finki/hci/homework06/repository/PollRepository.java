package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
