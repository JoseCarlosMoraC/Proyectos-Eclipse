package Repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;

import Models.Partido;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;


public class PartidoDao extends AbstractDao<Partido> {

    public PartidoDao() {
        super(Partido.class);
    }


    public List<Partido> findByRival(String rival) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Partido p WHERE p.rival = :rival";
        TypedQuery<Partido> query = sesion.createQuery(hql, Partido.class);
        query.setParameter("rival", rival);
        List<Partido> partidos = query.getResultList();
        sesion.close();
        return partidos;
    }

  
    public List<Partido> getPartidosOrdenadosPorFecha() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Partido p ORDER BY p.fecha DESC";
        TypedQuery<Partido> query = sesion.createQuery(hql, Partido.class);
        List<Partido> partidos = query.getResultList();
        sesion.close();
        return partidos;
    }


    public Long contarPartidos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "SELECT COUNT(p) FROM Partido p";
        TypedQuery<Long> query = sesion.createQuery(hql, Long.class);
        Long total = query.getSingleResult();
        sesion.close();
        return total;
    }

 
    public List<Partido> getPartidosFuturos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Partido p WHERE p.fecha > :ahora ORDER BY p.fecha ASC";
        TypedQuery<Partido> query = sesion.createQuery(hql, Partido.class);
        query.setParameter("ahora", LocalDateTime.now());
        List<Partido> partidos = query.getResultList();
        sesion.close();
        return partidos;
    }
}