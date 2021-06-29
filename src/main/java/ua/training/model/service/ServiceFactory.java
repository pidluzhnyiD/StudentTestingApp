package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.service.impl.ServiceFactoryImpl;

public abstract  class ServiceFactory {
	private static ServiceFactory serviceFactory;

    public abstract UserService createUserService();
    public abstract QuestionService createQuestionService();
    public abstract SubjectService createSubjectService();
    public abstract TestService createTestService();
    public abstract TestResultService createTestResultService();
    
    public static ServiceFactory getInstance(){
        if( serviceFactory == null ){
            synchronized (DaoFactory.class){
                if(serviceFactory==null){
                	ServiceFactory temp = new ServiceFactoryImpl();
                	serviceFactory = temp;
                }
            }
        }
        return serviceFactory;
    }
}
