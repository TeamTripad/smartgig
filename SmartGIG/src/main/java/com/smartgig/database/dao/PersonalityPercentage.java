package com.smartgig.database.dao;
/**Calculated personality percentage base on the total weight of the token from the Personality table*/
public class PersonalityPercentage {
	private String personalityPercentageId;
	private String personalityId;
	private String fbId;
	private long musicLover;/**The calculated percentage of the user's personality based on how much of a musicLover he is.*/
	private long sportsFan;/**The calculated percentage of the user's personality based on how much of a sportsFan he is.*/
	private long bookWorm;/**The calculated percentage of the user's personality based on how much of a bookWorm he is.*/
	private long outdoorEnthusiast;/**The calculated percentage of the user's personality based on how much of a outdoorEnthusiast he is.*/
	private long foodie;/**The calculated percentage of the user's personality based on how much of a foodie he is.*/
	private long fashionFriend;/**The calculated percentage of the user's personality based on how much of a fashionFiend he is.*/
	
	public PersonalityPercentage(String personalityPercentage, String personalityId, String fbId, long musicLover,
			long sportsFan, long bookWorm, long outdoorEnthusiast, long foodie, long fashionFriend) {
		super();
		this.personalityPercentageId = personalityPercentage;
		this.personalityId = personalityId;
		this.fbId = fbId;
		this.musicLover = musicLover;
		this.sportsFan = sportsFan;
		this.bookWorm = bookWorm;
		this.outdoorEnthusiast = outdoorEnthusiast;
		this.foodie = foodie;
		this.fashionFriend = fashionFriend;
	}
	public PersonalityPercentage() {
		super();
	}
	public String getPersonalityPercentageId() {
		return personalityPercentageId;
	}
	public void setPersonalityPercentageId(String personalityPercentage) {
		this.personalityPercentageId = personalityPercentage;
	}
	public String getPersonalityId() {
		return personalityId;
	}
	public void setPersonalityId(String personalityId) {
		this.personalityId = personalityId;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public long getMusicLover() {
		return musicLover;
	}
	public void setMusicLover(long musicLover) {
		this.musicLover = musicLover;
	}
	public long getSportsFan() {
		return sportsFan;
	}
	public void setSportsFan(long sportsFan) {
		this.sportsFan = sportsFan;
	}
	public long getBookWorm() {
		return bookWorm;
	}
	public void setBookWorm(long bookWorm) {
		this.bookWorm = bookWorm;
	}
	public long getOutdoorEnthusiast() {
		return outdoorEnthusiast;
	}
	public void setOutdoorEnthusiast(long outdoorEnthusiast) {
		this.outdoorEnthusiast = outdoorEnthusiast;
	}
	public long getFoodie() {
		return foodie;
	}
	public void setFoodie(long foodie) {
		this.foodie = foodie;
	}
	public long getFashionFriend() {
		return fashionFriend;
	}
	public void setFashionFriend(long fashionFriend) {
		this.fashionFriend = fashionFriend;
	}
}
