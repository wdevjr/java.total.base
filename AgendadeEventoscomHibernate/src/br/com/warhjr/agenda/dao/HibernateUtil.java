package br.com.warhjr.agenda.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class HibernateUtil {
	public static void main(String[] args) {
		System.out.println("Inicio");

		Configuration cfg = new Configuration();
		cfg.configure();
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);

		System.out.println("Fim");
	}

	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;

	static {
		try {
			sessionFactory = getSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactory.openSession();
			return sessionFactory;
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
