package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpecialEvents implements Listener {

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
