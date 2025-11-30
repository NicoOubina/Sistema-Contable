package com.sistemacontable.facturador.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FacturaDTO(
        Long id,
        String numero,
        String cliente,
        LocalDate fechaEmision,
        BigDecimal montoBase,
        BigDecimal alicuotaIva
) { }
