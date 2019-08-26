package it.dinokrodino.restApi.service;

import it.dinokrodino.restApi.api.v1.mapper.PersonMapper;
import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.domain.Person;
import it.dinokrodino.restApi.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;
    PersonService personService;

    public static final long ID = 1L;
    public static final String name = "Joe";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        personService = new PersonServiceImpl(PersonMapper.INSTANCE, personRepository);
    }

    @Test
    public void getAllPersons() {

        List<Person> list = Arrays.asList(new Person(), new Person(), new Person());

        when(personRepository.findAll()).thenReturn(list);

        List<PersonDTO> listDTO = personService.getAllPersons();

        assertEquals(3, listDTO.size());

    }

    @Test
    public void getPersonByFirstName() {

        Person person = new Person();
        person.setFirstName(name);
        person.setId(ID);

        when(personRepository.findByFirstName(anyString())).thenReturn(person);

        PersonDTO personDTO = personService.getPersonByFirstName(name);

        assertEquals(name, personDTO.getFirstName());

    }

    @Test
    public void getPersonById() {
        Person person = new Person();
        person.setFirstName(name);
        person.setId(ID);

        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        PersonDTO personDTO = personService.getPersonById(ID);

        assertEquals(name , personDTO.getFirstName());
    }

    @Test
    public void createNewPerson() {
        Person person = new Person();
        person.setId(ID);
        person.setFirstName(name);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(ID);
        personDTO.setFirstName(name);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO returnPerson = personService.createNewPerson(personDTO);

        assertEquals(personDTO.getFirstName()   , returnPerson.getFirstName());
        assertEquals("/api/v1/persons/1" , returnPerson.getCustomerUrl());
    }


    @Test
    public void updatePerson() {
        Person person = new Person();
        person.setId(ID);
        person.setFirstName(name);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(ID);
        personDTO.setFirstName(name);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO returnPerson = personService.updatePerson(ID, personDTO);
        
        assertEquals(name, returnPerson.getFirstName());
    }

    @Test
    public void deletePerson() {
        personRepository.deleteById(1L);
        verify(personRepository, times(1)).deleteById(anyLong());
    }
}