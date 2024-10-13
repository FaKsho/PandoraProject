package me.faksho.myfirstplugin.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class Tuto implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        e.setJoinMessage(ChatColor.AQUA + p.getName() + " se unió al server.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        e.setQuitMessage(ChatColor.RED + p.getName() + " se fue a la mierda.");
    }

}
