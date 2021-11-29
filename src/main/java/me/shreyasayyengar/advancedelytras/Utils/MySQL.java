package me.shreyasayyengar.advancedelytras.Utils;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    private final String host;
    private final String database;
    private final int port;
    private final String username;
    private final String password;
    private Connection connection;

    public MySQL(String host, String database, int port, String username, String password) {
        this.host = host;
        this.database = database;
        this.port = port;
        this.username = username;
        this.password = password;

        try {
            openConnection();
            Bukkit.getLogger().info("&aDatabase Connection Established & Successfully Connected!");
        } catch (SQLException x) {
            Bukkit.getLogger().severe("There was a problem connecting to the MySQL database. Please double check that the information is correct, and that the MySQL server is online");
        }
    }

    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
    }

    public PreparedStatement preparedStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException x) {
            Bukkit.getLogger().warning("There was a problem communicating with the MySQL Database!");
        }
        return ps;
    }

    public Connection getConnection() {
        return connection;
    }
}