package application.bean;

import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class FlywayBean {
	
	private final DataSource dataSource;

	@Default
	@Produces
	@ApplicationScoped
	public Flyway flyway() {
		return Flyway.configure()
				.dataSource(dataSource)
				.load();
	}
	
}
