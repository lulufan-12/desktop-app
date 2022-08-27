package application.bean;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class EntityManagerBeanTest {

	@Test
	public void selectEntityManagerSuccessfully() {
		SeContainer container = SeContainerInitializer.newInstance().initialize();
		EntityManager entityManager = container.select(EntityManager.class).get();
		
		Assertions.assertNotNull(entityManager);
		container.close();
	}
}
