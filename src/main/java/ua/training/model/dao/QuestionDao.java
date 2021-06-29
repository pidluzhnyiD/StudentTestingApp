package ua.training.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ua.training.model.entity.Question;

public interface QuestionDao extends GenericDao<Question>{
	Optional <Question> findById(int id, String language);
	List<Question> findByTestId(int id);
	List<Question> findByTestId(int id, String language);
	Boolean create(Question entity, int testId, String language) throws SQLException;
	Boolean create(Question entity, int testId) throws SQLException;
	Boolean update(Question question, String language) throws SQLException;
}
