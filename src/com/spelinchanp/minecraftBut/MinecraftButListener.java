package com.spelinchanp.minecraftBut;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;



public class MinecraftButListener implements Listener {
	public ButEvent but;
	
	MinecraftButListener(ButEvent but) {
		this.but = but;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
	}
	
	@EventHandler
	public void RandomBlocksWalking(PlayerMoveEvent e ) {
		if (but.butEvents.contains(ButEvents.RandomBlocksWalking)) {
			Player player = e.getPlayer();
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if (block.getType() == Material.GRASS_BLOCK) {
				Material mat = Material.AIR;
				
				mat = LootTablesBlocks
						.walkableBlocks
						.get(new Random().nextInt(
								LootTablesBlocks.walkableBlocks.size()));
				
				block.setType(mat);
				
				if (mat == Material.SPAWNER) {
					BlockState blockState = block.getState();
					CreatureSpawner spawner = ((CreatureSpawner)blockState);
					spawner.setSpawnedType(
							LootTablesEntities.spawnerEntities.get(new Random().nextInt(
									LootTablesEntities.spawnerEntities.size())));
					blockState.update();
				}
			}
		}
	}
	
	@EventHandler 
	public void EndermiteInfestedBlocks(BlockBreakEvent e) {
		if (but.butEvents.contains(ButEvents.EndermiteInfestedBlocks)) {
			Block block = e.getBlock();
			
			World world = block.getWorld();
			Location loc = block.getLocation();
			
			world.spawnEntity(loc, EntityType.ENDERMITE);
		}
	}
	
	@EventHandler
	public void GlassSkyWalker(PlayerMoveEvent e ) {
		if (but.butEvents.contains(ButEvents.GlassSkyWalker)) {
			Player player = e.getPlayer();
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if (block.getType() == Material.AIR) {
				Material mat = Material.AIR;
				
				mat = LootTablesBlocks
						.stainedGlassBLocks
						.get(new Random().nextInt(
								LootTablesBlocks.stainedGlassBLocks.size()));
				
				block.setType(mat);
			}
		}
	}
	
	@EventHandler
	public void EggsOP(ProjectileHitEvent e) {
		if (but.butEvents.contains(ButEvents.EggsOP)) {
			if (e.getEntity() instanceof Egg) {
				Egg egg = (Egg) e.getEntity();
				Block block = egg.getLocation().getBlock();
					
				if (block != null) {
					Location loc = block.getLocation();
						
					ItemStack item = LootGenerator.randomLuckyFishingItem();
					loc.getWorld().dropItem(loc, 
								item);
				}
			}
		}
	}
}
