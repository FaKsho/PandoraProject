package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TestEvents implements Listener {

    @EventHandler
    public void biomePrinter(PlayerInteractEvent event) {

        if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
            Biome biome = event.getPlayer().getWorld().getBiome(
                    event.getPlayer().getLocation());

            event.getPlayer().sendMessage(biome.toString());

            
        }

    }
}
