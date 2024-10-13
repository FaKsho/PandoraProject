package me.faksho.myfirstplugin.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class BlockEvents implements Listener {


    // * EVENTOS DE PONER BLOQUES
    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {


        // MENSAJES PERSONALIZADOS AL USAR WITHER SKULLS
        Material blockPlaced = e.getBlockPlaced().getType();

        if (blockPlaced == Material.WITHER_SKELETON_SKULL || blockPlaced == Material.WITHER_SKELETON_WALL_SKULL) {

            if (e.getBlockAgainst().getType() == Material.SOUL_SAND) {

                ArrayList<String> quotes = getStrings();

                e.getPlayer()
                        .getServer()
                        .broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD +
                            quotes.get(new Random().nextInt(quotes.size())));
            }
        }

        // --------------------------------------------------------------------------- //
    }

    private static ArrayList<String> getStrings() {
        ArrayList<String> quotes = new ArrayList<>();
        quotes.add("Sientes un terrible escalofrío por la espalda...");
        quotes.add("El eco de los alaridos suena por todas partes...");
        quotes.add("Crees que una presencia maligna te está observando...");
        quotes.add("Sientes vibraciones desde las profundidades…");
        quotes.add("Va a ser una noche horrible...");
        quotes.add("El aire es cada vez más frío a tu alrededor…");
        return quotes;
    }

    // ----------------------------------//--------------------------------------- //

    // * EVENTOS DE ROMPER BLOQUES
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {

            

    }
}
