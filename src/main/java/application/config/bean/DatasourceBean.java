package application.config.bean;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.osgi.PGDataSourceFactory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatasourceBean {
	
	public DataSource getDataSource() throws SQLException {
		Properties props = new Properties();
		
		props.put("url", "jdbc:postgresql://localhost:5432/application");
		props.put("user", "postgres");
		props.put("password", "54325432");
		
		return new PGDataSourceFactory().createDataSource(props);
	}

}
