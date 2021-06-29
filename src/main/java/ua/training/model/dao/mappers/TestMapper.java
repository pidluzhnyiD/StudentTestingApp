package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.Test;
import ua.training.model.entity.TestDifficulty;

import static ua.training.constants.Constants.*;

public class TestMapper implements ObjectMapper<Test>{
	@Override
    public Test extractFromResultSet(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt(ID));
        test.setSubjectId(rs.getInt(SUBJECT_ID));
        test.setEnglishName(rs.getString(NAME_EN));
        test.setRussianName(rs.getString(NAME_RU));
        test.setTestDuration(rs.getInt(DURATION));
        String tdEN = rs.getString(DIFFICULTY_EN);
        test.setEnglishDifficulty(TestDifficulty.valueOf(tdEN.toUpperCase()));
        String tdRU = rs.getString(DIFFICULTY_RU);
        test.setRussianDifficulty(TestDifficulty.valueOf(tdRU.toUpperCase()));
        test.setNumberOfRequests(rs.getInt(NUMBER_OF_REQUESTS));        
        return test;
    }

    @Override
    public Test makeUnique(Map<Integer, Test> cache, Test test) {
        cache.putIfAbsent(test.getId(), test);
        return cache.get(test.getId());
    }
}
