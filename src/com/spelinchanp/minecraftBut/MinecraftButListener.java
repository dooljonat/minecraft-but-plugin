package com.spelinchanp.minecraftBut;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class MinecraftButListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
	}
	
	@EventHandler
	public void RandomBlocksWalking(PlayerMoveEvent e ) {
		if (ButEvent.butEvent == ButEvents.RandomBlocksWalking) {
			Player player = e.getPlayer();
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if (block.getType() == Material.GRASS_BLOCK) {
				boolean isBlock = false;
				Material mat = Material.AIR;
				
				// this is disgusting, please find a better way to check if a material
				// is a block in the future
				while (!isBlock) {
					mat = Material.values()
							[new Random().nextInt(Material.values().length)];
					
					if (mat.isBlock())
						isBlock = true;
				}

				block.setType(mat);
			}
		}
	}
	
	@EventHandler
	public void EggBreakEvent(ProjectileHitEvent e) {
		if (ButEvent.butEvent == ButEvents.EggsOP) {
			if (e.getEntity() instanceof Egg) {
				Egg egg = (Egg) e.getEntity();
				
			}
		}
	}
}
