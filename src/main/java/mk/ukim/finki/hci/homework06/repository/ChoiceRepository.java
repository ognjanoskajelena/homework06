package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
