package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class DeathEntitiesEvents implements Listener {

    @EventHandler
    public void onPlayerKillEntity(EntityDeathEvent event) {

        Entity entity = event.getEntity();
        EntityType entityType = entity.getType();

        World world = entity.getWorld();

        Random random = new Random();

        switch (entityType) {

            case EVOKER:

                world.spawnEntity(entity.getLocation(), EntityType.ILLUSIONER);

            break;

            // -------------------------------------------------------------------------- //

            case ZOMBIE:
            case ZOMBIE_VILLAGER:


                if(random.nextInt(100) < 40) {

                    world.createExplosion(entity.getLocation(),
                            1.5f,
                            false);
                }

            break;

            // -------------------------------------------------------------------------- //

            case BLAZE:

                world.spawnEntity(entity.getLocation(), EntityType.BREEZE);

            break;

        }
    }
}
