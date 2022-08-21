package application.repository;

import application.model.User;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class UserRepository implements Repository<User, Long> {

    private final EntityManager em;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT e FROM User e");
        return (List<User>) query.getResultList();
    }

    @Override
    public void save(User user) {
        executeInsideTransaction(em -> em.persist(user));
    }

    @Override
    public void update(User user) {
        executeInsideTransaction(em -> em.merge(user));
    }

    @Override
    public void delete(User user) {
        executeInsideTransaction(em -> em.remove(user));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
