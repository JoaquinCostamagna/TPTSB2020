package Soporte;

import Negocio.Agrupacion;
import Negocio.Region;
import Negocio.Resultados;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//va a abrir el archivo y va a procesar el contenido para que lo podamos informar
public class TextFile {
    private File file;

    public TextFile(String path) {
        file = new File(path);
    }

    /**
     * Carga en una tabla todas las distintas agrupaciones a partir del archivo referenciado por {@code file}.
     * @return una tabla de hash con las distintas agrupaciones.
     */
    public TSBHashtableDA identificarAgrupaciones() {
        String linea = "", campos[];
        TSBHashtableDA ht = new TSBHashtableDA(10);
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

    /**
     * Realiza la sumatoria de votos por cada región a la tabla de resultados, a partir del archivo de resultados.
     * @param resultados el objeto de la clase Resultados que contiene la tabla de resultados.
     */
    public void sumarVotosPorRegion(Resultados resultados) {
        String linea = "", campos[], codAgrupacion;
        Region region;
        int votos;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                linea = scanner.nextLine(); //leo todas las lineas del archivo
                campos = linea.split("\\|");
                codAgrupacion = campos[5];
                //filtramos votacion para presidente
                if(campos[4].compareTo("000100000000000") == 0){
                    votos = Integer.parseInt(campos[6]);
                    //Acumulamos los votos del pais
                    resultados.sumarVotos("00", codAgrupacion, votos);
                    //Acumulamos todos los votos del distrito, seccion y circuito de la mesa
                    for (int i = 0; i < 3; i++) {
                        resultados.sumarVotos(campos[i], codAgrupacion, votos);
                    }
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
