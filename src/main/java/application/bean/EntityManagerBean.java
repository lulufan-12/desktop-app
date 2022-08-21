package application.bean;

import application.utils.EnvUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class EntityManagerBean {

	private final EnvUtils envUtils;
	
	@Default
	@Produces
	@ApplicationScoped
	public EntityManager entityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(envUtils.getEnv(EnvUtils.PERSISTENCE_UNIT));
		return factory.createEntityManager();
	}

}
