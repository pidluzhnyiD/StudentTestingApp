package ua.training.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.training.model.dao.impl.mysql.QuestionDaoImpl;
import ua.training.model.entity.Question;
import ua.training.model.service.impl.QuestionServiceImpl;

public class QuestionServiceImplTest {
	 @Mock
	 private QuestionDaoImpl questionDaoImpl;
	 @InjectMocks	 
	 private QuestionServiceImpl questionServiceImpl;
	 
	 private List<String>options = new ArrayList<String>();
	 private List<Boolean>answers = new ArrayList<Boolean>();
	 private Question question = new Question(0,0,"name", answers, options);
	  
	 @Test
	 public void shouldChangeName() {
		 question.setDescription("newName");
		 Assert.assertEquals("newName", question.getDescription());
	 }
	 
	 @Test
	 public void shouldReturnAnswers() {
		 Assert.assertEquals(new ArrayList<Boolean>(), question.getCorrectAnswers());
	 }
	 
	 @Test
	 public void shouldReturnOptions() {
		 Assert.assertEquals(new ArrayList<String>(), question.getOptions());
	 }
	 
	 @Test
	 public void shouldGetQuestionByTestID() {
		 MockitoAnnotations.initMocks(this);
		 List<Question> questions = questionServiceImpl.getQuestionsByTestId(0,"en");
		 Assert.assertFalse(!questions.isEmpty());
	 }
}
