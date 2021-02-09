package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class LootTablesEnchants {
	public static ArrayList<Enchantment> luckyFishingBowEnchants = loadLuckyFishingBowEnchants();
	public static ArrayList<Enchantment> luckyFishingRodEnchants = loadLuckyFishingRodEnchants();
	public static ArrayList<Enchantment> luckyFishingBookEnchants = loadLuckyFishingBookEnchants();
	
	
	public static ArrayList<Enchantment> loadLuckyFishingBowEnchants() {
		// In the future read this from a config file
		return new ArrayList<Enchantment>(
		        Arrays.asList(
		        		Enchantment.ARROW_INFINITE,
		        		Enchantment.ARROW_KNOCKBACK,
		        		Enchantment.ARROW_DAMAGE,
		        		Enchantment.ARROW_FIRE
		        		));
	}
	
	public static ArrayList<Enchantment> loadLuckyFishingRodEnchants() {
		// In the future read this from a config file
		return new ArrayList<Enchantment>(
		        Arrays.asList(
		        		Enchantment.LURE,
		        		Enchantment.MENDING,
		        		Enchantment.LUCK,
		        		Enchantment.DURABILITY
		        		));
	}
	
	public static ArrayList<Enchantment> loadLuckyFishingBookEnchants() {
		// In the future read this from a config file
		return new ArrayList<Enchantment>(
		        Arrays.asList(
		        		Enchantment.DIG_SPEED,
		        		Enchantment.MENDING,
		        		Enchantment.ARROW_DAMAGE,
		        		Enchantment.ARROW_INFINITE,
		        		Enchantment.CHANNELING,
		        		Enchantment.DAMAGE_ALL,
		        		Enchantment.DEPTH_STRIDER,
		        		Enchantment.FIRE_ASPECT,
		        		Enchantment.FROST_WALKER,
		        		Enchantment.IMPALING,
		        		Enchantment.KNOCKBACK,
		        		Enchantment.LOOT_BONUS_BLOCKS,
		        		Enchantment.LOOT_BONUS_MOBS,
		        		Enchantment.LOYALTY,
		        		Enchantment.LUCK,
		        		Enchantment.LURE,
		        		Enchantment.MULTISHOT,
		        		Enchantment.OXYGEN,
		        		Enchantment.PIERCING,
		        		Enchantment.PROTECTION_ENVIRONMENTAL,
		        		Enchantment.PROTECTION_EXPLOSIONS,
		        		Enchantment.PROTECTION_FALL,
		        		Enchantment.PROTECTION_FIRE,
		        		Enchantment.PROTECTION_PROJECTILE,
		        		Enchantment.QUICK_CHARGE,
		        		Enchantment.RIPTIDE,
		        		Enchantment.SILK_TOUCH,
		        		Enchantment.SOUL_SPEED,
		        		Enchantment.SWEEPING_EDGE,
		        		Enchantment.THORNS,
		        		Enchantment.WATER_WORKER
		        		));
	}
	
	// NOTE: Base tier loot, therefore = max levels of vanilla minecraft enchants
	public static int getLuckyFishingMaxLevels(Enchantment enchant) {
		// Load these from a config file in the future
		if (enchant == Enchantment.IMPALING ||
				enchant == Enchantment.DIG_SPEED ||
				enchant == Enchantment.ARROW_DAMAGE ||
				enchant == Enchantment.DAMAGE_ARTHROPODS ||
				enchant == Enchantment.DAMAGE_UNDEAD ||
				enchant == Enchantment.DAMAGE_ALL) {
			return 5;
		}
		else if (enchant == Enchantment.PROTECTION_ENVIRONMENTAL ||
				enchant == Enchantment.PROTECTION_EXPLOSIONS ||
				enchant == Enchantment.PROTECTION_FALL ||
				enchant == Enchantment.PROTECTION_FIRE ||
				enchant == Enchantment.PROTECTION_PROJECTILE ||
				enchant == Enchantment.PIERCING) {
			return 4;
		}
		else if (enchant == Enchantment.QUICK_CHARGE ||
				enchant == Enchantment.RIPTIDE ||
				enchant == Enchantment.LOYALTY ||
				enchant == Enchantment.DURABILITY ||
				enchant == Enchantment.LURE ||
				enchant == Enchantment.LUCK ||
				enchant == Enchantment.LOOT_BONUS_BLOCKS ||
				enchant == Enchantment.SWEEPING_EDGE ||
				enchant == Enchantment.LOOT_BONUS_MOBS ||
				enchant == Enchantment.SOUL_SPEED ||
				enchant == Enchantment.THORNS ||
				enchant == Enchantment.DEPTH_STRIDER ||
				enchant == Enchantment.OXYGEN) {
			return 3;
		}
		
		return 1;
	}
}
