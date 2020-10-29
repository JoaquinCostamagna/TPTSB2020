package Negocio;

import Soporte.TSBHashtable;
import Soporte.TextFile;

import java.rmi.activation.ActivationGroup;
import java.util.Collection;

public class Resultados {

    private TSBHashtable tabla;

    public Resultados(String path) {
        tabla = new TSBHashtable<>();
        TextFile fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        fileMesas.sumarVotosPorRegion(this);
    }


    public void sumarVotos(String codRegion, String codAgrupacion, int votos) {
        int actual;
        //Buscamos la región en la tabla, y la creamos si no existe
        if (tabla.get(codRegion) == null)
            tabla.put(codRegion, new Agrupaciones());
        //Actualizamos el total de votos
        //actual = (int) tabla.get(codRegion);
        //tabla.put(codRegion, actual + votos);
        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        a.getAgrupacion(codAgrupacion).sumarVotos(votos);
    }

    public Collection getResultadosPorRegion(String codRegion){
        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        return a.getResultados();
    }
}
