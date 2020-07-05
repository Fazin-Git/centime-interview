package com.centime.services.rest.webservices.api;

import com.centime.services.rest.webservices.config.LogMethodCall;
import com.centime.services.rest.webservices.dto.ResponseDTO;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final static Logger LOGGER = Logger
        .getLogger(GreetingController.class.getName());

    @GetMapping(path = "/v1/greeting")
    @LogMethodCall
    public ResponseDTO helloWorldBean(@RequestHeader Map<String, String> headers){
        return new ResponseDTO("Hello");
    }
}
