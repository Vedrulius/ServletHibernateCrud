package com.mihey.servletproject.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private final static SessionFactory sessionFactory = setSession();

    public static SessionFactory setSession() {
        Metadata metadata = null;
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        return metadata.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        try {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
