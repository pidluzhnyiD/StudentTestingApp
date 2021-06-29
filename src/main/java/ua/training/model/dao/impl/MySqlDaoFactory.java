package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import ua.training.model.dao.DBManager;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.QuestionDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.TestDao;
import ua.training.model.dao.TestResultDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.mysql.QuestionDaoImpl;
import ua.training.model.dao.impl.mysql.SubjectDaoImpl;
import ua.training.model.dao.impl.mysql.TestDaoImpl;
import ua.training.model.dao.impl.mysql.TestResultDaoImpl;
import ua.training.model.dao.impl.mysql.UserDaoImpl;

public class MySqlDaoFactory extends DaoFactory{
	private static final Logger logger = LogManager.getLogger(MySqlDaoFactory.class);
    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }
    
    @Override
    public QuestionDao createQuestionDao() {
        return new QuestionDaoImpl(getConnection());
    }
    
    @Override
    public SubjectDao createSubjectDao() {
        return new SubjectDaoImpl(getConnection());
    }
    
    @Override
    public TestDao createTestDao() {
        return new TestDaoImpl(getConnection());
    }
    
    @Override
    public TestResultDao createTestResultDao() {
        return new TestResultDaoImpl(getConnection());
    }

    private Connection getConnection(){
        try {
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            return DBManager.getConnection();
        } catch (SQLException e) {
        	logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
    }
}
