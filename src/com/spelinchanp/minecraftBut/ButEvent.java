package com.spelinchanp.minecraftBut;

public final class ButEvent {
	public static ButEvents butEvent;
	
	// Set Random ButEvents
	public static void setRandomButEvent() {
		boolean newEvent = false;
		
		while(!newEvent) {
			ButEvents event = ButEvents.values()[(int)(Math.random()*ButEvents.values().length)];
			
			if (event != butEvent) {
				newEvent = true;
				butEvent = event;
			}
		}
	}
	
}
	

