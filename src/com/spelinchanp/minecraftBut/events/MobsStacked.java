package com.spelinchanp.minecraftBut.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.spelinchanp.minecraftBut.Settings;
import com.spelinchanp.minecraftBut.Utils;

public class MobsStacked {
	private Plugin plugin;
	private Settings settings;
	
	public MobsStacked(Plugin plugin, Settings settings) {
		this.plugin = plugin;
		this.settings = settings;
		
		run();
	}
	
	private void run() {
		mobsStackedRunnable = mobsStacked;
		mobsStackedRunnable.runTaskTimer(plugin, 0, 20*2);
	}
	
	private BukkitRunnable mobsStackedRunnable = null;
	private BukkitRunnable mobsStacked = new BukkitRunnable() {
		int timesRun = 0;
		
		@Override
		public void run() {
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
			
			for (int i = 0; i < players.size(); i++) {				
				List<Entity> nearbyEntities = Utils.getEntitiesAroundPoint(players.get(i).getLocation(), 45);
				for (int j = 0; j < nearbyEntities.size(); j++) {
					Entity entity = nearbyEntities.get(j);
					if (entity.getPassengers().isEmpty() &&
							entity.getCustomName() == null &&
							!entity.getType().equals(EntityType.PLAYER) &&
							!entity.getType().equals(EntityType.SQUID) &&
							!entity.getType().equals(EntityType.BAT) &&
							!(entity instanceof Item) &&
							!(entity instanceof Arrow)) {
						entity.setCustomName("Stacked");
						
						// fix it so that it adds to the stack, not adds multiple entities to second layer
						int numToStack = Utils.getRandomIntRange(settings.mobsStackedMin, settings.mobsStackedMax);
						Utils.addPassengers(entity, numToStack);
					}
				}
			}
			
			timesRun++;
			if (timesRun == 30) {
				cancel();
				mobsStackedRunnable = null;
			}
		}
		
	};
}
