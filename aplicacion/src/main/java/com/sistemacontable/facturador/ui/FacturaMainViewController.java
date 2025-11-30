package com.sistemacontable.facturador.ui;

import com.sistemacontable.facturador.dto.FacturaDTO;
import com.sistemacontable.facturador.service.FacturaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class FacturaMainViewController {

    private final FacturaService facturaService;
    private final ObservableList<FacturaDTO> facturas = FXCollections.observableArrayList();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private TableView<FacturaDTO> tablaFacturas;
    @FXML
    private TableColumn<FacturaDTO, String> numeroCol;
    @FXML
    private TableColumn<FacturaDTO, String> clienteCol;
    @FXML
    private TableColumn<FacturaDTO, String> fechaCol;
    @FXML
    private TableColumn<FacturaDTO, String> baseCol;
    @FXML
    private TableColumn<FacturaDTO, String> ivaCol;
    @FXML
    private TableColumn<FacturaDTO, String> totalCol;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField clienteField;
    @FXML
    private TextField baseField;
    @FXML
    private TextField alicuotaField;
    @FXML
    private DatePicker fechaPicker;

    public FacturaMainViewController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @FXML
    public void initialize() {
        configurarColumnas();
        recargarTabla();
        fechaPicker.setValue(LocalDate.now());
    }

    private void configurarColumnas() {
        numeroCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().numero()));
        clienteCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().cliente()));
        fechaCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().fechaEmision().format(formatter)));
        baseCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().montoBase().toPlainString()));
        ivaCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().alicuotaIva().multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString() + "%"));
        totalCol.setCellValueFactory(cell -> new SimpleStringProperty(facturaService.calcularTotalConIva(cell.getValue()).toPlainString()));
        tablaFacturas.setItems(facturas);
    }

    @FXML
    public void registrarFactura(ActionEvent event) {
        try {
            FacturaDTO nuevaFactura = new FacturaDTO(
                    null,
                    numeroField.getText(),
                    clienteField.getText(),
                    fechaPicker.getValue(),
                    new BigDecimal(baseField.getText()),
                    new BigDecimal(alicuotaField.getText())
            );
            facturaService.crearFactura(nuevaFactura);
            limpiarFormulario();
            recargarTabla();
            mostrarAlerta("Factura registrada", "La factura se guardó correctamente");
        } catch (Exception e) {
            mostrarAlerta("Datos inválidos", "Verificá los campos: " + e.getMessage());
        }
    }

    private void recargarTabla() {
        facturas.setAll(facturaService.obtenerFacturas());
    }

    private void limpiarFormulario() {
        numeroField.clear();
        clienteField.clear();
        baseField.clear();
        alicuotaField.clear();
        fechaPicker.setValue(LocalDate.now());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
