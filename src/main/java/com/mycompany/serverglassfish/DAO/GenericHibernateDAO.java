/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.hibernate.Session;

/**
 *
 * @author a.zolotarev
 */
public class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;
    private Session session;

    public GenericHibernateDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected Session getSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public void closeSession() {
        getSession().close();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public String create(T entity) {
        try {
            Session ses = getSession();
            ses.beginTransaction();
            ses.save(entity);
            ses.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("!!!Exception create " + ex);
            return ExceptionConverter.getSpecialty(ex);
        }
        return "";
    }

    @Override
    public T get(ID id)  {
        return getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> cr = builder.createQuery(getPersistentClass());
        Root<T> pRoot = cr.from(getPersistentClass());
        cr.select(pRoot);
        cr.orderBy(builder.asc(pRoot.get("id")));
       
        List<T> p = getSession().createQuery(cr).getResultList();
        return p;
    }

    @Override
    public String update(T entity) {
        try {
            Session ses = getSession();
            ses.beginTransaction();
            ses.update(entity);
            ses.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("!!!Exception update " + ex);
            return ExceptionConverter.getSpecialty(ex);
        }
        return "";
    }

    @Override
    public String delete(ID id) {
        try (Session ses = getSession()) {
            ses.beginTransaction();
            getSession().delete(getSession().get(getPersistentClass(), id));
            System.out.println(id);
            ses.getTransaction().commit();
        } catch (Exception ex) {
            return ExceptionConverter.getSpecialty(ex);
        }
        return "";
    }

    @Override
    public String delete(Set<Integer> entity) {
        try (Session ses = getSession()) {
            ses.beginTransaction();
            for (Integer id : entity) {
                getSession().delete(getSession().get(getPersistentClass(), id));
                System.out.println(id);
            }
            ses.getTransaction().commit();
        } catch (Exception ex) {
            return ExceptionConverter.getSpecialty(ex);
        }
        return "";
    }

    public List<T> getList(GenericType criterion, SingularAttribute searchField_, String joinField) {
        System.out.println("get DownLoadList ");
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> cr = builder.createQuery(getPersistentClass());
        Root<T> pRoot = cr.from(getPersistentClass());
        
        cr.select(pRoot);
        Join<T, Object> joi = pRoot.join(joinField);
        cr.where(builder.equal(joi.get(searchField_), criterion.getValue()));
        cr.orderBy(builder.asc(pRoot.get("id")));
        List<T> list = getSession().createQuery(cr).getResultList();
        System.out.println("Files list");
        return list;
    }
}
