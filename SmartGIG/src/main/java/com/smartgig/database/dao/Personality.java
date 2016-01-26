package com.smartgig.database.dao;
/**Where distinct tokens are saved and it's overall weight.
 **/
public class Personality {
	private String personalityId;
	private String fbId;
	private String token;
	private boolean isMusicLover;
	private boolean isSportsFan;
	private boolean isFoodie;
	private boolean isFashionFiend;
	private boolean isBookWorm;
	private boolean isOutdoorEntusiast;
	private long weight;/**total weight of the token*/
	
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	public Personality(String personalityId, String fbId, String token, boolean isMusicLover, boolean isSportsFan,
			boolean isFoodie, boolean isFashionFiend, boolean isBookWorm, boolean isOutdoorEntusiast, long weight) {
		super();
		this.personalityId = personalityId;
		this.fbId = fbId;
		this.token = token;
		this.isMusicLover = isMusicLover;
		this.isSportsFan = isSportsFan;
		this.isFoodie = isFoodie;
		this.isFashionFiend = isFashionFiend;
		this.isBookWorm = isBookWorm;
		this.isOutdoorEntusiast = isOutdoorEntusiast;
		this.weight = weight;
	}
	public Personality() {
		super();
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
