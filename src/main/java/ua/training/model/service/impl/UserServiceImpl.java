package ua.training.model.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class UserServiceImpl implements UserService{
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	@Override
	 public List<User> getAllUsers(){
		try(UserDao dao = daoFactory.createUserDao()){
			return dao.findAll();
		}		
	 }
	
	@Override
	 public List<User> getAllStudents(){
		try(UserDao dao = daoFactory.createUserDao()){
			return dao.findAllStudents();
		}	
	 }
	 
	@Override
	 public Optional<User> getUserByLogin(String login){
		try(UserDao dao = daoFactory.createUserDao()){
			return dao.findByLogin(login);
		}
	 }
	 
	@Override
	public Boolean checkIfInputDataIsAccurate(String login, String password) {
		Optional <User> user = getUserByLogin(login);
		if(!password.equals(user.get().getPassword())) {
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean addNewUser(User user) {
		try(UserDao dao = daoFactory.createUserDao()){
			return dao.create(user);
		}
	}
	
	@Override
	public Boolean changeUserRights(String login) {
		User user = getUserByLogin(login).get();
		if(("BLOCKED").equals(user.getRole().toString())) {
			user.setRole(Role.STUDENT);
		}
		else {
			user.setRole(Role.BLOCKED);
		}
		try (UserDao dao = daoFactory.createUserDao()){
			return dao.update(user);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			return false;
		}
	}
	
	@Override
	public Boolean deleteUser(String login) {
		try(UserDao dao = daoFactory.createUserDao()){
			return dao.delete(login);
		}
	}
}
