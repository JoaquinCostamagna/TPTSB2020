package Interfaz;

import Negocio.Agrupaciones;
import Soporte.TextFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class PrincipalController {
    public Label lblUbicacion;
    public TextArea txtAgrupaciones;

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser(); //abre una ventana y me deja elegir una carpeta del sistema de archivos
        dc.setTitle("Seleccione ubicacion de los datos");
        dc.setInitialDirectory(new File(lblUbicacion.getText()));
        File file = dc.showDialog(null);
        if(file != null)
            lblUbicacion.setText(file.getPath());
    }

    public void cargarDatos(ActionEvent actionEvent) {
        ObservableList ol;
        //Generamos lista de resultados por agrupacion (para el pais)
        Agrupaciones agrupaciones = new Agrupaciones(lblUbicacion.getText());
        ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        lvwResultados.setItems(ol);
        txtAgrupaciones.setText(agrupaciones.toString());




    }
}
