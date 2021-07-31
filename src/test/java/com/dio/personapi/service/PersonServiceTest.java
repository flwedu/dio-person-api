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

    // O PersonService utiliza dois objetos, um PersonMapper e um PersonRepository
    // Então ambos serão mockados com @Mock, e posteriormente injetados ao service
    // com o @InjectMock
    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testThatPersonDTOisSavedAndAMessageIsReturned() {

        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        // Pedindo ao mockito para retornar objetos quando...
        // Quando tentar salvar qualquer objeto da classe Person
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        // Essa é a mensagem desejada:
        MessageResponse expectedMessage = MessageResponse.builder()
                .message(String.format("Created a person with ID %d", expectedSavedPerson.getId())).build();

        MessageResponse meesageCreated = personService.save(personDTO);

        // Comparando a mensagem esperada com a mensagem retornada
        Assertions.assertEquals(expectedMessage, meesageCreated);
    }
}
