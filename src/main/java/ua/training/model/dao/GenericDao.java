package ua.training.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Boolean create (T entity);
    Boolean update (T entity) throws SQLException;
    Optional <T> findById(int id);
    List<T> findAll();
    Boolean delete(int id);
    void close();
}