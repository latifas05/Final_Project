package org.example.final_project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdoptionRecordsDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/pet_adoption_system";
    private static final String user = "postgres";
    private static final String password = "12345";

    public AdoptionRecordsDAO() {
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pet_adoption_system", "postgres", "12345");
    }

    public static List<AdoptionRecord> getAllRecords() {
        List<AdoptionRecord> records = new ArrayList();
        String sql = "SELECT ar.id AS adoption_id, ar.adopter_id, ar.pet_id, a.name AS adopter_name, a.contact AS adopter_contact, p.name AS pet_name, p.type AS pet_type, ar.adoption_date FROM adoption_records ar JOIN adopters a ON ar.adopter_id = a.id JOIN pets p ON ar.pet_id = p.id";

        try {
            Connection conn = connect();

            try {
                Statement stmt = conn.createStatement();

                try {
                    ResultSet rs = stmt.executeQuery(sql);

                    try {
                        while(rs.next()) {
                            records.add(new AdoptionRecord(rs.getInt("adoption_id"), rs.getInt("adopter_id"), rs.getInt("pet_id"), rs.getString("adopter_name"), rs.getString("adopter_contact"), rs.getString("pet_name"), rs.getString("pet_type"), rs.getDate("adoption_date").toLocalDate()));
                        }
                    } catch (Throwable var10) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var11) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var12) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var12.addSuppressed(var7);
                    }
                }

                throw var12;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var13) {
            SQLException e = var13;
            e.printStackTrace();
        }

        return records;
    }

    public void addAdoptionRecord(AdoptionRecord record) {
        if (!this.doesAdopterExist(record.getAdopterId())) {
            System.out.println("Adopter with ID " + record.getAdopterId() + " does not exist.");
        } else {
            String sql = "INSERT INTO adoption_records (adopter_id, pet_id, adopter_name, adopter_contact, pet_name, pet_type, adoption_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                Connection conn = connect();

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    try {
                        pstmt.setInt(1, record.getAdopterId());
                        pstmt.setInt(2, record.getPetId());
                        pstmt.setString(3, record.getAdopterName());
                        pstmt.setString(4, record.getAdopterContact());
                        pstmt.setString(5, record.getPetName());
                        pstmt.setString(6, record.getPetType());
                        pstmt.setDate(7, Date.valueOf(record.getAdoptionDate()));
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

    private boolean doesAdopterExist(int adopterId) {
        String sql = "SELECT 1 FROM adopters WHERE id = ?";

        try {
            Connection conn = connect();

            boolean var6;
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);

                try {
                    pstmt.setInt(1, adopterId);
                    ResultSet rs = pstmt.executeQuery();
                    var6 = rs.next();
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

            return var6;
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
            return false;
        }
    }

    private String getPetName(int petId) {
        String sql = "SELECT name FROM pets WHERE id = ?";

        try {
            Connection conn = connect();

            label78: {
                String var6;
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    label80: {
                        try {
                            pstmt.setInt(1, petId);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                var6 = rs.getString("name");
                                break label80;
                            }
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
                        break label78;
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

                return var6;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

        return null;
    }

    private String getPetType(int petId) {
        String sql = "SELECT type FROM pets WHERE id = ?";

        try {
            Connection conn = connect();

            label78: {
                String var6;
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    label80: {
                        try {
                            pstmt.setInt(1, petId);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                var6 = rs.getString("type");
                                break label80;
                            }
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
                        break label78;
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

                return var6;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

        return null;
    }
}

