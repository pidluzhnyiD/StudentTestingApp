package ua.training.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ua.training.model.dao.impl.mysql.SubjectDaoImpl;

import ua.training.model.entity.Subject;
import ua.training.model.service.impl.SubjectServiceImpl;

public class SubjectServiceImplTest {
	 @Mock
	 private SubjectDaoImpl subjectDaoImpl;
	 @InjectMocks	 
	 private SubjectServiceImpl subjectServiceImpl;
	 
	 private List<ua.training.model.entity.Test> tests = new ArrayList<>();
	 private Subject subject = new Subject(0,"engName","rusName", tests);
	  
	 @Test
	 public void shouldChangeName() {
		 subject.setEnglishName("newEnglishName");
		 subject.setRussianName("newRussianName");
		 Assert.assertNotEquals("newRussianName", subject.getEnglishName());
		 Assert.assertEquals("newEnglishName", subject.getEnglishName());
	 }
	 
	 @Test
	 public void shouldReturnListOfSubjects() {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(subjectDaoImpl.findAll()).thenReturn(new ArrayList<Subject>());
		 List<Subject>subjects = subjectServiceImpl.getAllSubjects();
		 Assert.assertTrue(!subjects.isEmpty());
	 }
	 
	 @Test
	 public void shouldReturnTestCount() {
		 int testCount = subject.getTestCount();
		 Assert.assertEquals(testCount, 0);
	 }
	 
}
