package me.faksho.pandoraProject.eventListeners.entities.hostile.spawn;

import me.faksho.pandoraProject.MyPlugin;
import me.faksho.pandoraProject.util.Multipliers;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class HFrozenSpawn implements Listener {

    @EventHandler
    public void onHostileEntitySpawn(EntitySpawnEvent e){

        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();
        double blockTemp = entity.getLocation().getBlock().getTemperature();

        if(!(blockTemp <= 0.0)) return;

        World world = entity.getWorld();
        Server server = entity.getServer();

        Random random = new Random();
        double randomDouble100 = random.nextDouble(100);

        MyPlugin plugin = MyPlugin.getPlugin();
        FileConfiguration config = plugin.getConfig();


        switch (entityType) {

            case ZOMBIE:
            case ZOMBIE_VILLAGER:

                System.out.println("zombie spawned");
                String configPath = "frozen.multipliers.zombies";

                Multipliers mults = new Multipliers(entity, configPath);
                mults.applyMultipliers();


                break;


        }

    }

    /*

    private void applyMultipliers(Multipliers mults) {

        Entity entity = mults.getEntity();
        assert entity instanceof LivingEntity;
        LivingEntity livingEntity = (LivingEntity) entity;

        AttributeInstance speed = livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        AttributeInstance jump = livingEntity.getAttribute(Attribute.GENERIC_JUMP_STRENGTH);
        AttributeInstance swim = livingEntity.getAttribute(Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY);

        AttributeInstance health = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance def = livingEntity.getAttribute(Attribute.GENERIC_ARMOR);
        AttributeInstance defPlus = livingEntity.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS);
        AttributeInstance knb_res = livingEntity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);

        AttributeInstance attack = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance atk_spd = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_SPEED); // NO USAR EN ZOMBIES
        AttributeInstance atk_knb = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK);
        AttributeInstance follow_range = livingEntity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE);

        AttributeInstance size = livingEntity.getAttribute(Attribute.GENERIC_SCALE);

        // TODO arreglar problemas de nullpointerexception por mobs que no posean x atributos

        speed.setBaseValue(speed.getBaseValue() * mults.getSpeed());

        jump.setBaseValue(jump.getBaseValue() * mults.getJump());
        swim.setBaseValue(swim.getBaseValue() * mults.getSwim());

        System.out.println(entity.getName() + " BASE DEF:" + def.getBaseValue());
        System.out.println(entity.getName() + " DEF MULTIPLIER:" + mults.getDef());
        System.out.println(entity.getName() + " FINAL DEF" + def.getBaseValue() * mults.getDef());

        health.setBaseValue(health.getBaseValue() * mults.getHealth());
        def.setBaseValue(def.getBaseValue() * mults.getDef());
        defPlus.setBaseValue((defPlus.getBaseValue() + 0.1) + mults.getDefPlus());
        knb_res.setBaseValue(knb_res.getBaseValue() * mults.getKnbRes());

        attack.setBaseValue(attack.getBaseValue() * mults.getAttack());
        atk_spd.setBaseValue((atk_spd.getBaseValue() + 0.1)* mults.getAtkSpd()); // NO USAR EN ZOMBIES
        atk_knb.setBaseValue(atk_knb.getBaseValue() * mults.getAtkKnb());
        follow_range.setBaseValue(follow_range.getBaseValue() * mults.getFollowRange());


        size.setBaseValue(size.getBaseValue() * mults.getSize());

        System.out.println("MULTS APPLIED TO " + entity.getName() +
                " on " + entity.getLocation());

    }

     */


}
