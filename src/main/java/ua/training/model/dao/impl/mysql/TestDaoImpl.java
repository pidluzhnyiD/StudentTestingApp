package ua.training.model.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import ua.training.model.dao.TestDao;
import ua.training.model.dao.mappers.TestMapper;
import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;

import static ua.training.constants.SqlConstants.SELECT_ALL_TESTS;
import static ua.training.constants.SqlConstants.SELECT_N_TESTS_BY_SUBJECT;
import static ua.training.constants.SqlConstants.SELECT_TESTS_BY_SUBJECT;
import static ua.training.constants.SqlConstants.SELECT_TEST_BY_ID;
import static ua.training.constants.SqlConstants.INSERT_NEW_TEST;
import static ua.training.constants.SqlConstants.UPDATE_TEST_BY_ID;
import static ua.training.constants.SqlConstants.DELETE_TEST_BY_ID;
import static ua.training.constants.SqlConstants.COUNT_ALL_TESTS;
import static ua.training.constants.SqlConstants.COUNT_TESTS_BY_SUBJECT_ID;

public class TestDaoImpl  implements TestDao{
	private static final Logger logger = LogManager.getLogger(TestDaoImpl.class);
	private Connection connection;

    public TestDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
	@Override
	public Boolean create(Test test) {
		try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_TEST, 
				Statement.RETURN_GENERATED_KEYS)) {
			st.setInt(1, test.getSubjectId());
    		st.setString(2, test.getEnglishName());
    		st.setString(3, test.getRussianName());
    		st.setInt(4, test.getTestDuration());
    		st.setString(5, test.getEnglishDifficulty().name());
    		st.setString(6, test.getRussianDifficulty().name());
    		st.setInt(7, test.getNumberOfRequests());
    		st.execute();
    		
    		try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));     
                    return true;
                }
                else {
                    throw new SQLException("Creating test failed, no ID obtained.");
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
	public Boolean update(Test test) throws SQLException{
		try (PreparedStatement st = connection.prepareStatement(UPDATE_TEST_BY_ID)) {
    		st.setInt(1, test.getSubjectId());
    		st.setString(2, test.getEnglishName());
    		st.setString(3, test.getRussianName());
    		st.setInt(4, test.getTestDuration());
    		st.setString(5, test.getEnglishDifficulty().name());
    		st.setString(6, test.getRussianDifficulty().name());
    		st.setInt(7, test.getNumberOfRequests());
    		st.setInt(8, test.getId());
    		st.execute();
    		return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
	}

	@Override
	public Optional<Test> findById(int id) {
		Test test = null;
    	try (PreparedStatement st = connection.prepareStatement(SELECT_TEST_BY_ID)) {
    		st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            TestMapper testMapper = new TestMapper();

            while (rs.next()) {
                test = testMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
        return Optional.ofNullable(test);
	}

	@Override
	public List<Test> findAll() {
		Map<Integer, Test> tests = new LinkedHashMap<>();
        
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_TESTS);

            TestMapper testMapper = new TestMapper();

            while (rs.next()) {
                Test test = testMapper.extractFromResultSet(rs);
                test = testMapper.makeUnique(tests, test);
            }
            return new ArrayList<>(tests.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
	}

	@Override
	public List<Test> fintNTestsBySubjectId(int subjectId, int offset, int numberOfTests){
		Map<Integer, Test> tests = new LinkedHashMap<>();  
		
		try (PreparedStatement st = connection.prepareStatement(SELECT_N_TESTS_BY_SUBJECT)) {
			st.setInt(1, subjectId);
			st.setInt(2, offset);
			st.setInt(3, numberOfTests);

            ResultSet rs = st.executeQuery();

            TestMapper testMapper = new TestMapper();

            while (rs.next()) {
                Test test = testMapper.extractFromResultSet(rs);
                test = testMapper.makeUnique(tests, test);             
            }
            return new ArrayList<>(tests.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
	}

	@Override
	public List<Test> getNTestsSorted(String sortMethod) {
		System.out.println("Find Page 2");
		Map<Integer, Test> tests = new LinkedHashMap<>();  
		try (PreparedStatement st = connection.prepareStatement(sortMethod)) {

            ResultSet rs = st.executeQuery();

            TestMapper testMapper = new TestMapper();

            while (rs.next()) {
                Test test = testMapper.extractFromResultSet(rs);
                test = testMapper.makeUnique(tests, test);             
            }
            return new ArrayList<>(tests.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
	}	

	@Override
	public Boolean delete(int id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_TEST_BY_ID)) {
    		st.setInt(1, id);	
            st.execute();
            return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
	}

	@Override
	public int countNumberOfTests() {
		try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(COUNT_ALL_TESTS);
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);           
        }
		return -1;
	}
	
	@Override
	public int countTestsBySubjectId(int id) {
		try (PreparedStatement st = connection.prepareStatement(COUNT_TESTS_BY_SUBJECT_ID)) {
			st.setInt(1, id);	
			ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);           
        }
		return -1;
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

	@Override
	public List<Test> findAllTestsBySubject(Subject subject) {
		Map<Integer, Test> tests = new LinkedHashMap<>();
                	
        try (PreparedStatement st = connection.prepareStatement(SELECT_TESTS_BY_SUBJECT)) {
        	st.setInt(1, subject.getId());
            ResultSet rs = st.executeQuery();

            TestMapper testMapper = new TestMapper();

            while (rs.next()) {
                Test test = testMapper.extractFromResultSet(rs);
                test = testMapper.makeUnique(tests, test);
            }
            return new ArrayList<>(tests.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
	}

}
