package com.centime.services.rest.webservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.centime.services.rest.webservices.api.GreetingController;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GreetingControllerTest {


    GreetingController greetingController = new GreetingController();

    @Test
    public void helloWorldBeanTest(){
        Map<String, String> headers = new HashMap<>();
        headers.put("a","b");
        ResponseDTO responseDTO = greetingController.helloWorldBean(headers);
        assertEquals("Hello",responseDTO.getResponseValue());
    }
}
