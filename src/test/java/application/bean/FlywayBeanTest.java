package application.bean;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class FlywayBeanTest {

	@Test
	public void selectFlywaySuccessfully() {
		SeContainer container = SeContainerInitializer.newInstance().initialize();
		Flyway flyway = container.select(Flyway.class).get();
		
		Assertions.assertNotNull(flyway);
		container.close();
	}
}
