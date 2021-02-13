package com.spelinchanp.minecraftBut;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class LootGenerator {
	public static ItemStack randomLuckyFishingItem() {
		ItemStack item = new ItemStack(LootTablesItems.getRandomLuckyFishingItem());
		if (item.getType() == Material.ENCHANTED_BOOK) {
			int numEnchants = Utils.getRandomIntRange(3, 5);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment enchant = LootTablesEnchants.getRandomLuckyFishingBookEnchant();
				int level = Utils.getRandomIntRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(enchant));
				
				item = Utils.addBookEnchantment(item, enchant, level);
			}
		}
		else if (item.getType() == Material.FISHING_ROD) {
			int numEnchants = Utils.getRandomIntRange(2, 3);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment enchant = LootTablesEnchants.getRandomLuckyFishingBookEnchant();
				int level = Utils.getRandomIntRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(enchant));
				
				item = Utils.addOrStackEnchantment(item, enchant, level, 5);
			}
		}
		else if (item.getType() == Material.BOW ) {
			int numEnchants = Utils.getRandomIntRange(2, 5);
			
			for (int i = 0; i < numEnchants; i++) {
				Enchantment enchant = LootTablesEnchants.getRandomLuckyFishingBookEnchant();
				int level = Utils.getRandomIntRange(1, LootTablesEnchants.getLuckyFishingMaxLevels(enchant));
				
				item = Utils.addOrStackEnchantment(item, enchant, level, 5);
			}
		}

		return item;
		
	}
}
