package me.faksho.pandoraProject.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DieCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /die - automorision
        if(command.getName().equalsIgnoreCase("die")) {

            if(sender instanceof Player) {

                Player player = (Player) sender;
                player.setHealth(0.0);
                player.sendMessage(ChatColor.RED + "Automorision activada.");

            } else if (sender instanceof ConsoleCommandSender) {

                System.out.println("You can't use this command in the console.");
            } else if (sender instanceof BlockCommandSender) {

                System.out.println("You can't use this command on an Command Block.");
            }
        }

        return true;
    }
}
