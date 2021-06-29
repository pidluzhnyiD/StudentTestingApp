package ua.training.model.service;

import java.sql.SQLException;
import java.util.List;

import ua.training.model.entity.Question;

public interface QuestionService {
	public List<Question> getQuestionsByTestId(int id, String language);
	
	public Boolean createQuestion(int testId, String description, List<String>options, List<Boolean>answers) throws SQLException;
}
