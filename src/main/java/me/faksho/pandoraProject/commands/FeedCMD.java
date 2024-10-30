package me.faksho.pandoraProject.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {

            Player p = (Player) commandSender;
            p.setFoodLevel(20);
            p.sendMessage(ChatColor.GOLD + "Dejaste de ser venezolano.");
        }

        return true;
    }
}
