package application.config;

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;

import jakarta.inject.Inject;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class App {
	
	private final Flyway flyway;
	
	public void run() {
		System.out.println("Application initialization...");
		flyway.migrate();
		System.out.println("Application shutdown...");
	}
	
}
