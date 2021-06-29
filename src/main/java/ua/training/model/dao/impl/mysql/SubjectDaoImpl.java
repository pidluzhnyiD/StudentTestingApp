package ua.training.model.dao.impl.mysql;

import static ua.training.constants.SqlConstants.SELECT_ALL_SUBJECTS;
import static ua.training.constants.SqlConstants.SELECT_SUBJECT_BY_ID;
import static ua.training.constants.SqlConstants.INSERT_NEW_SUBJECT;
import static ua.training.constants.SqlConstants.UPDATE_SUBJECT_BY_ID;
import static ua.training.constants.SqlConstants.DELETE_SUBJECT_BY_ID;

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

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.mappers.SubjectMapper;
import ua.training.model.entity.Subject;

public class SubjectDaoImpl implements SubjectDao{
	private static final Logger logger = LogManager.getLogger(SubjectDaoImpl.class);
	private Connection connection;

    public SubjectDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
	@Override
	public Boolean create(Subject subject) {
		try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_SUBJECT,
				Statement.RETURN_GENERATED_KEYS)) {
    		st.setString(1, subject.getEnglishName());
    		st.setString(2, subject.getRussianName());
    		st.execute();
    		
    		try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));    
                    return true;
                }
                else {
                    throw new SQLException("Creating subject failed, no ID obtained.");
                }
            }
    		catch(SQLException e) {
    			logger.log(Level.ERROR, e);
    			return false;
    		}
    		
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
	}

	@Override
	public Boolean update(Subject subject) {
		try (PreparedStatement st = connection.prepareStatement(UPDATE_SUBJECT_BY_ID)) {
    		st.setString(1, subject.getEnglishName());
    		st.setString(2, subject.getRussianName());
    		st.setInt(3, subject.getId());
    		st.execute();
    		return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
	}

	@Override
	public Optional<Subject> findById(int id) {
		Subject subject = null;
    	try (PreparedStatement st = connection.prepareStatement(SELECT_SUBJECT_BY_ID)) {
    		st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            SubjectMapper subjectMapper  = new  SubjectMapper();

            while (rs.next()) {
            	subject = subjectMapper .extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
        return Optional.ofNullable(subject);
	}

	@Override
	public List<Subject> findAll() {
		Map<Integer, Subject> subjects = new HashMap<>();
		try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_SUBJECTS);

            SubjectMapper subjectMapper  = new  SubjectMapper();

            while (rs.next()) {
            	Subject subject = subjectMapper.extractFromResultSet(rs);
            	subject = subjectMapper.makeUnique(subjects, subject);
            }
            return new ArrayList<>(subjects.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
	}

	@Override
	public Boolean  delete(int id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_SUBJECT_BY_ID)) {
    		st.setInt(1, id);	
            st.execute();
            return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
	}


	@Override
	public void close() {
		try {
            connection.close();
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }		   
	}
}
