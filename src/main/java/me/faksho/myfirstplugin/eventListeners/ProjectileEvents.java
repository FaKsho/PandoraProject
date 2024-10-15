package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.security.KeyStore;
import java.util.List;
import java.util.Random;

public class ProjectileEvents implements Listener {

    @EventHandler
    public void onProjectileHits(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();
        EntityType entityType = projectile.getType();

        Entity damager = (Entity) projectile.getShooter();
        Entity hitEntity = event.getHitEntity();


        switch (entityType) {

            case LLAMA_SPIT:

                Location projectileLocation = projectile.getLocation();
                World world = projectileLocation.getWorld();


                if (world != null) {
                    // Generar partículas para simular el efecto del sonic boom
                    world.spawnParticle(Particle.SONIC_BOOM, projectileLocation, 10, 0.5, 0.5, 0.5, 0.1);

                    // Reproducir el sonido del Warden
                    world.playSound(projectileLocation, Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 1.0f);

                    // Aplicar daño a las entidades cercanas
                    if(hitEntity instanceof Player) {
                        Player player = (Player) hitEntity;
                        player.damage(19.0);
                    }

                }

            break;

                // -------------------------------------------------------------------- //

            case ARROW:
                if(damager instanceof Skeleton) {

                    if(new Random().nextInt(100) < 20) {
                        ((LivingEntity)hitEntity).addPotionEffect(
                                new PotionEffect(PotionEffectType.SLOWNESS, 150, 3)
                        );
                    }
                }
        }


    }

}
