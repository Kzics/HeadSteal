package com.headsteal.database;

import java.sql.*;
import java.time.Instant;
import java.util.UUID;

public class PlayerDeathDAO {
    private final Connection connection;

    public PlayerDeathDAO(String url) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(url);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS player_deaths (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "player_uuid VARCHAR(36) NOT NULL," +
                "death_time TIMESTAMP NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void insertPlayerDeath(UUID playerUUID) throws SQLException {
        String sql = "INSERT INTO player_deaths (player_uuid, death_time) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerUUID.toString());
            statement.setTimestamp(2, java.sql.Timestamp.from(Instant.now()));
            statement.executeUpdate();
        }
    }

    public void removePlayerDeath(UUID playerUUID) throws SQLException {
        String sql = "DELETE FROM player_deaths WHERE player_uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerUUID.toString());
            statement.executeUpdate();
        }
    }

    public boolean isPlayerDead(UUID playerUUID) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM player_deaths WHERE player_uuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerUUID.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}