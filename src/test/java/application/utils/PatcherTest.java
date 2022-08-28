package application.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.fge.jsonpatch.JsonPatchException;

import application.bean.ObjectMapperBean;
import application.model.User;

@TestInstance(Lifecycle.PER_CLASS)
public class PatcherTest {
	
	private Patcher patcher;
	
	@BeforeAll
	public void init() {
		ObjectMapperBean omb = new ObjectMapperBean();
		patcher = new Patcher(omb);
	}
	
	@Test
	public void mapModelSuccesfully() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1L);
		
		User user = patcher.map(map, User.class);
		Assertions.assertEquals(1L, user.getId());
	}
	
	@Test
	public void mapListSuccesfully() {
		Map<String, Long> map = new HashMap<>();
		map.put("id", 1L);
		
		Map<String, Long> map2 = new HashMap<>();
		map2.put("id", 2L);
		
		List<Map<String, Long>> list = new ArrayList<>();
		list.add(map);
		list.add(map2);
		
		List<User> users = patcher.map(list, User.class);
		
		Assertions.assertEquals(1L, users.get(0).getId());
		Assertions.assertEquals(2L, users.get(1).getId());
	}
	
	@Test
	public void patchJsonSuccessfully() throws JsonMappingException, JsonProcessingException, JsonPatchException {
		Map<String, Object> model = new HashMap<>();
		model.put("id", 1L);
		model.put("name", "User Test");
		
		String json = "{\"id\":2}";
		
		User user = patcher.patch(model, json, User.class);
		
		Assertions.assertEquals("User Test", user.getName());
		Assertions.assertEquals(2L, user.getId());
	}
	
	@Test
	public void patchFailed() throws JsonMappingException, JsonProcessingException, JsonPatchException {
		Map<String, Object> model = new HashMap<>();
		model.put("test", 1L);
		
		String json = "{\"hi\":2}";
		
		Assertions.assertThrows(Exception.class, () -> {
			patcher.patch(model, json, User.class);
		});
	}

}
