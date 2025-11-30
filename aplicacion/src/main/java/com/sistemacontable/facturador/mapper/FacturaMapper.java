package com.sistemacontable.facturador.mapper;

import com.sistemacontable.facturador.dto.FacturaDTO;
import com.sistemacontable.facturador.entity.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public FacturaDTO toDto(Factura factura) {
        return new FacturaDTO(
                factura.getId(),
                factura.getNumero(),
                factura.getCliente(),
                factura.getFechaEmision(),
                factura.getMontoBase(),
                factura.getAlicuotaIva()
        );
    }

    public Factura toEntity(FacturaDTO facturaDTO) {
        Factura factura = new Factura();
        factura.setId(facturaDTO.id());
        factura.setNumero(facturaDTO.numero());
        factura.setCliente(facturaDTO.cliente());
        factura.setFechaEmision(facturaDTO.fechaEmision());
        factura.setMontoBase(facturaDTO.montoBase());
        factura.setAlicuotaIva(facturaDTO.alicuotaIva());
        return factura;
    }
}
