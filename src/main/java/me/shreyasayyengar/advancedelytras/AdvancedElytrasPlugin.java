package me.shreyasayyengar.advancedelytras;

import me.shreyasayyengar.advancedelytras.Utils.Config;
import me.shreyasayyengar.advancedelytras.Utils.MySQL;
import me.shreyasayyengar.advancedelytras.commands.BaseAdminCommand;
import me.shreyasayyengar.advancedelytras.commands.FlyCommandUwU;
import me.shreyasayyengar.advancedelytras.commands.FlyTimeCommand;
import me.shreyasayyengar.advancedelytras.commands.RedeemCommand;
import me.shreyasayyengar.advancedelytras.events.Join;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class AdvancedElytrasPlugin extends JavaPlugin {

    private static AdvancedElytrasPlugin instance;
    private static MySQL database;

    public static MySQL getDatabase() {
        return database;
    }

    public static AdvancedElytrasPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("fly").setExecutor(new FlyCommandUwU());
        getCommand("redeem").setExecutor(new RedeemCommand());
        getCommand("flytime").setExecutor(new FlyTimeCommand());
        getCommand("elytraadmin").setExecutor(new BaseAdminCommand());

        getServer().getPluginManager().registerEvents(new Join(), this);

        new Config(this);
        initMySQL();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initMySQL() {
        try {
            database = new MySQL(Config.getHost(), Config.getDatabase(), Config.getPort(), Config.getUsername(), Config.getPassword());
            database.preparedStatement("create table if not exists player_info(player_uuid varchar(36) not null, fly_time int default 0 null);").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Database not connected! Please check that you have entered the correct details in the config, and that your " +
                    "MySQL server is online");
            // TODO:
        }
    }
}