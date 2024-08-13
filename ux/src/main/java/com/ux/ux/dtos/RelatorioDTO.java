package com.ux.ux.dtos;

import java.util.List;

public class RelatorioDTO {
    private List<VendasMensalDTO> vendasMensais;
    private VendasAnualDTO vendasAnuais;

    public List<VendasMensalDTO> getVendasMensais() { return vendasMensais; }
    public void setVendasMensais(List<VendasMensalDTO> vendasMensais) { this.vendasMensais = vendasMensais; }
    public VendasAnualDTO getVendasAnuais() { return vendasAnuais; }
    public void setVendasAnuais(VendasAnualDTO vendasAnuais) { this.vendasAnuais = vendasAnuais; }
}