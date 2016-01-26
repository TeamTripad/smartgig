package com.smartgig.database.dto;
public class ProductKeyword {
	private int prodID;
    private String keyword;
    private int frequency;

    public ProductKeyword(){
      
    }
    
    public ProductKeyword(int prodID, String keyword, int frequency) {
        this.prodID = prodID;
        this.keyword = keyword;
        this.frequency = frequency;
    }
    
    public int getItemID() {
        return prodID;
    }
    
    public void setProdID(int prodID) {
        this.prodID = prodID;
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
