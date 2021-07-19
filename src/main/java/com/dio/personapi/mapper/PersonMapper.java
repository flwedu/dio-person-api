package com.dio.personapi.mapper;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Essa biblioteca auxilia na criação automática de um Mapper (Objeto responsável por realizar conversões
 * entre DTO e Model.
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
