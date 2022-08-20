package application.bean;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.osgi.PGDataSourceFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;

public class DatasourceBean {
	
	@Default
	@Produces
	@ApplicationScoped
	public DataSource getDataSource() throws SQLException {
		Properties props = new Properties();
		
		props.put("url", "jdbc:postgresql://localhost:5432/application");
		props.put("user", "postgres");
		props.put("password", "54325432");
		
		return new PGDataSourceFactory().createDataSource(props);
	}

}
