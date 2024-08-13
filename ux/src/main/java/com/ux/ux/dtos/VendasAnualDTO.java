package com.ux.ux.dtos;

import java.math.BigDecimal;

public class VendasAnualDTO {
    private String clienteMaisNumeroVendasAno;
    private Integer totalVendasClienteMaisNumeroAno;
    private Integer anoFormatado;
    private String clienteMaisValorVendasAno;
    private BigDecimal totalValorClienteMaisValorAno;

    public String getClienteMaisNumeroVendasAno() { return clienteMaisNumeroVendasAno; }
    public void setClienteMaisNumeroVendasAno(String clienteMaisNumeroVendasAno) { this.clienteMaisNumeroVendasAno = clienteMaisNumeroVendasAno; }
    public Integer getTotalVendasClienteMaisNumeroAno() { return totalVendasClienteMaisNumeroAno; }
    public void setTotalVendasClienteMaisNumeroAno(Integer totalVendasClienteMaisNumeroAno) { this.totalVendasClienteMaisNumeroAno = totalVendasClienteMaisNumeroAno; }
    public Integer getAnoFormatado() { return anoFormatado; }
    public void setAnoFormatado(Integer anoFormatado) { this.anoFormatado = anoFormatado; }
    public String getClienteMaisValorVendasAno() { return clienteMaisValorVendasAno; }
    public void setClienteMaisValorVendasAno(String clienteMaisValorVendasAno) { this.clienteMaisValorVendasAno = clienteMaisValorVendasAno; }
    public BigDecimal getTotalValorClienteMaisValorAno() { return totalValorClienteMaisValorAno; }
    public void setTotalValorClienteMaisValorAno(BigDecimal totalValorClienteMaisValorAno) { this.totalValorClienteMaisValorAno = totalValorClienteMaisValorAno; }
}