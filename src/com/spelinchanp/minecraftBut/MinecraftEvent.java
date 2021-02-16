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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
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

public class MinecraftEvent {
	private Plugin plugin;
	private ButEvents event;
	
	MinecraftEvent(Plugin plugin, ButEvents event) {
		this.plugin = plugin;
		if (event == ButEvents.TntRain) {
			tntRainRunnable = tntRain;
			tntRainRunnable.runTaskTimer(plugin, 20, 20*10);
		}
		else if (event == ButEvents.RandomEnchants) {
			randomEnchantsRunnable = randomEnchants;
			randomEnchantsRunnable.runTaskTimer(plugin, 0, 20*1);
		}
		else if (event == ButEvents.MobsStacked) {
			mobsStackedRunnable = mobsStacked;
			mobsStackedRunnable.runTaskTimer(plugin, 0, 20*2);
		}
		
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
					Utils.createTNTnoDestruction(loc, world);
				}
			}		
			timesRun++;
			if (timesRun == 3) {
				cancel();
				tntRainRunnable = null;
			}
		}
	};
	
	private BukkitRunnable randomEnchantsRunnable = null;
	private BukkitRunnable randomEnchants = new BukkitRunnable() {
		int timesRun = 0;
		@Override
		public void run() {
			// Select a random item from the player's inventory
			// and enchant it
			Bukkit.broadcastMessage(ChatColor.BLUE + "Abra cadabra!");
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
				
			for (int i = 0; i < players.size(); i++) {
				// get actual inventory
				PlayerInventory inventory = players.get(i).getInventory();
					
				// get tools without null space
				List<ItemStack> tools = Utils.getInventoryTools(inventory);
				// get armor without null space
				List<ItemStack> armor = Utils.getNonNullArmor(inventory);
					
				int activeToolsSize = tools.size();
				int activeArmorSize = armor.size();
				int totalActiveSize = activeToolsSize+activeArmorSize;
					
				// generate random number to pick armor or item
				double num = Utils.getRandomDoubleRange(0, totalActiveSize);
					
				// pick armor
				if (num > totalActiveSize-activeArmorSize && armor.size() != 0) {
					ItemStack item = armor.get(new Random().nextInt(armor.size()));
						
					Enchantment newEnchant = Utils.getRandomEnchantment();
					ItemStack newItem = Utils.addOrStackEnchantment(item, 
							newEnchant, 
							item.getEnchantmentLevel(newEnchant), 
							100);
						
					if (Utils.isHelmet(newItem))
						inventory.setHelmet(newItem);
					else if (Utils.isChestplate(newItem)) 
						inventory.setChestplate(newItem);
					else if (Utils.isLeggings(newItem))
						inventory.setLeggings(newItem);
					else if (Utils.isBoots(newItem)) 
						inventory.setBoots(newItem);
						
				}
				// pick other
				if (tools.size() != 0) {
					ItemStack item = tools.get(new Random().nextInt(tools.size()));
						
					Enchantment newEnchant = Utils.getRandomEnchantment();
					ItemStack newItem;
					//Enchantment newEnchant = Enchantment.SILK_TOUCH;
					if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) 
							&& newEnchant == Enchantment.SILK_TOUCH) {
						Bukkit.broadcastMessage("FORTUNE");
						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.LOOT_BONUS_BLOCKS, 
								item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), 
								100);
					}
					else if (item.containsEnchantment(Enchantment.SILK_TOUCH) 
							&& newEnchant == Enchantment.LOOT_BONUS_BLOCKS) {
						Bukkit.broadcastMessage("SILK TOUCH");
						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.SILK_TOUCH, 
								item.getEnchantmentLevel(Enchantment.SILK_TOUCH), 
								100);
					}
					else {
						newItem = Utils.addOrStackEnchantment(item, 
								newEnchant, 
								item.getEnchantmentLevel(newEnchant), 
								100);
					}
					
						
					inventory.remove(item);
					inventory.addItem(newItem);
				}
			}
			timesRun++;
			if (timesRun == 30) {
				cancel();
				randomEnchantsRunnable = null;
			}
		}
	};
	
	private BukkitRunnable mobsStackedRunnable = null;
	private BukkitRunnable mobsStacked = new BukkitRunnable() {
		int timesRun = 0;
		
		@Override
		public void run() {
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
			
			for (int i = 0; i < players.size(); i++) {				
				List<Entity> nearbyEntities = Utils.getEntitiesAroundPoint(players.get(i).getLocation(), 45);
				for (int j = 0; j < nearbyEntities.size(); j++) {
					Entity entity = nearbyEntities.get(j);
					if (entity.getPassengers().isEmpty() &&
							entity.getCustomName() == null &&
							!entity.getType().equals(EntityType.PLAYER) &&
							!entity.getType().equals(EntityType.SQUID) &&
							!entity.getType().equals(EntityType.BAT) &&
							!(entity instanceof Item)) {
						World world = entity.getWorld();
						Location loc = entity.getLocation();
						entity.setCustomName("Stacked");
						
						// fix it so that it adds to the stack, not adds multiple entities to second layer
						int numToStack = Utils.getRandomIntRange(3, 15);
						Utils.addPassengers(entity, numToStack);
					}
				}
			}
			
			timesRun++;
			if (timesRun == 30) {
				cancel();
				randomEnchantsRunnable = null;
			}
		}
		
	};
	
	
}
