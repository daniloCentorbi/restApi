package it.dinokrodino.restApi.service;

import it.dinokrodino.restApi.api.v1.mapper.PersonMapper;
import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.bootstrap.Bootstrap;
import it.dinokrodino.restApi.domain.Person;
import it.dinokrodino.restApi.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNot.not;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonServiceImplIT {


    @Autowired
    PersonRepository personRepository;

    PersonService personService;

    @Before
    public void setUp() throws Exception {

        Bootstrap bootstrap = new Bootstrap(personRepository);
        bootstrap.run();

        personService = new PersonServiceImpl(PersonMapper.INSTANCE, personRepository);
    }

    public Long getFirstIdValue() {
        List<Person> list = personRepository.findAll();
        return list.get(0).getId();
    }

    @Test
    public void patchPersonFirstName() throws Exception {
        long id = getFirstIdValue();
        String updatedName = "updatedName";

        Person originalPerson = personRepository.findById(id).get();
        String originalName = originalPerson.getFirstName();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(updatedName);

        personService.patchPerson(id, personDTO);

        Person updatedPerson = personRepository.findById(id).get();

        assertEquals(updatedName, updatedPerson.getFirstName());
        assertThat(originalName, not(equalTo(updatedPerson.getFirstName())));
        assertThat(originalPerson.getLastName(), equalTo(updatedPerson.getLastName()));
    }

    @Test
    public void patchPersonLastName() throws Exception {
        long id = getFirstIdValue();
        String updatedName = "updatedName";

        Person originalPerson = personRepository.findById(id).get();
        String originalName = originalPerson.getLastName();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setLastName(updatedName);

        personService.patchPerson(id, personDTO);

        Person updatedPerson = personRepository.findById(id).get();

        assertEquals(updatedName, updatedPerson.getLastName());
        assertThat(originalName, not(equalTo(updatedPerson.getLastName())));
    }
}
