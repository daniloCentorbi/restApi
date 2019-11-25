package it.dinokrodino.restApi.controllers.v1;

import it.dinokrodino.restApi.api.v1.model.PersonDTO;
import it.dinokrodino.restApi.service.PersonService;
import it.dinokrodino.restApi.service.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;

import static it.dinokrodino.restApi.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PersonControllerTest {

    public static final String name = "Jim";
    public static final long id = 1L;

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void getallPersons() throws Exception{

        List<PersonDTO> listDTO = Arrays.asList(new PersonDTO(), new PersonDTO(), new PersonDTO());

        when(personService.getAllPersons()).thenReturn(listDTO);

        mockMvc.perform(get("/api/v1/persons/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(3)));

    }

    @Test
    public void getPersonByFirstName() throws Exception{
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(name);
        personDTO.setId(id);

        when(personService.getPersonByFirstName(anyString())).thenReturn(personDTO);

        mockMvc.perform(get("/api/v1/persons/name/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(name)));

    }

    @Test
    public void getPersonById() throws Exception{
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(name);
        personDTO.setId(id);

        when(personService.getPersonById(anyLong())).thenReturn(personDTO);

        mockMvc.perform(get("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(name)));
    }

    @Test
    public void createNewPerson() throws Exception{
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(name);
        personDTO.setId(id);

        PersonDTO returnDTO = new PersonDTO();
        returnDTO.setFirstName(personDTO.getFirstName());
        returnDTO.setId(personDTO.getId());
        returnDTO.setCustomerUrl("/api/v1/persons/1");

        when(personService.createNewPerson(personDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(name)))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/persons/1")));


//        String response = mockMvc.perform(post("/api/v1/persons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(returnDTO)))
//                        .andReturn().getResponse().getContentAsString();
//        System.out.println("response: " + response);

    }

    @Test
    public void updatePerson() throws Exception{
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(name);
        personDTO.setId(id);

        PersonDTO returnDTO = new PersonDTO();
        returnDTO.setFirstName(personDTO.getFirstName());
        returnDTO.setId(personDTO.getId());
        returnDTO.setCustomerUrl("/api/v1/persons/1");

        when(personService.updatePerson(anyLong(),ArgumentMatchers.any())).thenReturn(returnDTO);


        mockMvc.perform(put("/api/v1/persons/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(returnDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName" , equalTo(name)));
    }


    @Test
    public void patchPerson() throws Exception{
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(name);
        personDTO.setId(id);

        PersonDTO returnDTO = new PersonDTO();
        returnDTO.setFirstName(personDTO.getFirstName());
        returnDTO.setLastName("LastName");
        returnDTO.setId(personDTO.getId());
        returnDTO.setCustomerUrl("/api/v1/persons/1");

        when(personService.patchPerson(anyLong(),ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(patch("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName" , equalTo(name)))
                .andExpect(jsonPath("$.lastName" , equalTo(returnDTO.getLastName())));

    }

    @Test
    public void deletePerson() throws Exception {

        mockMvc.perform(delete("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(personService).deletePerson(anyLong());

    }


//    @Test
//    public void testGetByIdNotFound() throws Exception {
//
//        when(personService.getPersonById(anyLong())).thenThrow(ResourceNotFoundException.class);
//
//        mockMvc.perform(get("/api/v1/persons/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

}