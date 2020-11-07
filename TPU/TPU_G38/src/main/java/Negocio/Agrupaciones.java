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

    public Agrupacion getAgrupacion(String codAgrupacion){
        return (Agrupacion) conteo.get(codAgrupacion);
    }

    public Collection getResultados() {
        return conteo.values();
    }
}
