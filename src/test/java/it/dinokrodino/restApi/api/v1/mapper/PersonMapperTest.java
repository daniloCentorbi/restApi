package it.dinokrodino.restApi.api.v1.mapper;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.domain.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonMapperTest {

    PersonMapper personMapper = PersonMapper.INSTANCE;

    public static final String name = "John";
    public static final long id = 1L;

    @Test
    public void personToPersonDTO() {

        Person person = new Person();
        person.setFirstName(name);
        person.setId(id);

        PersonDTO personDto = personMapper.personToPersonDTO(person);

        assertEquals(Long.valueOf(id), personDto.getId() );
        assertEquals(name, personDto.getFirstName());
    }
}