package ua.training.controller;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.training.controller.command.UserListingCommand;
import ua.training.model.entity.User;
import ua.training.model.service.impl.UserServiceImpl;

public class UserListingCommandTest {
 	@Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private HttpSession sessionMock;
	@Mock
	private UserServiceImpl userServiceImpl;
	
    private UserListingCommand testingInstance = new UserListingCommand();
	@Test
	public void shouldReturnUserPage() {
	    MockitoAnnotations.initMocks(this);
	    Mockito.when(userServiceImpl.getAllStudents()).thenReturn(new ArrayList<User>());
	    Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
	    Mockito.doNothing().when(sessionMock).setAttribute(null, new ArrayList<User>());
	    String page = testingInstance.execute(requestMock, responseMock);
	    Assert.assertEquals("redirect:account/admin/userslist.jsp", page);
	}
}
