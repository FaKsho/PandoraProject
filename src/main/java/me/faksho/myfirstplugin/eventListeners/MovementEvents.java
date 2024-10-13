package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MovementEvents implements Listener {

    // WITHER EFFECT AL TENER HUEVOS EN EL INVENTARIO
    // TODO mover esto a una sección de eventos de inventario
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        // NO EGG

        if(p.getInventory().contains(Material.EGG)) {
            //p.sendMessage("Soltá el webo puto");
            //p.setWalkSpeed(-1);
            //Block b = p.getWorld().getBlockAt(p.getLocation());
            //b.setType(Material.COBWEB);

            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 5));

        } else {
            p.setWalkSpeed(0.2f);
        }
    }
}
