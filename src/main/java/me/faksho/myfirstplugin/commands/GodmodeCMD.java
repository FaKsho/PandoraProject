package me.faksho.myfirstplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodmodeCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(!player.isInvulnerable()) {

                player.setInvulnerable(true);
                player.sendMessage(ChatColor.GREEN + "Godmode ON");
            } else {
                player.setInvulnerable(false);
                player.sendMessage(ChatColor.DARK_RED + "Godmode OFF");
            }
        }

        return true;
    }
}
