package me.faksho.pandoraProject.eventListeners.custom;

import me.faksho.pandoraProject.MyPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TestEvents implements Listener {

    MyPlugin plugin = MyPlugin.getPlugin();
    FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void biomePrinter(PlayerInteractEvent event) {

        if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
            Biome biome = event.getPlayer().getWorld().getBiome(
                    event.getPlayer().getLocation());

            event.getPlayer().sendMessage(biome.toString() + biome.getKey());

            Block block = event.getPlayer().getLocation().getBlock();
            block.getTemperature();


        }

    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {

        if(!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        Location spawnLocation = entity.getLocation();
        double temp = spawnLocation.getBlock().getTemperature();

        //if(entity instanceof Player) return;

        //System.out.println(event.getEntity().toString() + " " + temp);




    }


    @EventHandler
    public void hotbarTermometer(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Double temp = player.getLocation().getBlock().getTemperature();

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent("Temperatura " + temp));

    }

    public PotionEffect createInfiniteEffect(PotionEffectType effectType, int amplifier) {
        return new PotionEffect(effectType,
                PotionEffect.INFINITE_DURATION,
                amplifier);
    }

    public void assingInfiniteEffects(LivingEntity entity, int amplifier, PotionEffectType... effectsType) {

        for(PotionEffectType effectType: effectsType) {
            entity.addPotionEffect(new PotionEffect(effectType,
                    PotionEffect.INFINITE_DURATION,
                    amplifier));
        }
    }

}
