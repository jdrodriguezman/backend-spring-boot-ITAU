package com.pruebaitau.demo.adapter.specific.api.impl;

import com.google.gson.Gson;
import com.pruebaitau.demo.adapter.manager.impl.EndpointManagerAbstract;
import com.pruebaitau.demo.adapter.specific.api.IServiciosApiCliente;
import com.pruebaitau.demo.adapter.specific.infrastructure.EndpointConfig;
import com.pruebaitau.demo.commons.domains.generic.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiciosApiClienteImpl extends EndpointManagerAbstract implements IServiciosApiCliente {

    @Autowired
    private final EndpointConfig endpointConfig;

    public ServiciosApiClienteImpl(EndpointConfig endpointConfig) {
        super(endpointConfig);
        this.endpointConfig  = endpointConfig;
    }

    @Override
    public ResponseEntity viewTodosConsume(String id, String title) {
        String url;
        if (!ObjectUtils.isEmpty(id) && !StringUtils.isEmpty(title)) {
            url = "https://jsonplaceholder.typicode.com/todos?id="+id+"&title="+title;
        }else if (!ObjectUtils.isEmpty(id) && StringUtils.isEmpty(title)){
            url = "https://jsonplaceholder.typicode.com/todos?id="+id;
        }else if (!StringUtils.isEmpty(title) && ObjectUtils.isEmpty(id)){
            url = "https://jsonplaceholder.typicode.com/todos?title="+title;
        }else{
            url = "https://jsonplaceholder.typicode.com/todos";
        }
        try {
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,  MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,  MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,  MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.GET,authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity viewListItemsConsume(String id, String title) {

            String url;
            if (!ObjectUtils.isEmpty(id) && StringUtils.isEmpty(title)){
                url = "https://my-project-itau.firebaseio.com/content.json?orderBy=\"id\"&equalTo=\""+id+"\"&print=pretty";
            }else if (!StringUtils.isEmpty(title) && ObjectUtils.isEmpty(id)){
                url = "https://my-project-itau.firebaseio.com/content.json?orderBy=\"title\"&equalTo=\""+title+"\"&print=pretty";
            }else{
                url = "https://my-project-itau.firebaseio.com/content.json";
            }
            try {
                HashMap<String, String> authHeader = new HashMap<>();
                authHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                authHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.GET,authHeader);

                return ResponseEntity.ok(responseEntity.getBody());
            }
            catch (HttpClientErrorException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .body(e.getMessage());
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .body(e.getMessage());
            }

    }


    @Override
    public ResponseEntity createItemConsume(ItemDTO item) {
        if (ObjectUtils.isEmpty(item)){

        }
        try {
            String url = "https://my-project-itau.firebaseio.com/content.json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.POST, new Gson().toJson(item), authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity editItemConsume(String registro, ItemDTO item) {
        try {
            String id = getRegitro(registro);
            String url = "https://my-project-itau.firebaseio.com/content/"+ id+".json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.PUT, new Gson().toJson(item), authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity deleteItemConsume(String registro) {
        try {
            String id = getRegitro(registro);
            String url = "https://my-project-itau.firebaseio.com/content/"+ id+".json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.DELETE,authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(e.getMessage());
        }
    }

    public String getRegitro(String registro){
        ResponseEntity<String> response  = viewListItemsConsume(registro,"");
        Map<String, Object> map = new Gson().fromJson(response.getBody(), Map.class);
        String id = "";
        for(String key : map.keySet()) {
            id = key;
        }
        return id;
    }

}
