package me.faksho.pandoraProject.eventListeners.players;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class ItemConsumeEvents implements Listener {

    @EventHandler
    public void onPlayerEats(PlayerItemConsumeEvent event) {

        Player player = event.getPlayer();
        Material itemType = event.getItem().getType();

        Random random = new Random();

        switch (itemType) { // TODO pendiente de terminar

            case GOLDEN_APPLE:
            case ENCHANTED_GOLDEN_APPLE:

                if(random.nextInt(100) <= 45) {

                    player.addPotionEffect(
                            new PotionEffect(PotionEffectType.NAUSEA, 200, 1)
                    );
                }

                break;

            case GLOW_BERRIES:

                player.addPotionEffect(new PotionEffect(
                        PotionEffectType.ABSORPTION,
                        600,
                        0
                ));

                break;

            case SWEET_BERRIES:
        }

    }
}
