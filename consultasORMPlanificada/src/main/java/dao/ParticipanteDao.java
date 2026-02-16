package dao;



import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import modelos.Participante;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class ParticipanteDao extends AbstractDao<Participante> {

	public ParticipanteDao() {
		setClase(Participante.class);
	}
	
	public Long getTotalArticulos() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String hql = "SELECT COUNT(a) FROM Participante a";
		TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
		Long total = query.getSingleResult();
		sesion.close();
		return total;
	}
	
}