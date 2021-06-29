package ua.training.model.service.impl;

import java.util.List;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Subject;
import ua.training.model.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{
	DaoFactory daoFactory = DaoFactory.getInstance();

	@Override
	public List<Subject> getAllSubjects() {
		try (SubjectDao dao = daoFactory.createSubjectDao()){
			return dao.findAll();
		}
	}

}
