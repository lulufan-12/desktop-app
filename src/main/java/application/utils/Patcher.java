package application.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.POJONode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
//import jakarta.inject.Inject;
//import lombok.RequiredArgsConstructor;

@Default
@ApplicationScoped
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Patcher {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public <E, T> T map(E bean, Class<T> target) {
		return mapper.convertValue(bean, target);
	}
	
	public <E, T> List<T> map(Collection<E> collection, Class<T> target) {
		return collection.stream()
				.map(bean -> mapper.convertValue(bean, target))
				.collect(Collectors.toList());
	}
	
	public <E, T> T patch(E bean, String json, Class<T> target) throws JsonPatchException, JsonMappingException, JsonProcessingException {
		JsonMergePatch patch = mapper.readValue(json, JsonMergePatch.class);
		 
		POJONode node = mapper.convertValue(bean, POJONode.class);
		
		JsonNode patched = patch.apply(node);
		
		return map(patched, target);
	}
	
	public <E, T> T patch(E bean, Map<String, Object> data, Class<T> target) throws JsonPatchException, JsonMappingException, JsonProcessingException {
		String json = mapper.convertValue(data, String.class);
		return patch(bean, json, target);
	}
	
	public <E, T> T convertTo(E bean, Class<T> target) {
		return mapper.convertValue(bean, target);
	}
}
