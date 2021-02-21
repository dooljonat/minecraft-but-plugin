package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;

public class LootTablesItems {
	public static ArrayList<Material> luckyFishingLootTable = loadLuckyFishingLootTable();
	
	public static Material getRandomLuckyFishingItem() {
		return luckyFishingLootTable.get(new Random().nextInt(luckyFishingLootTable.size()));
	}
	
	private static ArrayList<Material> loadLuckyFishingLootTable() {
		// In the future read this from a config file
		return new ArrayList<Material>(
		        Arrays.asList(
		        		Material.BOW,
		        		Material.ENCHANTED_BOOK,
		        		Material.FISHING_ROD,
		        		Material.NAME_TAG,
		        		Material.COD,
		        		Material.NAUTILUS_SHELL,
		        		Material.SADDLE
		        		));
	}
}
