package Repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;

import Models.Entrenamiento;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class EntrenamientoDao extends AbstractDao<Entrenamiento> {

    public EntrenamientoDao() {
        super(Entrenamiento.class);
    }

    public List<Entrenamiento> findByTipo(String tipo) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenamiento e WHERE e.tipo = :tipo";
        TypedQuery<Entrenamiento> query = sesion.createQuery(hql, Entrenamiento.class);
        query.setParameter("tipo", tipo);
        List<Entrenamiento> entrenamientos = query.getResultList();
        sesion.close();
        return entrenamientos;
    }

  
    public List<Entrenamiento> getEntrenamientosOrdenadosPorFecha() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenamiento e ORDER BY e.fecha DESC";
        TypedQuery<Entrenamiento> query = sesion.createQuery(hql, Entrenamiento.class);
        List<Entrenamiento> entrenamientos = query.getResultList();
        sesion.close();
        return entrenamientos;
    }

    public Double getPromedioDuracion() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT AVG(e.duracion) FROM Entrenamiento e WHERE e.duracion IS NOT NULL";
        Double promedio = sesion.createQuery(hql, Double.class).getSingleResult();
        sesion.close();
        return promedio;
    }


    public List<Entrenamiento> getEntrenamientosFuturos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenamiento e WHERE e.fecha > :ahora ORDER BY e.fecha ASC";
        TypedQuery<Entrenamiento> query = sesion.createQuery(hql, Entrenamiento.class);
        query.setParameter("ahora", LocalDateTime.now());
        List<Entrenamiento> entrenamientos = query.getResultList();
        sesion.close();
        return entrenamientos;
    }
}