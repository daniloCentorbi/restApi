package it.dinokrodino.restApi.controllers.v1;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.api.v1.model.PersonListDTO;
import it.dinokrodino.restApi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<PersonListDTO> getallPersons(){

        return new ResponseEntity<PersonListDTO>(
                new PersonListDTO(personService.getAllPersons()), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PersonDTO> getPersonByFirstName(@PathVariable String name){

        return new ResponseEntity<PersonDTO>(personService.getPersonByFirstName(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id){

        return new ResponseEntity<PersonDTO>(personService.getPersonById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody PersonDTO personDTO){

        return new ResponseEntity<PersonDTO>(personService.createNewPerson(personDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO){

        return new ResponseEntity<PersonDTO>(personService.updatePerson(id, personDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonDTO> patchPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO){

        return new ResponseEntity<PersonDTO>(personService.patchPerson(id, personDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){

        personService.deletePerson(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
