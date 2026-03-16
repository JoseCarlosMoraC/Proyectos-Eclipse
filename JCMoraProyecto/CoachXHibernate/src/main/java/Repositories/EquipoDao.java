package Repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Equipo;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;


public class EquipoDao extends AbstractDao<Equipo> {

    public EquipoDao() {
        super(Equipo.class);
    }

  
    public List<Equipo> findByCategoria(String categoria) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Equipo e WHERE e.categoria = :categoria";
        TypedQuery<Equipo> query = sesion.createQuery(hql, Equipo.class);
        query.setParameter("categoria", categoria);
        List<Equipo> equipos = query.getResultList();
        sesion.close();
        return equipos;
    }

  
    public Double getPromedioJugadoresPorEquipo() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT AVG(SIZE(e.jugadores)) FROM Equipo e";
        TypedQuery<Double> query = sesion.createQuery(hql, Double.class);
        Double promedio = query.getSingleResult();
        sesion.close();
        return promedio;
    }


    public List<Equipo> getEquiposOrdenadosPorNombreYCategoria() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Equipo e ORDER BY e.nombre ASC, e.categoria DESC";
        TypedQuery<Equipo> query = sesion.createQuery(hql, Equipo.class);
        List<Equipo> equipos = query.getResultList();
        sesion.close();
        return equipos;
    }

    public int actualizarTemporada(Long equipoId, String nuevaTemporada) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaUpdate<Equipo> update = cb.createCriteriaUpdate(Equipo.class);
            Root<Equipo> root = update.from(Equipo.class);
            
            update.set(root.get("temporada"), nuevaTemporada);
            update.where(cb.equal(root.get("id"), equipoId));
            
            int filasActualizadas = sesion.createMutationQuery(update).executeUpdate();
            tx.commit();
            return filasActualizadas;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            sesion.close();
        }
    }


    public List<String> getNombresEquiposPorCategoria(String categoria) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT e.nombre FROM Equipo e WHERE e.categoria = :categoria";
        TypedQuery<String> query = sesion.createQuery(hql, String.class);
        query.setParameter("categoria", categoria);
        List<String> nombres = query.getResultList();
        sesion.close();
        return nombres;
    }
}