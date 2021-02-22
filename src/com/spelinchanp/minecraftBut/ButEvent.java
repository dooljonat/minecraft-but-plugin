package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ButEvent {
	private Settings settings;
	
	public List<ButEvents> butEvents = new ArrayList<ButEvents>();
	
	ButEvent(Settings settings) {
		this.settings = settings;
	}
	
	// Set Random ButEvents
	public void setRandomButEvents() {
		butEvents.clear();
		
		List<ButEvents> tempList = new ArrayList<ButEvents>();
		
		for (int i = 0; i < settings.howManyActive; i++) {
			boolean newEvent = false;
			ButEvents event = null;
			
			// Make sure the new event is not a duplicate
			while(!newEvent) {
				event = settings.enabledEvents.get(new Random().nextInt(settings.enabledEvents.size()));
				
				if (!this.butEvents.contains(event)) {
					newEvent = true;
				}
			}
			butEvents.add(event);
		}	
	}
	
}
	

