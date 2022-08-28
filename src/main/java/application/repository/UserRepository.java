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
import java.util.function.Function;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class UserRepository implements Repository<User, Long> {

  private final EntityManager em;

  @Override
  public Optional<User> findById(Long id) {
    return Optional.ofNullable(em.find(User.class, id));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> findAll() {
    Query query = em.createQuery("SELECT e FROM User e");
    return (List<User>) query.getResultList();
  }

  @Override
  public Long save(User user) {
    Function<EntityManager, Long> function = em -> {
      em.persist(user);
      return user.getId();
    };
    return executeInsideTransactionAndReturnId(function);
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
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      action.accept(em);
      transaction.commit();
    }
    catch (RuntimeException e) {
      transaction.rollback();
      throw e;
    }
  }

  private Long executeInsideTransactionAndReturnId(Function<EntityManager, Long> action) {
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      Long id = action.apply(em);
      transaction.commit();
      return id;
    }
    catch (RuntimeException e) {
      transaction.rollback();
      throw e;
    }
  }
}
