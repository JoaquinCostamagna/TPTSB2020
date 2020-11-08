package Negocio;

import Soporte.TSBHashtableDA;
import Soporte.TextFile;

import java.util.Collection;

public class Agrupaciones {

    private static TSBHashtableDA inicial;
    private TSBHashtableDA conteo;

    public Agrupaciones(){
        conteo = new TSBHashtableDA();
        for (Object o : inicial.values()) {
            Agrupacion a = (Agrupacion) o;
            conteo.put(a.getCodigo(), new Agrupacion(a.getCodigo(),a.getNombre()));
        }
    }

    public Agrupacion getAgrupacion(String codAgrupacion){
        return (Agrupacion) conteo.get(codAgrupacion);
    }

    public Collection getResultados() {
        return conteo.values();
    }

    /**
     * Carga la tabla inicial de agrupaciones, a partir de la ubicación pasa por parámetro.
     * @param path La ubicación a partir de la cual se debe cargar la tabla inicial de agrupaciones.
     */
    public static void leerAgrupaciones(String path){
        TextFile fileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        inicial = fileAgrupaciones.identificarAgrupaciones();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o: conteo.values()) {
            sb.append("\n" + o);
        }
        return  sb.toString();
    }
}
