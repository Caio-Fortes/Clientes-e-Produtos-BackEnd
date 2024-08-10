package com.ux.ux.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ux.ux.dtos.ClienteRecordDto;
import com.ux.ux.models.ClienteModel;
import com.ux.ux.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/clientes")
    public ResponseEntity<ClienteModel> saveCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "id")UUID id) {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if(cliente0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não encontrado!");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(cliente0.get());
    }
    
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id")UUID id,
        @RequestBody @Valid ClienteRecordDto clienteRecordDto
    ) 
    {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if(cliente0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não encontrado!");
        }
        var clienteModel = cliente0.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteModel));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id")UUID id) 
    {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if(cliente0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente não encontrado!");
        }
        clienteRepository.delete(cliente0.get());
        return ResponseEntity.status(HttpStatus.OK).body("cliente deletado com sucesso!");
    }
}
