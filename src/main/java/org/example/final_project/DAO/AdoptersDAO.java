package org.example.final_project.DAO;

import org.example.final_project.Model.Adopter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptersDAO {
    // Database connection details
    private static final String url = "jdbc:postgresql://localhost:5432/pet_adoption_system";
    private static final String user = "postgres";
    private static final String password = "12345";

    public AdoptersDAO() {
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int addAdopter(Adopter adopter) {
        String sql = "INSERT INTO adopters (name, contact) VALUES (?, ?) RETURNING id";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adopter.getName());
            pstmt.setString(2, adopter.getContact());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }

    public int addAdopter(String name, String contact) {
        Adopter adopter = new Adopter(0, name, contact);
        return addAdopter(adopter); // Call the existing method
    }

    public List<Integer> getAllAdopterIds() {
        List<Integer> adopterIds = new ArrayList<>();
        String sql = "SELECT id FROM adopters";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                adopterIds.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adopterIds;
    }

    public List<Adopter> getAllAdopters() {
        List<Adopter> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters"; // Adjust the SQL query as needed
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Adopter adopter = new Adopter(rs.getInt("id"), rs.getString("name"), rs.getString("contact"));
                adopters.add(adopter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adopters;
    }

    public static Adopter getAdopterById(int id) {
        String sql = "SELECT * FROM adopters WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Adopter(rs.getInt("id"), rs.getString("name"), rs.getString("contact"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Indicate not found
    }
}