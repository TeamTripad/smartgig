package com.smartgig.ecommerce.database.dao;

public class ProductKeywords {
	  int pKeywordId;
	  int productId;
	  String keyword;
	  int frequency;
	  String status;
	  
	  
	public ProductKeywords() {
		super();
		
	}

	public ProductKeywords(int pKeywordId, int productId, String keyword, int frequency, String status) {
		super();
		this.pKeywordId = pKeywordId;
		this.productId = productId;
		this.keyword = keyword;
		this.frequency = frequency;
		this.status = status;
	}

	public ProductKeywords(int productId, String keyword) {
		super();
		this.productId = productId;
		this.keyword = keyword;
	}

	public int getpKeywordId() {
		return pKeywordId;
	}


	public void setpKeywordId(int pKeywordId) {
		this.pKeywordId = pKeywordId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	  
	  
	  
}
