package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.repository.InitiativeRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InitiativeServiceImpl implements InitiativeService {

    private final InitiativeRepository initiativeRepository;
    private final UserService userService;

    public InitiativeServiceImpl(InitiativeRepository initiativeRepository, UserService userService) {
        this.initiativeRepository = initiativeRepository;
        this.userService = userService;
    }

    @Override
    public List<Initiative> findAll() {
        return this.initiativeRepository.findAll();
    }

    @Override
    public Optional<Initiative> findById(Long id) {
        return this.initiativeRepository.findById(id);
    }

    @Override
    public Optional<Initiative> save(String title, String description, Long initiatorId) {
        Optional<User> initiator = this.userService.findById(initiatorId);
        if(initiator.isEmpty())
            throw new InitiatorNotFoundException(initiatorId);

        Initiative initiative = new Initiative(title, description, initiator.get());
        return Optional.of(this.initiativeRepository.save(initiative));
    }

    @Override
    public Optional<Initiative> deleteById(Long id) {
        Optional<Initiative> initiative = this.findById(id);
        if(initiative.isPresent()) {
            this.initiativeRepository.deleteById(id);
            return initiative;
        }
        throw new InitiativeNotFoundException(id);
    }
}
