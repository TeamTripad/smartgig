/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartgig.database.dao;

/**
 *
 * @author jzah
 */
public class CF_User {
    private int cfUserId;//PK
    private String aFbId;//FK
    private String uFbId;//FK
    private int isSameGender;
    private int isSameAgeStatus;
    private double hobbiesCosSim;
    private double favoritesCosSim;
    private double itemRatingPearson;
    private double average;
    private double userPearson;
    private double rating;
    
    public CF_User() {
    }

    public CF_User(int cfUserId, String aFbId, String uFbId, int isSameGender, int isSameAgeStatus, double hobbiesCosSim, double favoritesCosSim, double itemRatingPearson, double average, double userPearson, double rating) {
        this.cfUserId = cfUserId;
        this.aFbId = aFbId;
        this.uFbId = uFbId;
        this.isSameGender = isSameGender;
        this.isSameAgeStatus = isSameAgeStatus;
        this.hobbiesCosSim = hobbiesCosSim;
        this.favoritesCosSim = favoritesCosSim;
        this.itemRatingPearson = itemRatingPearson;
        this.average = average;
        this.userPearson = userPearson;
        this.rating = rating;
    }

    public int getIsSameGender() {
        return isSameGender;
    }

    public void setIsSameGender(int isSameGender) {
        this.isSameGender = isSameGender;
    }

    public int getIsSameAgeStatus() {
        return isSameAgeStatus;
    }

    public void setIsSameAgeStatus(int isSameAgeStatus) {
        this.isSameAgeStatus = isSameAgeStatus;
    }
    
    public double getHobbiesCosSim() {
        return hobbiesCosSim;
    }

    public void setHobbiesCosSim(double hobbiesCosSim) {
        this.hobbiesCosSim = hobbiesCosSim;
    }

    public double getFavoritesCosSim() {
        return favoritesCosSim;
    }

    public void setFavoritesCosSim(double favoritesCosSim) {
        this.favoritesCosSim = favoritesCosSim;
    }

    public double getItemRatingPearson() {
        return itemRatingPearson;
    }

    public void setItemRatingPearson(double itemRatingPearson) {
        this.itemRatingPearson = itemRatingPearson;
    }
    
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCfUserId() {
        return cfUserId;
    }

    public void setCfUserId(int cfUserId) {
        this.cfUserId = cfUserId;
    }

    public String getaFbId() {
        return aFbId;
    }

    public void setaFbId(String aFbId) {
        this.aFbId = aFbId;
    }

    public String getuFbId() {
        return uFbId;
    }

    public void setuFbId(String uFbId) {
        this.uFbId = uFbId;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getUserPearson() {
        return userPearson;
    }

    public void setUserPearson(double userPearson) {
        this.userPearson = userPearson;
    }
    
}
