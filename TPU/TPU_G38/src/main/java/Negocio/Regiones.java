package Negocio;

import Soporte.TextFile;

import java.util.Collection;

public class Regiones {
    private TextFile fileRegiones;
    private  Region pais;
    private TextFile fileMesas;

    public Regiones(String path) {
        fileRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        pais = (Region) fileRegiones.identificarRegiones();
    }

    public Collection getDistritos() {
        return pais.getSubregiones();
    }
}
