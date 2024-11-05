package me.faksho.pandoraProject.util;

import me.faksho.pandoraProject.MyPlugin;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Multipliers {

    private double speed;
    private double jump;
    private double swim;

    private double health;
    private double def;
    private double knbRes;

    private double attack;
    private double atkSpd;
    private double atkKnb;
    private double followRange;

    private double size;

    private final Entity entity;

    private static final MyPlugin plugin = MyPlugin.getPlugin();
    private static final FileConfiguration config = plugin.getConfig();

    public Multipliers(Entity entity, String configPath) {
        this.entity = entity;
        loadMultipliers(configPath);
    }

    private void loadMultipliers(String configPath) {

        this.setSpeed(config.getDouble(configPath + ".speed"));
        this.setJump(config.getDouble(configPath + ".jump"));
        this.setSwim(config.getDouble(configPath + ".swim"));
        this.setHealth(config.getDouble(configPath + ".health"));
        this.setDef(config.getDouble(configPath + ".def"));
        this.setKnbRes(config.getDouble(configPath + ".knb-res"));
        this.setAttack(config.getDouble(configPath + ".atk"));
        this.setAtkSpd(config.getDouble(configPath + ".atk-spd"));
        this.setAtkKnb(config.getDouble(configPath + ".atk-knb"));
        this.setFollowRange(config.getDouble(configPath + ".follow-range"));
        this.setSize(config.getDouble(configPath + ".size"));
    }

    public void applyMultipliers() {
        assert this.entity instanceof LivingEntity;
        LivingEntity livingEntity = (LivingEntity) entity;

        applyAttribute(livingEntity, Attribute.GENERIC_MOVEMENT_SPEED, this.getSpeed());
        applyAttribute(livingEntity, Attribute.GENERIC_JUMP_STRENGTH, this.getJump());
        applyAttribute(livingEntity, Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY, this.getSwim(), 0.1);
        applyAttribute(livingEntity, Attribute.GENERIC_MAX_HEALTH, this.getHealth());
        applyAttribute(livingEntity, Attribute.GENERIC_ARMOR, this.getDef());
        applyAttribute(livingEntity, Attribute.GENERIC_KNOCKBACK_RESISTANCE, this.getKnbRes(), 0.1);
        applyAttribute(livingEntity, Attribute.GENERIC_ATTACK_DAMAGE, this.getAttack());
        applyAttribute(livingEntity, Attribute.GENERIC_ATTACK_SPEED, this.getAtkSpd());
        applyAttribute(livingEntity, Attribute.GENERIC_ATTACK_KNOCKBACK, this.getAtkKnb(), 0.5);
        applyAttribute(livingEntity, Attribute.GENERIC_FOLLOW_RANGE, this.getFollowRange());
        applyAttribute(livingEntity, Attribute.GENERIC_SCALE, this.getSize());

        System.out.println(" ATTRIBUTES APPLIES TO " + livingEntity.getName());
    }

    private void applyAttribute(LivingEntity entity, Attribute attribute, double multiplier) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(attributeInstance.getBaseValue() * multiplier);
        } else {

            System.out.println( "WARNING: No se pudo aplicar " + attribute + " a " + entity.getName());
        }
    }

    private void applyAttribute(LivingEntity entity, Attribute attribute, double multiplier, double increment) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue((attributeInstance.getBaseValue() + increment) * multiplier);
        } else {

            System.out.println( "WARNING: No se pudo aplicar " + attribute + " a " + entity.getName());
        }
    }


    public Entity getEntity() {
        return entity;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getFollowRange() {
        return followRange;
    }

    public void setFollowRange(double followRange) {
        this.followRange = followRange;
    }

    public double getAtkKnb() {
        return atkKnb;
    }

    public void setAtkKnb(double atkKnb) {
        this.atkKnb = atkKnb;
    }

    public double getAtkSpd() {
        return atkSpd;
    }

    public void setAtkSpd(double atkSpd) {
        this.atkSpd = atkSpd;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getKnbRes() {
        return knbRes;
    }

    public void setKnbRes(double knbRes) {
        this.knbRes = knbRes;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSwim() {
        return swim;
    }

    public void setSwim(double swim) {
        this.swim = swim;
    }

    public double getJump() {
        return jump;
    }

    public void setJump(double jump) {
        this.jump = jump;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
