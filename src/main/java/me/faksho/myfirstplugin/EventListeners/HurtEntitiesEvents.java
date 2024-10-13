package me.faksho.myfirstplugin.EventListeners;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class HurtEntitiesEvents implements Listener {


    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) {

        System.out.println("PLAYER HARVEST EVENT TRIGGERED");

        Player p = e.getPlayer();
        Block b = e.getBlock();

        if(p.getInventory().contains(Material.CHICKEN)) {
            p.sendMessage("No chicken pliz");
            e.setCancelled(true);

        }

        System.out.println(b.getBlockData().getAsString());
    }

    // OVEJITA BRILLANTE
    @EventHandler
    public void onShearSheep(PlayerShearEntityEvent e) {

        Entity entity = e.getEntity();

        entity.setGlowing(true);
    }

    // FLECHA RAYO
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

    // ANTI MOVIMIENTO CON WEBO
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        // NO EGG

        if(p.getInventory().contains(Material.EGG)) {
            //p.sendMessage("Soltá el webo puto");
            //p.setWalkSpeed(-1);
            //Block b = p.getWorld().getBlockAt(p.getLocation());
            //b.setType(Material.COBWEB);

            for(int i = 0; i < 1; i++) {

                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 5));

            }

        } else {
            p.setWalkSpeed(0.2f);
        }
    }

    //
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e) {

        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        Location damagerLocation = damager.getLocation();

        EntityType entityType = entity.getType();
        switch (entityType) {

            // PROTECCIÓN DE GALLINAS
            case CHICKEN:

                if(entity.getType() == EntityType.CHICKEN) {

                    for(int i = 0; i < 10; i++) {
                        entity.getWorld().spawnEntity(damagerLocation, EntityType.CREEPER, true);
                        entity.getWorld().spawnEntity(damagerLocation, EntityType.LIGHTNING_BOLT);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // PROTECCIÓN DE VACAS
            case COW:
                if(entity.getType() == EntityType.COW) {

                    // TODO mover la cantidad a otro lado
                    for(int i = 0; i < 5; i++) {

                        entity.getWorld().spawnEntity(
                                damager.getLocation(),
                                EntityType.EVOKER_FANGS);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // PROTECCIÓN ENDERMAN
            case ENDERMAN:
                if(entity.getType() == EntityType.ENDERMAN) {

                    entity.getServer().broadcastMessage("LA REVELDÍA CONTRA EL RÉGIMEN NO SERÁ TOLERADA");
                    List<Entity> nearbyEntities = entity.getNearbyEntities(3, 3, 3);

                    for (Entity entityPlayer : nearbyEntities) {

                        if (entityPlayer.getType() == EntityType.PLAYER) {

                            Player player = (Player) entityPlayer;
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 50));
                        }
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // PROTECCIÓN DE CHANCHITOS
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

                        protectPigs(shooter);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // PROTEGER ABEJITAS
            case BEE:

                if(entity.getType() == EntityType.BEE) {

                    //Player player = (Player) damager;
                    if(damager.getType() == EntityType.PLAYER){

                        entity.getWorld().spawnEntity(entity.getLocation(), EntityType.BEE);

                        entity.setPassenger(
                                entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WITHER_SKELETON)
                        );
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            case AXOLOTL:

                // PROTEGER AJOLOTES
                if(entity.getType() == EntityType.AXOLOTL) {

                    if(damager.getType() == EntityType.PLAYER) {

                        damager.sendMessage("No lastimes bichitos xfi");
                        damager.getWorld().spawnEntity(entity.getLocation(), EntityType.VEX);
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // CONEJOS BLANCOS
            case RABBIT:
                if(entity.getType() == EntityType.RABBIT) {
                    Rabbit rabbit = (Rabbit) entity;
                    Rabbit.Type bunnyType = rabbit.getRabbitType();

                    if(bunnyType == Rabbit.Type.WHITE) {

                        // TODO mover la cantidad a otro lado
                        for(int i = 0; i < 5; i++) {
                            entity.getWorld().spawnEntity(damagerLocation, EntityType.TNT_MINECART);
                        }
                    }
                }
            break;

            // ------------------------------------------------------------------------------------- //

            // EFECTOS
            case SHEEP:



            break;


            // ------------------------------------------------------------------------------------- //

            // TORTUGAS
            case TURTLE:

                ((LivingEntity)damager).addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.POISON,
                                100,
                                5
                        ));

            break;

            // ------------------------------------------------------------------------------------- //

            // GATITOS
            case CAT:


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
