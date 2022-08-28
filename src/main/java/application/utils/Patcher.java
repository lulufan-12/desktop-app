package application.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

import application.bean.ObjectMapperBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Default
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Patcher {
	
	private final ObjectMapperBean objectMapperBean;
	
	public <E, T> T map(E bean, Class<T> target) {
		return objectMapperBean.objectMapper().convertValue(bean, target);
	}
	
	public <E, T> List<T> map(Collection<E> collection, Class<T> target) {
		return collection.stream()
				.map(bean -> objectMapperBean.objectMapper().convertValue(bean, target))
				.collect(Collectors.toList());
	}
	
	public <E, T> T patch(E bean, String json, Class<T> target) throws JsonPatchException, JsonMappingException, JsonProcessingException {
		JsonMergePatch patch = objectMapperBean.objectMapper().readValue(json, JsonMergePatch.class);
		 
		JsonNode node = objectMapperBean.objectMapper().convertValue(bean, JsonNode.class);
		
		JsonNode patched = patch.apply(node);
		
		return map(patched, target);
	}
	
}
