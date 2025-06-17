package s24825.model.person;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Person {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;

    private String email;
    private String password;


}
