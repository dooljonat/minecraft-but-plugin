package com.spelinchanp.minecraftBut.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.spelinchanp.minecraftBut.Utils;

public class GravitizedLeaves {
	private Plugin plugin;
	
	public GravitizedLeaves(Plugin plugin) {
		this.plugin = plugin;
		
		run();
	}
	
	private void run() {
		gravitizedLeavesRunnable = gravitizedLeaves;
		gravitizedLeavesRunnable.runTaskTimer(plugin, 0, 20*3);
	}
	
	private BukkitRunnable gravitizedLeavesRunnable = null;
	private BukkitRunnable gravitizedLeaves = new BukkitRunnable() {
		int timesRun = 0;
		
		@Override
		public void run() {
			// Gravitize all the leaves around the player every three seconds
			
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
			
			for (int i = 0; i < players.size(); i++) {
				World world = players.get(i).getWorld();
				Location playerLoc = players.get(i).getLocation();
				List<Block> nearbyBlocks = Utils.getNearbyBlocks(playerLoc, 15, "_LEAVES");
				
				for (int j = 0; j < nearbyBlocks.size(); j++) {
					Block block = nearbyBlocks.get(j);
					
					world.spawnFallingBlock(block.getLocation(), block.getBlockData());
					block.setType(Material.AIR);
				}
			}		
			timesRun++;
			if (timesRun == 10) {
				cancel();
				gravitizedLeavesRunnable = null;
			}
		}
	};
}
