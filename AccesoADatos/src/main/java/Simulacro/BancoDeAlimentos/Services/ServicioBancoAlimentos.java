package Simulacro.BancoDeAlimentos.Services;

import java.util.List;
import java.util.Set;

import Simulacro.BancoDeAlimentos.Exception.BancoException;
import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Trabajador;
import Simulacro.BancoDeAlimentos.Repositories.BancoAlimentos;

/**
 * Capa intermedia entre el controlador (GestionaBancoAlimentos)
 * y el repositorio (BancoAlimentos).
 * 
 * Su función es: 
 *  - Organizar las llamadas al repositorio.
 *  - Preparar los datos antes de pasarlos.
 *  - Aislar al controlador de la lógica de acceso a datos.
 * 
 * Aquí no se modifica nada del repositorio: solo se le delegan tareas.
 */
public class ServicioBancoAlimentos {

    // El servicio contiene una instancia del repositorio, para usar sus métodos.
    private BancoAlimentos repo;

    public ServicioBancoAlimentos() {
        this.repo = new BancoAlimentos(); // Se crea el repositorio cuando se crea el servicio.
    }

    /**
     * Recibe el conjunto de centros ya cargados desde el XML.
     * 
     * Por qué este parámetro:
     *  → En el XML se leen todos los centros con sus trabajadores.
     *  → El controlador (GestionaBancoAlimentos) los obtiene como List o Set.
     *  → Aquí los guardamos dentro del repositorio (en memoria) usando setListaCentros().
     */
    public void setCentros(Set<CentroLogistico> centros) {
        repo.setListaCentros(centros);
    }

    /**
     * Devuelve todos los centros logísticos almacenados en el repositorio.
     * 
     * No recibe parámetros porque simplemente devuelve la lista interna.
     */
    public Set<CentroLogistico> getCentros() {
        return repo.getListaCentros();
    }

    /**
     * Obtiene un centro por su ID.
     * 
     * Parámetro:
     *  - id: el identificador único del centro logístico que se quiere buscar.
     * 
     * Se pasa al repositorio para que lo busque y lo devuelva.
     */
    public CentroLogistico getCentro(String id) throws BancoException {
        return repo.getCentroLogistico(id);
    }

    /**
     * Obtiene un trabajador por su DNI.
     * 
     * Parámetro:
     *  - dni: identificador único del trabajador.
     * 
     * El repositorio recorre todos los centros hasta encontrarlo.
     */
    public Trabajador getTrabajador(String dni) throws BancoException {
        return repo.getTrabajador(dni);
    }

    /**
     * Devuelve los colaboradores filtrados por tipo (ASALARIADO o VOLUNTARIO).
     * 
     * Parámetro:
     *  - tipo: se pasa como cadena ("asalariado" o "voluntario") porque
     *          el XML y el controlador lo manejan así.
     * 
     * Internamente el repositorio lo convierte al enum Tipo (ASALARIADO/VOLUNTARIO).
     */
    public List<Trabajador> getColaboradoresPorTipo(String tipo) throws BancoException {
        return repo.getColaboradoresPorTipo(tipo);
    }

    /**
     * Agrega un nuevo centro logístico.
     * 
     * Parámetro:
     *  - id: el identificador del nuevo centro.
     * 
     * El método del repositorio solo necesita el ID (los demás datos se completarían después).
     * Si ya existe un centro con ese ID, el repositorio lanza BancoException.
     */
    public void agregarCentro(String id) throws BancoException {
        repo.agregarCentroLogistico(id);
    }

    /**
     * Agrega un trabajador a un centro existente.
     * 
     * Parámetros:
     *  - dni: identificador único del trabajador (para comprobar duplicados).
     *  - idCentro: ID del centro al que se le agregará el trabajador.
     * 
     * Estos se pasan al repositorio para:
     *  1️⃣ Buscar el centro correcto.
     *  2️⃣ Comprobar si ya existe un trabajador con ese DNI.
     *  3️⃣ Si no, agregarlo (aunque tu método actual no completa la lógica de añadir el objeto).
     */
    public void agregarTrabajadorACentro(String dni, String idCentro) throws BancoException {
        repo.agregarTrabajadorACentro(dni, idCentro);
    }
}
