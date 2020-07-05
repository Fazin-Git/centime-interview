package com.centime.services.rest.webservices.api;

import com.centime.services.rest.webservices.config.ApiException;
import com.centime.services.rest.webservices.config.LogMethodCall;
import com.centime.services.rest.webservices.dto.Person;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonNameController {

    private static final String HTTP_RESPONSE_MESSAGE_400 = "Bad request";

    @PostMapping(path = "/v1/name")
    @LogMethodCall
    public ResponseDTO helloWorldBean(@RequestBody Person person,@RequestHeader("traceID") String traceID){
        ResponseDTO responseDTO = null;
        if (null!=person){
            responseDTO = new ResponseDTO(" "+person.getName()+" "+person.getSurname());
        }else{
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), HTTP_RESPONSE_MESSAGE_400);
        }
        return responseDTO;
    }
}
