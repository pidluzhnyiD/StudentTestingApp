package ua.training.service;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ua.training.model.dao.impl.mysql.TestDaoImpl;
import ua.training.model.entity.Question;
import ua.training.model.entity.TestDifficulty;

public class TestServiceImpl {
	 @Mock
	 private TestDaoImpl testDaoImpl;
	 @InjectMocks	 
	 private TestServiceImpl testServiceImpl;
	 
	 private ua.training.model.entity.Test test = 
			 new ua.training.model.entity.Test(0, 0, "engName","rusName", 0, 
					 TestDifficulty.EASY, TestDifficulty.ЛЕГКИЙ, 0, new ArrayList<Question>());
	  
	 @Test
	 public void shouldChangeName() {
		 test.setEnglishName("newEnglishName");
		 test.setRussianName("newRussianName");
		 Assert.assertNotEquals("newRussianName", test.getEnglishName());
		 Assert.assertEquals("newEnglishName", test.getEnglishName());
	 }
	 
	 @Test
	 public void shouldChangeDifficulty() {
		 test.setEnglishDifficulty(TestDifficulty.HARD);
		 test.setRussianDifficulty(TestDifficulty.ТЯЖЁЛЫЙ);
		 Assert.assertNotEquals("newRussianName", test.getEnglishDifficulty());
		 Assert.assertEquals(TestDifficulty.ТЯЖЁЛЫЙ, test.getRussianDifficulty());
	 }
}
