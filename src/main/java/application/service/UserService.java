package application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import application.model.User;
import application.repository.UserRepository;
import application.utils.Patcher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserService {

	private final UserRepository repository;
	private final Patcher patcher;
	
	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
		
		if (optional.isEmpty()) {
			throw new RuntimeException(String.format("User with id {} not found", id));
		}
		
		return optional.get();
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Long save(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hash);
		return repository.save(user);
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
	
	public void update(User user) {
		repository.update(user);
	}
	
	public void patch(Long id, Map<String, Object> data) {
		String json = patcher.map(data, String.class);
		patch(id, json);
	}
	
	public void patch(Long id, String json) {
		User user = findById(id);
		
		try {
			user = patcher.patch(user, json, User.class);	
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(String.format("Failed to update the user with id {}", id));
		}
		
		repository.update(user);
	}
}
