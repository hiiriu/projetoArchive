package com.classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.classes.model.User;

public class UserDAO {
    private static final String COLUNAS = "id, username, displayname, email, password";

    public List<User> findAll(Connection conn) throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM user ORDER BY id";
        List<User> lista = new ArrayList<>();
        try (
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next())
                lista.add(mapear(rs));

        }
        return lista;
    }

    public Optional<User> findById(Connection conn, int id) throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM User WHERE id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // adicionar ao optional apenas se houver uma proxima linha
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();

            }
        }
    }

    public List<User> findByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM User WHERE username LIKE ? ";
        List<User> lista2 = new ArrayList<>();
        try (
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next())
                lista2.add(mapear(rs));

        }
        return lista2;

    }

    public Map<String, Integer> contarPosters(Connection conn) throws SQLException {
        String sql = "SELECT u.username, COUNT(p.id) AS total FROM Poster p "
                + "LEFT JOIN Poster p ON p.id_user = u.id GROUP BY u.id, u.usename ORDER BY p.id";
        Map<String, Integer> resultado = new LinkedHashMap<>();
        try (
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("username"), rs.getInt("total"));
            }
        }

        return resultado;

    }

    private static User mapear(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"), rs.getString("displayname"), rs.getString("email"),rs.getString("password"));
    }
}
