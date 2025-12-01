package com.SistemaContable.facturador.mapper;

import com.SistemaContable.facturador.dto.FacturaDTO;
import com.SistemaContable.facturador.entity.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public FacturaDTO toDto(Factura factura) {
        if (factura == null) {
            return null;
        }
        FacturaDTO dto = new FacturaDTO();
        dto.setId(factura.getId());
        dto.setNumeroFactura(factura.getNumeroFactura());
        dto.setCliente(factura.getCliente());
        dto.setFechaEmision(factura.getFechaEmision());
        dto.setTotal(factura.getTotal());
        return dto;
    }

    public Factura toEntity(FacturaDTO dto) {
        if (dto == null) {
            return null;
        }
        Factura factura = new Factura();
        factura.setId(dto.getId());
        factura.setNumeroFactura(dto.getNumeroFactura());
        factura.setCliente(dto.getCliente());
        factura.setFechaEmision(dto.getFechaEmision());
        factura.setTotal(dto.getTotal());
        return factura;
    }
}
