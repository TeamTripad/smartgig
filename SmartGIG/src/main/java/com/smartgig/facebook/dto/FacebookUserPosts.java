package com.smartgig.facebook.dto;

public class FacebookUserPosts {

	private String id;
	private String story;
	private String description;
	private String message;
	
	public FacebookUserPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacebookUserPosts(String id, String story, String description, String message) {
		super();
		this.id = id;
		this.story = story;
		this.description = description;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
