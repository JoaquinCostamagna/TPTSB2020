package Negocio;

import Soporte.TSBHashtable;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSBHashtable subregion;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        subregion = new TSBHashtable();
    }

    public void agregarSubregion(Region region) {
        subregion.put(region.codigo,region);
    }

    public Collection getSubregiones(){
        return subregion.values();
    }

    @Override
    public String toString() {
        return codigo + '|' + nombre;
    }
}
