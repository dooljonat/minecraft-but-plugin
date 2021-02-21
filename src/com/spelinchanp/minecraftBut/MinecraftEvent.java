package com.spelinchanp.minecraftBut;

import com.spelinchanp.minecraftBut.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MinecraftEvent {
	// TODO: create wrapper classes for all of the events
	// right now, its doing the dumb "already scheduled as" error because its not a re-initialized
	// object in the main run function anymore
	
	private Plugin plugin;
	
	MinecraftEvent(Plugin plugin) {
		this.plugin = plugin;
		
	}
	
	public void run(ButEvents event) {
		if (event == ButEvents.TntRain) {
			TntRain e = new TntRain(plugin);
		}
		else if (event == ButEvents.RandomEnchants) {
			RandomEnchants e = new RandomEnchants(plugin);
		}
		else if (event == ButEvents.MobsStacked) {
			MobsStacked e = new MobsStacked(plugin);
		}
		else if (event == ButEvents.GravitizedLeaves) {
			GravitizedLeaves e = new GravitizedLeaves(plugin);
		}
	}	
}
