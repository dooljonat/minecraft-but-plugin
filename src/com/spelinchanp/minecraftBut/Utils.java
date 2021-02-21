package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.Plugin;

public final class Utils {
	private static Plugin plugin = Bukkit.getPluginManager().getPlugin("MinecraftBut");
	
	
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
						type == "SHEARS" ||
				        type == "BOW"){
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
		return LootTablesEnchants.allEnchants.get(new Random().nextInt(LootTablesEnchants.allEnchants.size()));
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
	
	/*Add usable enchantments to books*/
	public static ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
	
	/* Get all entities in a specific radius around a location*/
	public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {	
	    List<Entity> entities = new ArrayList<Entity>();
	    World world = location.getWorld();

	    // To find chunks we use chunk coordinates (not block coordinates!)
	    int smallX = (int) Math.floor((location.getX() - radius) / 16.0D);
	    int bigX = (int) Math.floor((location.getX() + radius) / 16.0D);
	    int smallZ = (int) Math.floor((location.getZ() - radius) / 16.0D);
	    int bigZ = (int) Math.floor((location.getZ() + radius) / 16.0D);

	    for (int x = smallX; x <= bigX; x++) {
	        for (int z = smallZ; z <= bigZ; z++) {
	            if (world.isChunkLoaded(x, z)) {
	                entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities())); // Add all entities from this chunk to the list
	            }
	        }
	    }

	    Iterator<Entity> entityIterator = entities.iterator(); // Create an iterator so we can loop through the list while removing entries
	    while (entityIterator.hasNext()) {
	        if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) { // If the entity is outside of the sphere...
	            entityIterator.remove(); // Remove it
	        }
	    }
	    return entities;
	}
	
	/* Recursive function to add passengers to an entity */
	public static void addPassengers(Entity entity, int amount) {
		World world = entity.getWorld();
		Location loc = entity.getLocation();
		
		if (amount > 0) {
			Entity newEntity = world.spawnEntity(loc, entity.getType());
			newEntity.setCustomName("Stacker");
			entity.addPassenger(newEntity);	
			//Bukkit.broadcastMessage("Stacked a " + entity.getType().toString());
			
			addPassengers(newEntity, amount-1);
		}
		else {
			return;
		}
		
	}
	
	/* Get nearby blocks */
	public static List<Block> getNearbyBlocks(Location loc, int radius) {
		List<Block> blocks = new ArrayList<Block>();
		World world = loc.getWorld();
		
		// get nearby blocks
		for (int x = loc.getBlockX() - radius; x <= loc.getBlockX() + radius; x++) {
			for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; z++) {
				for (int y = loc.getBlockY() - radius; y <= loc.getBlockY() + radius; y++) {
					Block block = world.getBlockAt(x, y, z);
					if (block != null &&
						!block.getType().equals(Material.AIR)) {
						blocks.add(block);
					}
				}
			}
		}
		
		return blocks;
	}
	
	/* Get nearby blocks of a specific type */
	public static List<Block> getNearbyBlocks(Location loc, int radius, Material type) {
		List<Block> blocks = new ArrayList<Block>();
		World world = loc.getWorld();
		
		// get nearby blocks of a specific type
		for (int x = loc.getBlockX() - radius; x <= loc.getBlockX() + radius; x++) {
			for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; z++) {
				for (int y = loc.getBlockY() - radius; y <= loc.getBlockY() + radius; y++) {
					Block block = world.getBlockAt(x, y, z);
					if (block != null &&
						block.getType() == type) {
						blocks.add(block);
					}
				}
			}
		}
		
		return blocks;
	}
	
	/* Get nearby blocks of a general type based on their string suffix */
	/* ex: all material leaves end with "_LEAVES" */
	public static List<Block> getNearbyBlocks(Location loc, int radius, String suffix) {
		List<Block> blocks = new ArrayList<Block>();
		World world = loc.getWorld();
		
		// get nearby blocks of a specific type
		for (int x = loc.getBlockX() - radius; x <= loc.getBlockX() + radius; x++) {
			for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; z++) {
				for (int y = loc.getBlockY() - radius; y <= loc.getBlockY() + radius; y++) {
					Block block = world.getBlockAt(x, y, z);
					if (block != null) {
						String type = block.getType().toString();
						
						if (type.endsWith(suffix)) {
							blocks.add(block);
						}
					}
				}
			}
		}
		
		return blocks;
	}
}
