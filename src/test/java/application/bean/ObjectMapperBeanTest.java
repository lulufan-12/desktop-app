package application.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class ObjectMapperBeanTest {
	
	@Test
	public void selectObjectMapperBeanSuccessfully() {
		SeContainer container = SeContainerInitializer.newInstance().initialize();
		ObjectMapperBean objectMapperBean = container.select(ObjectMapperBean.class).get();
		Assertions.assertNotNull(objectMapperBean);
		
		ObjectMapper mapper = objectMapperBean.objectMapper();
		Assertions.assertNotNull(mapper);
		container.close();
	}
	
}
