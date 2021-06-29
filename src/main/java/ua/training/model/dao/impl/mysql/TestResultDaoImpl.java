package ua.training.model.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import ua.training.model.dao.TestResultDao;
import ua.training.model.dao.mappers.TestResultMapper;
import ua.training.model.entity.TestResult;

import static ua.training.constants.SqlConstants.SELECT_ALL_TEST_RESULTS;
import static ua.training.constants.SqlConstants.SELECT_TEST_RESULT_BY_ID;
import static ua.training.constants.SqlConstants.SELECT_TEST_RESULT_BY_USER_ID;
import static ua.training.constants.SqlConstants.INSERT_NEW_TEST_RESULT;
import static ua.training.constants.SqlConstants.UPDATE_TEST_RESULT_BY_ID;
import static ua.training.constants.SqlConstants.DELETE_TEST_RESULT_BY_ID;

public class TestResultDaoImpl implements TestResultDao {
	private static final Logger logger = LogManager.getLogger(TestResultDaoImpl.class);
	private Connection connection;

    public TestResultDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean create(TestResult testResult) {
    	try (PreparedStatement st = 
    			connection.prepareStatement(INSERT_NEW_TEST_RESULT,
    					Statement.RETURN_GENERATED_KEYS)) {
    		st.setInt(1, testResult.getUserId());
    		st.setInt(2, testResult.getTestId());
    		st.setTime(3, Time.valueOf(testResult.getCompletionTime()));
    		st.setTimestamp(4, Timestamp.valueOf(testResult.getCompletionDate()));    		
    		st.setDouble(5, testResult.getResult());
    		st.execute();
    		
    		try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                	testResult.setId(generatedKeys.getInt(1));  
                	return true;
                }
                else {
                    throw new SQLException("Creating test result failed, no ID obtained.");
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
    public List<TestResult> findByUserId(int id) {
    	Map<Integer, TestResult> testResults = new LinkedHashMap<>();
    	try (PreparedStatement st = connection.prepareStatement(SELECT_TEST_RESULT_BY_USER_ID)) {
    		st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            TestResultMapper testResultMapper = new TestResultMapper();

            while (rs.next()) {
            	TestResult testResult = testResultMapper.extractFromResultSet(rs);
            	testResult = testResultMapper.makeUnique(testResults, testResult);
            }
            return new ArrayList<>(testResults.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
    }
    
    @Override
    public Optional<TestResult> findById(int id) {
    	TestResult testResult = null;
    	try (PreparedStatement st = connection.prepareStatement(SELECT_TEST_RESULT_BY_ID)) {
    		st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            TestResultMapper testResultMapper = new TestResultMapper();

            while (rs.next()) {
            	testResult = testResultMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
        return Optional.ofNullable(testResult);
    }

    @Override
    public List<TestResult> findAll() {
        Map<Integer, TestResult> testResults = new HashMap<>();
        
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_TEST_RESULTS);

            TestResultMapper testResultMapper = new TestResultMapper();

            while (rs.next()) {
            	TestResult testResult = testResultMapper.extractFromResultSet(rs);
            	testResult = testResultMapper.makeUnique(testResults, testResult);
            }
            return new ArrayList<>(testResults.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
    }

    @Override
    public Boolean update(TestResult testResult) {
    	try (PreparedStatement st = connection.prepareStatement(UPDATE_TEST_RESULT_BY_ID)) {
    		st.setInt(1, testResult.getUserId());
    		st.setInt(2, testResult.getTestId());
    		st.setTime(3, Time.valueOf(testResult.getCompletionTime()));
    		st.setTimestamp(4, Timestamp.valueOf(testResult.getCompletionDate()));    		
    		st.setDouble(5, testResult.getResult());
    		st.setInt(6, testResult.getId());
    		st.execute();
    		return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_TEST_RESULT_BY_ID)) {
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
}
