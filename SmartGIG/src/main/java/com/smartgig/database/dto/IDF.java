package com.smartgig.database.dto;

public class IDF {
	private int keywordID;
	private String keyword;
	private int frequency;
	private double value;
	private String status;

	public IDF() {
	}

	public IDF(String keyword, double value) {
		this.keyword = keyword;
		this.value = value;
	}

	public IDF(int keywordID, String keyword, int frequency, double value, String status) {
		this.keywordID = keywordID;
		this.keyword = keyword;
		this.frequency = frequency;
		this.value = value;
		this.status = status;
	}

	public int getKeywordID() {
		return keywordID;
	}

	public void setKeywordID(int keywordID) {
		this.keywordID = keywordID;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
