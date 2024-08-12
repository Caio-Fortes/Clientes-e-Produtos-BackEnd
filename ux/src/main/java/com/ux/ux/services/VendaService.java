package com.ux.ux.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ux.ux.dtos.VendaRecordDto;
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
   
    public VendaModel criarVenda(VendaRecordDto vendaRecordDto) {
        VendaModel vendaModel = new VendaModel();
        vendaModel.setData(vendaRecordDto.data());
        vendaModel.setStatus(vendaRecordDto.status());
        vendaModel.setValor(vendaRecordDto.valor());
        UUID clienteId = UUID.fromString(vendaRecordDto.clienteId());

        ClienteModel clienteModel = clienteService.findClienteById(clienteId);

        vendaModel.setCliente(clienteModel);

        return vendaRepository.save(vendaModel);
    }

    public VendaModel findVendaById(UUID vendaId) {
        Optional<VendaModel> vendaModel = vendaRepository.findById(vendaId);

        return vendaModel.orElse(null);
    }

    public List<VendaModel> findAllVendas() {

        return vendaRepository.findAll();
    }

    public VendaModel atualizarVenda(VendaRecordDto vendaRecordDto, UUID id) {
        Optional <VendaModel> vendaModel = vendaRepository.findById(id);

        if(vendaModel.isEmpty()) {
            return null;
        }

        ClienteModel clienteModel = clienteService.findClienteById(UUID.fromString(vendaRecordDto.clienteId()));

        vendaModel.get().setCliente(clienteModel);
        vendaModel.get().setData(vendaRecordDto.data());
        vendaModel.get().setStatus(vendaRecordDto.status());
        vendaModel.get().setValor(vendaRecordDto.valor());

        return vendaRepository.save(vendaModel.get());
    }

    public void deleteVenda(VendaModel vendaModel) {

        vendaRepository.delete(vendaModel);
    }
}
