package it.dinokrodino.restApi.bootstrap;

import it.dinokrodino.restApi.domain.Person;
import it.dinokrodino.restApi.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private PersonRepository personRepository;

    public Bootstrap(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = new Person();
        person1.setFirstName("Berry");
        person1.setLastName("Lindon");

        personRepository.save(person1);

        Person person2 = new Person();
        person2.setFirstName("John");
        person2.setLastName("Strawn");

        personRepository.save(person2);
    }
}
