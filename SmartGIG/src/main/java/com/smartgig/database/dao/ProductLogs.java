package com.smartgig.database.dao;

public class ProductLogs {
	private String productLogsId;
	private String giverFbId;
	private String receiverFbId;
	private String productId;
	private String entryDate;
	private int prodQty;
	
	public ProductLogs(String salesId, String giverId, String receiverFbId, String productId, String entryDate,
			int prodQty) {
		super();
		this.productLogsId = salesId;
		this.giverFbId = giverId;
		this.receiverFbId = receiverFbId;
		this.productId = productId;
		this.entryDate = entryDate;
		this.prodQty = prodQty;
	}
	public ProductLogs() {
		super();
	}
	public String getSalesId() {
		return productLogsId;
	}
	public void setSalesId(String salesId) {
		this.productLogsId = salesId;
	}
	public String getGiverFbId() {
		return giverFbId;
	}
	public void setGiverFbId(String giverId) {
		this.giverFbId = giverId;
	}
	public String getReceiverFbId() {
		return receiverFbId;
	}
	public void setReceiverFbId(String receiverFbId) {
		this.receiverFbId = receiverFbId;
	}
	public String getProductLogId() {
		return productId;
	}
	public void setProductLogId(String productId) {
		this.productId = productId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public int getProdQty() {
		return prodQty;
	}
	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
}
