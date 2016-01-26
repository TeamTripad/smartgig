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
public class C_Item {
    private int cItemId;
    private String fbId;//FK
    private int productId;//FK
    private double rating;
    //<insert other instance objects>

    public C_Item() {
    }

    public C_Item(int cItemId, String fbId, int productId, double rating) {
        this.cItemId = cItemId;
        this.fbId = fbId;
        this.productId = productId;
        this.rating = rating;
    }

    public int getcItemId() {
        return cItemId;
    }

    public void setcItemId(int cItemId) {
        this.cItemId = cItemId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
