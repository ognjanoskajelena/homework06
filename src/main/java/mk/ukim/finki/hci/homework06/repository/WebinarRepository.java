package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebinarRepository extends JpaRepository<Webinar, Long> {
}
