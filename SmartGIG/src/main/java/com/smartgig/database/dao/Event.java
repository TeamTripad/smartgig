package com.smartgig.database.dao;
/**Event sa calendar
 * kanang inig add ug event sa calendar
 * kanang event sa calendar
 **/
public class Event {
	private String eventId;/**PK*/
	private String eventName;/**event/occasion type*/
	
	public Event(String eventId, String eventName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
	}
	public Event() {
		super();
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}