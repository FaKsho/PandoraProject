package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class HurtEntitiesEvents implements Listener {


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

            case CHICKEN:

                if(!(damager instanceof Player)) break;


                if (randomDouble100 <= config.getDouble("entity.hurt.chicken.chance")) {

                    for (int i = 0; i < config.getInt("entity.hurt.chicken.amount"); i++) {


                        for (String string : config.getStringList("entity.hurt.chicken.mobs")) {

                            world.spawnEntity(damagerLocation, EntityType.valueOf(string));
                        }
                    }
                }
                break;

            // ------------------------------------------------------------------------------------- //

            case COW:
                if(!(damager instanceof Player)) break;

                if (randomDouble100 <= config.getDouble("entity.hurt.cow.chance")) {

                    for (int i = 0; i < config.getInt("entity.hurt.cow.amount"); i++) {

                        for (String string : config.getStringList("entity.hurt.cow.mobs")) {

                            world.spawnEntity(damagerLocation, EntityType.valueOf(string));
                        }
                    }
                }
                break;

            // ------------------------------------------------------------------------------------- //

            case PIG:


                if (randomDouble100 <= config.getDouble("entity.hurt.pig.chance")) {

                    if (damager instanceof Player) {

                        Player player = (Player) e.getDamager();
                        protectPigs(player);
                    }

                    if (damager instanceof Arrow) {

                        System.out.println("ARROW");

                        Arrow arrow = (Arrow) e.getDamager();
                        LivingEntity shooter = (LivingEntity) arrow.getShooter();

                        assert shooter != null;
                        protectPigs(shooter);
                    }
                }

                break;

            // ------------------------------------------------------------------------------------- //

            case BEE:

                if (randomDouble100 <= config.getDouble("entity.hurt.bee.chance")) {

                    if (damager instanceof Player) {

                        for (int i = 0; i < config.getInt("entity.hurt.bee.amount"); i++) {

                            world.spawnEntity(entity.getLocation(), EntityType.BEE);

                            Entity passengerEntity = world.spawnEntity(entity.getLocation(),
                                    EntityType.valueOf(config.getString("entity.hurt.bee.mob")));

                            entity.addPassenger(passengerEntity);
                        }
                    }
                }
                break;

            // ------------------------------------------------------------------------------------- //

            case AXOLOTL:

                if (randomDouble100 <= config.getDouble("entity.hurt.axolotl.chance")) {

                    if (damager instanceof Player) {

                        for (int i = 0; i < config.getInt("entity.hurt.axolotl.amount"); i++) {
                            damager.getWorld().spawnEntity(entity.getLocation(), EntityType.VEX);
                        }
                    }
                }
                break;

            // ------------------------------------------------------------------------------------- //

            case RABBIT:
                if (randomDouble100 <= config.getDouble("entity.hurt.rabbit.chance")) {

                    for (int i = 0; i < config.getInt("entity.hurt.rabbit.amount"); i++) {

                        world.spawnEntity(damagerLocation, EntityType.TNT_MINECART);
                    }
                }
                break;

            // ------------------------------------------------------------------------------------- //

            case SHEEP:

                if (randomDouble100<= config.getDouble("entity.hurt.sheep.chance")) {

                    world.spawnEntity(entity.getLocation(), EntityType.valueOf(
                            config.getString("entity.hurt.sheep.mob")
                    ));

                }

                break;


            // ------------------------------------------------------------------------------------- //

            case TURTLE:

                if (randomDouble100 <= config.getDouble("entity.hurt.turtle.chance")) {
                    ((LivingEntity) damager).addPotionEffect(
                            new PotionEffect(
                                    PotionEffectType.POISON,
                                    100,
                                    5
                            ));
                }

                break;

            // ------------------------------------------------------------------------------------- //

            case LLAMA:
            case TRADER_LLAMA:
            case WANDERING_TRADER:

                if (randomDouble100 <= config.getDouble("entity.hurt.llama-wanderingt.chance")){
                    ((LivingEntity) damager)
                            .addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 5));
                }

            break;



            ////////////  //////////// MOBS HOSTILES ///////////// ////////////

            case ENDERMAN:

                if(entity.isInsideVehicle()) entity.getVehicle().eject();

                if(randomDouble100 <= config.getDouble("entity.hurt.enderman.chance")) {

                    if(damager instanceof Player) {

                        ((Player)damager).addPotionEffect(
                                new PotionEffect(PotionEffectType.NAUSEA, 400, 2)
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


    //// FUNCIONES CUSTOM ////

    // Ayuda para protección de chanchitos
    private void protectPigs(LivingEntity livingEntity) {

        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 100));

        if(livingEntity instanceof Player) {
            Player player = (Player) livingEntity;

            PlayerInventory playerInventory = player.getInventory();
            if (!playerInventory.contains(Material.WATER_BUCKET)) {
                player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
            }
        }
    }

}
