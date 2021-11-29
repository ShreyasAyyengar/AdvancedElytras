package me.shreyasayyengar.advancedelytras.Utils;

import me.shreyasayyengar.advancedelytras.AdvancedElytrasPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Config {

    private static AdvancedElytrasPlugin main;

    public Config(AdvancedElytrasPlugin main) {
        Config.main = main;
        main.getConfig().options().configuration();
        main.saveDefaultConfig();
    }

    public static String getUsername() {
        return main.getConfig().getString("MySQL.username");
    }

    public static String getPassword() {
        return main.getConfig().getString("MySQL.password");
    }

    public static String getDatabase() {
        return main.getConfig().getString("MySQL.database");
    }

    public static String getHost() {
        return main.getConfig().getString("MySQL.host");
    }

    public static int getPort() {
        return main.getConfig().getInt("MySQL.port");
    }

    public static int getTime(UUID uuid) {
        try {
            ResultSet resultSet = AdvancedElytrasPlugin.getDatabase().preparedStatement("select fly_time from player_info where player_uuid = '" + uuid + "'").executeQuery();
            resultSet.next();
            return resultSet.getInt("fly_time");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setTime(UUID uuid, int time) {
        try {
            AdvancedElytrasPlugin.getDatabase().preparedStatement("update player_info set fly_time = '" + time + "' where player_uuid = '" + uuid + "'").executeUpdate();

        } catch (SQLException ignored) {
        }
    }
}
