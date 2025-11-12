package Simulacro.BancoDeAlimentos.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Tipo;
import Simulacro.BancoDeAlimentos.Models.Trabajador;

public class BancoAlimentosAJson {
    
    private static final Logger logger = LogManager.getLogger(BancoAlimentosAJson.class);
    
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Lee un CSV de centros logísticos y sus trabajadores.
     * Mantengo la misma estructura que tu clase Pokémon.
     * CSV esperado: idCentro,nombreCentro,ciudad,numComedores,dniTrabajador,nombreTrabajador,fechaNacimiento,tipo
     */
    public List<CentroLogistico> leeCentrosCsv(String ruta) throws FileNotFoundException {
        List<CentroLogistico> listaCentros = new ArrayList<>();

        Scanner in = null;
        try {
            FileReader fichero = new FileReader(ruta);
            in = new Scanner(fichero);
            in.nextLine(); // Saltamos la cabecera

            while (in.hasNextLine()) {
                String linea = in.nextLine();
                String[] campos = linea.split(",");

                String idCentro = campos[0];
                String nombreCentro = campos[1];
                String ciudad = campos[2];
                int numComedores = Integer.parseInt(campos[3]);
                
                String dniTrabajador = campos[4];
                String nombreTrabajador = campos[5];
                String fechaNacimiento = campos[6];
                Tipo tipo = Tipo.valueOf(campos[7].toUpperCase());

                // Buscamos si ya existe el centro en la lista
                CentroLogistico centro = null;
                for (CentroLogistico c : listaCentros) {
                    if (c.getId().equalsIgnoreCase(idCentro)) {
                        centro = c;
                        break;
                    }
                }

                if (centro == null) {
                    centro = new CentroLogistico();
                    centro.setId(idCentro);
                    centro.setNombre(nombreCentro);
                    centro.setCiudad(ciudad);
                    centro.setNumComedores(numComedores);
                    listaCentros.add(centro);
                }

                // Creamos trabajador y lo añadimos al centro
                Trabajador t = new Trabajador();
                t.setDni(dniTrabajador);
                t.setNombre(nombreTrabajador);
                t.setFecha(java.time.LocalDate.parse(fechaNacimiento));
                t.setTipo(tipo);
                t.setIdCentro(idCentro);

                centro.getTrabajadores().add(t);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        logger.info("Centros leídos desde CSV: " + listaCentros);
        return listaCentros;
    }
    
    /**
     * Escribe la lista de centros logísticos en JSON
     */
    public void escribeCentrosJson(List<CentroLogistico> centros, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(centros);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir centros logísticos");
                }           
            }       
        }
        logger.info("Archivo JSON generado correctamente: " + nombreFichero);
    }
 

}