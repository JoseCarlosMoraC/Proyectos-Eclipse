package Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Exception.LiveFestException;
import Models.Artista;
import Services.LiveFestService;

/**
 * REPOSITORIO DE ARTISTAS
 * 
 * Este repositorio gestiona todas las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la colección de artistas del festival.
 * 
 * PATRÓN DE DISEÑO: Repositorio
 * - Separa la lógica de acceso a datos de la lógica de negocio
 * - Proporciona una interfaz limpia para manipular artistas
 * 
 * USO DE ITERATORS:
 * - Todos los métodos utilizan Iterator para recorrer la colección
 * - Permite eliminar elementos de forma segura durante la iteración
 * - Evita ConcurrentModificationException
 */
public class ArtistaRepositorio {
    // Referencia al servicio principal que contiene la colección de artistas
    private LiveFestService artistaServicio;

    /**
     * CONSTRUCTOR CON PARÁMETRO
     * Se usa cuando ya tenemos una instancia del servicio
     */
    public ArtistaRepositorio(LiveFestService artistaServicio) {
        this.artistaServicio = artistaServicio;
    }

    /**
     * CONSTRUCTOR VACÍO
     * IMPORTANTE: NO crear TorneoService aquí para evitar dependencias circulares
     * El servicio se asignará después mediante el setter
     */
    public ArtistaRepositorio() {
        // Constructor vacío - NO crear LiveFestService aquí
    }

    // ==================== GETTERS Y SETTERS ====================
    
    public LiveFestService getArtistaServicio() {
        return artistaServicio;
    }

    public void setArtistaServicio(LiveFestService artistaServicio) {
        this.artistaServicio = artistaServicio;
    }

    @Override
    public String toString() {
        return "ArtistaRepositorio [artistaServicio=" + artistaServicio + "]";
    }

    // ==================== OPERACIONES CRUD BÁSICAS ====================

    /**
     * OBTENER ARTISTA POR CÓDIGO
     * 
     * Busca un artista específico por su código único.
     * Usa Iterator para recorrer la colección de forma segura.
     * 
     * @param codigo - El código único del artista (ej: "LP", "TS")
     * @return El artista encontrado
     * @throws LiveFestException si no se encuentra el artista
     */
    public Artista getArtista(String codigo) throws LiveFestException {
        boolean encontrado = false;
        Artista artista = null;

        // Obtenemos el Iterator de la lista de artistas
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        
        // Recorremos mientras haya elementos Y no lo hayamos encontrado
        while (it.hasNext() && !encontrado) {
            Artista a = it.next(); // Obtenemos el siguiente artista
            
            // Comparamos códigos (ignorando mayúsculas/minúsculas)
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                artista = a;
            }
        }

        // Si no lo encontramos, lanzamos excepción
        if (!encontrado) {
            throw new LiveFestException("No se encuentra ningún artista asociado a ese código");
        }

