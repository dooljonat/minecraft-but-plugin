package com.spelinchanp.minecraftBut;

import java.lang.Math;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftBut extends JavaPlugin {
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
		// Register the event listener
		getServer().getPluginManager().registerEvents(new MinecraftButListener(), this);
		
		// Instantiate MinecraftBut
		MinecraftEvents events = new MinecraftEvents(this);
		
		// Enable all
		events.enableTNTrain();
		events.enableRandomEnchants();

		// Select a new MinecraftButEvent every five minutes
		Bukkit.getScheduler()
			.scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MinecraftBut"),
				new Runnable() {
					@Override
					public void run() {
						
						// Randomly select a new event
						//ButEvent.setRandomButEvent(); 
						ButEvent.butEvent = ButEvents.EggsOP;

						switch (ButEvent.butEvent) {
						case TntRain:
							Bukkit.broadcastMessage(
									ChatColor.RED + "TNT Rain has commenced! TNT will drop every three seconds");
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
						}
					}

				}, 1, 20 * 30);

	}
}
