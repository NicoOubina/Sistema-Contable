package com.SistemaContable.facturador.service;

import com.SistemaContable.facturador.dto.FacturaDTO;
import com.SistemaContable.facturador.entity.Factura;
import com.SistemaContable.facturador.mapper.FacturaMapper;
import com.SistemaContable.facturador.repository.FacturaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaService(FacturaRepository facturaRepository, FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaMapper = facturaMapper;
    }

    @Transactional(readOnly = true)
    public List<FacturaDTO> obtenerFacturas() {
        return facturaRepository.findAll()
                .stream()
                .map(facturaMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacturaDTO guardarFactura(FacturaDTO dto) {
        Factura factura = facturaMapper.toEntity(dto);
        Factura guardada = facturaRepository.save(factura);
        return facturaMapper.toDto(guardada);
    }
}
