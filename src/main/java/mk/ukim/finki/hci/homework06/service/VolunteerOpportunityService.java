package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.VolunteerOpportunity;

import java.util.Optional;

public interface VolunteerOpportunityService {
    Optional<VolunteerOpportunity> findById(Long id);

    Optional<VolunteerOpportunity> save(String topic, String description, Long authorId);

    Optional<VolunteerOpportunity> update(Long id, String topic, String description);

    Optional<VolunteerOpportunity> deleteById(Long id);
}
