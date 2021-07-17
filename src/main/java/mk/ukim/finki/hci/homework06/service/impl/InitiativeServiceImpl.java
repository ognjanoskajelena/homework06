package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Initiator;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.repository.InitiativeRepository;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import mk.ukim.finki.hci.homework06.service.InitiatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InitiativeServiceImpl implements InitiativeService {

    private final InitiativeRepository initiativeRepository;
    private final InitiatorService initiatorService;

    public InitiativeServiceImpl(InitiativeRepository initiativeRepository, InitiatorService initiatorService) {
        this.initiativeRepository = initiativeRepository;
        this.initiatorService = initiatorService;
    }

    @Override
    public Optional<Initiative> findById(Long id) {
        return this.initiativeRepository.findById(id);
    }

    @Override
    public Optional<Initiative> save(String title, String description, String dateOpened, Long initiatorId) {
        Optional<Initiator> initiator = this.initiatorService.findById(initiatorId);
        if(initiator.isEmpty())
            throw new InitiatorNotFoundException(initiatorId);

        Initiative initiative = new Initiative(title, description, LocalDate.parse(dateOpened), initiator.get());
        return Optional.of(this.initiativeRepository.save(initiative));
    }

    @Override
    public Optional<Initiative> update(Long id, String title, String description, String dateOpened) {
        Optional<Initiative> initiative = this.findById(id);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(id);

        Initiative updatedInitiative = initiative.get();
        updatedInitiative.setTitle(title);
        updatedInitiative.setDescription(description);
        updatedInitiative.setDateOpened(LocalDate.parse(dateOpened));

        return Optional.of(this.initiativeRepository.save(updatedInitiative));
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
