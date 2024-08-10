package com.ux.ux.services;

import com.ux.ux.models.ClienteModel;
import com.ux.ux.repositories.ClienteRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel findClienteById(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }
}
