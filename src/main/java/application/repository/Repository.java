package application.repository;

import application.model.DefaultModel;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends DefaultModel, K> {

    Optional<T> findById(K id);

    List<T> findAll();

    Long save(T t);

    void update(T t);

    void delete(T t);

}
