package ControllersReunion;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ModelsReunion.Reunion;
import ModelsReunion.Sala;
import RepositoriesReunion.SalaDao;
import RepositoriesReunion.ReunionDao;

public class GestionaReunion {

    private static final Logger logger = LogManager.getLogger(GestionaReunion.class);

    public static void main(String[] args) {

        ReunionDao daoReunion = new ReunionDao();
        SalaDao daoSala = new SalaDao();

        // 1️⃣ Obtenemos todas las salas existentes
        List<Sala> salas = daoSala.getAll();

        if (salas.isEmpty()) {
            logger.error("No hay salas en la base de datos. Primero crea una sala y vuelve a ejecutar.");
            return; // Salimos, no intentamos crear reunión sin sala
        }

        // 2️⃣ Usamos la primera sala existente
        Sala salaExistente = salas.get(0);

        // 3️⃣ Creamos la reunión asociada a la sala existente
        Reunion nuevaReunion = new Reunion(LocalDateTime.now(), "Reunión Importante");
        nuevaReunion.setSala(salaExistente);

        // 4️⃣ Persistimos la reunión (cascade PERSIST no tocará la sala existente)
        daoReunion.create(nuevaReunion);

        // 5️⃣ Mostramos todas las reuniones existentes
        List<Reunion> reuniones = daoReunion.getAll();
        for (Reunion reunion : reuniones) {
            logger.debug(reunion);
        }

        logger.info("Reunión creada correctamente y asociada a la sala existente: " + salaExistente.getNombre());
    }
}
