package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import me.faksho.myfirstplugin.util.ParticlesUtil;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEntityInteractionEvents implements Listener {

    private static MyPlugin plugin = MyPlugin.getPlugin();

    @EventHandler
    public void onPlayerInteractEntities(PlayerInteractEntityEvent event) { // Buen nombre de método eh

        Entity entityClicked = event.getRightClicked();
        Player player = event.getPlayer();

        World world = player.getWorld();

        if(entityClicked.getType() == EntityType.COW) {
            if(player.getInventory().getItemInMainHand().getType() == Material.BUCKET) {

                ((LivingEntity)entityClicked).addPotionEffect(
                        new PotionEffect(PotionEffectType.LEVITATION,
                                150,
                                25)
                );

                ParticlesUtil.createParticlesFromEntity(
                        entityClicked,
                        Particle.WHITE_SMOKE,
                        10,
                        1,
                        world
                );

                /*
                for(int i = 0; i < 1000; i++){
                    world.spawnParticle(Particle.CLOUD, entityClicked.getLocation(), 20);
                }

                 */




            }
        }



    }
}
