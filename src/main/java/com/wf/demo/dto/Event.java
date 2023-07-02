package com.wf.demo.dto;

import lombok.Data;

@Data 
public class Event {
	
	private long id; 
	private String date; 

	public Event() {
	}
	
	public Event(long currentTimeMillis, String date) {
		this.id = currentTimeMillis;
		this.date = date;
	}
}
