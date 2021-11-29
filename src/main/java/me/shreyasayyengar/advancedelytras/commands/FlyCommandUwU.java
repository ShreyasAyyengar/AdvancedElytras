package me.shreyasayyengar.advancedelytras.commands;

import me.shreyasayyengar.advancedelytras.Utils.Config;
import me.shreyasayyengar.advancedelytras.Utils.FlyRunnable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class FlyCommandUwU implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (player.hasPermission("elytra.fly")) {

                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
                    if (player.isFlying()) {
                        player.setAllowFlight(false);
                        return false;
                    } else if (!player.isFlying()) {

                        if (Config.getTime(player.getUniqueId()) > 0) {
                            player.setAllowFlight(true);
                            player.setFlying(true);
                            player.setVelocity(new Vector(player.getVelocity().getX(), 0.4, player.getVelocity().getZ()));
                            new FlyRunnable(player);

                        } else player.sendMessage(ChatColor.RED + "[AE] You do not have any flight time!");

                    }
                } else player.sendMessage(ChatColor.RED + "[AE] You must be wearing an elytra to use this command!");
            } else player.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
        }
        return false;
    }
}