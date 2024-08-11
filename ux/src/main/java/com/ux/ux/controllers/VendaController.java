package com.ux.ux.controllers;


import com.ux.ux.dtos.VendaRecordDto;
import com.ux.ux.models.VendaModel;
import com.ux.ux.repositories.VendaRepository;
import com.ux.ux.services.VendaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vendas")
    public ResponseEntity<VendaModel> saveVenda(@RequestBody VendaRecordDto vendaDto) {
        VendaModel vendaModel = new VendaModel();
        vendaModel.setData(vendaDto.data());
        vendaModel.setStatus(vendaDto.status());
        vendaModel.setValor(vendaDto.valor());
        UUID clienteId = UUID.fromString(vendaDto.clienteId());
        VendaModel novaVenda = vendaService.criarVenda(vendaModel, clienteId);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaModel>> getAllVendas() {
        return ResponseEntity.status(HttpStatus.OK).body(vendaRepository.findAll());
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<Object> getOneVendas(@PathVariable(value = "id")UUID id) {
        Optional<VendaModel> venda0 = vendaRepository.findById(id);
        if(venda0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("venda não encontrada!");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(venda0.get());
    }

    @PutMapping("/vendas/{id}")
    public ResponseEntity<Object> updateVenda(@PathVariable(value = "id")UUID id,
        @RequestBody @Valid VendaRecordDto vendaDto
    ) 
    {
        VendaModel vendaModel = new VendaModel();
        vendaModel.setData(vendaDto.data());
        vendaModel.setStatus(vendaDto.status());
        vendaModel.setValor(vendaDto.valor());
        UUID clienteId = UUID.fromString(vendaDto.clienteId());
        VendaModel novaVenda = vendaService.criarVenda(vendaModel, clienteId);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @DeleteMapping("/vendas/{id}")
    public ResponseEntity<Object> deleteVenda(@PathVariable(value = "id")UUID id) 
    {
        Optional<VendaModel> venda0 = vendaRepository.findById(id);
        if(venda0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada!");
        }

       

        vendaRepository.delete(venda0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Venda deletada com sucesso!");
    }
}
