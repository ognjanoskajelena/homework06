package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.VolunteerOpportunity;
import mk.ukim.finki.hci.homework06.model.exception.AdministratorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.VolunteerOpportunityNotFoundException;
import mk.ukim.finki.hci.homework06.repository.VolunteerOpportunityRepository;
import mk.ukim.finki.hci.homework06.service.UserService;
import mk.ukim.finki.hci.homework06.service.VolunteerOpportunityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerOpportunityServiceImpl implements VolunteerOpportunityService {

    private final VolunteerOpportunityRepository volunteerOpportunityRepository;
    private final UserService userService;

    public VolunteerOpportunityServiceImpl(VolunteerOpportunityRepository volunteerOpportunityRepository,
                                           UserService userService) {
        this.volunteerOpportunityRepository = volunteerOpportunityRepository;
        this.userService = userService;
    }

    @Override
    public Optional<VolunteerOpportunity> findById(Long id) {
        return this.volunteerOpportunityRepository.findById(id);
    }

    @Override
    public List<VolunteerOpportunity> findAll() {
        return this.volunteerOpportunityRepository.findAll();
    }

    @Override
    public Optional<VolunteerOpportunity> save(String topic, String description, Long administratorId) {
        Optional<User> administrator = this.userService.findById(administratorId);
        if(administrator.isEmpty())
            throw new AdministratorNotFoundException(administratorId);

        VolunteerOpportunity volunteerOpportunity = new VolunteerOpportunity(topic, description, administrator.get());
        return Optional.of(this.volunteerOpportunityRepository.save(volunteerOpportunity));
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
