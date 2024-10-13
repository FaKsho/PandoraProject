package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ExtraEvents implements Listener {

    // FLECHA RAYO
    /*
     * TODO hacer que esto solo pase bajo x condiciones
    @EventHandler
    public void onArrowHits(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();
        System.out.println("Projectile event");

        if(projectile.getType() == EntityType.ARROW) {

            if(projectile.getShooter() instanceof Player){
                System.out.println("Arrow");
                projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.LIGHTNING_BOLT);
            }
        }
    }
     */


}
