package com.centime.services.rest.webservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.centime.services.rest.webservices.dto.Person;
import com.centime.services.rest.webservices.api.PersonNameController;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PersonNameControllerTest {

    PersonNameController personNameController = new PersonNameController();

    @Test
    public void helloWorldBeanTest(){
        Map<String, String> headers = new HashMap<>();
        Person person = new Person("John","Doe");
        headers.put("a","b");
        ResponseDTO responseDTO = personNameController.helloWorldBean(person,"Id");
        assertEquals(" John Doe",responseDTO.getResponseValue());
    }
}
