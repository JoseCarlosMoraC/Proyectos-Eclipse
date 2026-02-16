package dao;

import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import modelos.Evento;
import modelos.Participante;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class EventoDao extends AbstractDao<Evento> {

	public EventoDao() {
		setClase(Evento.class);
	}
	public List<Participante> getParticipantesPorApellido() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Participante a ORDER BY a.apellidos DESC";
		TypedQuery<Participante> query = sesion.createQuery(hql, Participante.class);
		List<Participante> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}
	
	public List<Participante> getParticipantesPorNombre() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT a FROM Participante a ORDER BY a.nombre ASC";
		TypedQuery<Participante> query = sesion.createQuery(hql, Participante.class);
		List<Participante> articulos = query.getResultList();
		sesion.close();
		return articulos;
	}
	
}