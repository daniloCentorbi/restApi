package it.dinokrodino.restApi.service;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAllPersons();

    PersonDTO getPersonByFirstName(String firstName);

    PersonDTO getPersonById(long id);

    PersonDTO createNewPerson(PersonDTO personDTO);

    PersonDTO updatePerson(Long id, PersonDTO personDTO);

    PersonDTO patchPerson(Long id, PersonDTO personDTO);

    void deletePerson(Long id);

}
