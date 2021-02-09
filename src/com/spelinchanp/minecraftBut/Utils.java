package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public final class Utils {
	private static Plugin plugin = Bukkit.getPluginManager().getPlugin("MinecraftBut");
	
	private static Random rand = new Random();
	
	/* Returns a random number within the specified range */
	public static int getRandomRange(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
		
	/* Function to spawn TNT in a specified location in a specified world
	 * that damages entities but does not destroy blocks */
	public static void createTNTnoDestruction(Location loc, World world) {
		// Set TNT
		loc.getBlock().setType(Material.TNT);
		
		// create delayed explosion
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			loc.getBlock().setType(Material.AIR);
		    world.createExplosion(loc, 2.5f, false, false);
		}, 20L * 3L);
	}
	
	/* Function to get every item in a player's inventory, excluding null/empty slots */
	public static List<ItemStack> getInventory(Player player) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for (ItemStack i : player.getInventory().getContents()) {
			if (i != null) {
				stack.add(i);
			}
		}
		return stack;
	}
	
	/* Returns a random enchantment */
	public static Enchantment getRandomEnchantment() {
		return Enchantment.values()[(int) (Math.random()*Enchantment.values().length)];
	}
	
	/* Returns a random lucky item based off of fishing luck of the sea 3 loot tables */
	public static ItemStack getRandomLuckyFishingItem() {
		ItemStack item = LootTablesItems.luckyFishingLootTable
				.get(
						rand.nextInt(LootTablesItems.luckyFishingLootTable.size()));
		
		// If item is an enchanted book, fishing rod, or bow, 
		// give it 1 to four3 random enchants
		// (Create different enchantment tables for the different 
		if (item.getType() == Material.ENCHANTED_BOOK) {
			int numEnchants = rand.nextInt(5);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment ench = LootTablesEnchants.luckyFishingBookEnchants
					.get(rand.nextInt(LootTablesEnchants.luckyFishingBookEnchants.size()));
				int level = getRandomRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(ench));
			
				item.addUnsafeEnchantment(ench, level);
			}	
		}
		else if (item.getType() == Material.BOW) {
			int numEnchants = rand.nextInt(5);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment ench = LootTablesEnchants.luckyFishingBowEnchants
					.get(rand.nextInt(LootTablesEnchants.luckyFishingBowEnchants.size()));
				int level = getRandomRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(ench));
			
				item.addUnsafeEnchantment(ench, level);
			}	
		}
		else if (item.getType() == Material.FISHING_ROD) {
			int numEnchants = rand.nextInt(5);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment ench = LootTablesEnchants.luckyFishingRodEnchants
					.get(rand.nextInt(LootTablesEnchants.luckyFishingRodEnchants.size()));
				int level = getRandomRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(ench));
			
				item.addUnsafeEnchantment(ench, level);
			}	
		}
		
		return item;
	}
}
