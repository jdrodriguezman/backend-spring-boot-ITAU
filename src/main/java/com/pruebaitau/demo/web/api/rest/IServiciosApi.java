package com.pruebaitau.demo.web.api.rest;

import com.pruebaitau.demo.commons.domains.generic.ItemDTO;
import org.springframework.http.ResponseEntity;

public interface IServiciosApi {

    ResponseEntity viewTodos(String id, String title);
    ResponseEntity viewListItems(String id, String title);
    ResponseEntity createItem(ItemDTO item);
    ResponseEntity editItem(String registro,ItemDTO item);
    ResponseEntity deleteItem(String registro);
}
