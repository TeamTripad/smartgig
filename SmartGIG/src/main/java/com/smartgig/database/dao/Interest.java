package com.smartgig.database.dao;
/**Where the relevant tokens are saved from the gathered information(posts, likes, etc.) from the users SNS account. 
 **/
public class Interest {
	private String interestId;/**PK*/
	private String fbId;/**FK*/
	private String date;
	private String token;
	private int postCnt;/**# of times the token appears in the post*/
	private int shareCnt;/**# of times the token appears in the share*/
	private int likeCnt;/**# of times the token appears be liked by the user(1, 0, -1)*/
	private int dislikeCnt;/**# of times the token appears be disliked by the user(1, 0, -1)*/
	private int weight;/**computed total weight of the token (measures how much the user likes it(token))*/
	public Interest(String interestId, String fbId, String date, String token, int postCnt, int shareCnt, int likeCnt,
			int dislikeCnt, int weight) {
		super();
		this.interestId = interestId;
		this.fbId = fbId;
		this.date = date;
		this.token = token;
		this.postCnt = postCnt;
		this.shareCnt = shareCnt;
		this.likeCnt = likeCnt;
		this.dislikeCnt = dislikeCnt;
		this.weight = weight;
	}
	public Interest() {
		super();
	}
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getPostCnt() {
		return postCnt;
	}
	public void setPostCnt(int postCnt) {
		this.postCnt = postCnt;
	}
	public int getShareCnt() {
		return shareCnt;
	}
	public void setShareCnt(int shareCnt) {
		this.shareCnt = shareCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getDislikeCnt() {
		return dislikeCnt;
	}
	public void setDislikeCnt(int dislikeCnt) {
		this.dislikeCnt = dislikeCnt;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
