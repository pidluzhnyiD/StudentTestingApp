package ua.training.model.dao;

import java.util.List;
import java.util.Optional;

import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User>{
	Optional <User> findByLogin(String login);
	Boolean delete(String login);
	List<User> findAllStudents();
}
