package com.pruebaitau.demo.adapter.specific.api;

import com.pruebaitau.demo.commons.domains.generic.ItemDTO;
import org.springframework.http.ResponseEntity;

public interface IServiciosApiCliente {

    ResponseEntity viewTodosConsume(String id, String title);
    ResponseEntity viewListItemsConsume(String id, String title);
    ResponseEntity createItemConsume(ItemDTO item);
    ResponseEntity editItemConsume(String registro,ItemDTO item);
    ResponseEntity deleteItemConsume(String registro);
}
