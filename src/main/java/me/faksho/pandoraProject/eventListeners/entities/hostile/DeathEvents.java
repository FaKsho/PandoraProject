package me.faksho.pandoraProject.eventListeners.entities.hostile;

import me.faksho.pandoraProject.MyPlugin;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class DeathEvents implements Listener {

    @EventHandler
    public void onPlayerKillEntity(EntityDeathEvent event) {

        Entity entity = event.getEntity();
        EntityType entityType = entity.getType();

        World world = entity.getWorld();

        Random random = new Random();

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();

        switch (entityType) {

            case EVOKER:

                if(random.nextDouble(100) < config.getDouble("entity.death.evoker.chance")) {

                    for(int i = 0; i < config.getInt("entity.death.evoker.amount"); i++){

                        world.spawnEntity(entity.getLocation(), EntityType.ILLUSIONER);
                    }
                }

            break;

            // -------------------------------------------------------------------------- //

            case ZOMBIE:
            case ZOMBIE_VILLAGER:


                if(entity.getVehicle() != null) {

                    ZombieHorse zombieHorse = (ZombieHorse) entity.getVehicle();
                    zombieHorse.setHealth(0.0);
                }

                if(random.nextDouble(100) < config.getDouble("entity.death.zombies.explosion.chance")) {

                    world.createExplosion(entity.getLocation(),
                            (float) config.getDouble("entity.death.zombies.explosion.power"),
                            false,
                            config.getBoolean("entity.death.zombies.explosion.break-blocks"));

                }


            break;

            // -------------------------------------------------------------------------- //

            case BLAZE:

                if(random.nextDouble(100) < config.getDouble("entity.death.blaze.chance")) {

                    world.spawnEntity(entity.getLocation(), EntityType.BREEZE);
                }

            break;

        }
    }
}
