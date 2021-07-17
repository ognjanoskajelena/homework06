package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InitiativeNotFoundException extends RuntimeException {

    public InitiativeNotFoundException(Long id) {
        super(String.format("Initiative with id %d is not found!", id));
    }
}
