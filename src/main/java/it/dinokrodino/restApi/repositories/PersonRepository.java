package it.dinokrodino.restApi.repositories;

import it.dinokrodino.restApi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);

}
