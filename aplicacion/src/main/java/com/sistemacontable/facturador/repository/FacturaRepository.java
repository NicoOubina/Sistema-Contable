package com.sistemacontable.facturador.repository;

import com.sistemacontable.facturador.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
