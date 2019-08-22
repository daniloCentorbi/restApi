package it.dinokrodino.restApi.api.v1.model;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
