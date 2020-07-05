package com.centime.services.rest.webservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.centime.services.rest.webservices.config.ClientConfig;
import com.centime.services.rest.webservices.dto.Person;
import com.centime.services.rest.webservices.dto.ResponseStatus;
import com.centime.services.rest.webservices.api.HelloWorldController;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloWorldControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    ClientConfig clientConfig;

    @InjectMocks
    HelloWorldController helloWorldController;

    @Test
    public void getStudentTest(){
        MockitoAnnotations.initMocks(this);
        ResponseStatus responseDTO21 = helloWorldController.getStatus("1234f");
        assertEquals("Up",responseDTO21.getResponseStatus());

    }

    @Test
    public void personGreetingPostTest(){
        MockitoAnnotations.initMocks(this);
        doReturn("/second").when(clientConfig).getSecondServiceUrl();
        doReturn("/third").when(clientConfig).getThirdServiceUrl();
        ResponseEntity<ResponseDTO> result1 = new ResponseEntity<ResponseDTO>(new ResponseDTO("Hello"),HttpStatus.OK);
        ResponseEntity<ResponseDTO> result2 = new ResponseEntity<ResponseDTO>(new ResponseDTO("John Doe"),HttpStatus.OK);
        doReturn(result1).when(restTemplate).exchange(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.eq(HttpMethod.GET),
            ArgumentMatchers.<HttpEntity<Person>>any(),
            ArgumentMatchers.<Class<ResponseDTO>>any());
        doReturn(result2).when(restTemplate).exchange(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.eq(HttpMethod.POST),
            ArgumentMatchers.<HttpEntity<Person>>any(),
            ArgumentMatchers.<Class<ResponseDTO>>any());
        ResponseDTO responseDTO = helloWorldController.personGreetingPost(new Person("Joe", "Doe"), "asdf123");
        assertEquals("HelloJohn Doe",responseDTO.getResponseValue());
    }
}
