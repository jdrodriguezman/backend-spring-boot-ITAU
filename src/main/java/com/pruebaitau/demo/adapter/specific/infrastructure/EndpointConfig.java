package com.pruebaitau.demo.adapter.specific.infrastructure;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@Configuration
public class EndpointConfig{
    @Bean
    @ConfigurationProperties(prefix = "alfresco.client")
    public EndpointProperties properties() {
        return new EndpointProperties();
    }

    @Bean
    public HttpHeaders createAuthenticationHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, MediaType.ALL_VALUE);
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, MediaType.ALL_VALUE);
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, MediaType.ALL_VALUE);
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, MediaType.ALL_VALUE);
        return headers;
    }

    @Data
    @Builder
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class EndpointProperties {
        private String user;
        private String password;
    }

}