        return artista;
    }

    /**
     * AGREGAR ARTISTA
     * 
     * Añade un nuevo artista a la colección.
     * Primero verifica que no exista un artista con el mismo código.
     * 
     * @param artista - El artista a agregar
     * @throws LiveFestException si ya existe un artista con ese código
     */
    public void agregarArtista(Artista artista) throws LiveFestException {
        boolean existe = false;
        
        // Verificamos si ya existe un artista con ese código
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext() && !existe) {
            Artista a = it.next();
            if (a.getCodigo().equalsIgnoreCase(artista.getCodigo())) {
                existe = true; // Encontramos uno duplicado
            }
        }
        
        // Si existe, lanzamos excepción
        if (existe) {
            throw new LiveFestException("Ya existe el artista con código: " + artista.getCodigo());
        }
        
        // Si no existe, lo agregamos a la colección
        artistaServicio.getListaArtistas().add(artista);
    }

    /**
     * ELIMINAR ARTISTA POR CÓDIGO
     * 
     * Elimina un artista de la colección.
     * USA Iterator.remove() para eliminar de forma segura durante la iteración.
     * 
     * @param codigo - El código del artista a eliminar
     * @throws LiveFestException si no se encuentra el artista
     */
    public void eliminarArtista(String codigo) throws LiveFestException {
        boolean encontrado = false;
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext() && !encontrado) {
            Artista a = it.next();
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                // IMPORTANTE: Usar it.remove() NO listaArtistas.remove(a)
                // Esto evita ConcurrentModificationException
                it.remove();
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new LiveFestException("No se encuentra el artista con código: " + codigo);
        }
    }

    /**
     * ACTUALIZAR ARTISTA
     * 
     * Actualiza los datos de un artista existente.
     * Busca el artista por código y actualiza sus campos.
     * 
     * @param artistaActualizado - Artista con los nuevos datos
     * @throws LiveFestException si no se encuentra el artista
     */
    public void actualizarArtista(Artista artistaActualizado) throws LiveFestException {
        boolean encontrado = false;
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext() && !encontrado) {
            Artista a = it.next();
            if (a.getCodigo().equalsIgnoreCase(artistaActualizado.getCodigo())) {
                // Actualizamos los campos del artista encontrado
                a.setNombreArtistico(artistaActualizado.getNombreArtistico());
                a.setEmailManager(artistaActualizado.getEmailManager());
                a.setNumIntegrantes(artistaActualizado.getNumIntegrantes());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new LiveFestException("No se encuentra el artista con código: " + artistaActualizado.getCodigo());
        }
    }

    // ==================== CONSULTAS Y LISTADOS ====================

    /**
     * OBTENER TODOS LOS ARTISTAS
     * 
     * Devuelve una lista con todos los artistas.
     * Convertimos el Set a List para facilitar su uso.
     * 
     * @return Lista de todos los artistas
     */
    public List<Artista> getTodosLosArtistas() {
        List<Artista> lista = new ArrayList<>();
        
        // Recorremos con Iterator y agregamos cada elemento a la lista
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        
        return lista;
    }

    /**
     * FILTRAR ARTISTAS POR NÚMERO MÍNIMO DE INTEGRANTES
     * 
     * Devuelve artistas que tengan al menos el número de integrantes especificado.
     * Útil para encontrar bandas/grupos grandes.
     * 
     * @param numMinimo - Número mínimo de integrantes
     * @return Lista de artistas filtrados
     */
    public List<Artista> getArtistasPorNumIntegrantes(int numMinimo) {
        List<Artista> artistasFiltrados = new ArrayList<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            // Filtramos: solo agregamos si cumple la condición
            if (a.getNumIntegrantes() >= numMinimo) {
                artistasFiltrados.add(a);
            }
        }
        
        return artistasFiltrados;
    }

    /**
     * BUSCAR ARTISTAS POR NOMBRE
     * 
     * Busca artistas cuyo nombre contenga el texto especificado.
     * La búsqueda es case-insensitive (ignora mayúsculas).
     * 
     * @param texto - Texto a buscar en el nombre
     * @return Lista de artistas encontrados
     */
    public List<Artista> buscarArtistasPorNombre(String texto) {
        List<Artista> artistasEncontrados = new ArrayList<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            // Convertimos ambos a minúsculas para comparar
            if (a.getNombreArtistico().toLowerCase().contains(texto.toLowerCase())) {
                artistasEncontrados.add(a);
            }
        }
        
        return artistasEncontrados;
    }

    // ==================== ESTADÍSTICAS Y ANÁLISIS ====================

    /**
     * OBTENER ARTISTA CON MÁS INTEGRANTES
     * 
     * Encuentra el artista/banda con más miembros.
     * Recorre toda la colección comparando el número de integrantes.
     * 
     * @return El artista con más integrantes
     * @throws LiveFestException si no hay artistas
     */
    public Artista getArtistaConMasIntegrantes() throws LiveFestException {
        // Verificamos que la lista no esté vacía
        if (artistaServicio.getListaArtistas().isEmpty()) {
            throw new LiveFestException("No hay artistas en el sistema");
        }

        Artista artistaMax = null;
        int maxIntegrantes = 0;

        // Recorremos buscando el máximo
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            if (a.getNumIntegrantes() > maxIntegrantes) {
                maxIntegrantes = a.getNumIntegrantes();
                artistaMax = a; // Guardamos referencia al artista
            }
        }

        return artistaMax;
    }

    /**
     * OBTENER ARTISTA CON MENOS INTEGRANTES
     * 
     * Encuentra el artista solista o banda con menos miembros.
     * 
     * @return El artista con menos integrantes
     * @throws LiveFestException si no hay artistas
     */
    public Artista getArtistaConMenosIntegrantes() throws LiveFestException {
        if (artistaServicio.getListaArtistas().isEmpty()) {
            throw new LiveFestException("No hay artistas en el sistema");
        }

        Artista artistaMin = null;
        int minIntegrantes = Integer.MAX_VALUE; // Empezamos con el valor máximo posible

        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            if (a.getNumIntegrantes() < minIntegrantes) {
                minIntegrantes = a.getNumIntegrantes();
                artistaMin = a;
            }
        }

        return artistaMin;
    }

    /**
     * CALCULAR PROMEDIO DE INTEGRANTES POR ARTISTA
     * 
     * Calcula el número promedio de integrantes.
     * Útil para estadísticas del festival.
     * 
     * @return El promedio de integrantes
     * @throws LiveFestException si no hay artistas
     */
    public double getPromedioIntegrantesPorArtista() throws LiveFestException {
        if (artistaServicio.getListaArtistas().isEmpty()) {
            throw new LiveFestException("No hay artistas en el sistema");
        }

        int totalIntegrantes = 0;
        int contador = 0;

        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            totalIntegrantes += a.getNumIntegrantes();
            contador++;
        }

        // Convertimos antes a double
        double promedio = totalIntegrantes;
        promedio = promedio / contador;

        return promedio;
    }

    /**
     * CONTAR ARTISTAS
     * 
     * Devuelve el número total de artistas.
     * Usa el método size() de la colección (más eficiente que iterar).
     * 
     * @return Número total de artistas
     */
    public int contarArtistas() {
        return artistaServicio.getListaArtistas().size();
    }

    /**
     * VERIFICAR SI EXISTE UN ARTISTA
     * 
     * Comprueba si existe un artista con el código especificado.
     * Retorna boolean en lugar de lanzar excepción.
     * 
     * @param codigo - Código del artista a verificar
     * @return true si existe, false si no
     */
    public boolean existeArtista(String codigo) {
        boolean existe = false;
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext() && !existe) {
            Artista a = it.next();
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                existe = true; // Encontrado, salimos del bucle
            }
        }
        
        return existe;
    }

    // ==================== ORDENAMIENTOS ====================

    /**
     * OBTENER ARTISTAS ORDENADOS POR INTEGRANTES
     * 
     * Devuelve lista ordenada de menor a mayor número de integrantes.
     * Usa expresión lambda para el comparador.
     * 
     * @return Lista ordenada de artistas
     */
    public List<Artista> getArtistasOrdenadosPorIntegrantes() {
        List<Artista> artistasOrdenados = new ArrayList<>();

        // Copiamos todos los artistas a la lista
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            artistasOrdenados.add(it.next());
        }

        // Ordenamos usando Comparator clásico
        Collections.sort(artistasOrdenados, new Comparator<Artista>() {
            @Override
            public int compare(Artista a1, Artista a2) {
                return Integer.compare(a1.getNumIntegrantes(), a2.getNumIntegrantes());
            }
        });

        return artistasOrdenados;
    }

    /**
     * OBTENER ARTISTAS ORDENADOS POR NOMBRE
     * 
     * Devuelve lista ordenada alfabéticamente por nombre artístico.
     * 
     * @return Lista ordenada de artistas
     */
    public List<Artista> getArtistasOrdenadosPorNombre() {
        List<Artista> artistasOrdenados = new ArrayList<>();

        // Copiamos todos los artistas a la lista
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            artistasOrdenados.add(it.next());
        }

        // Ordenamos usando Comparator clásico
        Collections.sort(artistasOrdenados, new Comparator<Artista>() {
            @Override
            public int compare(Artista a1, Artista a2) {
                return a1.getNombreArtistico().compareToIgnoreCase(a2.getNombreArtistico());
            }
        });

        return artistasOrdenados;
    }

    // ==================== FILTROS ESPECÍFICOS ====================

    /**
     * OBTENER ARTISTAS CON NÚMERO EXACTO DE INTEGRANTES
     * 
     * Filtra artistas que tengan exactamente el número especificado.
     * Útil para buscar solistas (1) o cuartetos (4), etc.
     * 
     * @param numero - Número exacto de integrantes
     * @return Lista de artistas filtrados
     */
    public List<Artista> getArtistasPorNumeroExactoIntegrantes(int numero) {
        List<Artista> artistasFiltrados = new ArrayList<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            if (a.getNumIntegrantes() == numero) { // Comparación exacta con ==
                artistasFiltrados.add(a);
            }
        }
        
        return artistasFiltrados;
    }

    /**
     * OBTENER ESTADÍSTICAS POR NÚMERO DE INTEGRANTES
     * 
     * Crea un mapa que cuenta cuántos artistas hay de cada tamaño.
     * Ejemplo: {1=15, 3=2, 4=8, 5=3} significa:
     *   - 15 solistas
     *   - 2 tríos
     *   - 8 cuartetos
     *   - 3 quintetos
     * 
     * @return Mapa con estadísticas
     */
    public Map<Integer, Integer> getEstadisticasPorNumIntegrantes() {
        Map<Integer, Integer> estadisticas = new HashMap<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            int numIntegrantes = a.getNumIntegrantes();
            
            // getOrDefault: si la clave no existe, devuelve 0
            // Luego le sumamos 1 y actualizamos el mapa
            estadisticas.put(numIntegrantes, estadisticas.getOrDefault(numIntegrantes, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * OBTENER LISTA DE CÓDIGOS
     * 
     * Extrae solo los códigos de todos los artistas.
     * Útil para validaciones o comparaciones rápidas.
     * 
     * @return Lista de códigos
     */
    public List<String> getCodigosArtistas() {
        List<String> codigos = new ArrayList<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            codigos.add(a.getCodigo());
        }
        
        return codigos;
    }

    /**
     * BUSCAR ARTISTAS POR DOMINIO DE EMAIL
     * 
     * Encuentra artistas cuyo email del manager contenga el dominio especificado.
     * Ejemplo: dominio="gmail.com" encontrará todos los que usen Gmail.
     * 
     * @param dominio - Dominio a buscar (ej: "gmail.com", "manager")
     * @return Lista de artistas filtrados
     */
    public List<Artista> getArtistasPorDominioEmail(String dominio) {
        List<Artista> artistasFiltrados = new ArrayList<>();
        
        Iterator<Artista> it = artistaServicio.getListaArtistas().iterator();
        while (it.hasNext()) {
            Artista a = it.next();
            if (a.getEmailManager().toLowerCase().contains(dominio.toLowerCase())) {
                artistasFiltrados.add(a);
            }
        }
        
        return artistasFiltrados;
    }

    // ==================== OPERACIONES EN LOTE ====================

    /**
     * AGREGAR LISTA DE ARTISTAS
     * 
     * Agrega múltiples artistas de una vez.
     * Usa logging para registrar éxitos y errores.
     * NO lanza excepción: captura errores individualmente para no detener el proceso.
     * 
     * artistas - Lista de artistas a agregar
     */
    public void agregarListaArtistas(List<Artista> artistas) {
        // Logger de Log4j para registrar eventos
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ArtistaRepositorio.class);
        
        Iterator<Artista> it = artistas.iterator();
        while (it.hasNext()) {
            Artista artista = it.next();
            try {
                // Intentamos agregar el artista
                agregarArtista(artista);
                logger.info("Artista agregado correctamente: " + artista.getCodigo());
            } catch (LiveFestException e) {
                // Si falla (ej: duplicado), registramos el error pero continuamos
                logger.error("Error al agregar artista " + artista.getCodigo() + ": " + e.getMessage());
            }
        }
    }
}