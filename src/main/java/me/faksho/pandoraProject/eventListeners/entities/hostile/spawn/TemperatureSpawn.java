package me.faksho.pandoraProject.eventListeners.entities.hostile.spawn;

import me.faksho.pandoraProject.MyPlugin;
import me.faksho.pandoraProject.util.Multipliers;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class TemperatureSpawn implements Listener {

    @EventHandler
    public void onHostileEntitySpawn(EntitySpawnEvent e){

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();
        double blockTemp = entity.getLocation().getBlock().getTemperature();

        //if(!(blockTemp <= 0.0)) return;

        World world = entity.getWorld();
        Server server = entity.getServer();

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();

        String configPath;

        if (blockTemp <= 0.0) {
            configPath = "frozen.multipliers";
        } else if (blockTemp <= 0.25) {
            configPath = "cold.multipliers";
        } else if (blockTemp <= 0.75) {
            configPath = "warm.multipliers";
        } else if (blockTemp <= 0.99) {
            configPath = "hot.multipliers";
        } else {
            configPath = "veryhot.multipliers";
        }

        new Multipliers(entity, configPath);


    }
}
