package ua.training.model.service.impl;

import ua.training.model.service.QuestionService;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.SubjectService;
import ua.training.model.service.TestResultService;
import ua.training.model.service.TestService;
import ua.training.model.service.UserService;

public class ServiceFactoryImpl extends ServiceFactory{
	@Override
	public UserService createUserService() {
		return new UserServiceImpl();
	}

	@Override
	public QuestionService createQuestionService() {
		return new QuestionServiceImpl();
	}

	@Override
	public SubjectService createSubjectService() {
		return new SubjectServiceImpl();
	}

	@Override
	public TestService createTestService() {
		return new TestServiceImpl();
	}

	@Override
	public TestResultService createTestResultService() {
		return new TestResultServiceImpl();
	}
}
