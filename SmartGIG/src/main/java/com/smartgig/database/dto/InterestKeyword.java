package com.smartgig.database.dto;

public class InterestKeyword {
	private String fbID;
	private String keyword;
	private int frequency;

	public InterestKeyword(String fbID, String keyword, int frequency) {
		this.fbID = fbID;
		this.keyword = keyword;
		this.frequency = frequency;
	}

	public String getFbId() {
		return fbID;
	}

	public void setFbId(String fbID) {
		this.fbID = fbID;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
