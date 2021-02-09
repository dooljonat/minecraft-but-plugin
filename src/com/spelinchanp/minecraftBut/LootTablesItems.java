package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class LootTablesItems {
	public static ArrayList<ItemStack> luckyFishingLootTable = loadFishingLootTable();
	
	
	public static ArrayList<ItemStack> loadFishingLootTable() {
		// In the future read this from a config file
		return new ArrayList<ItemStack>(
		        Arrays.asList(
		        		new ItemStack(Material.BOW),
		        		new ItemStack(Material.ENCHANTED_BOOK),
		        		new ItemStack(Material.FISHING_ROD),
		        		new ItemStack(Material.NAME_TAG),
		        		new ItemStack(Material.COD),
		        		new ItemStack(Material.NAUTILUS_SHELL),
		        		new ItemStack(Material.SADDLE)
		        		));
	}
}
