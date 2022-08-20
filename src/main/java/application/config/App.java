package application.config;

import org.flywaydb.core.Flyway;

import jakarta.inject.Inject;

public class App {
	
	private final Flyway flyway;
	
	@Inject
	public App(Flyway flyway) {
		this.flyway = flyway;
	}
	
	public void run() {
		System.out.println("Hello World");
		flyway.migrate();
	}
	
}
