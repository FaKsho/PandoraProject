package me.faksho.pandoraProject.eventListeners.entities.hostile;

import me.faksho.pandoraProject.MyPlugin;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class HHurtEvent implements Listener {


    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e) {

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();

        Entity damager = e.getDamager();
        Location damagerLocation = damager.getLocation();

        World world = entity.getWorld();
        Server server = entity.getServer();

        Random random = new Random();
        double randomDouble100 = random.nextDouble(100);

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();

        // ------------------------------------------//------------------------------------------- //

        switch (entityType) {

            case ENDERMAN:

                if(entity.isInsideVehicle()) entity.getVehicle().eject();

                if(randomDouble100 <= config.getDouble("entity.hurt.enderman.chance")) {

                    if(damager instanceof Player) {

                        ((Player)damager).addPotionEffect(
                                new PotionEffect(PotionEffectType.NAUSEA, 150, 2)
                        );
                    }

                }


            break;

            // ------------------------------------------------------------------------------------- //

            case SPIDER:
            case CAVE_SPIDER:

                Player player = (Player) damager;
                LivingEntity spider = (LivingEntity) entity;

                if(randomDouble100 <= config.getDouble("entity.hurt.spiders.chance")) {

                    player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,
                            200,
                            1));
                }
                spider.addPassenger(player);
            break;


            // ------------------------------------------------------------------------------------- //


            case SKELETON:
            case WITHER_SKELETON:

                if(!config.getBoolean("entity.hurt.skeletons.enable-teleport")) break;

                LivingEntity skeleton = (LivingEntity) entity;

                if(damager.getType() == EntityType.ARROW ||
                        damager.getType() == EntityType.SPECTRAL_ARROW){
                    e.setCancelled(true);

                    world.playSound(entity, Sound.ENTITY_ENDERMAN_TELEPORT, 3, 1);

                    boolean teleported = false;
                    int maxAttempts = 10;

                    for (int i = 0; i < maxAttempts && !teleported; i++) {

                        double offsetX = (Math.random() * 20) - 10;
                        double offsetZ = (Math.random() * 20) - 10;

                        Location baseLocation = skeleton.getLocation().add(offsetX, 0, offsetZ);
                        Location newLocation = baseLocation.clone();

                        for (int y = baseLocation.getBlockY()+5; y > 0; y--) {
                            newLocation.setY(y);

                            if (world.getBlockAt(newLocation).getType().isSolid() &&
                                world.getBlockAt(newLocation.add(0, 1, 0)).getType() == Material.AIR &&
                                world.getBlockAt(newLocation.add(0, 2, 0)).getType() == Material.AIR) {

                                skeleton.teleport(newLocation.subtract(0, 2, 0));
                                teleported = true;
                                break;
                            }

                        }
                    }
                }

                break;


            // ------------------------------------------------------------------------------------- //


            case CREEPER:

                if(!config.getBoolean("entity.hurt.creeper.speed")) break;

                if(damager.getType() == EntityType.ARROW ||
                        damager.getType() == EntityType.SPECTRAL_ARROW){

                    ((LivingEntity)entity).addPotionEffect(new PotionEffect(
                       PotionEffectType.SPEED,
                       60,
                       3
                    ));

                    ((LivingEntity)entity).addPotionEffect(new PotionEffect(
                            PotionEffectType.GLOWING,
                            60,
                            1
                    ));
                }

                break;

            // ------------------------------------------------------------------------------------- //

            case PHANTOM:

                if(!config.getBoolean("entity.hurt.phantom.projectile-inmune")) break;

                if(damager instanceof Arrow) e.setCancelled(true);
                break;
        }

    }


}
