package Repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Jugador;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;


public class JugadorDao extends AbstractDao<Jugador> {

    public JugadorDao() {
        super(Jugador.class);
    }

    
    public List<Jugador> findByPosicion(String posicion) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Jugador j WHERE j.posicion = :posicion";
        TypedQuery<Jugador> query = sesion.createQuery(hql, Jugador.class);
        query.setParameter("posicion", posicion);
        List<Jugador> jugadores = query.getResultList();
        sesion.close();
        return jugadores;
    }

 
    public Long contarJugadoresPorEquipo(Long equipoId) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT COUNT(j) FROM Jugador j WHERE j.equipo.id = :equipoId";
        TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
        query.setParameter("equipoId", equipoId);
        Long total = query.getSingleResult();
        sesion.close();
        return total;
    }

    public List<Object[]> getNombreYApellidos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT j.nombre, j.apellidos FROM Jugador j";
        TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
        List<Object[]> resultados = query.getResultList();
        sesion.close();
        return resultados;
    }

   
    public List<Jugador> getJugadoresOrdenadosPorDorsal() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Jugador j WHERE j.dorsal IS NOT NULL ORDER BY j.dorsal ASC";
        TypedQuery<Jugador> query = sesion.createQuery(hql, Jugador.class);
        List<Jugador> jugadores = query.getResultList();
        sesion.close();
        return jugadores;
    }

   
    public int actualizarEstadoFisico(Long jugadorId, String nuevoEstado) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaUpdate<Jugador> update = cb.createCriteriaUpdate(Jugador.class);
            Root<Jugador> root = update.from(Jugador.class);
            
            update.set(root.get("estadoFisico"), nuevoEstado);
            update.where(cb.equal(root.get("id"), jugadorId));
            
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

  
    public int eliminarJugadoresSinEquipo() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaDelete<Jugador> delete = cb.createCriteriaDelete(Jugador.class);
            Root<Jugador> root = delete.from(Jugador.class);
            
            delete.where(cb.isNull(root.get("equipo")));
            
            int filasEliminadas = sesion.createMutationQuery(delete).executeUpdate();
            tx.commit();
            return filasEliminadas;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            sesion.close();
        }
    }


    public Jugador getPrimerJugadorPorNombre(String nombre) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Jugador j WHERE j.nombre = :nombre";
        TypedQuery<Jugador> query = sesion.createQuery(hql, Jugador.class);
        query.setParameter("nombre", nombre);
        query.setMaxResults(1);
        Jugador jugador = query.getResultStream().findFirst().orElse(null);
        sesion.close();
        return jugador;
    }
}