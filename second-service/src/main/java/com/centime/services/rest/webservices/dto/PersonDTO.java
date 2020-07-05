package com.centime.services.rest.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {
    @NonNull
    private String Name;
    @NonNull
    private String Surname;
}
