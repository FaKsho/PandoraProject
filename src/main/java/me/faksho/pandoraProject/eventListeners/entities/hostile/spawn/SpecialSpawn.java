package me.faksho.pandoraProject.eventListeners.entities.hostile.spawn;

import org.bukkit.event.Listener;

public class SpecialSpawn implements Listener {

    /*

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();

        World world = entity.getWorld();
        Server server = entity.getServer();

        Random random = new Random();
        double randomDouble100 = random.nextDouble(100);

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();

        switch (entityType) {

            case PHANTOM:

                if(randomDouble100 <= config.getDouble("entity.spawn.phantom.chance")){

                    server.broadcastMessage(ChatColor.BLUE +""+ ChatColor.BOLD +
                            "Virgencita de guadalupe, ¡Nos invaden los marcianos!");

                    Entity mountedEntity = world.spawnEntity(entity.getLocation(),
                            EntityType.valueOf(config.getString("entity.spawn.phantom.mob")));

                    mountedEntity.teleport(entity.getLocation().add(0, 0.5, 0)); // Ajusta la posición según sea necesario

                    // Opcional: Usar un runnable para ajustar la posición del Guardian si es necesario
                    Bukkit.getScheduler().runTaskTimer(MyPlugin.getPlugin(), () -> {
                        if (mountedEntity.isValid()) {
                            mountedEntity.teleport(entity.getLocation().add(0, 0.5, 0)); // Mantener la cercanía
                        }
                    }, 0L, 2L); // Ajusta el intervalo según sea necesario

                    if(config.getBoolean("entity.spawn.phantom.resistance")){
                        ((LivingEntity)entity).addPotionEffect(
                                new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 2)
                        );
                    }
                }

                break;

            // --------------------------------------------------------------------------- //

            case WITHER:
                server.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD +
                        "Se avecina un infierno inminente...");
            break;

            // --------------------------------------------------------------------------- //

            case PIGLIN_BRUTE:

                if(!config.getBoolean("entity.spawn.brute.speed.enable")) break;

                ((LivingEntity) entity)
                        .addPotionEffect(
                                new PotionEffect(
                                        PotionEffectType.SPEED,
                                        PotionEffect.INFINITE_DURATION,
                                        config.getInt("entity.spawn.brute.speed.amplifier")
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

                if(!config.getBoolean("entity.spawn.ravager.speed.enable")) break;

                ((LivingEntity)entity)
                    .addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.SPEED,
                                PotionEffect.INFINITE_DURATION,
                                config.getInt("entity.spawn.ravager.speed.amplifier"))
                );


            break;

            // --------------------------------------------------------------------------- //

            case ZOMBIE:
            case ZOMBIE_VILLAGER:

                Zombie zombie = (Zombie) entity;

                // Baby zombie spawn chance
                if(randomDouble100 <= config.getDouble("entity.spawn.zombies.baby.chance")) {

                    zombie.setBaby();

                    if(config.getBoolean("entity.spawn.zombies.baby.speed")){

                        zombie.addPotionEffect(new PotionEffect(
                                PotionEffectType.SPEED,
                                PotionEffect.INFINITE_DURATION,
                                1
                        ));
                    }

                }

                // Zombie montado en caballo
                if(randomDouble100 <= config.getDouble("entity.spawn.zombies.horse-mounted.chance")) {

                    ZombieHorse zombieHorse = (ZombieHorse)
                            world.spawnEntity(zombie.getLocation(), EntityType.ZOMBIE_HORSE);
                    zombieHorse.addPotionEffect(new PotionEffect(
                            PotionEffectType.SPEED,
                            PotionEffect.INFINITE_DURATION,
                            1
                    ));

                    zombieHorse.addPotionEffect(new PotionEffect(
                            PotionEffectType.RESISTANCE,
                            PotionEffect.INFINITE_DURATION,
                            2
                    ));

                    zombieHorse.addPassenger(zombie);
                }

                break;



        }
    }

     */
}

