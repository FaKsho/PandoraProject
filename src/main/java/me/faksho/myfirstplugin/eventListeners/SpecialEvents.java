package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class SpecialEvents implements Listener {

    MyPlugin plugin = MyPlugin.getPlugin();
    FileConfiguration config = plugin.getConfig();


    /* EVENTOS ESPECIALES
     * Estos eventos son eventos específicos, que todavía no encuentro otro lugar
     * para ponerlos. Así que van a ir acá y dentro de lo posible documentarlos
     * para no perderme tanto. :)
     */

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        /* Hace que el jugador solo pueda regenerar hasta 19 puntos de comida.
         * Esto con el objetivo de poder consumir cualquier alimento en cualquier
         * momento.
         */
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




    @EventHandler
    public void onEntityTargetPlayer(EntityTargetEvent event) {

        Entity targetEntity = event.getTarget();
        LivingEntity entity = (LivingEntity) event.getEntity();
        EntityType entityType = entity.getType();

        // Verificar si el target es un jugador
        if(!(targetEntity instanceof Player)) return;
        Player target = (Player) targetEntity;


        switch (entityType) {

            /* Los creepers reciben SPD si su objetivo tiene el efecto
             * especificado. En este caso SLOW.
             */

            case CREEPER:

                if(!(config.getBoolean("special.player-targeted.creeper.enable"))) break;

                if(hasEffect(target, PotionEffectType.SLOWNESS)) {

                    entity.addPotionEffect(new PotionEffect(
                            PotionEffectType.SPEED,
                            160,
                            2
                    ));

                    entity.addPotionEffect(new PotionEffect(
                            PotionEffectType.GLOWING,
                            160,
                            0
                    ));
                }
                break;

            case ENDERMAN:
                if(!(config.getBoolean("special.player-targeted.enderman.enable"))) break;

                Enderman enderman = (Enderman) entity;

                // TODO hacer que se pueda revertir el tamaño
                setSize(enderman, 0.62f);

                break;


            default:
                break;
        }

    }


    ////////////////////////// MÉTODOS CUSTOM //////////////////////////

    private boolean hasEffect(LivingEntity entity, PotionEffectType effect) {

        Collection<PotionEffect> effects = entity.getActivePotionEffects();

        for(PotionEffect potionEffect: effects) {

            if(potionEffect.getType() == effect) return true;
        }

        return false;
    }

    private void setSize(LivingEntity entity, float size) {

        entity.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(size);
    }

}
