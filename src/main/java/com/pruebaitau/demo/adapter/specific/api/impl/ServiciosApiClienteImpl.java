package com.pruebaitau.demo.adapter.specific.api.impl;

import com.google.gson.Gson;
import com.pruebaitau.demo.adapter.manager.impl.EndpointManagerAbstract;
import com.pruebaitau.demo.adapter.specific.api.IServiciosApiCliente;
import com.pruebaitau.demo.adapter.specific.infrastructure.EndpointConfig;
import com.pruebaitau.demo.commons.domains.generic.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;

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
            authHeader.put("Accept", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put("Content-type", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,  MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,  MediaType.APPLICATION_JSON_VALUE);
            authHeader.put(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,  MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.GET,authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity viewListItemsConsume() {
        try {
            String url = "https://my-project-itau.firebaseio.com/content.json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put("Accept", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put("Content-type", MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.GET,authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity createItemConsume(ItemDTO item) {
        if (ObjectUtils.isEmpty(item)){

        }
        try {
            String url = "https://my-project-itau.firebaseio.com/content.json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put("Accept", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put("Content-type", MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.POST, new Gson().toJson(item), authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity editItemConsume(String registro, ItemDTO item) {
        try {
            String url = "https://my-project-itau.firebaseio.com/content/"+ registro+".json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put("Accept", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put("Content-type", MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.PUT, new Gson().toJson(item), authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity deleteItemConsume(String registro) {
        try {
            String url = "https://my-project-itau.firebaseio.com/content/"+ registro+".json";
            HashMap<String, String> authHeader = new HashMap<>();
            authHeader.put("Accept", MediaType.APPLICATION_JSON_VALUE);
            authHeader.put("Content-type", MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = endpointConsumerClient(url, String.class, HttpMethod.DELETE,authHeader);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException e) {
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

}
