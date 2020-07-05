package com.centime.services.rest.webservices.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("service")
@Getter
@Setter
public class ClientConfig {
    private String secondServiceUrl;
    private String thirdServiceUrl;
}
