package com.spelinchanp.minecraftBut;

import java.lang.Math;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class MinecraftBut extends JavaPlugin {
	private static Plugin plugin;
	private static ButEvent butEvent;

	@Override
	public void onEnable() {
		Bukkit.broadcastMessage("'Minecraft, but' has been enabled!");

		MinecraftBut();
	}

	@Override
	public void onDisable() {
	}

	public void MinecraftBut() {
		// Set the plugin
		plugin = this;
		
		// Register the event listener
		getServer().getPluginManager().registerEvents(new MinecraftButListener(), this);
		

		// Select a new MinecraftButEvent every five minutes
		Bukkit.getScheduler()
			.scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MinecraftBut"),
				new Runnable() {
					@Override
					public void run() {
						// Randomly select a new event
						ButEvent.setRandomButEvent(); 
						//ButEvent.butEvent = ButEvents.GlassSkyWalker;
						
						// Instantiate MinecraftEvent
						// (all other listener-based events are run in MinecraftButListener)
						MinecraftEvent event = new MinecraftEvent(plugin, ButEvent.butEvent);	
						
						//Bukkit.broadcastMessage(ButEvent.butEvent.toString());

						switch (ButEvent.butEvent) {
						case TntRain:		
							Bukkit.broadcastMessage(
									ChatColor.RED + "TNT Rain has commenced! TNT will drop every ten seconds! Slap on some leather boots to become immune!");
							break;
						case RandomBlocksWalking:	
							Bukkit.broadcastMessage(
									ChatColor.GREEN + "Grass block chaos has commenced! Every grass block you "
											+ "walk on will change to a random block!");		
							break;
						case EggsOP:
							Bukkit.broadcastMessage(
									ChatColor.YELLOW + "Thrown eggs will spawn OP items!");		
							break;
						case RandomEnchants:
							Bukkit.broadcastMessage(
									ChatColor.BLUE + "Random enchants has commenced! A random item in your inventory"
											+ " will be given a random enchantment every three seconds");
							break;
						case MobsStacked:
							Bukkit.broadcastMessage(
									ChatColor.DARK_GRAY + "Mob stacking has commenced! "
											+ "Every mob has spawns will now be stacked!");
							break;
						case GlassSkyWalker:
							Bukkit.broadcastMessage(
									ChatColor.WHITE + "Glass sky walker has commenced!");
							break;
						}
					}
				}, 1, 20 * 30);

	}
}
