package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.checkerframework.checker.signature.qual.BinaryNameWithoutPackage;

public class SpawnEvents implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();

        World world = entity.getWorld();
        Server server = entity.getServer();

        switch (entityType) {

            case PHANTOM:

                server.broadcastMessage(ChatColor.BLUE +""+ ChatColor.BOLD +
                        "Virgencita de guadalupe, ¡Nos invaden los marcianos!");

                ((LivingEntity)entity).addPotionEffect(
                        new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 2)
                );

                Entity guardian = world.spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
                //entity.addPassenger(guardian);

                // Mover el Guardian a una posición cercana para que parezca que está asociado
                guardian.teleport(entity.getLocation().add(0, 0.5, 0)); // Ajusta la posición según sea necesario

                // Opcional: Usar un runnable para ajustar la posición del Guardian si es necesario
                Bukkit.getScheduler().runTaskTimer(MyPlugin.getPlugin(), () -> {
                    if (guardian.isValid()) {
                        guardian.teleport(entity.getLocation().add(0, 0.5, 0)); // Mantener la cercanía
                    }
                }, 0L, 5L); // Ajusta el intervalo según sea necesario

                break;

            // --------------------------------------------------------------------------- //

            case WITHER:
                server.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD +
                        "Se avecina un infierno inminente...");
            break;

            // --------------------------------------------------------------------------- //

            case PIGLIN_BRUTE:
                PiglinBrute piglinBrute = (PiglinBrute) entity;
                piglinBrute.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.SPEED,
                                PotionEffect.INFINITE_DURATION,
                                1
                        )
                );
            break;

            // --------------------------------------------------------------------------- //

            case ENDER_DRAGON:

                Entity elderGuardian = world.spawnEntity(entity.getLocation(),
                        EntityType.ELDER_GUARDIAN);

                elderGuardian.teleport(entity.getLocation());

                Bukkit.getScheduler().runTaskTimer(MyPlugin.getPlugin(), () -> {
                    if (!elderGuardian.isValid() || !entity.isValid()) {
                        elderGuardian.remove();
                        return;
                    }
                    elderGuardian.teleport(entity.getLocation().add(0, 10, 0));

                }, 0L, 1L);

            break;

            // --------------------------------------------------------------------------- //

            case RAVAGER:

                ((LivingEntity)entity).addPotionEffect(
                        new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1)
                );
                ((LivingEntity)entity).addPotionEffect(
                        new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 2)
                );


            break;

            // --------------------------------------------------------------------------- //

        }
    }



}

