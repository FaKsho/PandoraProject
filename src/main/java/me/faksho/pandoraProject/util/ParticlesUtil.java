package me.faksho.pandoraProject.util;

import me.faksho.pandoraProject.MyPlugin;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;


public class ParticlesUtil {

    private static MyPlugin plugin = MyPlugin.getPlugin();

    public static void createParticlesFromEntity(Entity entityClicked,
                                                 Particle particle,
                                                 int durationInSeconds,
                                                 int intervalInTicks) {

        World world = entityClicked.getWorld();


        new BukkitRunnable() {

            int ticks = 0;
            @Override
            public void run() {
                // Ejecutar la acción
                world.spawnParticle(particle, entityClicked.getLocation(), 50);

                // Incrementar el contador de ticks
                ticks += intervalInTicks;

                // Detener el bucle después de la duración especificada
                if (ticks >= durationInSeconds * 20) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

}
