package com.dio.personapi.service;

import static com.dio.personapi.utils.FakePersonFactory.*;
import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.mapper.PersonMapper;
import com.dio.personapi.message.MessageResponse;
import com.dio.personapi.model.Person;
import com.dio.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonMapper personMapper;

    // Pedindo ao mockito para criar um mock e injetar na classe service
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    void testThatPersonDTOisSavedAndAMessageIsReturned() {

        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponse expectedMessage = MessageResponse.builder().
                message(String.format("Created a person with ID %d", expectedSavedPerson.getId()))
                .build();

        MessageResponse meesageCreated = personService.save(personDTO);

        Assertions.assertEquals(expectedMessage, meesageCreated);

    }
}
