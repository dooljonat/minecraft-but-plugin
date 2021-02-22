package com.spelinchanp.minecraftBut.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.spelinchanp.minecraftBut.Settings;
import com.spelinchanp.minecraftBut.Utils;

public class TntRain {
	private Plugin plugin;
	private Settings settings;
	
	public TntRain(Plugin plugin, Settings settings) {
		this.plugin = plugin;
		this.settings = settings;
		
		run();
	}
	
	private void run() {
		tntRainRunnable = tntRain;
		tntRainRunnable.runTaskTimer(plugin, 0, 20 *
				30/settings.tntRainSpawnRate);
	}
	
	private BukkitRunnable tntRainRunnable = null;
	private BukkitRunnable tntRain = new BukkitRunnable() {
		int timesRun = 0;
		
		@Override
		public void run() {
			// Spawn a tnt block at the players location, 
			// schedule a delayed task to create an explosion which doesnt damage blocks 
			// and remove the tnt block 
			Bukkit.broadcastMessage(ChatColor.RED + "BOOM!");
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
				
			for (int i = 0; i < players.size(); i++) {
				// if player isn't wearing leather boots, set TNT block
				if (!Utils.hasArmorType(players.get(i).getInventory().getBoots(), Material.LEATHER_BOOTS)) {
					Location loc = players.get(i).getLocation();
					World world = players.get(i).getWorld();
					
					// if tnt-rain.grief is set to false, spawn not block damaging tnt
					if (settings.tntRainGriefBlocks) 
						Utils.createTNTDestruction(loc, world, (float)settings.tntPower);
					else 
						Utils.createTNTnoDestruction(loc, world, (float)settings.tntPower);
				}
			}		
			timesRun++;
			if (timesRun == 3) {
				cancel();
				tntRainRunnable = null;
			}
		}
	};
}
