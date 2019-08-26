package it.dinokrodino.restApi.controllers.v1;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.api.v1.model.PersonListDTO;
import it.dinokrodino.restApi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PersonListDTO getallPersons(){

        return new PersonListDTO(personService.getAllPersons());
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPersonByFirstName(@PathVariable String name){

        return personService.getPersonByFirstName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPersonById(@PathVariable Long id){

        return personService.getPersonById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createNewPerson(@RequestBody PersonDTO personDTO){

        return (personService.createNewPerson(personDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO){

        return personService.updatePerson(id, personDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO patchPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO){

        return personService.patchPerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id){

        personService.deletePerson(id);
    }
}
