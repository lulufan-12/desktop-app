package application.bean;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class DatasourceBeanTest {
	
	@Test
	public void selectDatasourceSuccessfully() {
		SeContainer container = SeContainerInitializer.newInstance().initialize();
		DataSource datasource = container.select(DataSource.class).get();
		
		Assertions.assertNotNull(datasource);
		container.close();
	}

}
