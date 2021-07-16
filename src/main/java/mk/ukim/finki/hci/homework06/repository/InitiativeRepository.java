package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Initiative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
}
