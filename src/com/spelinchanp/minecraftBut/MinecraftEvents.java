package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class MinecraftEvents {
	private Plugin plugin;
	
	MinecraftEvents(Plugin plugin) {
		this.plugin = plugin;
	}
	
	private BukkitRunnable tntRain = new BukkitRunnable() {
		@Override
		public void run() {
			if (ButEvent.butEvent == ButEvents.TntRain) {
				// Spawn a tnt block at the players location, 
				// schedule a delayed task to create an explosion which doesnt damage blocks 
				// and remove the tnt block 
				Bukkit.broadcastMessage(ChatColor.RED + "BOOM!");
				List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
				
				for (int i = 0; i < players.size(); i++) {
					// set TNT block
					Location loc = players.get(i).getLocation();
					World world = players.get(i).getWorld();
					Utils.createTNTnoDestruction(loc, world);
				}		
			}
		}
	};
	
	private BukkitRunnable randomEnchants = new BukkitRunnable() {
		@Override
		public void run() {
			if (ButEvent.butEvent == ButEvents.RandomEnchants) {
				// Select a random item from the player's inventory
				// and enchant it
				Bukkit.broadcastMessage(ChatColor.BLUE + "Abra cadabra!");
				List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
				
				for (int i = 0; i < players.size(); i++) {
					// get actual inventory
					PlayerInventory inventory = players.get(i).getInventory();
					
					// get inventory without null space
					List<ItemStack> inv = Utils.getNonNullInventory(players.get(i));
					
					// Get random item
					ItemStack item = inv.get(new Random().nextInt(inv.size()));
					
					// Check if armor slots are null first,
					// then check if the item is in an armor slot or not
					if (inventory.getHelmet() != null) {
						if (inventory.getHelmet().equals(item)) {
							// Create new item and random enchantment
							ItemStack newItem = item;
							Enchantment newEnchant = Utils.getRandomEnchantment();
							
							// If item already has enchantment, stack the level
							// if not, add new enchant
							if (item.containsEnchantment(newEnchant)) {
								// level cap
								if (item.getEnchantmentLevel(newEnchant) < 100) 
									newItem.addUnsafeEnchantment(newEnchant, 
											item.getEnchantmentLevel(newEnchant)+1);
							}		
							else 
								newItem.addUnsafeEnchantment(newEnchant, 1);
							
							// Remove the item
							if (item == inventory.getHelmet()) 
								inventory.setHelmet(newItem);			
						} 
					}
					else if (inventory.getChestplate() != null) {
						if (inventory.getChestplate().equals(item)) {
							// Create new item and random enchantment
							ItemStack newItem = item;
							Enchantment newEnchant = Utils.getRandomEnchantment();
							
							// If item already has enchantment, stack the level
							// if not, add new enchant
							if (item.containsEnchantment(newEnchant)) {
								// level cap
								if (item.getEnchantmentLevel(newEnchant) < 100) 
									newItem.addUnsafeEnchantment(newEnchant, 
											item.getEnchantmentLevel(newEnchant)+1);
							}		
							else 
								newItem.addUnsafeEnchantment(newEnchant, 1);
							
							// Remove the item
							if (item == inventory.getHelmet()) 
								inventory.setChestplate(newItem);			
						} 
					}
					else if (inventory.getLeggings() != null) {
						if (inventory.getLeggings().equals(item)) {
							// Create new item and random enchantment
							ItemStack newItem = item;
							Enchantment newEnchant = Utils.getRandomEnchantment();
							
							// If item already has enchantment, stack the level
							// if not, add new enchant
							if (item.containsEnchantment(newEnchant)) {
								// level cap
								if (item.getEnchantmentLevel(newEnchant) < 100) 
									newItem.addUnsafeEnchantment(newEnchant, 
											item.getEnchantmentLevel(newEnchant)+1);
							}		
							else 
								newItem.addUnsafeEnchantment(newEnchant, 1);
							
							// Remove the item
							if (item == inventory.getHelmet()) 
								inventory.setLeggings(newItem);			
						} 
					}
					else if (inventory.getBoots() != null) {
						if (inventory.getBoots().equals(item)) {
							// Create new item and random enchantment
							ItemStack newItem = item;
							Enchantment newEnchant = Utils.getRandomEnchantment();
							
							// If item already has enchantment, stack the level
							// if not, add new enchant
							if (item.containsEnchantment(newEnchant)) {
								// level cap
								if (item.getEnchantmentLevel(newEnchant) < 100) 
									newItem.addUnsafeEnchantment(newEnchant, 
											item.getEnchantmentLevel(newEnchant)+1);
							}		
							else 
								newItem.addUnsafeEnchantment(newEnchant, 1);
							
							// Remove the item
							if (item == inventory.getHelmet()) 
								inventory.setBoots(newItem);			
						} 
					}
					else {
						// Create new item and random enchantment
						ItemStack newItem = item;
						Enchantment newEnchant = Utils.getRandomEnchantment();
						
						// if item has fortune and newEnchant = silk touch vice versa
						if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)
								&& newEnchant == Enchantment.SILK_TOUCH) {
							newItem.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 
									item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
						}
						else if (item.containsEnchantment(Enchantment.SILK_TOUCH)
								&& newEnchant == Enchantment.LOOT_BONUS_BLOCKS) {
							newItem.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 
									item.getEnchantmentLevel(Enchantment.SILK_TOUCH));
						}
						// If item already has enchantment, stack the level
						// if not, add new enchant
						if (item.containsEnchantment(newEnchant)) {
							// level cap
							if (item.getEnchantmentLevel(newEnchant) < 100) 
								newItem.addUnsafeEnchantment(newEnchant, 
										item.getEnchantmentLevel(newEnchant)+1);
						}		
						else 
							newItem.addUnsafeEnchantment(newEnchant, 1);
						
						// Replace the item
						inventory.remove(item);
						inventory.addItem(newItem);	
					}
				}
			}
		}
	};
	
	
	public void enableTNTrain() {
		tntRain.runTaskTimer(plugin, 0, 20*10);
	} 
	
	public void enableRandomEnchants() {
		randomEnchants.runTaskTimer(plugin, 0, 20*1);
	}
	
	public void disableAll() {	
		tntRain.cancel();	
		randomEnchants.cancel();
		//Bukkit.getScheduler().cancelTask(randomEnchantsTask);
	}
	
}
