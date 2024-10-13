package me.faksho.myfirstplugin;

import me.faksho.myfirstplugin.commands.DieCMD;
import me.faksho.myfirstplugin.commands.FeedCMD;
import me.faksho.myfirstplugin.commands.GodmodeCMD;
import me.faksho.myfirstplugin.eventListeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // Event Listeners
        System.out.println("Plugin started.");

        // Registrar listeners
        // TODO: podría leer las clases que están en el paquete <<eventListeners>> y cargarlas automáticamente.
        getServer().getPluginManager().registerEvents(new HurtEntitiesEvents(), this);
        getServer().getPluginManager().registerEvents(new Tuto(), this);
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);
        getServer().getPluginManager().registerEvents(new MovementEvents(), this);
        getServer().getPluginManager().registerEvents(new SpawnEvents(), this);
        getServer().getPluginManager().registerEvents(new VillagerEvents(), this);

        getServer().getPluginManager().registerEvents(new ExtraEvents(), this);


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
