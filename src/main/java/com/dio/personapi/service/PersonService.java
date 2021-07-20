package com.dio.personapi.service;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.exception.PersonNotFoundException;
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
     * Verifica se existe algum elemento com esse Id.
     * Caso não exista, lança uma Exception diretamente.
     * @param id Id do elemento a ser buscado
     * @throws PersonNotFoundException Excessão lançada caso não seja encontrado.
     */
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    /**
     * Lista todas as pessoas do repositório
     * @return lista com todas as pessoas
     */
    public List<PersonDTO> listAll(){
        List<Person> personList = repository.findAll();
        return personList
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Salva uma nova Pessoa no repositório
     * @param personDTO DTO com os dados a serem persistidos
     * @return Mensagem que será retornada
     */
    public MessageResponse save(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);

        return MessageResponse
                .builder()
                .message(String.format("Created a person with ID %d", savedPerson.getId()))
                .build();
    }

    /**
     * Realiza a busca de uma elemento pelo seu Id
     * @param id Id do elemento desejado
     * @return Caso seja encontrada um elemento
     * @throws PersonNotFoundException Caso não seja encontrado.
     */
    public PersonDTO getById(Long id) throws PersonNotFoundException {

        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    /**
     * Deleta um elemento encontrado pelo Id.
     * @param id Id do elemento
     */
    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        repository.deleteById(id);
    }
}
