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
	
	public static ArrayList<ItemStack> luckyFishingLootTable = new ArrayList<ItemStack>(
	        Arrays.asList(
	        		new ItemStack(Material.BOW),
	        		new ItemStack(Material.ENCHANTED_BOOK),
	        		new ItemStack(Material.FISHING_ROD),
	        		new ItemStack(Material.NAME_TAG),
	        		new ItemStack(Material.NAUTILUS_SHELL),
	        		new ItemStack(Material.SADDLE)
	        		));
	
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
		ItemStack item = luckyFishingLootTable.get(new Random()
				.nextInt(luckyFishingLootTable.size()));
		
		// If item is an enchanted book, fishing rod, or bow, 
		// give it 1 to four random enchants
		// (Create different enchantment tables for the different 
		if (item.getType() == Material.ENCHANTED_BOOK ||
			item.getType() == Material.FISHING_ROD ||
			item.getType() == Material.BOW) {
			
		}
		
		return item;
	}
}
