package com.smartgig.database.dao;
/**ang joint table
 * ang bridge sa event ug calendar
 **/
public class CalendarEvent {
	private String calendarEventId;/**PK (ang event bow)*/
	private String calendarId;/**FK (ang calendar kung asa nabelong ang event*/
	private String eventId;/**FK (ang event)*/
	public CalendarEvent(String calendarEventId, String calendarId, String eventId) {
		super();
		this.calendarEventId = calendarEventId;
		this.calendarId = calendarId;
		this.eventId = eventId;
	}
	public CalendarEvent() {
		super();
	}
	public String getCalendarEventId() {
		return calendarEventId;
	}
	public void setCalendarEventId(String calendarEventId) {
		this.calendarEventId = calendarEventId;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
}
