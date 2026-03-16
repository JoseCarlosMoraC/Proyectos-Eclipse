package Repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Club;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;


public class ClubDao extends AbstractDao<Club> {

    public ClubDao() {
        super(Club.class);
    }


  
    public Club getPrimerClub() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Club c ORDER BY c.id ASC";
        TypedQuery<Club> query = sesion.createQuery(hql, Club.class);
        query.setMaxResults(1);
        Club club = query.getResultStream().findFirst().orElse(null);
        sesion.close();
        return club;
    }

  
    public List<String> getNombresClub() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT c.nombre FROM Club c";
        TypedQuery<String> query = sesion.createQuery(hql, String.class);
        List<String> nombres = query.getResultList();
        sesion.close();
        return nombres;
    }

  
    public List<Object[]> getNombreYContacto() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT c.nombre, c.contacto FROM Club c";
        TypedQuery<Object[]> query = sesion.createQuery(hql, Object[].class);
        List<Object[]> resultados = query.getResultList();
        sesion.close();
        return resultados;
    }

   
    public Club findByNombre(String nombre) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Club c WHERE c.nombre = :nombre";
        TypedQuery<Club> query = sesion.createQuery(hql, Club.class);
        query.setParameter("nombre", nombre);
        Club club = query.getResultStream().findFirst().orElse(null);
        sesion.close();
        return club;
    }

    
    public List<Club> findByNombreContiene(String texto) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Club c WHERE c.nombre LIKE :texto";
        TypedQuery<Club> query = sesion.createQuery(hql, Club.class);
        query.setParameter("texto", "%" + texto + "%");
        List<Club> clubes = query.getResultList();
        sesion.close();
        return clubes;
    }

   
    public Long contarClubes() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT COUNT(c) FROM Club c";
        TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
        Long total = query.getSingleResult();
        sesion.close();
        return total;
    }

  
   
    public List<Club> getClubesOrdenadosPorNombre() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Club c ORDER BY c.nombre ASC";
        TypedQuery<Club> query = sesion.createQuery(hql, Club.class);
        List<Club> clubes = query.getResultList();
        sesion.close();
        return clubes;
    }

  
    public int actualizarContacto(Long clubId, String nuevoContacto) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaUpdate<Club> update = cb.createCriteriaUpdate(Club.class);
            Root<Club> root = update.from(Club.class);
            
            update.set(root.get("contacto"), nuevoContacto);
            update.where(cb.equal(root.get("id"), clubId));
            
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

    public int eliminarClubesSinEquipos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            CriteriaBuilder cb = sesion.getCriteriaBuilder();
            CriteriaDelete<Club> delete = cb.createCriteriaDelete(Club.class);
            Root<Club> root = delete.from(Club.class);
            
           
            delete.where(cb.isEmpty(root.get("equipos")));
            
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
}