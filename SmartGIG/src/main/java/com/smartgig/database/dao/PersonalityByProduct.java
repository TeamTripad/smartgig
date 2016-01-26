package com.smartgig.database.dao;

public class PersonalityByProduct {
	private int personalityId;
	private int prodId;
	private String token;
	private boolean isMusicLover;
	private boolean isSportsFan;
	private boolean isFoodie;
	private boolean isFashionFiend;
	private boolean isBookWorm;
	private boolean isOutdoorEntusiast;
	
	
	public PersonalityByProduct(int personalityId, int prodId, String token, boolean isMusicLover, boolean isSportsFan,
			boolean isFoodie, boolean isFashionFiend, boolean isBookWorm, boolean isOutdoorEntusiast) {
		super();
		this.personalityId = personalityId;
		this.prodId = prodId;
		this.token = token;
		this.isMusicLover = isMusicLover;
		this.isSportsFan = isSportsFan;
		this.isFoodie = isFoodie;
		this.isFashionFiend = isFashionFiend;
		this.isBookWorm = isBookWorm;
		this.isOutdoorEntusiast = isOutdoorEntusiast;
	}
	public PersonalityByProduct() {
		super();
	}
	public int getPersonalityId() {
		return personalityId;
	}
	public void setPersonalityId(int personalityId) {
		this.personalityId = personalityId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdID(int prodId) {
		this.prodId = prodId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isMusicLover() {
		return isMusicLover;
	}
	public void setMusicLover(boolean isMusicLover) {
		this.isMusicLover = isMusicLover;
	}
	public boolean isSportsFan() {
		return isSportsFan;
	}
	public void setSportsFan(boolean isSportsFan) {
		this.isSportsFan = isSportsFan;
	}
	public boolean isFoodie() {
		return isFoodie;
	}
	public void setFoodie(boolean isFoodie) {
		this.isFoodie = isFoodie;
	}
	public boolean isFashionFiend() {
		return isFashionFiend;
	}
	public void setFashionFiend(boolean isFashionFiend) {
		this.isFashionFiend = isFashionFiend;
	}
	public boolean isBookWorm() {
		return isBookWorm;
	}
	public void setBookWorm(boolean isBookWorm) {
		this.isBookWorm = isBookWorm;
	}
	public boolean isOutdoorEntusiast() {
		return isOutdoorEntusiast;
	}
	public void setOutdoorEntusiast(boolean isOutdoorEntusiast) {
		this.isOutdoorEntusiast = isOutdoorEntusiast;
	}
}
