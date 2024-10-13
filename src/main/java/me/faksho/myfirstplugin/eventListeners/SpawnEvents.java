package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnEvents implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();

        World world = entity.getWorld();
        Server server = entity.getServer();

        switch (entityType) {

            case PHANTOM:

                server.broadcastMessage(ChatColor.BLUE +""+ ChatColor.BOLD +
                        "Virgencita de guadalupe, ¡Nos invaden los marcianos!");

                Entity guardian = world.spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
                entity.addPassenger(guardian);

            break;

            // --------------------------------------------------------------------------- //

            case WITHER:
                server.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD +
                        "Se avecina un infierno inminente...");
            break;

            // --------------------------------------------------------------------------- //

            case PIGLIN_BRUTE:
                PiglinBrute piglinBrute = (PiglinBrute) entity;
                piglinBrute.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.SPEED,
                                PotionEffect.INFINITE_DURATION,
                                1
                        )
                );
            break;

            // --------------------------------------------------------------------------- //


        }
    }

}

