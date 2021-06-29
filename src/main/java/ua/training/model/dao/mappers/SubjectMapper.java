package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.Subject;

public class SubjectMapper implements ObjectMapper<Subject>{
	 @Override
	 public Subject extractFromResultSet(ResultSet rs) throws SQLException {
		 Subject.Builder subjectBuilder = new Subject.Builder();
		 Subject subject = subjectBuilder
		   .setId(rs.getInt("id"))
		   .setEnglishName(rs.getString("name_en"))
		   .setRussianName(rs.getString("name_ru"))
		   .build();
		 return subject;
	 }

	 @Override
	 public Subject makeUnique(Map<Integer, Subject> cache, Subject subject) {
		 cache.putIfAbsent(subject.getId(), subject);
		 return cache.get(subject.getId());
	}   	    
}
