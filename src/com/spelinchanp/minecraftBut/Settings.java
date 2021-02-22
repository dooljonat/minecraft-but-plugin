package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.List;

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
	public double tntPower;
	public int randomEnchantsLevelCap;
	public int mobsStackedMin;
	public int mobsStackedMax;
	
	public void read() {
		// load enabled events
		this.tntRainEnabled = plugin.getConfig().getBoolean("enabled.tnt-rain");
		this.eggsOPEnabled = plugin.getConfig().getBoolean("enabled.eggs-op");
		this.randomEnchantsEnabled = plugin.getConfig().getBoolean("enabled.random-enchants");
		this.mobsStackedEnabled = plugin.getConfig().getBoolean("enabled.mobs-stacked");
		this.randomBlocksWalkingEnabled = plugin.getConfig().getBoolean("enabled.random-blocks-walking");
		this.glassSkyWalkerEnabled = plugin.getConfig().getBoolean("enabled.glass-sky-walker");
		this.endermiteInfestedBlocksEnabled = plugin.getConfig().getBoolean("enabled.endermite-infested-blocks");
		this.gravitizedLeavesEnabled = plugin.getConfig().getBoolean("enabled.gravitized-leaves");
		
		// create enabled events list
		this.enabledEvents = getEnabledEventsList();
		
		// timer-specific settings
		this.howManyActive = plugin.getConfig().getInt("timer.how-many-active");
		this.howOftenChange = plugin.getConfig().getInt("timer.how-often-change");
		this.howManyChange = plugin.getConfig().getInt("timer.how-many-change");
		
		// event-specific settings
		this.tntRainGriefBlocks = plugin.getConfig().getBoolean("tnt-rain.grief");
		this.tntRainSpawnRate = plugin.getConfig().getInt("tnt-rain.spawn-rate");
		this.tntPower = plugin.getConfig().getDouble("tnt-rain.power");
		
		this.randomEnchantsLevelCap = plugin.getConfig().getInt("random-enchants.level-cap");
		
		this.mobsStackedMin = plugin.getConfig().getInt("mobs-stacked.min-mobs");
		this.mobsStackedMax = plugin.getConfig().getInt("mobs-stacked.max-mobs");
		
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
			System.out.println(enabled.get(i).toString());
		}
		
		return enabled;
	}
	
}
