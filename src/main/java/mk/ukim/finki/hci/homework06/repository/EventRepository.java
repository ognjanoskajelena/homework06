package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
