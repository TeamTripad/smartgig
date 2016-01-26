package com.smartgig.ecommerce.database.dao;

public class WordAssociation {

	int categoryId;
	String word;
	
	public WordAssociation(int categoryId, String word) {
		super();
		this.categoryId = categoryId;
		this.word = word;
	}

	public WordAssociation(String word) {
		super();
		this.word = word;
	}

	public WordAssociation() {
		super();
		
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
}
