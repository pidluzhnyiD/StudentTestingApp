package ua.training.model.service;

import java.util.List;
import java.util.Optional;

import ua.training.model.entity.User;

public interface UserService {
	public List<User> getAllUsers();
	
	public List<User> getAllStudents();
	 
	public Optional<User> getUserByLogin(String login);
	 
	public Boolean checkIfInputDataIsAccurate(String login, String password);
	
	public Boolean addNewUser(User user);

	Boolean changeUserRights(String login);

	Boolean deleteUser(String login);
}
