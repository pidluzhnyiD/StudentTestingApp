package ua.training.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.training.model.dao.impl.mysql.UserDaoImpl;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.impl.UserServiceImpl;

public class UserServiceImplTest {
	 @Mock
	 private UserDaoImpl userDaoImpl;
	 @InjectMocks	 
	 private UserServiceImpl userServiceImpl;
	 
	 private User user = new User(0,"John","Login","Password",Role.ADMIN);
	  
	 @Test
	 public void shouldChangeUserRole() {
		 user.setRole(Role.BLOCKED);
		 Assert.assertNotEquals(user.getRole().name(), Role.ADMIN.name());
		 Assert.assertEquals(user.getRole().name(), Role.BLOCKED.name());
	 }
	 
	 @Test
	 public void shouldReturnListOfStudents() {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(userDaoImpl.findAllStudents()).thenReturn(new ArrayList<User>());
		 List<User>users = userServiceImpl.getAllStudents();
		 Assert.assertTrue(!users.isEmpty());
	 }
	 
	 @Test
	 public void shouldReturnListOfUsers() {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(userDaoImpl.findAll()).thenReturn(new ArrayList<User>());
		 List<User>users = userServiceImpl.getAllUsers();
		 Assert.assertTrue(!users.isEmpty());
	 }
	 
	 @Test
	 public void shouldNotCreateNewUser() {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(userDaoImpl.create(user)).thenReturn(false);
		 Boolean newUserCreated = userServiceImpl.addNewUser(user);
		 Assert.assertFalse(newUserCreated);
	 }
	 
}
