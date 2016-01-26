package com.smartgig.database.dto;

public class OverAllPercentage {
	private int prodId;
	private double cosSimPercentage;
	private double personlityPercentage;
	private double eventPercentage;
	private double totalPercentage;
	
	
	public OverAllPercentage() {
		super();
	}
	public OverAllPercentage(int prodId, double cosSimPercentage, double personlityPercentage, double eventPercentage,
			double totalPercentage) {
		super();
		this.prodId = prodId;
		this.cosSimPercentage = cosSimPercentage;
		this.personlityPercentage = personlityPercentage;
		this.eventPercentage = eventPercentage;
		this.totalPercentage = totalPercentage;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public double getCosSimPercentage() {
		return cosSimPercentage;
	}
	public void setCosSimPercentage(double cosSimPercentage) {
		this.cosSimPercentage = cosSimPercentage;
	}
	public double getPersonlityPercentage() {
		return personlityPercentage;
	}
	public void setPersonlityPercentage(double personlityPercentage) {
		this.personlityPercentage = personlityPercentage;
	}
	public double getEventPercentage() {
		return eventPercentage;
	}
	public void setEventPercentage(double eventPercentage) {
		this.eventPercentage = eventPercentage;
	}
	public double getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	
	
	
}
