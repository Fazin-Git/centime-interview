package com.centime.services.rest.webservices.api;

import com.centime.services.rest.webservices.config.ClientConfig;
import com.centime.services.rest.webservices.config.LogMethodCall;
import com.centime.services.rest.webservices.config.exceptions.ApiException;
import com.centime.services.rest.webservices.dto.Person;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import com.centime.services.rest.webservices.dto.ResponseStatus;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {

    private static final String HTTP_RESPONSE_MESSAGE_503 = "Service unavailable";
    @Autowired
    private              RestTemplate restTemplate;
    @Autowired
    ClientConfig clientConfig;

    @GetMapping(path = "/v1/status")
    @LogMethodCall
    public ResponseStatus getStatus(@RequestHeader("traceID") String traceID) {
        return new ResponseStatus("Up");
    }

    @PostMapping(path = "/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodCall
    public ResponseDTO personGreetingPost(@RequestBody Person person, @RequestHeader("traceID") String traceID) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //headers.set("traceID",traceID);
        headers.add("traceID",traceID);
        HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);

        ResponseEntity<ResponseDTO>  response1 = restTemplate.exchange(clientConfig.getSecondServiceUrl(), HttpMethod.GET, entity, ResponseDTO.class);
        ResponseEntity<ResponseDTO> response2 = restTemplate.exchange(clientConfig.getThirdServiceUrl(), HttpMethod.POST, entity, ResponseDTO.class);
        if(null==response1 || null==response2){
            throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE.value(), HTTP_RESPONSE_MESSAGE_503);
        }
        ResponseDTO responseSecondService = response1.getBody();
        responseSecondService.setResponseValue(responseSecondService.getResponseValue() + response2.getBody().getResponseValue());
        return responseSecondService;
    }
}

