package com.ux.ux.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.ux.ux.models.VendaModel;
import com.ux.ux.services.ClienteService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> clienteModelOptional = Optional.ofNullable(clienteService.save(clienteRecordDto));

        return clienteModelOptional.<ResponseEntity<Object>>map(clienteModel -> ResponseEntity.status(HttpStatus.CREATED).body(clienteModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: CNPJ já cadastrado!"));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getAllClientes() {

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getOneCliente(@PathVariable UUID id) {
        Optional<ClienteModel> clienteModelOptional = Optional.ofNullable(clienteService.findClienteById(id));

        return clienteModelOptional.<ResponseEntity<Object>>map(clienteModel -> ResponseEntity.status(HttpStatus.OK).body(clienteModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!"));
    }
    
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable UUID id,
        @RequestBody @Valid ClienteRecordDto clienteRecordDto
    ) 
    {
        ClienteModel clienteModel = clienteService.atualizarCliente(clienteRecordDto, id);

        if (clienteModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }

        return new ResponseEntity<>(clienteRecordDto, HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable UUID id) 
    {
        ClienteModel clienteModel = clienteService.findClienteById(id);

        if (clienteModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrada!");
        }

        clienteService.deleteCliente(clienteModel);
        
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
