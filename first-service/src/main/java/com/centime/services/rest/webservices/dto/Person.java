package com.centime.services.rest.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    @NonNull
    private String Name;
    @NonNull
    private String Surname;
}
