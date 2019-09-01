package com.pruebaitau.demo.adapter.manager.impl;

import com.pruebaitau.demo.adapter.manager.EndpointManager;
import com.pruebaitau.demo.adapter.specific.cmis.util.ClientUtil;
import com.pruebaitau.demo.adapter.specific.infrastructure.EndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public abstract class EndpointManagerAbstract implements EndpointManager {

    private EndpointConfig endpointConfig;

    @Autowired
    public EndpointManagerAbstract(EndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;

    }


    public ResponseEntity endpointConsumerClient(final String pathEndpoint,
                                                 final Class<?> typeResponse,
                                                 final HttpMethod method,
                                                 final Object body) {

        RestTemplate clientConsumer = new RestTemplate();
        HttpHeaders httpHeadersConsumer = endpointConfig.createAuthenticationHeaders();
        return clientConsumer.exchange(pathEndpoint, method, new HttpEntity<>(body, httpHeadersConsumer), typeResponse);
    }

    public ResponseEntity endpointConsumerClient(final String pathEndpoint,
                                                 final Class<?> typeResponse,
                                                 final HttpMethod method,
                                                 final HashMap<String, String> headers) {

        RestTemplate clientConsumer = new RestTemplate();
        HttpHeaders httpHeadersConsumer = endpointConfig.createAuthenticationHeaders();
        httpHeadersConsumer.setAll(headers);
        return clientConsumer.exchange(pathEndpoint, method, new HttpEntity<>(httpHeadersConsumer), String.class);
    }

    public ResponseEntity endpointConsumerClient(final String pathEndpoint,
                                                 final Class<?> typeResponse,
                                                 final HttpMethod method,
                                                 final HttpEntity<?> headers) {

        RestTemplate clientConsumer = new RestTemplate();
        return clientConsumer.exchange(pathEndpoint, method, headers, String.class);
    }


    public ResponseEntity endpointConsumerClient(final String pathEndpoint,
                                                 final Class<?> typeResponse,
                                                 final HttpMethod method) {

        RestTemplate clientConsumer = new RestTemplate();
        HttpHeaders httpHeadersConsumer = endpointConfig.createAuthenticationHeaders();
        return clientConsumer.exchange(pathEndpoint, method, new HttpEntity<>(httpHeadersConsumer), String.class);
    }

    public ResponseEntity endpointConsumerClient(final String pathEndpoint,
                                                 final Class<?> typeResponse,
                                                 final HttpMethod method,
                                                 final Object body,
                                                 final HashMap<String, String> headers) {

        RestTemplate clientConsumer = new RestTemplate();
        HttpHeaders httpHeadersConsumer = endpointConfig.createAuthenticationHeaders();
        httpHeadersConsumer.setAll(headers);
        return clientConsumer.exchange(pathEndpoint, method, new HttpEntity<>(body, httpHeadersConsumer), String.class);
    }

}
