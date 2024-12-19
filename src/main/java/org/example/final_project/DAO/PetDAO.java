package org.example.final_project.DAO;

import org.example.final_project.Model.Pet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private final String url = "jdbc:postgresql://localhost:5432/pet_adoption_system";
    private final String user = "postgres";
    private final String password = "12345";

    public PetDAO() {
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<Pet> getAllPets(String typeFilter) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE is_adopted = FALSE";
        if (typeFilter != null && !typeFilter.equals("All")) {
            sql += " AND type = ?";
        }

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (typeFilter != null && !typeFilter.equals("All")) {
                stmt.setString(1, typeFilter);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pets.add(new Pet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("breed"),
                            rs.getString("type"),
                            rs.getInt("age"),
                            rs.getString("description")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    public void addPet(Pet pet) {
        String sql = "INSERT INTO pets (name, breed, type, age, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getBreed());
            pstmt.setString(3, pet.getType());
            pstmt.setInt(4, pet.getAge());
            pstmt.setString(5, pet.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePet(Pet pet) {
        // Check if the pet is referenced in the adoption_records table
        String checkQuery = "SELECT COUNT(*) FROM adoption_records WHERE pet_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, pet.getId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cannot delete pet. There are adoption records associated with this pet.");
                return; // Prevent deletion
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Proceed to delete the pet if no adoption records exist
        String sql = "DELETE FROM pets WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pet.getId());
            pstmt.executeUpdate();
            System.out.println("Pet with ID " + pet.getId() + " has been removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}