package Repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Utils.HibernateUtil;
import Utils.IDao;
import jakarta.persistence.TypedQuery;

public abstract class AbstractDao<T> implements IDao<T> {

    private Class<T> clase;

    public AbstractDao(Class<T> clase) {
        this.clase = clase;
    }

    @Override
    public void create(T t) {
        executeInsideTransaction(t);
    }

    @Override
    public T get(int id) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        T entity = sesion.find(clase, id);
        sesion.close();
        return entity;
    }

    @Override
    public List<T> getAll() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM " + clase.getName();
        TypedQuery<T> query = sesion.createQuery(hql, clase);
        List<T> resultados = query.getResultList();
        sesion.close();
        return resultados;
    }

    @Override
    public void update(T t) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        executeInsideTransaction(sesion, sesion.merge(t));
    }

    @Override
    public void delete(T t) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.remove(t);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            sesion.close();
        }
    }

    private void executeInsideTransaction(T t) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        executeInsideTransaction(sesion, t);
    }

    private void executeInsideTransaction(Session sesion, T t) {
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.persist(t);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            sesion.close();
        }
    }
    
}
