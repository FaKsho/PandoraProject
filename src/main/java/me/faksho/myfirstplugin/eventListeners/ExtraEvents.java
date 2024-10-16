package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
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
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if(player.getFoodLevel() < 20) {
            return;
        }
        System.out.println("19");
        player.getServer().getScheduler().scheduleSyncDelayedTask(MyPlugin.getPlugin(), new Runnable() {
            public void run() {
                player.setFoodLevel(19);
            }
        }, 1L);
    }



}
