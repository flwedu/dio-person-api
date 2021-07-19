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

@Service
public class PersonService {

    private PersonRepository repository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponse savePerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);

        return MessageResponse
                .builder()
                .message(String.format("Created a person with ID %d", savedPerson.getId()))
                .build();
    }
}
