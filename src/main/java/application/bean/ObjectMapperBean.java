package application.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;

public class ObjectMapperBean {

	@Default
	@Produces
	@ApplicationScoped
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
