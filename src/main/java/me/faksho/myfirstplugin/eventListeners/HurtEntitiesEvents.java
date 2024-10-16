package me.faksho.myfirstplugin.eventListeners;

import org.bukkit.*;
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

        // ------------------------------------------//------------------------------------------- //

        switch (entityType) {

            case CHICKEN:

                if(entity.getType() == EntityType.CHICKEN) {

                    for(int i = 0; i < 10; i++) {
                        world.spawnEntity(damagerLocation, EntityType.CREEPER, true);
                        world.spawnEntity(damagerLocation, EntityType.LIGHTNING_BOLT);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case COW:
                if(entity.getType() == EntityType.COW) {

                    // TODO mover la cantidad a otro lado
                    for(int i = 0; i < 5; i++) {
                        world.spawnEntity(
                                damager.getLocation(),
                                EntityType.EVOKER_FANGS);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case PIG:

                if (entity.getType() == EntityType.PIG) {
                    if(damager.getType() == EntityType.PLAYER) {

                        Player player = (Player) e.getDamager();
                        protectPigs(player);
                    }

                    if(damager.getType() == EntityType.ARROW) {

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

                if(entity.getType() == EntityType.BEE) {

                    //Player player = (Player) damager;
                    if(damager.getType() == EntityType.PLAYER){

                        world.spawnEntity(entity.getLocation(), EntityType.BEE);

                        Entity witherSkeleton = world.spawnEntity(entity.getLocation(), EntityType.WITHER_SKELETON);
                        entity.addPassenger(witherSkeleton);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case AXOLOTL:

                if(entity.getType() == EntityType.AXOLOTL) {

                    if(damager.getType() == EntityType.PLAYER) {

                        damager.sendMessage("No lastimes bichitos xfi");
                        damager.getWorld().spawnEntity(entity.getLocation(), EntityType.VEX);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case RABBIT:
                if(entity.getType() == EntityType.RABBIT) {
                    Rabbit rabbit = (Rabbit) entity;
                    Rabbit.Type bunnyType = rabbit.getRabbitType();

                    if(bunnyType == Rabbit.Type.WHITE) {

                        // TODO mover la cantidad a otro lado
                        for(int i = 0; i < 5; i++) {
                            world.spawnEntity(damagerLocation, EntityType.TNT_MINECART);
                        }
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case SHEEP:

                if(random.nextInt(100) <= 25){ // Probs pa otro lado
                    world.spawnEntity(entity.getLocation(), EntityType.CAVE_SPIDER);
                }

            break;


            // ------------------------------------------------------------------------------------- //

            case TURTLE:

                ((LivingEntity)damager).addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.POISON,
                                100,
                                5
                        ));

            break;

            // ------------------------------------------------------------------------------------- //

            case LLAMA:
            case WANDERING_TRADER:

                ((LivingEntity)damager)
                        .addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 5));

            break;



            ////////////  //////////// MOBS HOSTILES ///////////// ////////////

            case ENDERMAN:


                if(entity.isInsideVehicle()) {
                    entity.getVehicle().eject();
                }
                if(damager instanceof Player) {

                    ((Player)damager).addPotionEffect(
                            new PotionEffect(PotionEffectType.NAUSEA, 400, 2)
                    );
                }

            break;

            // ------------------------------------------------------------------------------------- //

            case SPIDER:
            case CAVE_SPIDER:

                Player player = (Player) damager;
                LivingEntity spider = (LivingEntity) entity;

                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,
                        100,
                        1));

                spider.addPassenger(player);


            break;


            // ------------------------------------------------------------------------------------- //


            case SKELETON:
            case WITHER_SKELETON:

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
                            //newLocation.subtract(0, 1, 0);
                        }
                    }
                }

                break;


            // ------------------------------------------------------------------------------------- //


            case CREEPER:

                System.out.println(damager.getName());

                if(damager.getType() == EntityType.ARROW ||
                        damager.getType() == EntityType.SPECTRAL_ARROW){

                    System.out.println("creepa");

                    ((LivingEntity)entity).addPotionEffect(new PotionEffect(
                       PotionEffectType.SPEED,
                       50,
                       3
                    ));

                    ((LivingEntity)entity).addPotionEffect(new PotionEffect(
                            PotionEffectType.GLOWING,
                            50,
                            1
                    ));
                }


                break;


            // ------------------------------------------------------------------------------------- //

            case PHANTOM:

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
