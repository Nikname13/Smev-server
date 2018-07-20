/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author a.zolotarev
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
     
    static {
        try {
            System.out.println("!getSessionFactory");
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("!Initial SessionFactory creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
     
    public static SessionFactory getSessionFactory() {
        System.out.println("return sessionFactory");
        return sessionFactory;
    }
}
