package me.shreyasayyengar.advancedelytras.commands;

import me.shreyasayyengar.advancedelytras.Utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyTimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.hasPermission("elytra.flytime")) {
                player.sendMessage(ChatColor.GRAY + "[AE] You have " + Config.getTime(player.getUniqueId()) + " remaining");
            } else player.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
        }

        return false;
    }
}
