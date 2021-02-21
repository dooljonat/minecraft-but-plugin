package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Settings {
	private Plugin plugin;
	
	// TODO: the settings aren't loading for some reason, fix it
	
	Settings(Plugin plugin) {
		this.plugin = plugin;
	}
	
	// enabled events
	private boolean tntRainEnabled;
	private boolean eggsOPEnabled;
	private boolean randomEnchantsEnabled;
	private boolean mobsStackedEnabled;
	private boolean randomBlocksWalkingEnabled;
	private boolean glassSkyWalkerEnabled;
	private boolean endermiteInfestedBlocksEnabled;
	private boolean gravitizedLeavesEnabled;
	public  List<ButEvents> enabledEvents;
	
	
	// how many events active at a time
	public int howManyActive;
	
	// how many to change every time they change
	public int howManyChange;
	
	// how often they change
	public int howOftenChange;
	
	// event-specific settings	
	public boolean tntRainGriefBlocks;
	public int tntRainSpawnRate;
	public int randomEnchantsLevelCap;
	
	public void read() {
		// load enabled events
		tntRainEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.tnt-rain"));
		eggsOPEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.eggs-op"));
		randomEnchantsEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.random-enchants"));
		mobsStackedEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.mobs-stacked"));
		randomBlocksWalkingEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.random-blocks-walking"));
		glassSkyWalkerEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.glass-sky-walker"));
		endermiteInfestedBlocksEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.endermite-infested-blocks"));
		gravitizedLeavesEnabled = Boolean.parseBoolean(plugin.getConfig().getString("enabled.gravitized-leaves"));
		enabledEvents = getEnabledEventsList();
		
		// timer-specific settings
		howManyActive = Integer.parseInt(plugin.getConfig().getString("timer.how-many-active"));
		howOftenChange = Integer.parseInt(plugin.getConfig().getString("timer.how-often-change"));
		howManyChange = Integer.parseInt(plugin.getConfig().getString("timer.how-many-change"));
		
		// event-specific settings
		tntRainGriefBlocks = Boolean.parseBoolean(plugin.getConfig().getString("tnt-rain.grief"));
		tntRainSpawnRate = Integer.parseInt(plugin.getConfig().getString("tnt-rain.spawn-rate"));
		
		randomEnchantsLevelCap = Integer.parseInt(plugin.getConfig().getString("random-enchants.level-cap"));
		
	}
	
	public List<ButEvents> getEnabledEventsList() {
		List<ButEvents> enabled = new ArrayList<ButEvents>();
		
		if (tntRainEnabled)
			enabled.add(ButEvents.TntRain);
		if (eggsOPEnabled)
			enabled.add(ButEvents.EggsOP);
		if (randomEnchantsEnabled)
			enabled.add(ButEvents.RandomEnchants);
		if (mobsStackedEnabled) 
			enabled.add(ButEvents.MobsStacked);
		if (randomBlocksWalkingEnabled) 
			enabled.add(ButEvents.RandomBlocksWalking);
		if (glassSkyWalkerEnabled) 
			enabled.add(ButEvents.GlassSkyWalker);
		if (endermiteInfestedBlocksEnabled) 
			enabled.add(ButEvents.EndermiteInfestedBlocks);
		if (gravitizedLeavesEnabled)
			enabled.add(ButEvents.GravitizedLeaves);
		
		for (int i = 0; i < enabled.size(); i++) {
			Bukkit.broadcastMessage(enabled.get(i).toString());
		}
		
		return enabled;
	}
	
}
