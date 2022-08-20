package application.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
	
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	private static final DatabaseManager instance = new DatabaseManager();
	
	private DatabaseManager() {
		super();
		entityManagerFactory = Persistence.createEntityManagerFactory("unit");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static DatabaseManager getInstance() {
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
