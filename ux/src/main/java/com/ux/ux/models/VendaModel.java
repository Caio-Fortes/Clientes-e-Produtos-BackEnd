package com.ux.ux.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "TB_Vendas")
public class VendaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVenda;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private ClienteModel cliente;

    private String data;
    private String status;
    private BigDecimal valor;

    public UUID getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(UUID idVenda) {
        this.idVenda = idVenda;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
