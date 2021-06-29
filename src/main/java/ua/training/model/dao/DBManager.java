package ua.training.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class DBManager {
	private static DataSource ds;
	private static final Logger logger = LogManager.getLogger(DBManager.class);
	
    private DBManager() {

    }

    public static Connection getConnection() throws SQLException {
        if(ds == null) {
        	 synchronized (DBManager.class) {
        		 if(ds == null) {
        			 BasicDataSource dataSource = new BasicDataSource();
                     dataSource.setUrl("jdbc:mysql://localhost:3307/studenttestingdb?useSSL=false");
                     dataSource.setUsername("root");
                     dataSource.setPassword("root");
                     dataSource.setMinIdle(5);
                     dataSource.setMaxIdle(10);
                     dataSource.setMaxOpenPreparedStatements(100);
                     ds = dataSource;
        		 }
        	 }
        }    
        return ds.getConnection();
    }

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {           
            	logger.log(Level.ERROR, e);
                throw new Error(e.getMessage());
            }
        }
    }

    public static void rollback(Connection con) {
        if(con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
            	logger.log(Level.ERROR, e);
                throw new Error(e.getMessage());
            }
        }
    }
}
