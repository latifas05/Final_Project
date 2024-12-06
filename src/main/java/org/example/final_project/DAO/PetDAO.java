package org.example.final_project;

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
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pet_adoption_system", "postgres", "12345");
    }

    public List<Pet> getAllPets(String typeFilter) {
        List<Pet> pets = new ArrayList();
        String sql = "SELECT * FROM pets";
        if (typeFilter != null && !typeFilter.equals("All")) {
            sql = sql + " WHERE type = ?";
        }

        try {
            Connection conn = this.connect();

            try {
                PreparedStatement stmt = conn.prepareStatement(sql);

                try {
                    if (typeFilter != null && !typeFilter.equals("All")) {
                        stmt.setString(1, typeFilter);
                    }

                    ResultSet rs = stmt.executeQuery();

                    try {
                        while(rs.next()) {
                            pets.add(new Pet(rs.getInt("id"), rs.getString("name"), rs.getString("breed"), rs.getString("type"), rs.getInt("age"), rs.getString("description")));
                        }
                    } catch (Throwable var12) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var11) {
                                var12.addSuppressed(var11);
                            }
                        }

                        throw var12;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var13) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var10) {
                            var13.addSuppressed(var10);
                        }
                    }

                    throw var13;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var14) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var9) {
                        var14.addSuppressed(var9);
                    }
                }

                throw var14;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            e.printStackTrace();
        }

        return pets;
    }

    public void addPet(Pet pet) {
        String sql = "INSERT INTO pets (name, breed, type, age, description) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.connect();

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);

                try {
                    pstmt.setString(1, pet.getName());
                    pstmt.setString(2, pet.getBreed());
                    pstmt.setString(3, pet.getType());
                    pstmt.setInt(4, pet.getAge());
                    pstmt.setString(5, pet.getDescription());
                    pstmt.executeUpdate();
                } catch (Throwable var9) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

    }

    public void removePet(Pet pet) {
        String sql = "DELETE FROM pets WHERE id = ?";

        try {
            Connection conn = this.connect();

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);

                try {
                    pstmt.setInt(1, pet.getId());
                    pstmt.executeUpdate();
                } catch (Throwable var9) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

    }
}

