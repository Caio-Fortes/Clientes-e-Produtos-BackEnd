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

@CrossOrigin(origins = "*")
@RestController
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vendas")
    public ResponseEntity<VendaModel> saveVenda(@RequestBody VendaRecordDto vendaDto) {
        VendaModel novaVenda = vendaService.criarVenda(vendaDto);

        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaModel>> getAllVendas() {
        List<VendaModel> vendasModel = vendaService.findAllVendas();

        return ResponseEntity.status(HttpStatus.OK).body(vendasModel);
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<Object> getOneVendas(@PathVariable UUID id) {
        Optional<VendaModel> vendaModelOptional = Optional.ofNullable(vendaService.findVendaById(id));

        return vendaModelOptional.<ResponseEntity<Object>>map(vendaModel -> ResponseEntity.status(HttpStatus.OK).body(vendaModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada!"));
    }

    @PutMapping("/vendas/{id}")
    public ResponseEntity<Object> updateVenda(@PathVariable UUID id,
        @RequestBody @Valid VendaRecordDto vendaDto
    ) 
    {
        VendaModel vendaModel = vendaService.atualizarVenda(vendaDto, id);

        if(vendaModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada!");
        }

        return new ResponseEntity<>(vendaDto, HttpStatus.OK);
    }

    @DeleteMapping("/vendas/{id}")
    public ResponseEntity<Object> deleteVenda(@PathVariable UUID id) 
    {
        VendaModel vendaModel = vendaService.findVendaById(id);

        if (vendaModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada!");
        }

        vendaService.deleteVenda(vendaModel);

        return ResponseEntity.status(HttpStatus.OK).body("Venda deletado com sucesso!");
    }
}
