package com.dio.personapi.service;

import static com.dio.personapi.utils.FakePersonFactory.createFakeDTO;
import static com.dio.personapi.utils.FakePersonFactory.createFakeEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void testThatPersonServiceReturnsAListType() {

        List<Person> listaParaRetorno = new ArrayList<Person>();

        when(personRepository.findAll()).thenReturn(listaParaRetorno);

        List<PersonDTO> listaRetornada = personService.listAll();

        Assertions.assertEquals(listaRetornada.getClass(), ArrayList.class);

    }

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

    @Test
    void testThatPersonServiceReturnsAListWithSameElements() {

        Person person = createFakeEntity();
        List<Person> listaParaRetorno = new ArrayList<Person>();
        listaParaRetorno.add(person);

        when(personRepository.findAll()).thenReturn(listaParaRetorno);

        // Configurando um mapper para converter

        List<PersonDTO> listaRetornada = personService.listAll();

        Assertions.assertEquals(person.getId(), listaRetornada.get(0).getId());
        Assertions.assertEquals(person.getFirstName(), listaRetornada.get(0).getFirstName());
        Assertions.assertEquals(person.getLastName(), listaRetornada.get(0).getLastName());
        Assertions.assertEquals(person.getBirthDate().toString(), listaRetornada.get(0).getBirthDate());
        Assertions.assertEquals(person.getCpf(), listaRetornada.get(0).getCpf());
    }

    @Test
    void testThatReturnedListHaveTheRightSize() {

        Person person = createFakeEntity();
        List<Person> listaParaRetorno = new ArrayList<Person>();
        listaParaRetorno.add(person);
        listaParaRetorno.add(person);

        when(personRepository.findAll()).thenReturn(listaParaRetorno);

        List<PersonDTO> listaRetornada = personService.listAll();

        Assertions.assertEquals(listaRetornada.size(), 2);
    }
}
