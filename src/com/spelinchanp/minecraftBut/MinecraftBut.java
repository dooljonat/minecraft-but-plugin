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
	private static BukkitTask task = null;

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
						// Instantiate MinecraftEvent
						MinecraftEvent events = new MinecraftEvent(plugin);	
						
						// Randomly select a new event
						ButEvent.setRandomButEvent(); 

						switch (ButEvent.butEvent) {
						case TntRain:
							if (task != null) {
								task.cancel();
							}
							task = events.enableTNTrain();
							
							Bukkit.broadcastMessage(
									ChatColor.RED + "TNT Rain has commenced! TNT will drop every three seconds");
							break;
						case RandomBlocksWalking:	
							if (task != null) {
								task.cancel();
							}
							
							Bukkit.broadcastMessage(
									ChatColor.GREEN + "Grass block chaos has commenced! Every grass block you "
											+ "walk on will change to a random block!");		
							break;
						case EggsOP:
							if (task != null) {
								task.cancel();
							}
							
							Bukkit.broadcastMessage(
									ChatColor.YELLOW + "Thrown eggs will spawn OP items!");		
							break;
						case RandomEnchants:
							if (task != null) {
								task.cancel();
							}
							task = events.enableRandomEnchants();
							
							Bukkit.broadcastMessage(
									ChatColor.BLUE + "Random enchants has commenced! A random item in your inventory"
											+ " will be given a random enchantment every three seconds");
							break;
						}
					}
				}, 1, 20 * 30);

	}
}
