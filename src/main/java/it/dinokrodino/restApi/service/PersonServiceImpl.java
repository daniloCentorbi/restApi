package it.dinokrodino.restApi.service;

import it.dinokrodino.restApi.api.v1.mapper.PersonMapper;
import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.domain.Person;
import it.dinokrodino.restApi.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    public PersonServiceImpl(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;


    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().
                stream().
                map(person -> {
                    PersonDTO personDTO = personMapper.personToPersonDTO(person);
                    personDTO.setCustomerUrl("/api/v1/persons/" + person.getId());
                    return personDTO;
                }).
                collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonByFirstName(String firstName) {
        PersonDTO personDTO = personMapper.personToPersonDTO(personRepository.findByFirstName(firstName));
        personDTO.setCustomerUrl("/api/v1/persons/" + personDTO.getId());
        return personDTO;
    }

    @Override
    public PersonDTO getPersonById(long id) {
        return personRepository.findById(id)
                .map(person -> {
                    PersonDTO personDTO = personMapper.personToPersonDTO(person);
                    personDTO.setCustomerUrl("/api/v1/person/" + person.getId());
                    return personDTO;
                })
                .orElseThrow(RuntimeException::new);//todo better exception handling
    }

    @Override
    public PersonDTO createNewPerson(PersonDTO personDTO) {
        Person person = personMapper.personDTOToPerson(personDTO);
        Person savedPerson = personRepository.save(person);
        PersonDTO returnPersonDTO = personMapper.personToPersonDTO(savedPerson);
        returnPersonDTO.setCustomerUrl("/api/v1/persons/" + savedPerson.getId());
        return returnPersonDTO;
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Person person = personMapper.personDTOToPerson(personDTO);
        person.setId(id);
        Person savedPerson = personRepository.save(person);
        PersonDTO returnPersonDTO = personMapper.personToPersonDTO(savedPerson);
        returnPersonDTO.setCustomerUrl("/api/v1/persons/" + savedPerson.getId());
        return returnPersonDTO;
    }

    @Override
    public PersonDTO patchPerson(Long id, PersonDTO personDTO) {
        return personRepository.findById(id).map(person -> {
            if(personDTO.getFirstName() != null){
                person.setFirstName(personDTO.getFirstName());
            }
            if(personDTO.getLastName() != null){
                person.setLastName(personDTO.getLastName());
            }
            PersonDTO returnPersonDTO = personMapper.personToPersonDTO(personRepository.save(person));
            returnPersonDTO.setCustomerUrl("/api/v1/persons/" + returnPersonDTO.getId());
            return returnPersonDTO;
                }).orElseThrow(RuntimeException::new);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
