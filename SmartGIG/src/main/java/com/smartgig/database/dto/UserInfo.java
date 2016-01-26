/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartgig.database.dto;
/**
 *
 * @author jzah
 */
public class UserInfo {
    private String fbId;
    private String birthday;
    private String ageStatus;
    private String gender;
    private String fname;
    private String lname;

    public UserInfo() {
    }

    public UserInfo(String fbId, String birthday, String ageStatus, String gender, String fname, String lname) {
        this.fbId = fbId;
        this.birthday = birthday;
        this.ageStatus = ageStatus;
        this.gender = gender;
        this.fname = fname;
        this.lname = lname;
    }

    public String getAgeStatus() {
        return ageStatus;
    }

    public void setAgeStatus(String ageStatus) {
        this.ageStatus = ageStatus;
    }
    
    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
