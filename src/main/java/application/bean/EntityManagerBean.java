package application.bean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerBean {
	
	@Default
	@Produces
	@ApplicationScoped
	public EntityManager entityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
		return factory.createEntityManager();
	}

}
