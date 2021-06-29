package ua.training.model.entity;

import java.util.List;

public class Test {
	private int id;
	private int subjectId;
	private String englishName;
	private String russianName;
	private int testDuration;
	private TestDifficulty englishDifficulty;
	private TestDifficulty russianDifficulty;
	private int numberOfRequests;
	private List<Question>questions;
	private int questionsCount;
		
	public Test() {
		
	}
	
	public Test(int id, int subjectId, String englishName, String russianName, int testDuration,
			TestDifficulty englishDifficulty, TestDifficulty russianDifficulty, int numberOfRequests,
			List<Question> questions) {
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.englishName = englishName;
		this.russianName = russianName;
		this.testDuration = testDuration;
		this.englishDifficulty = englishDifficulty;
		this.russianDifficulty = russianDifficulty;
		this.numberOfRequests = numberOfRequests;
		this.questions = questions;
		this.questionsCount = 0;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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
	
	public int getTestDuration() {
		return testDuration;
	}
	
	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
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
	
	public int getNumberOfRequests() {
		return numberOfRequests;
	}
	
	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
		this.questionsCount = questions.size();
	}
	
	public int getQuestionsCount() {
		return questionsCount;
	}
	
	@Override
	public String toString() {
		return "Test [Name=" + englishName + ", Duration=" + testDuration + ", DifficultyEn=" + englishDifficulty
				+ ", DifficultyRu=" + russianDifficulty+", Number Of Requests=" + numberOfRequests + "]";
	}	
}
