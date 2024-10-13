package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEvents implements Listener {

    @EventHandler
    public void onPlayerUseBed(PlayerBedEnterEvent event) {

        event.setCancelled(true);

        Block bed = event.getBed();
        Player player = event.getPlayer();

        bed.getWorld().createExplosion(player.getLocation(), 50.0f, false, false);
        bed.breakNaturally();
    }
}
