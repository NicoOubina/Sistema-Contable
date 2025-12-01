package com.SistemaContable.facturador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(FacturadorApplication.class)
                .run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("Gestor de Facturas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}
