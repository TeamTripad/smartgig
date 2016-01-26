package com.smartgig.database.dao;

public class Personality_Category {
	private int personalityCatId;
	private String personalityCategory;
	
	public Personality_Category(){
		
	}
	public Personality_Category(int personalityCatId, String personalityCategory) {
		super();
		this.personalityCatId = personalityCatId;
		this.personalityCategory = personalityCategory;
	}
	public int getPersonalityCatId() {
		return personalityCatId;
	}
	public void setPersonalityCatId(int personalityCatId) {
		this.personalityCatId = personalityCatId;
	}
	public String getPersonalityCategory() {
		return personalityCategory;
	}
	public void setPersonalityCategory(String personalityCategory) {
		this.personalityCategory = personalityCategory;
	}

}
