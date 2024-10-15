package me.faksho.myfirstplugin.eventListeners;

import me.faksho.myfirstplugin.MyPlugin;
import me.faksho.myfirstplugin.util.ParticlesUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
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
        EntityType entityType = entityClicked.getType();


        switch (entityType){

        case COW:
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



            }

            break;

            case SILVERFISH:

                if(player.getInventory().getItemInMainHand().getType() != Material.STICK){
                    return;
                }

                Silverfish silverfish = (Silverfish) entityClicked;


                new BukkitRunnable() {
                    @Override
                    public void run() {

                        silverfish.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 1));
                        silverfish.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 1));

                        if (!silverfish.isDead() && player.isOnline()) {
                            silverfish.setGlowing(true);
                            Location playerLocation = player.getLocation();
                            silverfish.setTarget(player);
                            silverfish.setInvulnerable(true);
                            silverfish.teleport(silverfish.getLocation().add(playerLocation.getDirection().multiply(1)));
                        } else {
                            this.cancel(); // Detiene la tarea si el silverfish o el jugador no están vivos
                        }
                    }
                }.runTaskTimer(MyPlugin.getPlugin(), 0, 20); // Repite cada segundo (20 ticks)

        }

    }
}
