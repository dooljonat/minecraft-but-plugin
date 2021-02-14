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
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.Plugin;

public final class Utils {
	private static Plugin plugin = Bukkit.getPluginManager().getPlugin("MinecraftBut");
	
	private static Random rand = new Random();
	
	/* Returns a random number within the specified range */
	public static int getRandomIntRange(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	/* Returns a random number within the specified range */
	public static double getRandomDoubleRange(double min, double max) {
		return ((Math.random() * (max - min)) + min);
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
	
	/* Function to get every item in a player's inventory, excluding null/empty slots and armor */
	public static List<ItemStack> getNonNullInventory(PlayerInventory inventory) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for (ItemStack i : inventory.getStorageContents()) {
			if (i != null) {
				stack.add(i);
			}
		}
		return stack;
	}
	
	/* Function to get every tool in a player's inventory, excluding null/empty slots and armor */
	public static List<ItemStack> getInventoryTools(PlayerInventory inventory) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for (ItemStack i : inventory.getStorageContents()) {
			if (i != null) {
				String type = i.getType().toString();
				if (type.endsWith("_PICKAXE") ||
						type.endsWith("_SHOVEL") ||
						type.endsWith("_SWORD") ||
						type.endsWith("_AXE") ||
						type.endsWith("_HOE") ||
						type == "SHEARS") {
					stack.add(i);
				}
			}
		}
		return stack;
	}
	
	/* Function to get armor contents of player */
	public static List<ItemStack> getNonNullArmor(PlayerInventory inventory) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for (ItemStack i : inventory.getArmorContents()) {
			if (i != null) {
				stack.add(i);
			}
		}
		return stack;
	}
	
	/* Function to return true is player's inventory is empty */
	public static boolean isInventoryEmpty(Player p){
		   for(ItemStack item : p.getInventory().getContents())
		   {
		      if(item != null)
		         return false;
		   }
		return true;
	}
	
	/* Function that returns true is ItemStack is a helmet */
	public static boolean isHelmet(ItemStack i) {
		if (i == null) {
			return false;
		}
		
		String type = i.getType().toString();
		if (type.endsWith("_HELMET")) {
			return true;
		}
		return false;
	}
	
	/* Function that returns true is ItemStack is a chestplate */
	public static boolean isChestplate(ItemStack i) {
		if (i == null) {
			return false;
		}
		
		String type = i.getType().toString();
		if (type.endsWith("_CHESTPLATE")) {
			return true;
		}
		return false;
	}
	
	/* Function that returns true is ItemStack is leggings */
	public static boolean isLeggings(ItemStack i) {
		if (i == null) {
			return false;
		}
		
		String type = i.getType().toString();
		if (type.endsWith("_LEGGINGS")) {
			return true;
		}
		return false;
	}
	
	/* Function that returns true is ItemStack is boots */
	public static boolean isBoots(ItemStack i) {
		if (i == null) {
			return false;
		}
		
		String type = i.getType().toString();
		if (type.endsWith("_BOOTS")) {
			return true;
		}
		return false;
	}
	
	/* Function to test if player has armor without a null pointer exception */
	public static boolean hasArmorType(ItemStack item, Material type)
	{
	    return (item == null ? false : item.getType() == type);
	}
		
	/* Returns a random enchantment */
	public static Enchantment getRandomEnchantment() {
		return Enchantment.values()[(int) (Math.random()*Enchantment.values().length)];
	}
	
	// If item already has enchantment, stack the level
	// if not, add new enchant
	public static ItemStack addOrStackEnchantment(ItemStack item, 
			Enchantment enchant, 
			int currentLevel, 
			int levelCap) {
		if (currentLevel > 0) {
			// level cap
			if (currentLevel < levelCap) {
				item.addUnsafeEnchantment(enchant, currentLevel+1);
			}
		}
		else
			item.addUnsafeEnchantment(enchant, 1);
		
		return item;
	}
	
	// If item already has enchantment, stack the level
	// if not, add new enchant
	// if item has silk touch, dont add fortune,
	// vice versa
	public static ItemStack addOrStackEnchantmentFOST(ItemStack item, 
			Enchantment enchant, 
			int currentLevel, 
			int levelCap) {
		
		// If item has fortune, dont add silk touch
		if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)
				&& enchant == Enchantment.SILK_TOUCH) {
			item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 
					item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)+1);
		}
		// If item has silk touch, dont add fortune
		else if (item.containsEnchantment(Enchantment.SILK_TOUCH)
				&& enchant == Enchantment.LOOT_BONUS_BLOCKS) {
			item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 
					item.getEnchantmentLevel(Enchantment.SILK_TOUCH)+1);
		}
		else if (currentLevel > 0) {
			// level cap
			if (currentLevel < levelCap) {
				item.addUnsafeEnchantment(enchant, currentLevel+1);
			}
		}
		else
			item.addUnsafeEnchantment(enchant, 1);
		
		return item;
	}
	
	public static ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
	
}
