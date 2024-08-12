package com.ux.ux.services;

import com.ux.ux.dtos.ClienteRecordDto;
import com.ux.ux.models.ClienteModel;
import com.ux.ux.models.VendaModel;
import com.ux.ux.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ux.ux.repositories.VendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendaRepository vendaRepository;

    public ClienteModel findClienteById(UUID clienteId) {
        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(clienteId);

        return clienteModelOptional.orElse(null);
    }

    public List<ClienteModel> findAllClientes() {

        return clienteRepository.findAll();
    }

    public ClienteModel save(ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> existingClient = clienteRepository.findByCNPJ(clienteRecordDto.CNPJ());

        if (existingClient.isPresent()) {
            return null;
        }

        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return clienteModel;
    }

    public ClienteModel atualizarCliente(ClienteRecordDto clienteRecordDto, UUID clienteId) {
        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(clienteId);

        if(clienteModelOptional.isEmpty()){
            return null;
        }

        var clienteModel = clienteModelOptional.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);

        return clienteRepository.save(clienteModel);
    }

    public void deleteCliente(ClienteModel clienteModel) {
        List<VendaModel> vendasCliente = vendaRepository.findByCliente(clienteModel);

        vendaRepository.deleteAll(vendasCliente);

        clienteRepository.delete(clienteModel);
    }
}
