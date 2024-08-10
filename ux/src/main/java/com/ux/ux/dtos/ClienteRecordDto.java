package com.ux.ux.dtos;

import javax.validation.constraints.NotBlank;

public record ClienteRecordDto(@NotBlank String nome, @NotBlank String CNPJ, @NotBlank String email, @NotBlank String telefone, @NotBlank String UI) {

}
