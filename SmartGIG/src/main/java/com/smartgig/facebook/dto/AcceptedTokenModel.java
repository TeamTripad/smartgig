package com.smartgig.facebook.dto;

public class AcceptedTokenModel {
	private String tokenID;
    private String userID;
    private String word;
    private int positiveCount;
    private int negativeCount;

    public AcceptedTokenModel() {
    }

    public AcceptedTokenModel(String tokenID, String userID, String word, int positiveCount, int negativeCount) {
        this.tokenID = tokenID;
        this.userID = userID;
        this.word = word;
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(int positiveCount) {
        this.positiveCount = positiveCount;
    }

    public int getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(int negativeCount) {
        this.negativeCount = negativeCount;
    }
    
    public void addPositiveCount(){
        this.positiveCount++;
    }
    
    public void addNegativeCount(){
        this.negativeCount++;
    }
}
