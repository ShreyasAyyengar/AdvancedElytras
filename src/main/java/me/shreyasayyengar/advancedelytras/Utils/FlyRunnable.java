package me.shreyasayyengar.advancedelytras.Utils;

import me.shreyasayyengar.advancedelytras.AdvancedElytrasPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyRunnable extends BukkitRunnable {

    private final Player player;

    public FlyRunnable(Player player) {
        this.player = player;
        this.runTaskTimer(AdvancedElytrasPlugin.getInstance(), 0, 1200);
    }

    @Override
    public void run() {
        if (player.isFlying()) {
            Config.setTime(player.getUniqueId(), Config.getTime(player.getUniqueId()) - 1);
        }

        if (Config.getTime(player.getUniqueId()) < 0) {
            manageCancel();
        }

        if (Config.getTime(player.getUniqueId()) == 1) {
            player.sendMessage(ChatColor.DARK_PURPLE + "[AE] You have 1 minute of flight time remaining, make sure you stay low!");
        }

        try {
            if (!player.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
                manageCancel();
            }
        } catch (Exception x) {
            manageCancel();
        }
    }

    private void manageCancel() {
        player.setAllowFlight(false);
        player.setFlying(false);

        player.sendMessage(ChatColor.YELLOW + "[AE] You have no more flight time left! Disabling your fly...");
        cancel();
    }
}
