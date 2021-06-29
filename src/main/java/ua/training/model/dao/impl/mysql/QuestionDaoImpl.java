package ua.training.model.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import ua.training.model.dao.QuestionDao;
import ua.training.model.dao.mappers.QuestionMapper;
import ua.training.model.entity.Question;

import static ua.training.constants.SqlConstants.SELECT_QUESTION_BY_ID_ENGLISH;
import static ua.training.constants.SqlConstants.SELECT_QUESTION_BY_ID_RUSSIAN;
import static ua.training.constants.SqlConstants.SELECT_QUESTION_BY_TEST_ID_ENGLISH;
import static ua.training.constants.SqlConstants.SELECT_QUESTION_BY_TEST_ID_RUSSIAN;
import static ua.training.constants.SqlConstants.INSERT_RUSSIAN_QUESTION;
import static ua.training.constants.SqlConstants.INSERT_ENGLISH_QUESTION;
import static ua.training.constants.SqlConstants.INSERT_QUESTION_DETAILS;
import static ua.training.constants.SqlConstants.UPDATE_QUESTION_DETAILS_BY_ID;
import static ua.training.constants.SqlConstants.UPDATE_ENGLISH_QUESTION_BY_ID;
import static ua.training.constants.SqlConstants.UPDATE_RUSSIAN_QUESTION_BY_ID;
import static ua.training.constants.SqlConstants.DELETE_QUESTION_BY_ID;

import static ua.training.constants.Constants.DEFAULT_NUMBER_OF_OPTIONS;
import static ua.training.constants.Constants.DEFAULT_LANGUAGE;

public class QuestionDaoImpl implements QuestionDao{
	private Connection connection;
	private static final Logger logger = LogManager.getLogger(QuestionDaoImpl.class);
	
