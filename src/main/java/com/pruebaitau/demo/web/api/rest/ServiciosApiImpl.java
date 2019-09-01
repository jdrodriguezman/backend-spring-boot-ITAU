package com.pruebaitau.demo.web.api.rest;

import com.pruebaitau.demo.adapter.specific.api.IServiciosApiCliente;
import com.pruebaitau.demo.commons.constansts.EndPointServiciosApi;
import com.pruebaitau.demo.commons.domains.generic.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPointServiciosApi.ENDPOINT_BASE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiciosApiImpl implements IServiciosApi{


    private final IServiciosApiCliente iServiciosApiCliente;

    @Autowired
    public ServiciosApiImpl(IServiciosApiCliente iServiciosApiCliente) {
       this.iServiciosApiCliente = iServiciosApiCliente;
    }

    @Override
    @GetMapping(EndPointServiciosApi.GET_ALL_TODOS)
    public ResponseEntity viewTodos(@RequestParam(required = false) String id, @RequestParam(required = false) String title) {
        return iServiciosApiCliente.viewTodosConsume(id,title);
    }

    @Override
    @GetMapping(EndPointServiciosApi.GET_ALL_CONTENT_ITEM)
    public ResponseEntity viewListItems(@RequestParam(required = false) String id, @RequestParam(required = false) String title) {
        return iServiciosApiCliente.viewListItemsConsume(id,title);
    }

    @Override
    @PostMapping(EndPointServiciosApi.CREATE_CONTENT_ITEM)
    public ResponseEntity createItem(@RequestBody ItemDTO item) {
        return iServiciosApiCliente.createItemConsume(item);
    }

    @Override
    @PutMapping(EndPointServiciosApi.EDIT_CONTENT_ITEM)
    public ResponseEntity editItem(@PathVariable("registro") String registro, @RequestBody ItemDTO item) {
        return iServiciosApiCliente.editItemConsume(registro,item);
    }

    @Override
    @DeleteMapping(EndPointServiciosApi.DELETE_CONTENT_ITEM)
    public ResponseEntity deleteItem(@PathVariable("registro") String registro) {
        return iServiciosApiCliente.deleteItemConsume(registro);
    }

}
