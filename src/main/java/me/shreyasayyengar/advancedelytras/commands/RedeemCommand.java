package me.shreyasayyengar.advancedelytras.commands;

import me.shreyasayyengar.advancedelytras.AdvancedElytrasPlugin;
import me.shreyasayyengar.advancedelytras.Utils.Config;
import me.shreyasayyengar.advancedelytras.Utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class RedeemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (player.hasPermission("elytra.redeem")) {
                MySQL database = AdvancedElytrasPlugin.getDatabase();
                ItemStack item = player.getInventory().getItemInMainHand();

                if (item.getType() == Material.FIREWORK_ROCKET) {

                    FireworkMeta meta = (FireworkMeta) item.getItemMeta();

                    int totalTime = (meta.getPower() * item.getAmount()) + Config.getTime(player.getUniqueId());

                    try {
                        database.preparedStatement("update player_info set fly_time = '" + totalTime + "' where player_uuid ='" + player.getUniqueId() + "'").executeUpdate();

                        Bukkit.getLogger().info("Total " + totalTime);
                        Bukkit.getLogger().info("Player " + Config.getTime(player.getUniqueId()));

                        player.sendMessage(ChatColor.AQUA + "[AE] " + totalTime + " has been successfully " + ChatColor.GREEN + "added " + ChatColor.AQUA + "to your flight time!");
                        player.getInventory().setItemInMainHand(null);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(ChatColor.RED + "[AE] Failed!");
                    }
                }
            } else player.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
        } else sender.sendMessage("You cannot do this from the console lol jimb00b");
        return false;
    }
}