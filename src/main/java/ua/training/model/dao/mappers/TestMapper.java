package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.Test;
import ua.training.model.entity.TestDifficulty;

public class TestMapper implements ObjectMapper<Test>{
	@Override
    public Test extractFromResultSet(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setSubjectId(rs.getInt("subject_id"));
        test.setEnglishName(rs.getString("name_en"));
        test.setRussianName(rs.getString("name_ru"));
        test.setTestDuration(rs.getInt("duration"));
        String tdEN = rs.getString("difficulty_en");
        test.setEnglishDifficulty(TestDifficulty.valueOf(tdEN.toUpperCase()));
        String tdRU = rs.getString("difficulty_ru");
        test.setRussianDifficulty(TestDifficulty.valueOf(tdRU.toUpperCase()));
        test.setNumberOfRequests(rs.getInt("number_of_requests"));        
        return test;
    }

    @Override
    public Test makeUnique(Map<Integer, Test> cache, Test test) {
        cache.putIfAbsent(test.getId(), test);
        return cache.get(test.getId());
    }
}
