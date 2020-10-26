package Soporte;

import Negocio.Agrupacion;
import Negocio.Region;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//va a abrir el archivo y va a procesar el contenido para que lo podamos informar
public class TextFile {
    private File file;

    public TextFile(String path) {
        file = new File(path);
    }

    public String leerEncabezado(){
        String linea = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                linea = scanner.nextLine();
                break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo " );
        }
        return linea;
    }

    public TSBHashtable identificarAgrupaciones() {
        String linea = "", campos[];
        TSBHashtable ht = new TSBHashtable(10);
        Agrupacion agrupacion;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                linea = scanner.nextLine(); //leo todas las lineas del archivo
                campos = linea.split("\\|");
                //filtramos votacion para presidente
                if(campos[0].compareTo("000100000000000") == 0){
                    agrupacion = new Agrupacion(campos[2], campos[3]);
                    ht.put(agrupacion.getCodigo(), agrupacion);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo " );
        }
        return ht;
    }

    public void sumarVotosPorAgrupacion(TSBHashtable table) {
        String linea = "", campos[];
        Agrupacion agrupacion;
        int votos;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                linea = scanner.nextLine(); //leo todas las lineas del archivo
                campos = linea.split("\\|");
                //filtramos votacion para presidente
                if(campos[4].compareTo("000100000000000") == 0){
                    agrupacion = (Agrupacion) table.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.sumarVotos(votos);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo " );
        }
    }

    public Object identificarRegiones() {
        String linea = "", campos[], codigo, nombre;
        Region pais = new Region("00","Argentina");
        Region distrito;
        Region seccion;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                linea = scanner.nextLine(); //leo todas las lineas del archivo
                campos = linea.split("\\|");
                codigo = campos[0];
                nombre = campos[1];
                switch(codigo.length()){
                    case 2:
                        //Distrito
                        distrito = pais.getOrPutSubregion(codigo);
                        distrito.setNombre(nombre);
                        break;
                    case 5:
                        //Seccion
                        distrito = pais.getOrPutSubregion(codigo.substring(0,2));
                        seccion = distrito.getOrPutSubregion(codigo);
                        seccion.setNombre(nombre);
                        break;
                    case 11:
                        //Circuito
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo.substring(0, 5));
                        seccion.agregarSubregion(new Region(codigo, nombre));
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo " );
        }
        return pais;

    }
}
