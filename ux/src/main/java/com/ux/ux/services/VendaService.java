package com.ux.ux.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ux.ux.models.ClienteModel;
import com.ux.ux.models.VendaModel;
import com.ux.ux.repositories.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteService clienteService;
   
    public VendaModel criarVenda(VendaModel vendaModel, UUID clienteId) {
        ClienteModel cliente = clienteService.findClienteById(clienteId);
        vendaModel.setCliente(cliente);
        return vendaRepository.save(vendaModel);
    }
}
