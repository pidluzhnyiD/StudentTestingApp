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

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mappers.UserMapper;
import ua.training.model.entity.User;

import static ua.training.constants.SqlConstants.SELECT_ALL_USERS;
import static ua.training.constants.SqlConstants.SELECT_ALL_STUDENTS;
import static ua.training.constants.SqlConstants.SELECT_USER_BY_ID;
import static ua.training.constants.SqlConstants.SELECT_USER_BY_LOGIN;
import static ua.training.constants.SqlConstants.DELETE_USER_BY_ID;
import static ua.training.constants.SqlConstants.DELETE_USER_BY_LOGIN;
import static ua.training.constants.SqlConstants.INSERT_USER_INTO_USERS;
import static ua.training.constants.SqlConstants.UPDATE_USER_BY_ID;

public class  UserDaoImpl implements UserDao{
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Boolean create(User user) {
    	try (PreparedStatement st = connection.prepareStatement(INSERT_USER_INTO_USERS,
    			Statement.RETURN_GENERATED_KEYS)) {
    		st.setString(1, user.getName());
    		st.setString(2, user.getLogin());
    		st.setString(3, user.getPassword());
    		st.setString(4, user.getRole().name());
    		st.execute();
    		
    		try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1)); 
                    return true;
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
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
    public Boolean update(User user) {
    	try (PreparedStatement st = connection.prepareStatement(UPDATE_USER_BY_ID)) {
    		st.setString(1, user.getName());
    		st.setString(2, user.getLogin());
    		st.setString(3, user.getPassword());
    		st.setString(4, user.getRole().name());
    		st.setInt(5, user.getId());
    		st.execute();
    		return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return false;
        }
    }

    @Override
    public Optional <User> findById(int id) {
    	User user = null;
    	try (PreparedStatement st = connection.prepareStatement(SELECT_USER_BY_ID)) {
    		st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
        return Optional.ofNullable(user);
    }
    
    @Override
    public Optional <User> findByLogin(String login) {
    	User user = null;
    	try (PreparedStatement st = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
    		st.setString(1, login);
            ResultSet rs = st.executeQuery();

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_USERS);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
    }
    
    @Override
    public List<User> findAllStudents() {
        Map<Integer, User> users = new HashMap<>();
        
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_STUDENTS);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            return null;
        }
    }

    @Override
    public Boolean delete(int id) {
    	try (PreparedStatement st = connection.prepareStatement(DELETE_USER_BY_ID)) {
    		st.setInt(1, id);	
            st.execute();
            return true;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
    		return false;
        }
    }
    
    @Override
    public Boolean delete(String login) {
    	try (PreparedStatement st = connection.prepareStatement(DELETE_USER_BY_LOGIN)) {
    		st.setString(1, login);	
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
