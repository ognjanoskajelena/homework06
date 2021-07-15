package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.Role;

import javax.persistence.Entity;

@Data
@Entity
public class Administrator extends User {

    public Administrator() {
        super();
    }

    public Administrator(String name, String surname, String username, String password, String email){
        super(name, surname, username, password, email, Role.ROLE_ADMIN);
    }
}
