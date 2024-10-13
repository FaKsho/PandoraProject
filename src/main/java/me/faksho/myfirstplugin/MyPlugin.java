package me.faksho.myfirstplugin;

import me.faksho.myfirstplugin.Commands.DieCMD;
import me.faksho.myfirstplugin.Commands.FeedCMD;
import me.faksho.myfirstplugin.Commands.GodmodeCMD;
import me.faksho.myfirstplugin.EventListeners.BlockEvents;
import me.faksho.myfirstplugin.EventListeners.HurtEntitiesEvents;
import me.faksho.myfirstplugin.EventListeners.OreEvents;
import me.faksho.myfirstplugin.EventListeners.Tuto;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // Event Listeners
        System.out.println("Plugin started.");

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new HurtEntitiesEvents(), this);
        getServer().getPluginManager().registerEvents(new Tuto(), this);
        getServer().getPluginManager().registerEvents(new OreEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);

        // Registrar comandos
        getCommand("god").setExecutor(new GodmodeCMD());
        getCommand("die").setExecutor(new DieCMD());
        getCommand("feed").setExecutor(new FeedCMD());

    }

    /*
    @Override
    public void onDisable() {
        // Plugin shutdown logic


    }
     */
}
