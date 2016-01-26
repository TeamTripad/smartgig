package com.smartgig.facebook.dto;

public class FacebookUserProfile {
	private String user_id;
	private String first_name;
	private String last_name;
	private String birthday;
	private String gender;
	private int age;
	private String email;
	private String bio;
	
	public FacebookUserProfile() {
		super();
	}

	public FacebookUserProfile(String user_id, String first_name, String last_name, String birthday, String gender, 
			int age, String email, String bio) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthday = birthday;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.bio = bio;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	

}
