package com.ux.ux.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.ux.ux.dtos.RelatorioDTO;
import com.ux.ux.dtos.VendaRecordDto;
import com.ux.ux.dtos.VendasAnualDTO;
import com.ux.ux.dtos.VendasMensalDTO;
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

    public RelatorioDTO obterRelatorio(String ano) {
        RelatorioDTO relatorio = new RelatorioDTO();

        List<VendasMensalDTO> vendasMensais = getVendasMensais();
        relatorio.setVendasMensais(vendasMensais);

        VendasAnualDTO vendasAnuais = getVendasAnuais(ano);
        relatorio.setVendasAnuais(vendasAnuais);

        return relatorio;
    }

    private List<VendasMensalDTO> getVendasMensais() {
        List<Object[]> results = vendaRepository.findVendasMensais();
        List<VendasMensalDTO> dtoList = new ArrayList<>();

        for (Object[] result : results) {
            VendasMensalDTO dto = new VendasMensalDTO();
            dto.setClienteMaisNumeroVendas((String) result[0]);
            dto.setTotalVendasClienteMaiorNumero(((Number) result[1]).intValue());
            dto.setMesFormatado(Integer.toString(LocalDate.parse((String) result[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")).getMonthValue()));

            dto.setClienteMaisValorVendas((String) result[3]);
            dto.setTotalValorClienteMaiorValor(new BigDecimal(result[4].toString()));

            dtoList.add(dto);
        }

        return dtoList;
    }

    private VendasAnualDTO getVendasAnuais(String ano) {
        List<Object[]> results = vendaRepository.findVendasAnuais(ano);
        if (results.isEmpty()) {
            return null;
        }

        Object[] result = results.get(0);
        VendasAnualDTO dto = new VendasAnualDTO();
        dto.setClienteMaisNumeroVendasAno((String) result[0]);
        dto.setTotalVendasClienteMaisNumeroAno(((Number) result[1]).intValue());
        dto.setAnoFormatado(((Number) result[2]).intValue());

        dto.setClienteMaisValorVendasAno((String) result[3]);
        dto.setTotalValorClienteMaisValorAno(new BigDecimal(result[4].toString()));

        return dto;
    }
}
