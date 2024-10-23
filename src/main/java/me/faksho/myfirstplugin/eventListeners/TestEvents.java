package me.faksho.myfirstplugin.eventListeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TestEvents implements Listener {

    @EventHandler
    public void biomePrinter(PlayerInteractEvent event) {

        if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
            Biome biome = event.getPlayer().getWorld().getBiome(
                    event.getPlayer().getLocation());

            event.getPlayer().sendMessage(biome.toString() + biome.getKey());

            Block block = event.getPlayer().getLocation().getBlock();
            block.getTemperature();


        }

    }

    /*
    @EventHandler
    public void hotbarTermometer(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Double temp = player.getLocation().getBlock().getTemperature();

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent("Temperatura " + temp));

    }

     */
}
