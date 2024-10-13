package me.faksho.myfirstplugin.eventListeners;

import jdk.javadoc.internal.doclint.HtmlTag;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Random;

public class BlockEvents implements Listener {

    // * EVENTOS DE PONER BLOQUES
    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {

        Block block = e.getBlock();
        Material blockPlaced = e.getBlockPlaced().getType();

        World world = block.getWorld();

        //  WITHER SKULLS CUSTOM MESSAGES
        if (blockPlaced == Material.WITHER_SKELETON_SKULL || blockPlaced == Material.WITHER_SKELETON_WALL_SKULL) {

            if (e.getBlockAgainst().getType() == Material.SOUL_SAND) {

                ArrayList<String> quotes = new ArrayList<>();
                quotes.add("Sientes un terrible escalofrío por la espalda...");
                quotes.add("El eco de los alaridos suena por todas partes...");
                quotes.add("Crees que una presencia maligna te está observando...");
                quotes.add("Sientes vibraciones desde las profundidades…");
                quotes.add("Va a ser una noche horrible...");
                quotes.add("El aire es cada vez más frío a tu alrededor…");

                e.getPlayer()
                        .getServer()
                        .broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD +
                            quotes.get(new Random().nextInt(quotes.size())));
            }
        }


        // --------------------------------------------------------------------------- //



    }





    // ----------------------------------//--------------------------------------- //





    // * EVENTOS DE ROMPER BLOQUES
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {

        Block block = e.getBlock();
        Material blockType = block.getType();

        World world = block.getWorld();

        switch (blockType) {

            // ORE DE DIAMANTE
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
                // TODO PLANTEARME bajar probabilidad de los creepers
                if(!new Random().nextBoolean()) {

                    e.getPlayer()
                            .getWorld()
                            .spawnEntity(e.getPlayer().getLocation(), EntityType.CREEPER);
                    e.getPlayer()
                            .getWorld()
                            .spawnEntity(e.getPlayer().getLocation(), EntityType.LIGHTNING_BOLT);
                }

            break;

            // --------------------------------------------------------------------------- //

            // PANAL DE ABEJAS
            case BEE_NEST:

                for(int i=0; i < 5; i++) {

                    // TODO hacer que spawneen con bichos encima, pero unificar esto con lo de HurEntitiesEvents
                    world.spawnEntity(
                            block.getLocation(),
                            EntityType.BEE);
                }
        }

        // --------------------------------------------------------------------------- //

        /*
         * TODO repensar esto o moverlo a otro lado

        // No romper bloques con pollo encima
        Player p = e.getPlayer();

        if(p.getInventory().contains(Material.CHICKEN)) {
            p.sendMessage("No chicken pliz");
            e.setCancelled(true);
        }
         */



    }
}
