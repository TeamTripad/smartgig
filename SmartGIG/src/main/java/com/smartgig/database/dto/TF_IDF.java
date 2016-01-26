package com.smartgig.database.dto;

public class TF_IDF {
	private int prodId;
	private String fbId;
	private String word;
	private double value;

	public TF_IDF() {
	}

	public TF_IDF(int prodId, String word, double value) {
		this.prodId = prodId;
		this.word = word;
		this.value = value;
	}
	
	public TF_IDF(String fbId, String word, double value) {
		this.fbId = fbId;
		this.word = word;
		this.value = value;
	}
	
	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
