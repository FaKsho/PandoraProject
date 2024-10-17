package me.faksho.myfirstplugin.eventListeners;

import jdk.javadoc.internal.doclint.HtmlTag;
import me.faksho.myfirstplugin.MyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.configuration.file.FileConfiguration;
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

        Random random = new Random();

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
                            quotes.get(random.nextInt(quotes.size())));
            }
        }


        // --------------------------------------------------------------------------- //



    }


    // ----------------------------------//--------------------------------------- //
    // ----------------------------------//--------------------------------------- //
    // ----------------------------------//--------------------------------------- //


    // * EVENTOS DE ROMPER BLOQUES
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {

        Block block = e.getBlock();
        Material blockType = block.getType();
        Player player = e.getPlayer();

        World world = block.getWorld();

        Random random = new Random();

        MyPlugin plugin = MyPlugin.getPlugin();

        FileConfiguration config = plugin.getConfig();

        switch (blockType) {

            // ORE DE DIAMANTE
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:

                if(random.nextDouble(100.0) <= config.getDouble("spawn-on-break.diamond-ore.chance")) {

                    for (int i = 0; i < config.getInt("spawn-on-break.diamond-ore.amount"); i++) {

                        for(String string: config.getStringList("spawn-on-break.diamond-ore.mobs")) {

                            world.spawnEntity(e.getPlayer().getLocation(), EntityType.valueOf(string));
                        }
                    }
                }
            break;

            // --------------------------------------------------------------------------- //
            // TODO terminar
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:

            break;

            // --------------------------------------------------------------------------- //

            // Piedra
            case STONE:
            case DEEPSLATE:

                if(random.nextDouble(100.0) == config.getDouble("spawn-on-break.stones.chance")) {

                    for(int i = 0; i < config.getInt("spawn-on-break.stones.amount"); i++) {

                        for(String string: config.getStringList("spawn-on-break.stones.mobs")) {

                            world.spawnEntity(e.getPlayer().getLocation(), EntityType.valueOf(string));
                        }
                    }
                }
            break;


            // --------------------------------------------------------------------------- //
            // PANAL DE ABEJAS
            case BEE_NEST:

                if(random.nextDouble(100.0) == config.getDouble("spawn-on-break.beenest.chance"))

                for(int i=0; i <  config.getInt("spawn-on-break.beenest.amount"); i++) {

                    for(String string: config.getStringList("spawn-on-break.beenest.mobs")) {

                        world.spawnEntity(e.getPlayer().getLocation(), EntityType.valueOf(string));
                    }
                }
            break;
        }

        // --------------------------------------------------------------------------- //

    }
}
