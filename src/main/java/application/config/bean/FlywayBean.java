package application.config.bean;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class FlywayBean {
	
	private final DataSource dataSource;
	
	@Inject
	public FlywayBean(DatasourceBean dataSourceBean) throws SQLException {
		this.dataSource = dataSourceBean.getDataSource();
	}

	@Default
	@Produces
	@ApplicationScoped
	public Flyway flyway() throws SQLException {
		return Flyway.configure()
				.dataSource(dataSource)
				.load();
	}
	
}
