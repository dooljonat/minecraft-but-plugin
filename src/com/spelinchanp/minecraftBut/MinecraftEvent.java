package com.spelinchanp.minecraftBut;

import org.bukkit.plugin.Plugin;

import com.spelinchanp.minecraftBut.events.GravitizedLeaves;
import com.spelinchanp.minecraftBut.events.MobsStacked;
import com.spelinchanp.minecraftBut.events.RandomEnchants;
import com.spelinchanp.minecraftBut.events.TntRain;

public class MinecraftEvent {
	// TODO: create wrapper classes for all of the events
	// right now, its doing the dumb "already scheduled as" error because its not a re-initialized
	// object in the main run function anymore
	
	private Plugin plugin;
	private Settings settings;
	
	MinecraftEvent(Plugin plugin, Settings settings) {
		this.plugin = plugin;
		this.settings = settings;
		
	}
	
	public void run(ButEvents event) {
		if (event == ButEvents.TntRain) {
			TntRain e = new TntRain(plugin, settings);
		}
		else if (event == ButEvents.RandomEnchants) {
			RandomEnchants e = new RandomEnchants(plugin, settings);
		}
		else if (event == ButEvents.MobsStacked) {
			MobsStacked e = new MobsStacked(plugin, settings);
		}
		else if (event == ButEvents.GravitizedLeaves) {
			GravitizedLeaves e = new GravitizedLeaves(plugin);
		}
	}	
}
