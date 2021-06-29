package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.TestDifficulty;
import ua.training.model.entity.TestResult;

import static ua.training.constants.Constants.*;

public class TestResultMapper  implements ObjectMapper<TestResult>{
	@Override
    public TestResult extractFromResultSet(ResultSet rs) throws SQLException {
		TestResult testResult = new TestResult();
		testResult.setId(rs.getInt(ID));
		testResult.setUserId(rs.getInt(USER_ID));
		testResult.setTestId(rs.getInt(TEST_ID));
		testResult.setEnglishName(rs.getString(NAME_EN));
		testResult.setRussianName(rs.getString(NAME_RU));
		String tdEN = rs.getString(DIFFICULTY_EN);
		testResult.setEnglishDifficulty(TestDifficulty.valueOf(tdEN.toUpperCase()));
        String tdRU = rs.getString(DIFFICULTY_RU);
        testResult.setRussianDifficulty(TestDifficulty.valueOf(tdRU.toUpperCase()));
        testResult.setCompletionTime(rs.getTime(COMPLETION_TIME));
        testResult.setCompletionDate(rs.getTimestamp(COMPLETION_DATE));
        testResult.setResult(rs.getDouble(RESULT));
        return testResult;
    }

    @Override
    public TestResult makeUnique(Map<Integer, TestResult> cache, TestResult testResult) {
        cache.putIfAbsent(testResult.getId(), testResult);
        return cache.get(testResult.getId());
    }
}
