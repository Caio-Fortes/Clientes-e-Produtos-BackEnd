package com.ux.ux.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "TB_Clientes")
public class ClienteModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID idCLiente;
    private String nome;
    private String CNPJ;
    private String email;
    private String telefone;
    private String UI;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String cnpj) {
        CNPJ = cnpj;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getUI() {
        return UI;
    }
    public void setUI(String ui) {
        UI = ui;
    }
}
