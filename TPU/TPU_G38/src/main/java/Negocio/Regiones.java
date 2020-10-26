package Negocio;

import Soporte.TextFile;

import java.util.Collection;

public class Regiones {
    private TextFile fileRegiones;
    private  Region pais;
    private TextFile fileMesas;


    public Regiones(String path) {
        fileRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        //fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        pais = (Region) fileRegiones.identificarRegiones();
        //fileMesas.sumarVotosPorAgrupacion(pais);
    }

    public Collection getDistritos() {
        return pais.getSubregiones();
    }
}
