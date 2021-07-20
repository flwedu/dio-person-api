package com.dio.personapi.service;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.mapper.PersonMapper;
import com.dio.personapi.message.MessageResponse;
import com.dio.personapi.model.Person;
import com.dio.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository repository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todas as pessoas do repositório
     * @return lista com todas as pessoas
     */
    public List<PersonDTO> listAllPerson(){
        List<Person> listDTO = repository.findAll();
        return listDTO
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Salva uma nova Pessoa no repositório
     * @param personDTO DTO com os dados a serem persistidos
     * @return Mensagem que será retornada
     */
    public MessageResponse savePerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);

        return MessageResponse
                .builder()
                .message(String.format("Created a person with ID %d", savedPerson.getId()))
                .build();
    }
}
