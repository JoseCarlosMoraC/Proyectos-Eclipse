package Config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConexion {
    private static final Logger logger = LogManager.getLogger(MongoDBConexion.class);
    private MongoDatabase db;

    public MongoDBConexion() {
        try {
            Propiedades propiedades = new Propiedades("app.properties");
            String uri = propiedades.get("mongodb.uri");
            String baseDatos = propiedades.get("mongodb.database");

            MongoClient client = MongoClients.create(uri);
            this.db = client.getDatabase(baseDatos);

            logger.debug("Conectado a la base de datos: " + db.getName());

        } catch (IOException e) {
            logger.error("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public MongoDatabase getDb() {
        return db;
    }
}