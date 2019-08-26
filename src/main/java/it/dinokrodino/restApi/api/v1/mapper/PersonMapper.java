package it.dinokrodino.restApi.api.v1.mapper;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToPersonDTO(Person person);

    Person personDTOToPerson(PersonDTO personDTO);
}
