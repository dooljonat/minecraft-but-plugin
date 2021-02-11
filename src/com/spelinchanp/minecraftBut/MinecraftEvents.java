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
					// get armor without null space
					List<ItemStack> armor = Utils.getNonNullArmor(players.get(i));
					
					int activeInvSize = inv.size();
					int activeArmorSize = armor.size();
					Bukkit.broadcastMessage(String.valueOf(armor.size()));
					int totalActiveSize = activeInvSize+activeArmorSize;
					
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
					else if (inv.size() != 0) {
						ItemStack item = inv.get(new Random().nextInt(inv.size()));
						
						Enchantment newEnchant = Utils.getRandomEnchantment();
						ItemStack newItem = Utils.addOrStackEnchantmentFOST(item, 
								newEnchant, 
								item.getEnchantmentLevel(newEnchant), 
								100);
						
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
