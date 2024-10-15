package me.faksho.myfirstplugin;

import me.faksho.myfirstplugin.commands.DieCMD;
import me.faksho.myfirstplugin.commands.FeedCMD;
import me.faksho.myfirstplugin.commands.GodmodeCMD;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Set;

public final class MyPlugin extends JavaPlugin {

    private static MyPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        System.out.println("Plugin started.");

        registrarListener();

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

    public static MyPlugin getPlugin() {

        return plugin;
    }

    private void registrarListener() { // CHATGPT COSAS

        // Especifica el paquete donde están tus clases Listener
        Reflections reflections = new Reflections("me.faksho.myfirstplugin.eventListeners");

        // Encuentra todas las clases que implementan la interfaz Listener
        Set<Class<? extends Listener>> listeners = reflections.getSubTypesOf(Listener.class);

        // Instancia y registra cada clase Listener
        for (Class<? extends Listener> listenerClass : listeners) {

            try {

                Listener listener = listenerClass.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
                getLogger().info("Registrado Listener: " + listenerClass.getName());
            } catch (Exception e) {

                getLogger().severe("No se pudo registrar el Listener: " + listenerClass.getName());
                e.printStackTrace();
            }
        }
    }


}
