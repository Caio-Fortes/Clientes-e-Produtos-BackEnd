package com.ux.ux.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClienteRecordDto(@NotBlank String nome, @NotBlank String CNPJ, @NotBlank String email, @NotBlank String telefone, @NotBlank String UI) {

}
