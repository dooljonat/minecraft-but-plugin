package com.spelinchanp.minecraftBut;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;



public class MinecraftButListener implements Listener {
	public static int timer;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
	}
	
	@EventHandler
	public void RandomBlocksWalking(PlayerMoveEvent e ) {
		if (ButEvent.butEvent == ButEvents.RandomBlocksWalking) {
			Player player = e.getPlayer();
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if (block.getType() == Material.GRASS_BLOCK) {
				Material mat = Material.AIR;
				
				mat = LootTablesBlocks
						.walkableBlocks
						.get(new Random().nextInt(
								LootTablesBlocks.walkableBlocks.size()));

				block.setType(mat);
			}
		}
	}
	
	@EventHandler
	public void EggBreakEvent(ProjectileHitEvent e) {
		if (ButEvent.butEvent == ButEvents.EggsOP) {
			if (e.getEntity() instanceof Egg) {
				Egg egg = (Egg) e.getEntity();
				Block block = egg.getLocation().getBlock();
					
				if (block != null) {
					Location loc = block.getLocation();
						
					ItemStack item = LootGenerator.randomLuckyFishingItem();
					loc.getWorld().dropItem(loc, 
								item);
					
					timer++;
					Bukkit.broadcastMessage(String.valueOf(timer));
				}
			}
			

		}
	}
}
