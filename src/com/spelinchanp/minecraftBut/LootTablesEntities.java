package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.EntityType;

public class LootTablesEntities {
	public static ArrayList<EntityType> spawnerEntities = loadSpawnerEntityTypes();

	
	private static ArrayList<EntityType> loadSpawnerEntityTypes() {
		// Load these from file in the future
		return new ArrayList<EntityType>(
			Arrays.asList(
				EntityType.BAT,
				EntityType.BEE,
				EntityType.BLAZE,
				EntityType.CAT,
				EntityType.CAVE_SPIDER,
				EntityType.CHICKEN,
				EntityType.COD,
				EntityType.COW,
				EntityType.CREEPER,
				EntityType.DOLPHIN,
				EntityType.DONKEY,
				EntityType.DROWNED,
				EntityType.ELDER_GUARDIAN,
				EntityType.ENDERMAN,
				EntityType.ENDERMITE,
				EntityType.EVOKER,
				EntityType.EVOKER_FANGS,
				EntityType.FOX,
				EntityType.GHAST,
				EntityType.GIANT,
				EntityType.GUARDIAN,
				EntityType.HOGLIN,
				EntityType.HORSE,
				EntityType.HUSK,
				EntityType.ILLUSIONER,
				EntityType.IRON_GOLEM,
				EntityType.LLAMA,
				EntityType.MAGMA_CUBE,
				EntityType.MULE,
				EntityType.MUSHROOM_COW,
				EntityType.OCELOT,
				EntityType.PANDA,
				EntityType.PARROT,
				EntityType.PHANTOM,
				EntityType.PIG,
				EntityType.PIGLIN,
				EntityType.PIGLIN_BRUTE,
				EntityType.PILLAGER,
				EntityType.POLAR_BEAR,
				EntityType.PRIMED_TNT,
				EntityType.PUFFERFISH,
				EntityType.RABBIT,
				EntityType.RAVAGER,
				EntityType.SALMON,
				EntityType.SHEEP,
				EntityType.SHULKER,
				EntityType.TRADER_LLAMA,
				EntityType.TRIDENT,
				EntityType.TROPICAL_FISH,
				EntityType.TURTLE,
				EntityType.VEX,
				EntityType.VILLAGER,
				EntityType.VINDICATOR,
				EntityType.WANDERING_TRADER,
				EntityType.WITCH,
				EntityType.WITHER_SKELETON,
				EntityType.WOLF,
				EntityType.ZOGLIN,
				EntityType.ZOMBIE,
				EntityType.ZOMBIE_HORSE,
				EntityType.ZOMBIE_VILLAGER,
				EntityType.ZOMBIFIED_PIGLIN
        		));
	}
}
