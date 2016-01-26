package com.smartgig.database.dao;
/**User's calendar
 **/
public class Calendar {
	private String calendarId;/**PK*/
	private String fbId;/**FK (user id)*/	
	private String eventId;/**FK*/
	/** 1 or 0 if na-Giftan(buy gift) na ang giver sa kana nga event
	 * */
	private boolean satus;
	public Calendar(String calendarId, String fbId, String eventId, boolean satus) {
		super();
		this.calendarId = calendarId;
		this.fbId = fbId;
		this.eventId = eventId;
		this.satus = satus;
	}
	public Calendar() {
		super();
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public boolean isSatus() {
		return satus;
	}
	public void setSatus(boolean satus) {
		this.satus = satus;
	}
}
	
