package me.faksho.myfirstplugin.EventListeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;

import java.util.Random;

public class OreEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Block block = e.getBlock();
        System.out.println(block.getType());

        if(block.getType() == Material.DIAMOND_ORE) {

            Random random = new Random();

            if(!random.nextBoolean()) {

                e.getPlayer()
                        .getWorld()
                        .spawnEntity(e.getPlayer().getLocation(), EntityType.CREEPER);
                e.getPlayer()
                        .getWorld()
                        .spawnEntity(e.getPlayer().getLocation(), EntityType.LIGHTNING_BOLT);
            }
        }
    }
}
