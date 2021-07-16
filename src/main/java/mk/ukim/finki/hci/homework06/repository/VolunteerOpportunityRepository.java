package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.VolunteerOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerOpportunityRepository extends JpaRepository<VolunteerOpportunity, Long> {
}
