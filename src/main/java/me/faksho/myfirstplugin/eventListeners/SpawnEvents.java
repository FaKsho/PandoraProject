package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnEvents implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {

        EntityType entityType = e.getEntity().getType();

        // PHANTOMS CON SORPRESITAS
        if (entityType == EntityType.PHANTOM) {


            Entity phantomEntity = e.getEntity();
            phantomEntity.getServer().broadcastMessage(ChatColor.BLUE +""+ ChatColor.BOLD +
                    "Virgencita de guadalupe, ¡Nos invaden los marcianos!");

            // No voy a volver a entender esto luego
            phantomEntity
                .setPassenger(
                        phantomEntity.getWorld().spawnEntity(
                                phantomEntity.getLocation(), EntityType.GUARDIAN
                    )
                );
        }


        // --------------------------------------------------------------------------- //

        // MENSAJE PERSONALIZADO DE INVOCACIÓN DE WITHER
        if(entityType == EntityType.WITHER) {

            e.getEntity().getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD +
                    "Se avecina un infierno inminente...");
        }


        // --------------------------------------------------------------------------- //


    }
}
