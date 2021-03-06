package com.smartgig.ecommerce.database.dao;
/**Where distinct tokens are saved and it's overall weight.
 **/
public class Personality {
	private String personalityId;
	private String prodId;
	private int isMusicLover;
	private int isSportsFan;
	private int isFoodie;
	private int isFashionFiend;
	private int isBookWorm;
	private int isOutdoorEntusiast;
	private int weight;/**total weight of the token*/
	
	

	public Personality(String personalityId, String fbId, int musicLover, int sportsFan, int foodie,
			int fashionFiend, int bookWorm, int outdoorEntusiast, int weight) {
		super();
		this.personalityId = personalityId;
		this.prodId = fbId;
		isMusicLover = musicLover;
		isSportsFan = sportsFan;
		isFoodie = foodie;
		isFashionFiend = fashionFiend;
		isBookWorm = bookWorm;
		isOutdoorEntusiast = outdoorEntusiast;
		this.weight = weight;
	}
	
	public Personality() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPersonalityId() {
		return personalityId;
	}
	public void setPersonalityId(String personalityId) {
		this.personalityId = personalityId;
	}
	public String getFbId() {
		return prodId;
	}
	public void setFbId(String fbId) {
		this.prodId = fbId;
	}
	public int getMusicLover() {
		return isMusicLover;
	}
	public void setMusicLover(int musicLover) {
		isMusicLover = musicLover;
	}
	public int getSportsFan() {
		return isSportsFan;
	}
	public void setSportsFan(int sportsFan) {
		isSportsFan = sportsFan;
	}
	public int getFoodie() {
		return isFoodie;
	}
	public void setFoodie(int foodie) {
		isFoodie = foodie;
	}
	public int getFashionFiend() {
		return isFashionFiend;
	}
	public void setFashionFiend(int fashionFiend) {
		isFashionFiend = fashionFiend;
	}
	public int getBookWorm() {
		return isBookWorm;
	}
	public void setBookWorm(int bookWorm) {
		isBookWorm = bookWorm;
	}
	public int getOutdoorEntusiast() {
		return isOutdoorEntusiast;
	}
	public void setOutdoorEntusiast(int outdoorEntusiast) {
		isOutdoorEntusiast = outdoorEntusiast;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
}
