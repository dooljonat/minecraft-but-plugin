package com.spelinchanp.minecraftBut;

import java.util.Random;

public class ButEvent {
	private Settings settings;
	
	public ButEvents butEvent;
	
	ButEvent(Settings settings) {
		this.settings = settings;
	}
	
	// Set Random ButEvents
	public void setRandomButEvent() {
		boolean newEvent = false;
		
		while(!newEvent) {
			// TODO: this is broken
			ButEvents event = ButEvents.values()[new Random().nextInt(ButEvents.values().length)];
			
			if (event != butEvent) {
				newEvent = true;
				butEvent = event;
			}
		}
	}
	
}
	

