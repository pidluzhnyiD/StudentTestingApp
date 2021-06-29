package ua.training.model.entity;

import java.util.List;

public class Question {
	private int id;
	private int testId;
	private String description;
	private List <Boolean> correctAnswers;
	private List <String> options;
	private int optionsCount;
	
	public Question() {
		 
	 }
	 
	public Question(int id, int testId, String description, List<Boolean> 
						correctAnswers, List<String> options) {
		this.id = id;
		this.testId = testId;
		this.description=description;
		this.correctAnswers = correctAnswers;
		this.options = options;
		this.optionsCount=0;
	}
	
	public int getId() {
		return id;
	}
	 
	public void setId(int id) {
		this.id = id;
	} 
	
	public int getTestId() {
		return testId;
	}
	 
	public void setTestId(int testId) {
		this.testId = testId;
	} 

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Boolean> getCorrectAnswers() {
		return correctAnswers;
	}
	
	public void setCorrectAnswers(List<Boolean> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	public List<String> getOptions() {
		return options;
	}
	
	public void setOptions(List<String> options) {
		this.options = options;
		this.optionsCount = options.size();
	}
	
	public int getOptionsCount() {
		return optionsCount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(description).append(System.lineSeparator());
		for(int i = 1; i<options.size(); i++) {
			sb.append(i).append(". ");
			sb.append(options.get(i)).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
}
