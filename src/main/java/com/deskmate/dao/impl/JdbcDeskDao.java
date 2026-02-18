package com.deskmate.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.deskmate.dao.DeskDao;
import com.deskmate.exception.DatabaseOperationException;
import com.deskmate.model.Desk;
import com.deskmate.utils.DbConnectionFactory;

public class JdbcDeskDao implements DeskDao {

    @Override
    public long insertDesk(String code, String name) {
        String sql = "INSERT INTO desks (desk_code, name, active) VALUES (?, ?, TRUE)";
        try (Connection c = DbConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, code);
            ps.setString(2, name);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to insert desk", e);
        }
    }

    @Override
    public void deactivateDesk(long deskId) {
        String sql = "UPDATE desks SET active = FALSE WHERE desk_id = ?";
        try (Connection c = DbConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, deskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to deactivate desk", e);
        }
    }

    @Override
    public Optional<Desk> findByCode(String code) {
        String sql = "SELECT desk_id, desk_code, name, active, created_at FROM desks WHERE desk_code = ?";
        try (Connection c = DbConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find desk by code", e);
        }
    }

    @Override
    public Optional<Desk> findById(long deskId) {
        String sql = "SELECT desk_id, desk_code, name, active, created_at FROM desks WHERE desk_id = ?";
        try (Connection c = DbConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, deskId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find desk by id", e);
        }
    }

    @Override
    public List<Desk> listActive() {
        String sql = "SELECT desk_id, desk_code, name, active, created_at FROM desks WHERE active = TRUE ORDER BY desk_code";
        try (Connection c = DbConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Desk> out = new ArrayList<>();
            while (rs.next()) out.add(map(rs));
            return out;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to list active desks", e);
        }
    }

    private Desk map(ResultSet rs) throws SQLException {
        return new Desk(
                rs.getLong("desk_id"),
                rs.getString("desk_code"),
                rs.getString("name"),
                rs.getBoolean("active"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}

