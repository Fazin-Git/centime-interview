package com.centime.rest.webservices.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private Integer id;
    @NonNull
    private Integer  parentid;
    @NonNull
    private String    name;
    @NonNull
    private String    color;
}
