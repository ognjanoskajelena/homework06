package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.VolunteerOpportunity;

import java.util.List;
import java.util.Optional;

public interface VolunteerOpportunityService {
    Optional<VolunteerOpportunity> findById(Long id);

    List<VolunteerOpportunity> findAll();

    Optional<VolunteerOpportunity> save(String topic, String description, Long authorId);

    Optional<VolunteerOpportunity> deleteById(Long id);
}
