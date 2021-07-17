package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VolunteerOpportunityNotFoundException extends RuntimeException {

    public VolunteerOpportunityNotFoundException(Long id) {
        super(String.format("Volunteer opportunity with id %d is not found!", id));
    }
}
