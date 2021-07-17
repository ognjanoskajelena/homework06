package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiator;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.Webinar;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.WebinarNotFoundException;
import mk.ukim.finki.hci.homework06.repository.WebinarRepository;
import mk.ukim.finki.hci.homework06.service.InitiatorService;
import mk.ukim.finki.hci.homework06.service.ParticipantService;
import mk.ukim.finki.hci.homework06.service.WebinarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class WebinarServiceImpl implements WebinarService {

    private final WebinarRepository webinarRepository;
    private final InitiatorService initiatorService;
    private final ParticipantService participantService;

    public WebinarServiceImpl(WebinarRepository webinarRepository,
                              InitiatorService initiatorService,
                              ParticipantService participantService) {
        this.webinarRepository = webinarRepository;
        this.initiatorService = initiatorService;
        this.participantService = participantService;
    }

    @Override
    public Optional<Webinar> findById(Long id) {
        return this.webinarRepository.findById(id);
    }

    @Override
    public Optional<Webinar> save(String topic, String description, String link, String date, String time, Long initiatorId) {
        Optional<Initiator> initiator = this.initiatorService.findById(initiatorId);
        if(initiator.isPresent()) {
            Webinar webinar = new Webinar(topic, description, link, LocalDate.parse(date), LocalTime.parse(time),
                    initiator.get());
            return Optional.of(this.webinarRepository.save(webinar));
        }
        throw new InitiatorNotFoundException(initiatorId);
    }

    @Override
    public Optional<Webinar> update(Long id, String topic, String description, String link, String date, String time) {
        Optional<Webinar> webinar = this.webinarRepository.findById(id);
        if(webinar.isPresent()) {
            Webinar updatedWebinar = webinar.get();
            updatedWebinar.setTopic(topic);
            updatedWebinar.setDescription(description);
            updatedWebinar.setLink(link);
            updatedWebinar.setDate(LocalDate.parse(date));
            updatedWebinar.setTime(LocalTime.parse(time));

            return Optional.of(this.webinarRepository.save(updatedWebinar));
        }
        throw new WebinarNotFoundException(id);
    }

    @Override
    public Optional<Webinar> deleteById(Long id) {
        Optional<Webinar> webinar = this.findById(id);
        if(webinar.isPresent()) {
            this.webinarRepository.deleteById(id);
            return webinar;
        }
        throw new WebinarNotFoundException(id);
    }

    @Override
    public Optional<Webinar> interested(Long webinarId, Long participantId) {
        Optional<Webinar> webinar = this.findById(webinarId);
        if(webinar.isEmpty())
            throw new WebinarNotFoundException(webinarId);

        Optional<Participant> participant = this.participantService.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Webinar updatedWebinar = webinar.get();
        updatedWebinar.addToInterestedParticipants(participant.get());
        return Optional.of(this.webinarRepository.save(updatedWebinar));
    }
}
