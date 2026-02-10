package Consultas;

import org.hibernate.Session;
import jakarta.persistence.TypedQuery;

import modelos.Articulo;
import modelos.Autor;
import modelos.Revista;
import utiles.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase con TODAS las consultas HQL posibles para el sistema de Revistas y Artículos
 * Incluye más de 100 consultas diferentes cubriendo todos los casos posibles
 */
public class ConsultasHQL {

	// ================================================================
	// CONSULTAS BÁSICAS DE ARTÍCULOS (1-15)
	// ================================================================

	/**
	 * 1. Artículos de un autor ordenados alfabéticamente (CONSULTA ORIGINAL)
	 */
	public List<Articulo> getArticulosPorAutorHQL(String nombreAutor) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a JOIN a.autores autor WHERE autor.nombre = :nombreAutor ORDER BY a.titulo ASC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 2. Artículos con más de 6 páginas (CONSULTA ORIGINAL)
	 */
	public List<Object[]> getArticulosMasDe6PaginasHQL() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, (a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "FROM Articulo a " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) > 6";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 3. Artículos con más de 6 páginas + datos de revista (CONSULTA ORIGINAL)
	 */
	public List<Object[]> getArticulosConRevistaHQL() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, " +
		             "(a.numPaginaFin - a.numPaginaInicio + 1), " +
		             "r.nombreRevista, " +
		             "r.fecha " +
		             "FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) > 6";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 4. Buscar artículos por título exacto
	 */
	public List<Articulo> getArticulosPorTituloExacto(String titulo) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE a.titulo = :titulo";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("titulo", titulo);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 5. Buscar artículos que contienen una palabra en el título
	 */
	public List<Articulo> getArticulosPorPalabraClave(String palabra) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE a.titulo LIKE :palabra";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("palabra", "%" + palabra + "%");
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 6. Todos los artículos ordenados por título
	 */
	public List<Articulo> getTodosArticulosOrdenadosPorTitulo() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY a.titulo ASC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 7. Todos los artículos ordenados por número de páginas (descendente)
	 */
	public List<Articulo> getTodosArticulosOrdenadosPorPaginas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY (a.numPaginaFin - a.numPaginaInicio) DESC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 8. Artículos en un rango de páginas
	 */
	public List<Articulo> getArticulosPorRangoPaginas(int minPaginas, int maxPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) BETWEEN :min AND :max";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("min", minPaginas);
		query.setParameter("max", maxPaginas);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 9. Artículos con menos de N páginas
	 */
	public List<Articulo> getArticulosConMenosDePaginas(int numPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) < :num";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("num", numPaginas);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 10. Artículos con exactamente N páginas
	 */
	public List<Articulo> getArticulosConPaginasExactas(int numPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) = :num";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("num", numPaginas);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 11. Artículo más largo
	 */
	public Articulo getArticuloMasLargo() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY (a.numPaginaFin - a.numPaginaInicio) DESC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setMaxResults(1);
		Articulo articulo = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return articulo;
	}

	/**
	 * 12. Artículo más corto
	 */
	public Articulo getArticuloMasCorto() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY (a.numPaginaFin - a.numPaginaInicio) ASC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setMaxResults(1);
		Articulo articulo = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return articulo;
	}

	/**
	 * 13. Promedio de páginas de todos los artículos
	 */
	public Double getPromedioPaginasArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT AVG(a.numPaginaFin - a.numPaginaInicio + 1) FROM Articulo a";
		TypedQuery<Double> query = sesion.createQuery(hql, Double.class);
		Double promedio = query.getSingleResult();
		sesion.close();
		return promedio;
	}

	/**
	 * 14. Total de artículos
	 */
	public Long getTotalArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Articulo a";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 15. Solo títulos de artículos
	 */
	public List<String> getTitulosArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo FROM Articulo a ORDER BY a.titulo";
		TypedQuery<String> query = sesion.createQuery(hql, String.class);
		List<String> titulos = query.getResultList();
		sesion.close();
		return titulos;
	}

	// ================================================================
	// CONSULTAS POR AUTOR (16-35)
	// ================================================================

	/**
	 * 16. Artículos de un autor por DNI
	 */
	public List<Articulo> getArticulosPorAutorDNI(String dni) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a JOIN a.autores autor WHERE autor.dni = :dni";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("dni", dni);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 17. Artículos de un autor por email
	 */
	public List<Articulo> getArticulosPorAutorEmail(String email) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a JOIN a.autores autor WHERE autor.email = :email";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("email", email);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 18. Artículos con más de un autor (colaboraciones)
	 */
	public List<Articulo> getArticulosConVariosAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE SIZE(a.autores) > 1";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 19. Artículos con exactamente N autores
	 */
	public List<Articulo> getArticulosPorNumeroAutores(int numAutores) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE SIZE(a.autores) = :num";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("num", numAutores);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 20. Artículos sin autores asignados
	 */
	public List<Articulo> getArticulosSinAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE SIZE(a.autores) = 0";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 21. Contar artículos de un autor
	 */
	public Long contarArticulosPorAutor(String nombreAutor) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Articulo a " +
		             "JOIN a.autores autor WHERE autor.nombre = :nombreAutor";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		query.setParameter("nombreAutor", nombreAutor);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 22. Artículos de un autor con más de N páginas
	 */
	public List<Articulo> getArticulosLargosPorAutor(String nombreAutor, int minPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND (a.numPaginaFin - a.numPaginaInicio + 1) >= :min";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("min", minPaginas);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 23. Artículos de un autor en una revista específica
	 */
	public List<Articulo> getArticulosPorAutorYRevista(String nombreAutor, String nombreRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND r.nombreRevista = :nombreRevista";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("nombreRevista", nombreRevista);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 24. Artículos de un autor en un año específico
	 */
	public List<Articulo> getArticulosPorAutorYAnio(String nombreAutor, int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND YEAR(r.fecha) = :anio";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("anio", anio);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 25. Artículos con título y autores
	 */
	public List<Object[]> getArticulosConAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, autor.nombre " +
		             "FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "ORDER BY a.titulo, autor.nombre";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 26. Número de autores por artículo
	 */
	public List<Object[]> getNumeroAutoresPorArticulo() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, SIZE(a.autores) " +
		             "FROM Articulo a " +
		             "ORDER BY SIZE(a.autores) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 27. Autores que han escrito artículos largos (>10 páginas)
	 */
	public List<Autor> getAutoresConArticulosLargos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT autor FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) > 10";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 28. Artículos agrupados por número de autores
	 */
	public List<Object[]> getArticulosAgrupadosPorNumeroAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT SIZE(a.autores), COUNT(a) " +
		             "FROM Articulo a " +
		             "GROUP BY SIZE(a.autores) " +
		             "ORDER BY SIZE(a.autores)";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	// ================================================================
	// CONSULTAS POR REVISTA (29-50)
	// ================================================================

	/**
	 * 29. Número de artículos por revista (CONSULTA ORIGINAL)
	 */
	public List<Object[]> getNumeroArticulosPorRevistaHQL() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, COUNT(a) " +
		             "FROM Revista r " +
		             "LEFT JOIN r.articulos a " +
		             "GROUP BY r.nombreRevista";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 30. Revistas publicadas antes de una fecha (CONSULTA ORIGINAL)
	 */
	public List<Revista> getRevistasAntesDeFehaHQL(LocalDate fecha) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r " +
		             "WHERE r.fecha < :fecha " +
		             "ORDER BY r.fecha DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("fecha", fecha);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 31. Artículos de una revista específica
	 */
	public List<Articulo> getArticulosPorRevista(String nombreRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE r.nombreRevista = :nombreRevista";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreRevista", nombreRevista);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 32. Artículos de una revista por ID
	 */
	public List<Articulo> getArticulosPorRevistaId(Integer idRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE a.revista.idRevista = :id";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("id", idRevista);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 33. Artículos sin revista asignada
	 */
	public List<Articulo> getArticulosSinRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE a.revista IS NULL";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 34. Artículos publicados en un año específico
	 */
	public List<Articulo> getArticulosPorAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE YEAR(r.fecha) = :anio";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("anio", anio);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 35. Artículos publicados en un rango de fechas
	 */
	public List<Articulo> getArticulosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE r.fecha BETWEEN :inicio AND :fin";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("inicio", fechaInicio);
		query.setParameter("fin", fechaFin);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 36. Artículos publicados después de una fecha
	 */
	public List<Articulo> getArticulosPublicadosDespuesDe(LocalDate fecha) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE r.fecha > :fecha " +
		             "ORDER BY r.fecha ASC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("fecha", fecha);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 37. Artículos publicados antes de una fecha
	 */
	public List<Articulo> getArticulosPublicadosAntesDe(LocalDate fecha) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE r.fecha < :fecha " +
		             "ORDER BY r.fecha DESC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("fecha", fecha);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 38. Revistas con más de N artículos
	 */
	public List<Revista> getRevistasConMasDeNArticulos(int numArticulos) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r WHERE SIZE(r.articulos) > :num";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("num", numArticulos);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 39. Revistas sin artículos
	 */
	public List<Revista> getRevistasSinArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r WHERE SIZE(r.articulos) = 0";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 40. Revistas ordenadas por número de artículos
	 */
	public List<Revista> getRevistasOrdenadasPorArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY SIZE(r.articulos) DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 41. Revista con más artículos
	 */
	public Revista getRevistaConMasArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY SIZE(r.articulos) DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setMaxResults(1);
		Revista revista = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return revista;
	}

	/**
	 * 42. Revistas publicadas en un año
	 */
	public List<Revista> getRevistasPorAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r WHERE YEAR(r.fecha) = :anio ORDER BY r.fecha";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("anio", anio);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 43. Revistas con más de N unidades impresas
	 */
	public List<Revista> getRevistasConMasDeNUnidades(int unidades) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r " +
		             "WHERE r.unidadesImpresas > :unidades " +
		             "ORDER BY r.unidadesImpresas DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("unidades", unidades);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 44. Revista con más unidades impresas
	 */
	public Revista getRevistaConMasUnidades() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.unidadesImpresas DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setMaxResults(1);
		Revista revista = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return revista;
	}

	/**
	 * 45. Revista más antigua
	 */
	public Revista getRevistaMasAntigua() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.fecha ASC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setMaxResults(1);
		Revista revista = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return revista;
	}

	/**
	 * 46. Revista más reciente
	 */
	public Revista getRevistaMasReciente() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.fecha DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setMaxResults(1);
		Revista revista = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return revista;
	}

	/**
	 * 47. Total de revistas
	 */
	public Long getTotalRevistas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(r) FROM Revista r";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 48. Suma total de unidades impresas
	 */
	public Long getSumaTotalUnidadesImpresas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT SUM(r.unidadesImpresas) FROM Revista r";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long suma = query.getSingleResult();
		sesion.close();
		return suma;
	}

	/**
	 * 49. Promedio de unidades impresas
	 */
	public Double getPromedioUnidadesImpresas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT AVG(r.unidadesImpresas) FROM Revista r";
		TypedQuery<Double> query = sesion.createQuery(hql, Double.class);
		Double promedio = query.getSingleResult();
		sesion.close();
		return promedio;
	}

	/**
	 * 50. Revistas agrupadas por año con conteo
	 */
	public List<Object[]> getRevistasAgrupadasPorAnio() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT YEAR(r.fecha), COUNT(r) " +
		             "FROM Revista r " +
		             "GROUP BY YEAR(r.fecha) " +
		             "ORDER BY YEAR(r.fecha) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	// ================================================================
	// CONSULTAS DE AUTORES (51-70)
	// ================================================================

	/**
	 * 51. Todos los autores ordenados por nombre
	 */
	public List<Autor> getTodosAutoresOrdenadosPorNombre() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a ORDER BY a.nombre ASC";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 52. Buscar autor por nombre exacto
	 */
	public Autor getAutorPorNombre(String nombre) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE a.nombre = :nombre";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("nombre", nombre);
		Autor autor = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return autor;
	}

	/**
	 * 53. Buscar autor por email
	 */
	public Autor getAutorPorEmail(String email) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE a.email = :email";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("email", email);
		Autor autor = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return autor;
	}

	/**
	 * 54. Autores con más de N artículos
	 */
	public List<Autor> getAutoresConMasDeNArticulos(int numArticulos) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE SIZE(a.articulos) > :num";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("num", numArticulos);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 55. Autores sin artículos
	 */
	public List<Autor> getAutoresSinArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE SIZE(a.articulos) = 0";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 56. Autores con artículos
	 */
	public List<Autor> getAutoresConArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE SIZE(a.articulos) > 0";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 57. Autor con más artículos
	 */
	public Autor getAutorConMasArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a ORDER BY SIZE(a.articulos) DESC";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setMaxResults(1);
		Autor autor = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		sesion.close();
		return autor;
	}

	/**
	 * 58. Autores ordenados por número de artículos
	 */
	public List<Autor> getAutoresOrdenadosPorArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a ORDER BY SIZE(a.articulos) DESC";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 59. Total de autores
	 */
	public Long getTotalAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Autor a";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 60. Número de artículos por cada autor
	 */
	public List<Object[]> getNumeroArticulosPorAutor() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, SIZE(a.articulos) " +
		             "FROM Autor a " +
		             "ORDER BY SIZE(a.articulos) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 61. Autores que han publicado en una revista específica
	 */
	public List<Autor> getAutoresPorRevista(String nombreRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT a FROM Autor a " +
		             "JOIN a.articulos art " +
		             "JOIN art.revista r " +
		             "WHERE r.nombreRevista = :nombreRevista";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("nombreRevista", nombreRevista);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 62. Autores que han publicado en un año específico
	 */
	public List<Autor> getAutoresPorAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT a FROM Autor a " +
		             "JOIN a.articulos art " +
		             "JOIN art.revista r " +
		             "WHERE YEAR(r.fecha) = :anio";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("anio", anio);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 63. Autores que han colaborado (artículos con más de un autor)
	 */
	public List<Autor> getAutoresQueHanColaborado() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT a FROM Autor a " +
		             "JOIN a.articulos art " +
		             "WHERE SIZE(art.autores) > 1";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 64. Autores por dominio de email
	 */
	public List<Autor> getAutoresPorDominioEmail(String dominio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE a.email LIKE :dominio";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("dominio", "%@" + dominio);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 65. Autores cuyo nombre empieza con una letra
	 */
	public List<Autor> getAutoresPorLetraInicial(String letra) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE a.nombre LIKE :letra";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("letra", letra + "%");
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 66. Promedio de artículos por autor
	 */
	public Double getPromedioArticulosPorAutor() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT AVG(SIZE(a.articulos)) FROM Autor a";
		TypedQuery<Double> query = sesion.createQuery(hql, Double.class);
		Double promedio = query.getSingleResult();
		sesion.close();
		return promedio;
	}

	/**
	 * 67. Autores con exactamente N artículos
	 */
	public List<Autor> getAutoresConNArticulos(int numArticulos) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a WHERE SIZE(a.articulos) = :num";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setParameter("num", numArticulos);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 68. Solo nombres de autores
	 */
	public List<String> getNombresAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre FROM Autor a ORDER BY a.nombre";
		TypedQuery<String> query = sesion.createQuery(hql, String.class);
		List<String> nombres = query.getResultList();
		sesion.close();
		return nombres;
	}

	/**
	 * 69. Nombres y emails de autores
	 */
	public List<Object[]> getNombresYEmailsAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, a.email FROM Autor a ORDER BY a.nombre";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 70. Top N autores por número de artículos
	 */
	public List<Autor> getTopAutoresPorArticulos(int topN) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Autor a ORDER BY SIZE(a.articulos) DESC";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		query.setMaxResults(topN);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	// ================================================================
	// CONSULTAS ESTADÍSTICAS Y AGREGADAS (71-90)
	// ================================================================

	/**
	 * 71. Suma total de páginas de todos los artículos
	 */
	public Long getSumaTotalPaginasArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT SUM(a.numPaginaFin - a.numPaginaInicio + 1) FROM Articulo a";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long suma = query.getSingleResult();
		sesion.close();
		return suma;
	}

	/**
	 * 72. Máximo de páginas de un artículo
	 */
	public Integer getMaxPaginasArticulo() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT MAX(a.numPaginaFin - a.numPaginaInicio + 1) FROM Articulo a";
		TypedQuery<Integer> query = sesion.createQuery(hql, Integer.class);
		Integer max = query.getSingleResult();
		sesion.close();
		return max;
	}

	/**
	 * 73. Mínimo de páginas de un artículo
	 */
	public Integer getMinPaginasArticulo() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT MIN(a.numPaginaFin - a.numPaginaInicio + 1) FROM Articulo a";
		TypedQuery<Integer> query = sesion.createQuery(hql, Integer.class);
		Integer min = query.getSingleResult();
		sesion.close();
		return min;
	}

	/**
	 * 74. Artículos agrupados por número de páginas
	 */
	public List<Object[]> getArticulosAgrupadosPorPaginas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT (a.numPaginaFin - a.numPaginaInicio + 1), COUNT(a) " +
		             "FROM Articulo a " +
		             "GROUP BY (a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "ORDER BY (a.numPaginaFin - a.numPaginaInicio + 1)";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 75. Promedio de artículos por revista
	 */
	public Double getPromedioArticulosPorRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT AVG(SIZE(r.articulos)) FROM Revista r";
		TypedQuery<Double> query = sesion.createQuery(hql, Double.class);
		Double promedio = query.getSingleResult();
		sesion.close();
		return promedio;
	}

	/**
	 * 76. Revistas ordenadas por fecha
	 */
	public List<Revista> getRevistasOrdenadasPorFecha() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.fecha DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 77. Revistas ordenadas por nombre
	 */
	public List<Revista> getRevistasOrdenadasPorNombre() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.nombreRevista ASC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 78. Información completa de artículos (título, páginas, revista, fecha, autores)
	 */
	public List<Object[]> getInformacionCompletaArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, " +
		             "(a.numPaginaFin - a.numPaginaInicio + 1), " +
		             "r.nombreRevista, " +
		             "r.fecha, " +
		             "SIZE(a.autores) " +
		             "FROM Articulo a " +
		             "LEFT JOIN a.revista r";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 79. Estadísticas completas de revistas
	 */
	public List<Object[]> getEstadisticasRevistas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, " +
		             "r.numeroRevista, " +
		             "r.fecha, " +
		             "r.unidadesImpresas, " +
		             "SIZE(r.articulos) " +
		             "FROM Revista r " +
		             "ORDER BY r.fecha DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 80. Información completa de autores (nombre, email, num artículos)
	 */
	public List<Object[]> getInformacionCompletaAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.dni, a.nombre, a.email, SIZE(a.articulos) " +
		             "FROM Autor a " +
		             "ORDER BY a.nombre";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 81. Top N artículos más largos
	 */
	public List<Articulo> getTopArticulosMasLargos(int topN) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY (a.numPaginaFin - a.numPaginaInicio) DESC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setMaxResults(topN);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 82. Top N artículos más cortos
	 */
	public List<Articulo> getTopArticulosMasCortos(int topN) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a ORDER BY (a.numPaginaFin - a.numPaginaInicio) ASC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setMaxResults(topN);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 83. Top N revistas por unidades impresas
	 */
	public List<Revista> getTopRevistasPorUnidades(int topN) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r ORDER BY r.unidadesImpresas DESC";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setMaxResults(topN);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 84. Artículos con título y número de páginas ordenados por páginas
	 */
	public List<Object[]> getArticulosTituloYPaginasOrdenados() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, (a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "FROM Articulo a " +
		             "ORDER BY (a.numPaginaFin - a.numPaginaInicio + 1) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 85. Conteo de autores por número de artículos
	 */
	public Long contarAutoresConArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Autor a WHERE SIZE(a.articulos) > 0";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 86. Conteo de autores sin artículos
	 */
	public Long contarAutoresSinArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Autor a WHERE SIZE(a.articulos) = 0";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 87. Conteo de revistas por año
	 */
	public Long contarRevistasPorAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(r) FROM Revista r WHERE YEAR(r.fecha) = :anio";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		query.setParameter("anio", anio);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}

	/**
	 * 88. Unidades impresas totales por año
	 */
	public List<Object[]> getUnidadesImpresasPorAnio() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT YEAR(r.fecha), SUM(r.unidadesImpresas) " +
		             "FROM Revista r " +
		             "GROUP BY YEAR(r.fecha) " +
		             "ORDER BY YEAR(r.fecha) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 89. Máximo de unidades impresas
	 */
	public Integer getMaxUnidadesImpresas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT MAX(r.unidadesImpresas) FROM Revista r";
		TypedQuery<Integer> query = sesion.createQuery(hql, Integer.class);
		Integer max = query.getSingleResult();
		sesion.close();
		return max;
	}

	/**
	 * 90. Mínimo de unidades impresas
	 */
	public Integer getMinUnidadesImpresas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT MIN(r.unidadesImpresas) FROM Revista r";
		TypedQuery<Integer> query = sesion.createQuery(hql, Integer.class);
		Integer min = query.getSingleResult();
		sesion.close();
		return min;
	}

	// ================================================================
	// CONSULTAS COMPLEJAS Y COMBINADAS (91-120)
	// ================================================================

	/**
	 * 91. Artículos de un autor en una revista en un año
	 */
	public List<Articulo> getArticulosAutorRevistaAnio(String nombreAutor, String nombreRevista, int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND r.nombreRevista = :nombreRevista " +
		             "AND YEAR(r.fecha) = :anio";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("nombreRevista", nombreRevista);
		query.setParameter("anio", anio);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 92. Revistas de un año con más de N unidades impresas
	 */
	public List<Revista> getRevistasAnioConUnidades(int anio, int minUnidades) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r " +
		             "WHERE YEAR(r.fecha) = :anio " +
		             "AND r.unidadesImpresas > :min";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("anio", anio);
		query.setParameter("min", minUnidades);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 93. Artículos largos (>N páginas) de un autor
	 */
	public List<Articulo> getArticulosLargosAutor(String nombreAutor, int minPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND (a.numPaginaFin - a.numPaginaInicio + 1) > :min";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("min", minPaginas);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 94. Número de revistas diferentes en las que ha publicado cada autor
	 */
	public List<Object[]> getNumeroRevistasPorAutor() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, COUNT(DISTINCT art.revista) " +
		             "FROM Autor a " +
		             "JOIN a.articulos art " +
		             "GROUP BY a.nombre " +
		             "ORDER BY COUNT(DISTINCT art.revista) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 95. Revistas que tienen artículos con más de N páginas
	 */
	public List<Revista> getRevistasConArticulosLargos(int minPaginas) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT r FROM Revista r " +
		             "JOIN r.articulos a " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) > :min";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("min", minPaginas);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 96. Autores que han publicado solo (sin colaboración)
	 */
	public List<Autor> getAutoresSinColaboracion() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT a FROM Autor a " +
		             "JOIN a.articulos art " +
		             "WHERE SIZE(art.autores) = 1";
		TypedQuery<Autor> query = sesion.createQuery(hql, Autor.class);
		List<Autor> autores = query.getResultList();
		sesion.close();
		return autores;
	}

	/**
	 * 97. Revistas en un rango de fechas con artículos
	 */
	public List<Revista> getRevistasRangoFechasConArticulos(LocalDate inicio, LocalDate fin) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r " +
		             "WHERE r.fecha BETWEEN :inicio AND :fin " +
		             "AND SIZE(r.articulos) > 0";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("inicio", inicio);
		query.setParameter("fin", fin);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 98. Páginas totales escritas por cada autor
	 */
	public List<Object[]> getPaginasTotalesPorAutor() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, SUM(art.numPaginaFin - art.numPaginaInicio + 1) " +
		             "FROM Autor a " +
		             "JOIN a.articulos art " +
		             "GROUP BY a.nombre " +
		             "ORDER BY SUM(art.numPaginaFin - art.numPaginaInicio + 1) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 99. Páginas totales por revista
	 */
	public List<Object[]> getPaginasTotalesPorRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, SUM(a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "FROM Revista r " +
		             "JOIN r.articulos a " +
		             "GROUP BY r.nombreRevista " +
		             "ORDER BY SUM(a.numPaginaFin - a.numPaginaInicio + 1) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 100. Promedio de páginas por artículo de cada autor
	 */
	public List<Object[]> getPromedioPaginasPorAutor() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, AVG(art.numPaginaFin - art.numPaginaInicio + 1) " +
		             "FROM Autor a " +
		             "JOIN a.articulos art " +
		             "GROUP BY a.nombre " +
		             "ORDER BY AVG(art.numPaginaFin - art.numPaginaInicio + 1) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 101. Autores con artículos en múltiples revistas
	 */
	public List<Object[]> getAutoresEnMultiplesRevistas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, COUNT(DISTINCT art.revista) " +
		             "FROM Autor a " +
		             "JOIN a.articulos art " +
		             "GROUP BY a.nombre " +
		             "HAVING COUNT(DISTINCT art.revista) > 1";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 102. Artículos con colaboración (más de un autor) con detalles
	 */
	public List<Object[]> getArticulosColaboracionDetallada() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, SIZE(a.autores), r.nombreRevista " +
		             "FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE SIZE(a.autores) > 1 " +
		             "ORDER BY SIZE(a.autores) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 103. Revistas ordenadas por total de páginas publicadas
	 */
	public List<Object[]> getRevistasOrdenadas List<Object[]> PorTotalPaginas() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, SUM(a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "FROM Revista r " +
		             "JOIN r.articulos a " +
		             "GROUP BY r.nombreRevista " +
		             "ORDER BY SUM(a.numPaginaFin - a.numPaginaInicio + 1) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 104. Años con más publicaciones
	 */
	public List<Object[]> getAniosConMasPublicaciones() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT YEAR(r.fecha), COUNT(a) " +
		             "FROM Revista r " +
		             "LEFT JOIN r.articulos a " +
		             "GROUP BY YEAR(r.fecha) " +
		             "ORDER BY COUNT(a) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 105. Artículos publicados el mismo año que fueron escritos por el mismo autor
	 */
	public List<Object[]> getArticulosMismoAutorMismoAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT autor.nombre, COUNT(a) " +
		             "FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE YEAR(r.fecha) = :anio " +
		             "GROUP BY autor.nombre " +
		             "HAVING COUNT(a) > 1";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		query.setParameter("anio", anio);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 106. Artículos que empiezan con una letra específica
	 */
	public List<Articulo> getArticulosPorLetraInicial(String letra) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a WHERE a.titulo LIKE :letra";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("letra", letra + "%");
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 107. Revistas con número específico
	 */
	public List<Revista> getRevistasPorNumero(int numeroRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r FROM Revista r WHERE r.numeroRevista = :numero";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("numero", numeroRevista);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 108. Autores y sus colaboradores (otros autores en el mismo artículo)
	 */
	public List<Object[]> getAutoresYColaboradores(String nombreAutor) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT a.titulo, colaborador.nombre " +
		             "FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.autores colaborador " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND colaborador.nombre != :nombreAutor";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		query.setParameter("nombreAutor", nombreAutor);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 109. Revistas con artículos de un autor específico
	 */
	public List<Revista> getRevistasConArticulosDeAutor(String nombreAutor) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT DISTINCT r FROM Revista r " +
		             "JOIN r.articulos a " +
		             "JOIN a.autores autor " +
		             "WHERE autor.nombre = :nombreAutor";
		TypedQuery<Revista> query = sesion.createQuery(hql, Revista.class);
		query.setParameter("nombreAutor", nombreAutor);
		List<Revista> revistas = query.getResultList();
		sesion.close();
		return revistas;
	}

	/**
	 * 110. Promedio de páginas por revista
	 */
	public List<Object[]> getPromedioPaginasPorRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, AVG(a.numPaginaFin - a.numPaginaInicio + 1) " +
		             "FROM Revista r " +
		             "JOIN r.articulos a " +
		             "GROUP BY r.nombreRevista";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 111. Artículos y revistas de un mes específico
	 */
	public List<Object[]> getArticulosPorMes(int mes, int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, r.nombreRevista, r.fecha " +
		             "FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE MONTH(r.fecha) = :mes " +
		             "AND YEAR(r.fecha) = :anio";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		query.setParameter("mes", mes);
		query.setParameter("anio", anio);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 112. Total de artículos por año
	 */
	public List<Object[]> getTotalArticulosPorAnio() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT YEAR(r.fecha), COUNT(a) " +
		             "FROM Revista r " +
		             "JOIN r.articulos a " +
		             "GROUP BY YEAR(r.fecha) " +
		             "ORDER BY YEAR(r.fecha) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 113. Artículos con rango de páginas en una revista
	 */
	public List<Articulo> getArticulosPorPaginasYRevista(int minPag, int maxPag, String nombreRevista) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "WHERE (a.numPaginaFin - a.numPaginaInicio + 1) BETWEEN :min AND :max " +
		             "AND r.nombreRevista = :nombreRevista";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("min", minPag);
		query.setParameter("max", maxPag);
		query.setParameter("nombreRevista", nombreRevista);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 114. Autores más productivos (más artículos) en un año
	 */
	public List<Object[]> getAutoresMasProductivosAnio(int anio) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT autor.nombre, COUNT(a) " +
		             "FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE YEAR(r.fecha) = :anio " +
		             "GROUP BY autor.nombre " +
		             "ORDER BY COUNT(a) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		query.setParameter("anio", anio);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 115. Revistas más productivas (más artículos) por año
	 */
	public List<Object[]> getRevistasMasProductivasPorAnio() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT r.nombreRevista, YEAR(r.fecha), COUNT(a) " +
		             "FROM Revista r " +
		             "LEFT JOIN r.articulos a " +
		             "GROUP BY r.nombreRevista, YEAR(r.fecha) " +
		             "ORDER BY COUNT(a) DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 116. Búsqueda combinada: artículos por autor, revista y rango de páginas
	 */
	public List<Articulo> busquedaCombinada(String nombreAutor, String nombreRevista, int minPag, int maxPag) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.autores autor " +
		             "JOIN a.revista r " +
		             "WHERE autor.nombre = :nombreAutor " +
		             "AND r.nombreRevista = :nombreRevista " +
		             "AND (a.numPaginaFin - a.numPaginaInicio + 1) BETWEEN :min AND :max";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setParameter("nombreAutor", nombreAutor);
		query.setParameter("nombreRevista", nombreRevista);
		query.setParameter("min", minPag);
		query.setParameter("max", maxPag);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 117. Artículos ordenados por fecha de publicación
	 */
	public List<Object[]> getArticulosOrdenadosPorFecha() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.titulo, r.nombreRevista, r.fecha " +
		             "FROM Articulo a " +
		             "JOIN a.revista r " +
		             "ORDER BY r.fecha DESC";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 118. Artículos más recientes (últimos N)
	 */
	public List<Articulo> getArticulosMasRecientes(int limit) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Articulo a " +
		             "JOIN a.revista r " +
		             "ORDER BY r.fecha DESC";
		TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
		query.setMaxResults(limit);
		List<Articulo> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}

	/**
	 * 119. Distribución de artículos por número de autores
	 */
	public List<Object[]> getDistribucionArticulosPorNumAutores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT SIZE(a.autores) AS numAutores, COUNT(a) AS numArticulos " +
		             "FROM Articulo a " +
		             "GROUP BY SIZE(a.autores) " +
		             "ORDER BY SIZE(a.autores)";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}

	/**
	 * 120. Resumen general del sistema
	 */
	public List<Object[]> getResumenGeneral() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT " +
		             "(SELECT COUNT(a) FROM Articulo a), " +
		             "(SELECT COUNT(r) FROM Revista r), " +
		             "(SELECT COUNT(au) FROM Autor au), " +
		             "(SELECT AVG(a.numPaginaFin - a.numPaginaInicio + 1) FROM Articulo a), " +
		             "(SELECT SUM(r.unidadesImpresas) FROM Revista r)";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}
}