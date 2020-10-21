package Negocio;

import Soporte.TSBHashtable;
import Soporte.TextFile;

public class Agrupaciones {
    private TextFile fileAgrupaciones;
    private TSBHashtable table;
    private TextFile fileMesas;

    public Agrupaciones(String path) {
        fileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        table = fileAgrupaciones.identificarAgrupaciones();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o:table.values()) {
            sb.append("\n" + o);
        }
        return  sb.toString();
    }
}
