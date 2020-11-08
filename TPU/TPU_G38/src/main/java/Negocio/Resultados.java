package Negocio;

import Soporte.TSBHashtableDA;
import Soporte.TextFile;

import java.rmi.activation.ActivationGroup;
import java.util.Collection;

public class Resultados {

    private TSBHashtableDA tabla;

    public Resultados(String path) {
        tabla = new TSBHashtableDA<>();
        TextFile fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        fileMesas.sumarVotosPorRegion(this);
    }

    /**
     * Realiza la sumatoria de votos paa una agrupación en una región determinada. Si la región no estaba cargada, la
     * agrega a la tabla.
     * @param codRegion EL código de la región en donde cargar los datos.
     * @param codAgrupacion El código de la agrupación a sumarle los votos.
     * @param votos El número de votos a sumar.
     */
    public void sumarVotos(String codRegion, String codAgrupacion, int votos) {
        int actual;
        //Buscamos la región en la tabla, y la creamos si no existe
        if (tabla.get(codRegion) == null)
            tabla.put(codRegion, new Agrupaciones());
        //Actualizamos el total de votos
        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        a.getAgrupacion(codAgrupacion).sumarVotos(votos);
    }

    public Collection getResultadosPorRegion(String codRegion){
        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        return a.getResultados();
    }
}
