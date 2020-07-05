package com.centime.services.rest.webservices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStatus {
    private String responseStatus;
    public ResponseStatus(String status) {
        this.responseStatus = status;
    }

}
