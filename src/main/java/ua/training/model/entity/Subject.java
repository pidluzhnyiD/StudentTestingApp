package ua.training.model.entity;

import java.util.List;

public class Subject {
	private int id;
	private String englishName;
	private String russianName;
	private int testCount;
	private List<Test> tests;
	
	public Subject() {

	}
	
	public Subject(int id, String englishName, String russianName, List<Test> tests) {
		this.id = id;
		this.englishName = englishName;
		this.russianName = russianName;
		this.tests = tests;
		this.testCount=0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public int getTestCount() {
		return testCount;
	}
	
	public List<Test> getTests() {
		return tests;
	}
	
	public void setTests(List<Test> tests) {
		this.tests = tests;
		this.testCount=tests.size();
	}

	@Override
	public String toString() {
		return "Subject #" + id + ", English Name=" + englishName+", "
				+ "Russian Name=" + russianName +", Test Count=" + testCount;
	}
	
	public static class Builder {

		private int id = 0;
		private String englishName = null;
		private String russianName = null;
		private int testCount=0;
		private List<Test> tests;
		
	    public Builder setId(int id) {
	        this.id = id;
	        return this;
	    }

	    public Builder setEnglishName(String englishName) {
	        this.englishName = englishName;
	        return this;
	    }
	    
	    public Builder setRussianName(String russianName) {
	        this.russianName = russianName;
	        return this;
	    }
	    		
	    public Builder setTests(List<Test> tests) {
	        this.tests = tests;
	        this.testCount=tests.size();
	        return this;
	    }

	    public Subject build() {
	        return new Subject(id, englishName, russianName, tests);
	    }
	}
	
}
