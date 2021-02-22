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
			ButEvents event = settings.enabledEvents.get(new Random().nextInt(settings.enabledEvents.size()));
			
			if (event != butEvent) {
				newEvent = true;
				butEvent = event;
			}
		}
	}
	
}
	

