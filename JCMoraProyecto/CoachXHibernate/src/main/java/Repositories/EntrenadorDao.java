package Repositories;

import java.util.List;

import org.hibernate.Session;

import Models.Entrenador;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

public class EntrenadorDao extends AbstractDao<Entrenador> {

    public EntrenadorDao() {
        super(Entrenador.class);
    }


    public Entrenador findByEmail(String email) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenador e WHERE e.email = :email";
        Entrenador e = sesion.createQuery(hql, Entrenador.class)
                             .setParameter("email", email)
                             .setMaxResults(1)
                             .uniqueResult();
        sesion.close();
        return e;
    }

 
    public String getNombrePorId(Long id) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT e.nombre FROM Entrenador e WHERE e.id = :id";
        String nombre = sesion.createQuery(hql, String.class)
                              .setParameter("id", id)
                              .getSingleResult();
        sesion.close();
        return nombre;
    }

    public Object[] getNombreYEmail(Long id) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT e.nombre, e.email FROM Entrenador e WHERE e.id = :id";
        Object[] datos = sesion.createQuery(hql, Object[].class)
                               .setParameter("id", id)
                               .getSingleResult();
        sesion.close();
        return datos;
    }

  
    public List<Entrenador> getEntrenadoresPorLicencia(String licencia) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenador e WHERE e.licencia = :licencia";
        List<Entrenador> lista = sesion.createQuery(hql, Entrenador.class)
                                       .setParameter("licencia", licencia)
                                       .getResultList();
        sesion.close();
        return lista;
    }

 
    public Long contarEntrenadores() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Long total = sesion.createQuery(
                "SELECT COUNT(e) FROM Entrenador e", Long.class
        ).getSingleResult();
        sesion.close();
        return total;
    }

    public Double mediaTelefono() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Double media = sesion.createQuery(
                "SELECT AVG(e.telefono) FROM Entrenador e", Double.class
        ).getSingleResult();
        sesion.close();
        return media;
    }


    public List<Entrenador> getEntrenadoresOrdenadosPorNombre() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenador e WHERE e.licencia IS NOT NULL ORDER BY e.nombre ASC";
        List<Entrenador> lista = sesion.createQuery(hql, Entrenador.class)
                                       .getResultList();
        sesion.close();
        return lista;
    }

   
    public void actualizarTelefono(Long id, String nuevoTelefono) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        sesion.beginTransaction();

        CriteriaBuilder cb = sesion.getCriteriaBuilder();
        CriteriaUpdate<Entrenador> update = cb.createCriteriaUpdate(Entrenador.class);
        Root<Entrenador> root = update.from(Entrenador.class);

        update.set("telefono", nuevoTelefono)
              .where(cb.equal(root.get("id"), id));

        sesion.createQuery(update).executeUpdate();
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarPorEmail(String email) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        sesion.beginTransaction();

        CriteriaBuilder cb = sesion.getCriteriaBuilder();
        CriteriaDelete<Entrenador> delete = cb.createCriteriaDelete(Entrenador.class);
        Root<Entrenador> root = delete.from(Entrenador.class);

        delete.where(cb.equal(root.get("email"), email));

        sesion.createQuery(delete).executeUpdate();
        sesion.getTransaction().commit();
        sesion.close();
    }
}
