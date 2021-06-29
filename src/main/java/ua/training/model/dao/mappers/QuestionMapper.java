package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.training.model.entity.Question;

import static ua.training.constants.Constants.*;

public class QuestionMapper implements ObjectMapper<Question>{
	@Override
    public Question extractFromResultSet(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt(ID));
        question.setTestId(rs.getInt(TEST_ID));
        question.setDescription(rs.getString(DESCRIPTION));
        question.setOptions(getOptions(rs, DEFAULT_NUMBER_OF_OPTIONS));
        question.setCorrectAnswers(getAnswers(rs, DEFAULT_NUMBER_OF_OPTIONS));
        return question;
    }
	
	private List<String>getOptions(ResultSet rs, int N) throws SQLException{
		ArrayList<String>options = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			options.add(rs.getString(OPTION_+i));
		}
		return options;
	}
	
	private List<Boolean>getAnswers(ResultSet rs, int N) throws SQLException{
		ArrayList<Boolean>answers = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			answers.add(rs.getBoolean(ANSWER_+i));
		}
		return answers;
	}

    @Override
    public Question makeUnique(Map<Integer, Question> cache, Question question) {
        cache.putIfAbsent(question.getId(), question);
        return cache.get(question.getId());
    }
}
