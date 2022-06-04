package com.algaworks.financeiro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.context.RequestContext;
public class JpaUtil {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("FinanceiroPU");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	


}