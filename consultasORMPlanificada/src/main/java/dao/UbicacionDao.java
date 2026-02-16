package dao;

import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import modelos.Ubicacion;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class UbicacionDao extends AbstractDao<Ubicacion> {

	public UbicacionDao() {
		setClase(Ubicacion.class);
	}
	public List<Object[]> getEventosConMasDe91Minutos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a.nombre, tipoEvento, duracion " +
		             "FROM Evento a " +
		             "WHERE duracion > 91";
		TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
		List<Object[]> resultados = query.getResultList();
		sesion.close();
		return resultados;
	}
	public List<Ubicacion> getEventosPorUbicacionNombre(String nombreUbicacion) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Ubicacion a JOIN a.eventos evento WHERE ubicacion.nombre = :nombreUbicacion";
		TypedQuery<Ubicacion> query = sesion.createQuery(hql, Ubicacion.class);
		query.setParameter("nombreUbicacion", nombreUbicacion);
		List<Ubicacion> ubicaciones = query.getResultList();
		sesion.close();
		return ubicaciones;
	}
}
