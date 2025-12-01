package com.sistemacontable.facturador;

import com.sistemacontable.facturador.dto.FacturaDTO;
import com.sistemacontable.facturador.service.FacturaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FacturadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturadorApplication.class, args);
    }

    @Bean
    CommandLineRunner preloadData(FacturaService facturaService) {
        return args -> {
            if (facturaService.obtenerFacturas().isEmpty()) {
                facturaService.crearFactura(new FacturaDTO(null, "F0001-00000001", "Cooperativa Andina",
                        LocalDate.now().minusDays(3), new BigDecimal("120000"), new BigDecimal("0.21")));
                facturaService.crearFactura(new FacturaDTO(null, "F0001-00000002", "Librería Central",
                        LocalDate.now().minusDays(1), new BigDecimal("45200"), new BigDecimal("0.105")));
                facturaService.crearFactura(new FacturaDTO(null, "F0001-00000003", "Distribuidora Patagonia",
                        LocalDate.now(), new BigDecimal("87300"), new BigDecimal("0.21")));
            }
        };
    }
}
