package me.shreyasayyengar.advancedelytras.commands;

import me.shreyasayyengar.advancedelytras.Utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class BaseAdminCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.hasPermission("elytra.admin")) {

            if (args.length == 0 || args.length == 1) {
                sender.sendMessage("not a command lol " + ChatColor.RED + "L");
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("check")) {

                    Player player = Bukkit.getPlayer(args[1]);

                    try {
                        int time = Config.getTime(player.getUniqueId());
                        player.sendMessage(ChatColor.BLUE + "[AE] " + player.getName() + " has " + time + " minutes remaining.");
                    } catch (Exception x) {
                        sender.sendMessage(ChatColor.DARK_RED + "[AE] That player has not joined the server yet!");
                    }
                }
            }

            if (args.length == 3) {

                if (args[0].equalsIgnoreCase("set")) {

                    Player player = Bukkit.getPlayer(args[1]);

                    int min;

                    try {
                        min = Integer.parseInt(args[2]);
                    } catch (NumberFormatException x) {
                        sender.sendMessage(ChatColor.RED + "[AE] That is not a valid number!");
                        return false;
                    }

                    try {
                        Config.setTime(player.getUniqueId(), min);
                        sender.sendMessage(ChatColor.GREEN + "" + min + " was successfully " + ChatColor.GOLD + " set " + ChatColor.GREEN + "to " + player.getName() + "'s fly time!");
                    } catch (Exception x) {
                        sender.sendMessage(ChatColor.DARK_RED + "[AE] That player has not joined the server yet!");
                    }

                }

                if (args[0].equalsIgnoreCase("add")) {

                    Player player = Bukkit.getPlayer(args[1]);

                    int min;

                    try {
                        min = Integer.parseInt(args[2]);
                    } catch (NumberFormatException x) {
                        sender.sendMessage(ChatColor.RED + "[AE] That is not a valid number!");
                        return false;
                    }

                    try {
                        Config.setTime(player.getUniqueId(), Config.getTime(player.getUniqueId()) + min);
                        sender.sendMessage(ChatColor.GREEN + "" + min + " was successfully" + ChatColor.AQUA + " add " + ChatColor.GREEN + "to " + player.getName() + "'s fly time!");
                    } catch (Exception x) {
                        sender.sendMessage(ChatColor.DARK_RED + "[AE] That player has not joined the server yet!");
                    }

                }

                if (args[0].equalsIgnoreCase("subtract")) {

                    Player player = Bukkit.getPlayer(args[1]);

                    int min;

                    try {
                        min = Integer.parseInt(args[2]);

                    } catch (NumberFormatException x) {
                        sender.sendMessage(ChatColor.RED + "[AE] That is not a valid number!");
                        return false;
                    }

                    try {
                        if (Config.getTime(player.getUniqueId()) - min < 0) {
                            sender.sendMessage(ChatColor.RED + "[AE] That player only has " + Config.getTime(player.getUniqueId()) + " left!");
                            return false;
                        } else {
                            Config.setTime(player.getUniqueId(), Config.getTime(player.getUniqueId()) - min);
                            sender.sendMessage(ChatColor.GREEN + "" + min + " was successfully" + ChatColor.RED + " subtracted " + ChatColor.GREEN + "from  " + player.getName() + "'s fly time!");
                        }
                    } catch (Exception x) {
                        sender.sendMessage(ChatColor.DARK_RED + "[AE] That player has not joined the server yet!");
                    }

                }
            }
        } else sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (args.length == 1) {
            return Arrays.asList("set", "add", "subtract", "check");
        }

        return null;
    }
}
