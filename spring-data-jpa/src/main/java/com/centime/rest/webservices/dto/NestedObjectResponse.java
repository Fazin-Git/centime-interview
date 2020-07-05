package com.centime.rest.webservices.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NestedObjectResponse {
    private List<MasterNames> masterNames;
}
