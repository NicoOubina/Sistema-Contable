package com.SistemaContable.facturador.controller;

import com.SistemaContable.facturador.dto.FacturaDTO;
import com.SistemaContable.facturador.service.FacturaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;

@Controller
public class MainViewController {

    private final FacturaService facturaService;

    private final ObservableList<String> facturas = FXCollections.observableArrayList();

    @FXML
    private ListView<String> facturaListView;

    @FXML
    private Label detalleLabel;

    @FXML
    private Button crearFacturaButton;

    public MainViewController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @FXML
    public void initialize() {
        facturaListView.setItems(facturas);
        cargarFacturas();
    }

    @FXML
    public void onCrearFactura(ActionEvent event) {
        FacturaDTO nuevaFactura = new FacturaDTO();
        nuevaFactura.setNumeroFactura("FAC-" + System.currentTimeMillis());
        nuevaFactura.setCliente("Cliente Demo");
        nuevaFactura.setFechaEmision(LocalDate.now());
        nuevaFactura.setTotal(BigDecimal.valueOf(1000.00));

        facturaService.guardarFactura(nuevaFactura);
        cargarFacturas();
        detalleLabel.setText("Factura creada y listada");
    }

    private void cargarFacturas() {
        List<FacturaDTO> dtoList = facturaService.obtenerFacturas();
        facturas.setAll(dtoList.stream()
                .map(dto -> String.format("%s - %s - $%.2f", dto.getNumeroFactura(), dto.getCliente(), dto.getTotal()))
                .toList());
    }
}
