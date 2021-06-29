package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.Subject;

import static ua.training.constants.Constants.*;

public class SubjectMapper implements ObjectMapper<Subject>{
	 @Override
	 public Subject extractFromResultSet(ResultSet rs) throws SQLException {
		 Subject.Builder subjectBuilder = new Subject.Builder();
		 Subject subject = subjectBuilder
		   .setId(rs.getInt(ID))
		   .setEnglishName(rs.getString(NAME_EN))
		   .setRussianName(rs.getString(NAME_RU))
		   .build();
		 return subject;
	 }

	 @Override
	 public Subject makeUnique(Map<Integer, Subject> cache, Subject subject) {
		 cache.putIfAbsent(subject.getId(), subject);
		 return cache.get(subject.getId());
	}   	    
}
