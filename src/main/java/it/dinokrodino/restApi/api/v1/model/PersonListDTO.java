package it.dinokrodino.restApi.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PersonListDTO {
    List<PersonDTO> persons;

    public PersonListDTO(List<PersonDTO> persons) {
        this.persons = persons;
    }
}
