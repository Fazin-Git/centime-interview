package com.centime.rest.webservices.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"name","subClasses"})
public class MasterNames {
    private String            name;
    private List<StudentName> subClasses;
}
