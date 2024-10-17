package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEvents implements Listener {


    @EventHandler
    public void onPlayerUseBed(PlayerBedEnterEvent event) {

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();

        if(!config.getBoolean("bed.can-sleep")) {
            event.setCancelled(true);

            Block bed = event.getBed();
            Player player = event.getPlayer();

            bed.getWorld().createExplosion(player.getLocation(), 50.0f, false, false);
            bed.breakNaturally();
        }
    }
}
