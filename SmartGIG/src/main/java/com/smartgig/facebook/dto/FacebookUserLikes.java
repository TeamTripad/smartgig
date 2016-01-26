package com.smartgig.facebook.dto;

public class FacebookUserLikes {

	private String likeId;
	private String name;
	private String description;
	private String about;
	public FacebookUserLikes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FacebookUserLikes(String likeId, String name, String description, String about) {
		super();
		this.likeId = likeId;
		this.name = name;
		this.description = description;
		this.about = about;
	}
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
}
