package me.shreyasayyengar.advancedelytras.events;

import me.shreyasayyengar.advancedelytras.AdvancedElytrasPlugin;
import me.shreyasayyengar.advancedelytras.Utils.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.util.UUID;

public class Join implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        MySQL database = AdvancedElytrasPlugin.getDatabase();

        try {
            ResultSet resultSet = database.preparedStatement("select count(player_uuid) from player_info where player_uuid = '" + uuid + "'").executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                database.preparedStatement("insert into player_info (player_uuid, fly_time) " +
                        "VALUES('" + uuid + "', default)").executeUpdate();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}
