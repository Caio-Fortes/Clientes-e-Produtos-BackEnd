package com.ux.ux.dtos;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record VendaRecordDto(
    @NotNull String clienteId,
    @NotBlank String data,
    @NotBlank String status,
    @NotNull BigDecimal valor
) {}
