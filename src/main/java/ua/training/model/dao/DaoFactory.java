package ua.training.model.dao;

import ua.training.model.dao.impl.MySqlDaoFactory;

public abstract class DaoFactory {
	private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract QuestionDao createQuestionDao();
    public abstract SubjectDao createSubjectDao();
    public abstract TestDao createTestDao();
    public abstract TestResultDao createTestResultDao();
    
    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new MySqlDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
