package com.ux.ux.controllers;

import com.ux.ux.dtos.VendaRecordDto;
import com.ux.ux.models.VendaModel;
import com.ux.ux.services.VendaService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaModel> saveVenda(@RequestBody VendaRecordDto vendaDto) {
        VendaModel vendaModel = new VendaModel();
        vendaModel.setData(vendaDto.data());
        vendaModel.setStatus(vendaDto.status());
        vendaModel.setValor(vendaDto.valor());
        UUID clienteId = UUID.fromString(vendaDto.clienteId());
        VendaModel novaVenda = vendaService.criarVenda(vendaModel, clienteId);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    
}
