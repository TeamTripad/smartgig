package com.smartgig.database.dao;

import java.io.InputStream;

public class ProductImage {
	private String prodImageId;
	private String filename;
	private InputStream imageIn;
	private byte[] imageOut;
	
	public ProductImage(String productId, String filename, InputStream imageIn, byte[] imageOut) {
		super();
		this.prodImageId = productId;
		this.filename = filename;
		this.imageIn = imageIn;
		this.imageOut = imageOut;
	}
	public ProductImage(){}
	public String getFilename() {
		return filename;
	}
	public String getProdImageId() {
		return prodImageId;
	}
	public void setProductImageId(String prodImageId) {
		this.prodImageId = prodImageId;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public InputStream getImageIn() {
		return imageIn;
	}
	public void setImageIn(InputStream imageIn) {
		this.imageIn = imageIn;
	}
	public byte[] getImageOut() {
		return imageOut;
	}
	public void setImageOut(byte[] imageOut) {
		this.imageOut = imageOut;
	}
}