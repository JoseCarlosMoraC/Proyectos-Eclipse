package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Propiedades {
    private final static String rutaResources = "src\\main\\resources\\";
    private static final Logger logger = LogManager.getLogger(Propiedades.class);
    private Properties props = new Properties();

    public Propiedades(String nombreFichero) throws IOException {
        try {
            props.load(new FileInputStream(rutaResources + nombreFichero));
            logger.debug("Archivo de propiedades cargado: " + nombreFichero);
        } catch (IOException e) {
            logger.error("Error al cargar el archivo de propiedades: " + e.getMessage());
            throw e;
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}