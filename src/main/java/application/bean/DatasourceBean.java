package application.bean;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import org.postgresql.osgi.PGDataSourceFactory;

import application.utils.EnvUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class DatasourceBean {
	
	private final EnvUtils envUtils;
	
	@Default
	@Produces
	@ApplicationScoped
	public DataSource getDataSource() throws SQLException {
		Properties props = new Properties();
		
		props.put("url", envUtils.getEnv(EnvUtils.DB_URL));
		props.put("user", envUtils.getEnv(EnvUtils.DB_USER));
		props.put("password", envUtils.getEnv(EnvUtils.DB_PASSWORD));
		
		return new PGDataSourceFactory().createDataSource(props);
	}

}
