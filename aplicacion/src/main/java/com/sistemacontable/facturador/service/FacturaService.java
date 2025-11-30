package com.sistemacontable.facturador.service;

import com.sistemacontable.facturador.dto.FacturaDTO;
import com.sistemacontable.facturador.entity.Factura;
import com.sistemacontable.facturador.mapper.FacturaMapper;
import com.sistemacontable.facturador.repository.FacturaRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaService(FacturaRepository facturaRepository, FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaMapper = facturaMapper;
    }

    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturas() {
        return facturaRepository.findAll().stream()
                .map(facturaMapper::toDto)
                .toList();
    }

    @Transactional
    public FacturaDTO crearFactura(FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Factura guardada = facturaRepository.save(factura);
        return facturaMapper.toDto(guardada);
    }

    public BigDecimal calcularTotalConIva(FacturaDTO facturaDTO) {
        BigDecimal base = facturaDTO.montoBase();
        BigDecimal iva = base.multiply(facturaDTO.alicuotaIva());
        return base.add(iva).setScale(2, RoundingMode.HALF_UP);
    }
}
