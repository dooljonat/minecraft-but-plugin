package com.spelinchanp.minecraftBut.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.spelinchanp.minecraftBut.Settings;
import com.spelinchanp.minecraftBut.Utils;

public class RandomEnchants {
	private Plugin plugin;
	private Settings settings;
	
	public RandomEnchants(Plugin plugin, Settings settings) {
		this.plugin = plugin;
		this.settings = settings;
		
		run();
	}
	
	private void run() {
		randomEnchantsRunnable = randomEnchants;
		randomEnchantsRunnable.runTaskTimer(plugin, 0, 20*1);
	}
	
	private BukkitRunnable randomEnchantsRunnable = null;
	private BukkitRunnable randomEnchants = new BukkitRunnable() {
		int timesRun = 0;
		@Override
		public void run() {
			// Select a random item from the player's inventory
			// and enchant it
			int levelCap = settings.randomEnchantsLevelCap;
			
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
					ItemStack newItem;
					if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) 
							&& newEnchant == Enchantment.SILK_TOUCH) {
						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.LOOT_BONUS_BLOCKS, 
								item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), 
								levelCap);
					}
					else if (item.containsEnchantment(Enchantment.SILK_TOUCH) 
							&& newEnchant == Enchantment.LOOT_BONUS_BLOCKS) {
						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.SILK_TOUCH, 
								item.getEnchantmentLevel(Enchantment.SILK_TOUCH), 
								levelCap);
					}
					else {
						newItem = Utils.addOrStackEnchantment(item, 
								newEnchant, 
								item.getEnchantmentLevel(newEnchant), 
								levelCap);
					}
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

						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.LOOT_BONUS_BLOCKS, 
								item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), 
								levelCap);
					}
					else if (item.containsEnchantment(Enchantment.SILK_TOUCH) 
							&& newEnchant == Enchantment.LOOT_BONUS_BLOCKS) {
						
						newItem = Utils.addOrStackEnchantment(item, 
								Enchantment.SILK_TOUCH, 
								item.getEnchantmentLevel(Enchantment.SILK_TOUCH), 
								levelCap);
					}
					else {
						newItem = Utils.addOrStackEnchantment(item, 
								newEnchant, 
								item.getEnchantmentLevel(newEnchant), 
								levelCap);
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
}
