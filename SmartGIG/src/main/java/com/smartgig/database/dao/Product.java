package com.smartgig.database.dao;

import java.io.InputStream;

/**
 **/
public class Product {
	private int prodId;
	private int adminId;
	private int catId;
        private int subCatId;
        private int sourceId;//NO NEED FOR SOURCE ID for REVISION
	private String prodName;
        private String brand;
	private String prodDesc;
	private int quantity;
	private float price;
	private String size;
	private String color;
	InputStream image;

    public Product() {
    }

    public Product(int prodId, int adminId, int catId, int subCatId, int sourceId, String prodName, String brand, String prodDesc, int quantity, float price, String size, String color, InputStream image) {
        this.prodId = prodId;
        this.adminId = adminId;
        this.catId = catId;
        this.subCatId = subCatId;
        this.sourceId = sourceId;
        this.prodName = prodName;
        this.brand = brand;
        this.prodDesc = prodDesc;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.color = color;
        this.image = image;
    }

    public Product(int prodId, String prodName) {
		this.prodId = prodId;
		this.prodName = prodName;
	}
    
    public Product(String prodName,float price) {
 		this.prodName = prodName;
 		this.price = price;
 	}
    
	public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    
}
