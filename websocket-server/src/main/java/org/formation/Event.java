package org.formation;

public class Event {
	private String eventId;
    private String eventTs;
    
    public Event() {}
    
	public Event(String eventId, String eventTs) {
		super();
		this.eventId = eventId;
		this.eventTs = eventTs;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventTs() {
		return eventTs;
	}

	public void setEventTs(String eventTs) {
		this.eventTs = eventTs;
	}
	
	
    
    
}
