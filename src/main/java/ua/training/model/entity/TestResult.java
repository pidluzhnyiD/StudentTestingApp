package ua.training.model.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestResult {
	private int id;
	private int userId;
	private int testId;
	private String englishName;
	private String russianName;
	private TestDifficulty englishDifficulty;
	private TestDifficulty russianDifficulty;
	private LocalTime completionTime;
	private LocalDateTime completionDate;
	private double result;
	
	public TestResult() {}
	
	public TestResult(int id, int userId, int testId, String englishName, String russianName,
			TestDifficulty englishDifficulty, TestDifficulty russianDifficulty, LocalTime completionTime,
			LocalDateTime completionDate, double result) {
		super();
		this.id = id;
		this.userId = userId;
		this.testId = testId;
		this.englishName = englishName;
		this.russianName = russianName;
		this.englishDifficulty = englishDifficulty;
		this.russianDifficulty = russianDifficulty;
		this.completionTime = completionTime;
		this.completionDate = completionDate;
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getRussianName() {
		return russianName;
	}

	public void setRussianName(String russianName) {
		this.russianName = russianName;
	}

	public TestDifficulty getEnglishDifficulty() {
		return englishDifficulty;
	}

	public void setEnglishDifficulty(TestDifficulty englishDifficulty) {
		this.englishDifficulty = englishDifficulty;
	}

	public TestDifficulty getRussianDifficulty() {
		return russianDifficulty;
	}

	public void setRussianDifficulty(TestDifficulty russianDifficulty) {
		this.russianDifficulty = russianDifficulty;
	}

	public LocalTime getCompletionTime() {
		return completionTime;
	}
	
	public void setCompletionTime(Time time) {
		this.completionTime = time.toLocalTime();
	}
	
	public LocalDateTime getCompletionDate() {
		return completionDate;
	}
	
	public void setCompletionDate(Timestamp timestamp) {
		this.completionDate = timestamp.toLocalDateTime();
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Test Result #" + id + ", Name=" + englishName + ", Difficulty=" 
				+ englishDifficulty + ", Completion Time=" + completionTime + ", Completion Date="
				+ completionDate + ", Result=" + result + "%]";
	}
}
