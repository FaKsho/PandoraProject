package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementEvents implements Listener {

    @EventHandler
    public void onPlayerGetArchivment(PlayerAdvancementDoneEvent event) {

        Advancement advancement = event.getAdvancement();

        advancement.getDisplay().getTitle();

    }
}
