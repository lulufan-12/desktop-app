package application.bean;

import application.utils.EnvUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

	public class EntityManagerBean {

	private final EnvUtils envUtils;
	@Inject
	public EntityManagerBean(EnvUtils envUtils) {
		this.envUtils = envUtils;
	}
	
	@Default
	@Produces
	@ApplicationScoped
	public EntityManager entityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(envUtils.getEnv(EnvUtils.PERSISTENCE_UNIT));
		return factory.createEntityManager();
	}

}
