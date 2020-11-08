package Interfaz;

import Negocio.Agrupaciones;
import Negocio.Region;
import Negocio.Regiones;
import Negocio.Resultados;
import Soporte.TextFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class PrincipalController {
    public Label lblUbicacion;
    public ListView lvwResultados;
    public ComboBox cboDistritos;
    public ComboBox cboSecciones;
    public ComboBox cboCircuito;
    public Label lblCargando;
    public AnchorPane panel;
    Resultados resultados;
    //public TextArea txtAgrupaciones;


    public void initialize() {
        lblUbicacion.setText(System.getProperty("user.dir") + "\\120819-054029");
    }

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser(); //abre una ventana y me deja elegir una carpeta del sistema de archivos
        dc.setTitle("Seleccione ubicacion de los datos");
        dc.setInitialDirectory(new File(lblUbicacion.getText()));
        File file = dc.showDialog(null);
        if(file != null)
            lblUbicacion.setText(file.getPath());
    }

    public void cargarDatos(ActionEvent actionEvent) {

        Runnable r = new Runnable() {
            public void run() {
                ObservableList ol;
                //Generamos lista de agrupaciones
                Agrupaciones.leerAgrupaciones(lblUbicacion.getText());
                //Generamos lista de distritos del país
                Regiones regiones = new Regiones(lblUbicacion.getText());
                ol = FXCollections.observableArrayList(regiones.getDistritos());
                cboDistritos.setItems(ol);
                //Procesamos los totales por region
                resultados = new Resultados(lblUbicacion.getText());
                Resultados resultados = new Resultados(lblUbicacion.getText());
                ol = FXCollections.observableArrayList(resultados.getResultadosPorRegion("00"));
                lvwResultados.setItems(ol);
                lblCargando.setVisible(false);
                panel.setCursor(Cursor.DEFAULT);
            }
        };

        lblCargando.setVisible(true);
        panel.setCursor(Cursor.WAIT);

        new Thread(r).start();

    }

    public void elegirDistrito(ActionEvent actionEvent) {
        //Generamos lista de Secciones del distrito elegido por el usuario
        ObservableList ol;
        Region region = (Region) cboDistritos.getValue();
        ol = FXCollections.observableArrayList(region.getSubregiones());
        cboSecciones.setItems(ol);
        //Mostramos resultados del distrito
        try{ ol = FXCollections.observableArrayList(resultados.getResultadosPorRegion(region.getCodigo()));
        lvwResultados.setItems(ol);}
        catch (NullPointerException ex)
        {
            ol = FXCollections.observableArrayList(FXCollections.emptyObservableList());
            ol.add("No se encontraron resultados en este distrito");
            lvwResultados.setItems(ol);
        }

    }

    public void elegirSeccion(ActionEvent actionEvent) {
        //Generamos lista de Circuitos de la Sección elegida por el usuario
        if(cboSecciones.getValue() != null) {
            ObservableList ol;
            Region seccion = (Region) cboSecciones.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuito.setItems(ol);
            //Mostramos resultados de la seccio
            try{ol = FXCollections.observableArrayList(resultados.getResultadosPorRegion(seccion.getCodigo()));
                lvwResultados.setItems(ol);}
            catch (NullPointerException ex)
            {
                ol = FXCollections.observableArrayList(FXCollections.emptyObservableList());
                ol.add("No se encontraron resultados en esta sección");
                lvwResultados.setItems(ol);
            }
        }
        else
            cboCircuito.setItems(null);

    }


    public void elegirCircuito(ActionEvent actionEvent) {
        if(cboCircuito.getValue() != null) {
            ObservableList ol;
            Region circuito = (Region) cboCircuito.getValue();
            //Mostramos resultados del circuito
            try {
                ol = FXCollections.observableArrayList(resultados.getResultadosPorRegion(circuito.getCodigo()));
                lvwResultados.setItems(ol);
            } catch (NullPointerException ex) {
                ol = FXCollections.observableArrayList(FXCollections.emptyObservableList());
                ol.add("No se encontraron resultados en ese circuito");
                lvwResultados.setItems(ol);
            }
        }
    }
}
