package br.com.aps.quarto.semestre.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;
	
	public static EntityManager getEntityManager() {
		try {
			
			if (emf==null) {
				emf=Persistence.createEntityManagerFactory("aps");
			}
			
			return emf.createEntityManager();
			
		}catch (Exception e) {
			throw new RuntimeException("Erro ao criar o EntityManager",e);
		}
	}
}
