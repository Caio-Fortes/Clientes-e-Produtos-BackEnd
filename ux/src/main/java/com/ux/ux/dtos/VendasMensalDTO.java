package com.ux.ux.dtos;

import java.math.BigDecimal;

public class VendasMensalDTO {
    private String clienteMaisNumeroVendas;
    private Integer totalVendasClienteMaiorNumero;
    private String mesFormatado;

    private String clienteMaisValorVendas;
    private BigDecimal totalValorClienteMaiorValor;

    public String getClienteMaisNumeroVendas() { return clienteMaisNumeroVendas; }
    public void setClienteMaisNumeroVendas(String clienteMaisNumeroVendas) { this.clienteMaisNumeroVendas = clienteMaisNumeroVendas; }
    public Integer getTotalVendasClienteMaiorNumero() { return totalVendasClienteMaiorNumero; }
    public void setTotalVendasClienteMaiorNumero(Integer totalVendasClienteMaiorNumero) { this.totalVendasClienteMaiorNumero = totalVendasClienteMaiorNumero; }
    public String getMesFormatado() { return mesFormatado; }
    public void setMesFormatado(String mesFormatado) { this.mesFormatado = mesFormatado; }
    public String getClienteMaisValorVendas() { return clienteMaisValorVendas; }
    public void setClienteMaisValorVendas(String clienteMaisValorVendas) { this.clienteMaisValorVendas = clienteMaisValorVendas; }
    public BigDecimal getTotalValorClienteMaiorValor() { return totalValorClienteMaiorValor; }
    public void setTotalValorClienteMaiorValor(BigDecimal totalValorClienteMaiorValor) { this.totalValorClienteMaiorValor = totalValorClienteMaiorValor; }
}