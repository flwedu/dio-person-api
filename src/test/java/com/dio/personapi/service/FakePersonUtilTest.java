package com.dio.personapi.service;

import static com.dio.personapi.utils.FakePersonFactory.createFakeDTO;
import static com.dio.personapi.utils.FakePersonFactory.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.model.Person;

import org.junit.jupiter.api.Test;

public class FakePersonUtilTest {

    @Test
    public void testIfAFakeDtoIsCreated() {

        var fakePersonDTO = createFakeDTO();

        assertEquals(fakePersonDTO.getClass(), PersonDTO.class);
    }

    @Test
    public void testIfAFakeEntityIsCreated() {

        var fakePerson = createFakeEntity();

        assertEquals(fakePerson.getClass(), Person.class);
    }
}
