package me.faksho.pandoraProject.eventListeners.custom;

import me.faksho.pandoraProject.MyPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TestEvents implements Listener {

    MyPlugin plugin = MyPlugin.getPlugin();
    FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void debugToolsEnabledWarning(PlayerJoinEvent event) {

        event.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "¡¡Las herramientas de debug están activas!!");
    }

    @EventHandler
    public void biomePrinter(PlayerInteractAtEntityEvent event) {

        if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {

            Entity entity = event.getRightClicked();
            Player player = event.getPlayer();

            if (!(entity instanceof LivingEntity)) {
                player.sendMessage("Esta entidad no tiene atributos visibles.");
                return;
            }

            LivingEntity livingEntity = (LivingEntity) entity;
            String entityName = livingEntity.getName();

            StringBuilder info = new StringBuilder(ChatColor.GREEN + ">>>>>>>>>>>>>>> Información de " + entityName + ":\n");
            // agregar vida actual y maxima
            info.append(ChatColor.WHITE).append("HP Actual ").append(livingEntity.getHealth()).append("\n");
            info.append("----------------- \n");

            addAttributeInfo(info, "Velocidad de movimiento", livingEntity, Attribute.GENERIC_MOVEMENT_SPEED);
            addAttributeInfo(info, "Salto", livingEntity, Attribute.GENERIC_JUMP_STRENGTH);
            addAttributeInfo(info, "Velocidad en agua", livingEntity, Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY);
            addAttributeInfo(info, "Vida", livingEntity, Attribute.GENERIC_MAX_HEALTH);
            addAttributeInfo(info, "Defensa", livingEntity, Attribute.GENERIC_ARMOR);
            addAttributeInfo(info, "Resistencia al Knockback", livingEntity, Attribute.GENERIC_KNOCKBACK_RESISTANCE);
            addAttributeInfo(info, "Fuerza de ataque", livingEntity, Attribute.GENERIC_ATTACK_DAMAGE);
            addAttributeInfo(info, "Velocidad de ataque", livingEntity, Attribute.GENERIC_ATTACK_SPEED);
            addAttributeInfo(info, "Knockback de ataque", livingEntity, Attribute.GENERIC_ATTACK_KNOCKBACK);
            addAttributeInfo(info, "Rango de seguimiento", livingEntity, Attribute.GENERIC_FOLLOW_RANGE);
            addAttributeInfo(info, "Tamaño", livingEntity, Attribute.GENERIC_SCALE);

            player.sendMessage(info.toString());

        }
    }

    private void addAttributeInfo(StringBuilder info, String attributeName, LivingEntity entity, Attribute attribute) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            double baseValue = attributeInstance.getBaseValue();
            info.append(attributeName).append(": ").append(baseValue).append("\n");
            info.append("----------------- \n");

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

    @EventHandler
    public void permanentTotem(EntityResurrectEvent event) {

        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Inventory inventory = player.getInventory();

            inventory.setItem(40, new ItemStack(Material.TOTEM_OF_UNDYING));
            player.setHealth(20);

        }
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
