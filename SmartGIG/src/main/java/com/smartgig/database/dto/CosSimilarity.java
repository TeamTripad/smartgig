package com.smartgig.database.dto;

public class CosSimilarity {
	private int prodId;
	private String fbId;
	private double value;

	public CosSimilarity(int prodId, double value) {
		this.prodId = prodId;
		this.value = value;
	}

	public CosSimilarity() {
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
