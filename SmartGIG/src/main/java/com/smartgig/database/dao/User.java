package com.smartgig.database.dao;

public class User {
	private String fbId;
        private String birthDate;
        private String ageStatus;
        private String relationShipStatus;
        private String gender;
	private String fName;
	private String lName;
	
    public User() {
            super();
    }

    public User(String fbId, String birthDate, String ageStatus, String relationShipStatus, String gender, String fName, String lName) {
        this.fbId = fbId;
        this.birthDate = birthDate;
        this.ageStatus = ageStatus;
        this.relationShipStatus = relationShipStatus;
        this.gender = gender;
        this.fName = fName;
        this.lName = lName;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAgeStatus() {
        return ageStatus;
    }

    public void setAgeStatus(String ageStatus) {
        this.ageStatus = ageStatus;
    }

    public String getRelationShipStatus() {
        return relationShipStatus;
    }

    public void setRelationShipStatus(String relationShipStatus) {
        this.relationShipStatus = relationShipStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}