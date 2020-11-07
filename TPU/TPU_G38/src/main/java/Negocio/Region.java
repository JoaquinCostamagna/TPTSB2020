package Negocio;

import Soporte.TSBHashtableDA;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSBHashtableDA subregion;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        subregion = new TSBHashtableDA();
    }

    public String getCodigo() {
        return codigo;
    }

    public void agregarSubregion(Region region) {
        subregion.put(region.codigo,region);
    }

    public Collection getSubregiones(){
        return subregion.values();
    }

    public Region getSubregion(String codigo) {
        return (Region) subregion.get(codigo);
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo + " | " + nombre;
    }

    public Region getOrPutSubregion(String codigo) {
        Region sub = (Region) subregion.get(codigo);
        if (sub == null)
            subregion.put(codigo, new Region(codigo, ""));
        return (Region) subregion.get(codigo);
    }
}
