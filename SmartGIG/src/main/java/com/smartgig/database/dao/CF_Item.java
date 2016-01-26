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
public class CF_Item {
    private int productId;
    private int fb_id;
    private double cBasedWeight;
    private double cfBasedWeight;
    private double hybridWeight;
    private boolean isPredicted;

    public CF_Item(int productId) {
        this.productId = productId;
    }

    public CF_Item(int productId, int fb_id, double cBasedWeight, double cfBasedWeight, double hybridWeight, boolean isPredicted) {
        this.productId = productId;
        this.fb_id = fb_id;
        this.cBasedWeight = cBasedWeight;
        this.cfBasedWeight = cfBasedWeight;
        this.hybridWeight = hybridWeight;
        this.isPredicted = isPredicted;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getFb_id() {
        return fb_id;
    }

    public void setFb_id(int fb_id) {
        this.fb_id = fb_id;
    }

    public double getcBasedWeight() {
        return cBasedWeight;
    }

    public void setcBasedWeight(double cBasedWeight) {
        this.cBasedWeight = cBasedWeight;
    }

    public double getCfBasedWeight() {
        return cfBasedWeight;
    }

    public void setCfBasedWeight(double cfBasedWeight) {
        this.cfBasedWeight = cfBasedWeight;
    }

    public double getHybridWeight() {
        return hybridWeight;
    }

    public void setHybridWeight(double hybridWeight) {
        this.hybridWeight = hybridWeight;
    }

    public boolean isIsPredicted() {
        return isPredicted;
    }

    public void setIsPredicted(boolean isPredicted) {
        this.isPredicted = isPredicted;
    }
}