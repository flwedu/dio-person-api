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
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Lista todas as pessoas do repositório
     * @return lista com todas as pessoas
     */
    public List<PersonDTO> listAll(){
        List<Person> personList = personRepository.findAll();
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

        Person savedPerson = personRepository.save(personToSave);

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

        personRepository.deleteById(id);
    }

    /**
     * Atualiza um elemento com novos dados.
     * @param id Id do elemento a ser atualizado.
     * @param personDTO Novos dados.
     * @return um MessageResponse} com informações a serem exibidas
     * @throws PersonNotFoundException Exception lançada caso não seja encontrado nenhum elemento.
     */
    public MessageResponse updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person personAfterUpdate = personRepository.save(personToUpdate);

        return MessageResponse
                .builder()
                .message(String.format("Updated person with Id %d", personAfterUpdate.getId()))
                .build();

    }

    /**
     * Verifica se existe algum elemento com esse Id.
     * Caso não exista, lança uma Exception diretamente.
     * @param id Id do elemento a ser buscado
     * @throws PersonNotFoundException Exception lançada caso não seja encontrado.
     */
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
