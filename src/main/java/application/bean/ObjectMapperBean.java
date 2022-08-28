package application.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ObjectMapperBean {

	@Default
	@Produces
	@ApplicationScoped
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
