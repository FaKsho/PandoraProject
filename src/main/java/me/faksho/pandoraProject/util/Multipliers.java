package me.faksho.pandoraProject.util;

import org.bukkit.entity.Entity;

public class Multipliers {

    private double speed;
    private double jump;
    private double swim;

    private double health;
    private double def;
    private double defPlus = def * 0.2;
    private double knbRes;

    private double attack;
    private double atkSpd;
    private double atkKnb;
    private double followRange;

    private double size;

    private final Entity entity;

    public Multipliers(Entity entity) {
        this.entity = entity;
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

    public double getDefPlus() {
        return defPlus;
    }
}
