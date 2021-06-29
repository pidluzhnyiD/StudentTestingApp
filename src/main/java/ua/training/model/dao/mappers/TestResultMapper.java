package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.TestDifficulty;
import ua.training.model.entity.TestResult;

public class TestResultMapper  implements ObjectMapper<TestResult>{
	@Override
    public TestResult extractFromResultSet(ResultSet rs) throws SQLException {
		TestResult testResult = new TestResult();
		testResult.setId(rs.getInt("id"));
		testResult.setUserId(rs.getInt("user_id"));
		testResult.setTestId(rs.getInt("test_id"));
		testResult.setEnglishName(rs.getString("name_en"));
		testResult.setRussianName(rs.getString("name_ru"));
		String tdEN = rs.getString("difficulty_en");
		testResult.setEnglishDifficulty(TestDifficulty.valueOf(tdEN.toUpperCase()));
        String tdRU = rs.getString("difficulty_ru");
        testResult.setRussianDifficulty(TestDifficulty.valueOf(tdRU.toUpperCase()));
        testResult.setCompletionTime(rs.getTime("completion_time"));
        testResult.setCompletionDate(rs.getTimestamp("completion_date"));
        testResult.setResult(rs.getDouble("result"));
        return testResult;
    }

    @Override
    public TestResult makeUnique(Map<Integer, TestResult> cache, TestResult testResult) {
        cache.putIfAbsent(testResult.getId(), testResult);
        return cache.get(testResult.getId());
    }
}
