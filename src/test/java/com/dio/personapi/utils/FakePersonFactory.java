package com.dio.personapi.utils;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.model.Person;

import java.time.LocalDate;
import java.util.Collections;

public class FakePersonFactory {

        private static final String FIRST_NAME = "Famous";
        private static final String LAST_NAME = "Joker";
        private static final String CPF_NUMBER = "369.333.878-79";
        private static final long PERSON_ID = 1L;
        public static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

        public static PersonDTO createFakeDTO() {
            return PersonDTO.builder()
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .cpf(CPF_NUMBER)
                    .birthDate("01-01-2021")
                    .phones(Collections.singletonList(FakePhoneFactory.createFakeDTO()))
                    .build();
        }

        public static Person createFakeEntity() {
            return Person.builder()
                    .id(PERSON_ID)
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .cpf(CPF_NUMBER)
                    .birthDate(BIRTH_DATE)
                    .phones(Collections.singletonList(FakePhoneFactory.createFakeEntity()))
                    .build();
        }

    }
