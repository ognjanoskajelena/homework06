package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username \"%s\" is already taken!", username));
    }
}
