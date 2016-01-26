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
public class UserPersonality {
    private int userPersonalityId;
    private String fbId;
    private double musicLoverWeight;
    private double sportsFanWeight;
    private double foodieWeight;
    private double fashionFiendWeight;
    private double bookWormWeight;
    private double outdoorEnthusiastWeight;
    private double musicLoverPercentage;
    private double sportsFanPercentage;
    private double foodiePercentage;
    private double fashionFiendPercentage;
    private double bookWormPercentage;
    private double outdoorEnthusiastPercentage;

    public UserPersonality() {
    }

    public UserPersonality(int userPersonalityId, String fbId, double musicLoverWeight, double sportsFanWeight, double foodieWeight, double fashionFiendWeight, double bookWormWeight, double outdoorEnthusiastWeight) {
        this.userPersonalityId = userPersonalityId;
        this.fbId = fbId;
        this.musicLoverWeight = musicLoverWeight;
        this.sportsFanWeight = sportsFanWeight;
        this.foodieWeight = foodieWeight;
        this.fashionFiendWeight = fashionFiendWeight;
        this.bookWormWeight = bookWormWeight;
        this.outdoorEnthusiastWeight = outdoorEnthusiastWeight;
    }

    public UserPersonality(int userPersonalityId, String fbId, double musicLoverWeight, double sportsFanWeight, double foodieWeight, double fashionFiendWeight, double bookWormWeight, double outdoorEnthusiastWeight, double musicLoverPercentage, double sportsFanPercentage, double foodiePercentage, double fashionFiendPercentage, double bookWormPercentage, double outdoorEnthusiastPercentage) {
        this.userPersonalityId = userPersonalityId;
        this.fbId = fbId;
        this.musicLoverWeight = musicLoverWeight;
        this.sportsFanWeight = sportsFanWeight;
        this.foodieWeight = foodieWeight;
        this.fashionFiendWeight = fashionFiendWeight;
        this.bookWormWeight = bookWormWeight;
        this.outdoorEnthusiastWeight = outdoorEnthusiastWeight;
        this.musicLoverPercentage = musicLoverPercentage;
        this.sportsFanPercentage = sportsFanPercentage;
        this.foodiePercentage = foodiePercentage;
        this.fashionFiendPercentage = fashionFiendPercentage;
        this.bookWormPercentage = bookWormPercentage;
        this.outdoorEnthusiastPercentage = outdoorEnthusiastPercentage;
    }

    public double getMusicLoverPercentage() {
        return musicLoverPercentage;
    }

    public void setMusicLoverPercentage(double musicLoverPercentage) {
        this.musicLoverPercentage = musicLoverPercentage;
    }

    public double getSportsFanPercentage() {
        return sportsFanPercentage;
    }

    public void setSportsFanPercentage(double sportsFanPercentage) {
        this.sportsFanPercentage = sportsFanPercentage;
    }

    public double getFoodiePercentage() {
        return foodiePercentage;
    }

    public void setFoodiePercentage(double foodiePercentage) {
        this.foodiePercentage = foodiePercentage;
    }

    public double getFashionFiendPercentage() {
        return fashionFiendPercentage;
    }

    public void setFashionFiendPercentage(double fashionFiendPercentage) {
        this.fashionFiendPercentage = fashionFiendPercentage;
    }

    public double getBookWormPercentage() {
        return bookWormPercentage;
    }

    public void setBookWormPercentage(double bookWormPercentage) {
        this.bookWormPercentage = bookWormPercentage;
    }

    public double getOutdoorEnthusiastPercentage() {
        return outdoorEnthusiastPercentage;
    }

    public void setOutdoorEnthusiastPercentage(double outdoorEnthusiastPercentage) {
        this.outdoorEnthusiastPercentage = outdoorEnthusiastPercentage;
    }
    public int getUserPersonalityId() {
        return userPersonalityId;
    }

    public void setUserPersonalityId(int userPersonalityId) {
        this.userPersonalityId = userPersonalityId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public double getMusicLoverWeight() {
        return musicLoverWeight;
    }

    public void setMusicLoverWeight(double musicLoverWeight) {
        this.musicLoverWeight = musicLoverWeight;
    }

    public double getSportsFanWeight() {
        return sportsFanWeight;
    }

    public void setSportsFaWeight(double sportsFanWeight) {
        this.sportsFanWeight = sportsFanWeight;
    }

    public double getFoodieWeight() {
        return foodieWeight;
    }

    public void setFoodieWeight(double foodieWeight) {
        this.foodieWeight = foodieWeight;
    }

    public double getFashionFiendWeight() {
        return fashionFiendWeight;
    }

    public void setFashionFiendWeight(double fashionFiendWeight) {
        this.fashionFiendWeight = fashionFiendWeight;
    }

    public double getBookWormWeight() {
        return bookWormWeight;
    }

    public void setBookWormWeight(double bookWormWeight) {
        this.bookWormWeight = bookWormWeight;
    }

    public double getOutdoorEnthusiastWeight() {
        return outdoorEnthusiastWeight;
    }

    public void setOutdoorEnthusiastWeight(double outdoorEnthusiastWeight) {
        this.outdoorEnthusiastWeight = outdoorEnthusiastWeight;
    }
}