    public QuestionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean create(Question question, int testId, String language) throws SQLException {
    	connection.setAutoCommit(false);
    	try (PreparedStatement stm1 = connection.prepareStatement(INSERT_QUESTION_DETAILS, 
    			Statement.RETURN_GENERATED_KEYS)) {
    		stm1.setInt(1, testId);
    		for(int i = 0; i<DEFAULT_NUMBER_OF_OPTIONS; i++) {
    			if(i<question.getCorrectAnswers().size()) {
    				stm1.setBoolean(i+2, question.getCorrectAnswers().get(i));
    			}
    			else {
    				stm1.setBoolean(i+2, false);
    			}
    		}
    		stm1.executeUpdate();
    		
    		String languageQuery= language==DEFAULT_LANGUAGE?INSERT_ENGLISH_QUESTION:INSERT_RUSSIAN_QUESTION;
    		
    		try (ResultSet generatedKeys = stm1.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    try (PreparedStatement stm2 = connection.prepareStatement(languageQuery)){
                    	stm2.setInt(1, generatedKeys.getInt(1));
                    	stm2.setString(2, question.getDescription());
                    	for(int i = 0; i<DEFAULT_NUMBER_OF_OPTIONS; i++) {
                    		if(i<question.getOptions().size()) {
                    			stm2.setString(i+3, question.getOptions().get(i));
                			}
                			else {
                				stm2.setString(i+3, null);
                			}
                		}
                    	stm2.execute();
                    	connection.commit();
                    	return true;
                    }
                }
            }
    		catch(SQLException e) {
    			logger.log(Level.ERROR, e);
    	        connection.rollback();        
    	        return false;
    		}		
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            connection.rollback();        
            return false;
        }
    	return false;
    }
    
    
	@Override
    public Boolean update(Question question, String language) throws SQLException {
    	connection.setAutoCommit(false);
    	try (PreparedStatement stm1 = connection.prepareStatement(UPDATE_QUESTION_DETAILS_BY_ID, 
    			Statement.RETURN_GENERATED_KEYS)) {
    		stm1.setInt(1, question.getTestId());
    		for(int i = 0; i<DEFAULT_NUMBER_OF_OPTIONS; i++) {
    			if(i<question.getCorrectAnswers().size()) {
    				stm1.setBoolean(i+2, question.getCorrectAnswers().get(i));
    			}
    			else {
    				stm1.setBoolean(i+2, false);
    			}
    		}
    		stm1.setInt(DEFAULT_NUMBER_OF_OPTIONS+2, question.getId());
    		stm1.executeUpdate();
    		
    		String languageQuery = 
    			language == DEFAULT_LANGUAGE?UPDATE_ENGLISH_QUESTION_BY_ID:UPDATE_RUSSIAN_QUESTION_BY_ID;
    		
    		 try (PreparedStatement stm2 = connection.prepareStatement(languageQuery)){
             	stm2.setInt(1, question.getId());
             	stm2.setString(2, question.getDescription());
             	for(int i = 0; i<DEFAULT_NUMBER_OF_OPTIONS; i++) {
             		if(i<question.getOptions().size()) {
             			stm2.setString(i+3, question.getOptions().get(i));
         			}
         			else {
         				stm2.setString(i+3, null);
         			}
         		}
             	stm2.setInt(DEFAULT_NUMBER_OF_OPTIONS+3, question.getId());
             	stm2.execute();
             	connection.commit();
             	return true;
             }
    		 catch(SQLException e) {
    			 logger.log(Level.ERROR, e);
    			 connection.rollback();
    	         return false;
    		 }
    		
    		
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            connection.rollback();
            return false;
        }
    }

    @Override
    public Optional<Question> findById(int id) {
    	 return findById(id, DEFAULT_LANGUAGE);
    }
    @Override
    public List<Question> findByTestId(int id) {
    	 return findByTestId(id, DEFAULT_LANGUAGE);
    }
    
	@Override
	public Boolean create(Question question, int testId) throws SQLException {
		try {
			return create(question, testId, DEFAULT_LANGUAGE);	
		}
		catch (SQLException e){
			logger.log(Level.ERROR, e);
			return false;
		}
			
	}
	

	@Override
	public Boolean update(Question question) throws SQLException{
		try {
			return update(question, DEFAULT_LANGUAGE);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			return false;
		}				
	}
    
	@Override
	public Optional<Question> findById(int id, String language) {
		Question question = null;
		String query= language==DEFAULT_LANGUAGE?SELECT_QUESTION_BY_ID_ENGLISH:SELECT_QUESTION_BY_ID_RUSSIAN;
		
        try (PreparedStatement st = connection.prepareStatement(query)) {
    		st.setInt(1, id);
    		
    		ResultSet rs = st.executeQuery();

            QuestionMapper questionMapper = new QuestionMapper();

            while (rs.next()) {
                question = questionMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
        }
        return Optional.ofNullable(question);
	}
    
	@Override
    public List<Question> findByTestId(int id, String language) {
		String query= language==DEFAULT_LANGUAGE?SELECT_QUESTION_BY_TEST_ID_ENGLISH:SELECT_QUESTION_BY_TEST_ID_RUSSIAN;
		 Map<Integer, Question> questions = new HashMap<>();
	        
		 try (PreparedStatement st = connection.prepareStatement(query)) {
			 	st.setInt(1, id);
			 	
	            ResultSet rs = st.executeQuery();

	            QuestionMapper questionMapper = new QuestionMapper();

	            while (rs.next()) {
	                Question question = questionMapper.extractFromResultSet(rs);
	                question = questionMapper.makeUnique(questions, question);
	            }
	            return new ArrayList<>(questions.values());
	        }
	        catch (SQLException e) {
	        	logger.log(Level.ERROR, e);
	            return null;
	        }
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Boolean delete(int id) {
    	try (PreparedStatement st = connection.prepareStatement(DELETE_QUESTION_BY_ID)) {
    		st.setInt(1, id);	
            st.execute();
            return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
    }

	@Override
	public Boolean create(Question entity) {
		return false;		
	}
}
