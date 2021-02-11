package com.spelinchanp.minecraftBut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class MinecraftEvent {
	private Plugin plugin;
	
	MinecraftEvent(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public BukkitTask enableTNTrain() {
		return EventRunnables.tntRain.runTaskTimer(plugin, 0, 20*10);
	} 
	
	public BukkitTask enableRandomEnchants() {
		return EventRunnables.randomEnchants.runTaskTimer(plugin, 0, 20*1);
	}
	
}
