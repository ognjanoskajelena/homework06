package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Administrator;
import mk.ukim.finki.hci.homework06.model.VolunteerOpportunity;
import mk.ukim.finki.hci.homework06.model.exception.AdministratorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.VolunteerOpportunityNotFoundException;
import mk.ukim.finki.hci.homework06.repository.VolunteerOpportunityRepository;
import mk.ukim.finki.hci.homework06.service.AdministratorService;
import mk.ukim.finki.hci.homework06.service.VolunteerOpportunityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolunteerOpportunityServiceImpl implements VolunteerOpportunityService {

    private final VolunteerOpportunityRepository volunteerOpportunityRepository;
    private final AdministratorService administratorService;

    public VolunteerOpportunityServiceImpl(VolunteerOpportunityRepository volunteerOpportunityRepository,
                                           AdministratorService administratorService) {
        this.volunteerOpportunityRepository = volunteerOpportunityRepository;
        this.administratorService = administratorService;
    }

    @Override
    public Optional<VolunteerOpportunity> findById(Long id) {
        return this.volunteerOpportunityRepository.findById(id);
    }

    @Override
    public Optional<VolunteerOpportunity> save(String topic, String description, Long administratorId) {
        Optional<Administrator> administrator = this.administratorService.findById(administratorId);
        if(administrator.isEmpty())
            throw new AdministratorNotFoundException(administratorId);

        VolunteerOpportunity volunteerOpportunity = new VolunteerOpportunity(topic, description, administrator.get());
        return Optional.of(this.volunteerOpportunityRepository.save(volunteerOpportunity));
    }

    @Override
    public Optional<VolunteerOpportunity> update(Long id, String topic, String description) {
        Optional<VolunteerOpportunity> volunteerOpportunity = this.volunteerOpportunityRepository.findById(id);
        if(volunteerOpportunity.isEmpty())
            throw new VolunteerOpportunityNotFoundException(id);

        VolunteerOpportunity updatedVolunteerOpportunity = volunteerOpportunity.get();
        updatedVolunteerOpportunity.setTopic(topic);
        updatedVolunteerOpportunity.setDescription(description);

        return Optional.of(this.volunteerOpportunityRepository.save(updatedVolunteerOpportunity));
    }

    @Override
    public Optional<VolunteerOpportunity> deleteById(Long id) {
        Optional<VolunteerOpportunity> volunteerOpportunity = this.findById(id);
        if(volunteerOpportunity.isPresent()) {
            this.volunteerOpportunityRepository.deleteById(id);
            return volunteerOpportunity;
        }
        throw new VolunteerOpportunityNotFoundException(id);
    }
}
