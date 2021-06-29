package ua.training.model.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.QuestionDao;
import ua.training.model.entity.Question;
import ua.training.model.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	DaoFactory daoFactory = DaoFactory.getInstance();
	QuestionDao dao = daoFactory.createQuestionDao();
	
	public List<Question> getQuestionsByTestId(int id, String language){
		try(QuestionDao dao = daoFactory.createQuestionDao();){
			return dao.findByTestId(id, language);
		}		
	}
	 
	public Boolean createQuestion(int testId, String description, List<String>options, List<Boolean>answers) throws SQLException{			
		Question question = new Question(0, testId, description, answers, options);
		try(QuestionDao dao = daoFactory.createQuestionDao();){
			return dao.create(question, testId);
		}		
	}
}
