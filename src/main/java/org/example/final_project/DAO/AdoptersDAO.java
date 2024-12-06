package org.example.final_project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdoptersDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/pet_adoption_system";
    private static final String user = "postgres";
    private static final String password = "12345";

    public AdoptersDAO() {
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pet_adoption_system", "postgres", "12345");
    }

    public List<Adopter> getAllAdopters() {
        List<Adopter> adopters = new ArrayList();
        String sql = "SELECT * FROM adopters";

        try {
            Connection conn = connect();

            try {
                Statement stmt = conn.createStatement();

                try {
                    ResultSet rs = stmt.executeQuery(sql);

                    try {
                        while(rs.next()) {
                            adopters.add(new Adopter(rs.getInt("id"), rs.getString("name"), rs.getString("contact")));
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            e.printStackTrace();
        }

        return adopters;
    }

    public int addAdopter(Adopter adopter) {
        String sql = "INSERT INTO adopters (name, contact) VALUES (?, ?)";

        try {
            Connection conn = connect();

            int var7;
            label117: {
                try {
                    PreparedStatement pstmt;
                    label109: {
                        pstmt = conn.prepareStatement(sql, 1);

                        try {
                            pstmt.setString(1, adopter.getName());
                            pstmt.setString(2, adopter.getContact());
                            int rowsAffected = pstmt.executeUpdate();
                            if (rowsAffected <= 0) {
                                break label109;
                            }

                            ResultSet generatedKeys = pstmt.getGeneratedKeys();

                            label92: {
                                try {
                                    if (generatedKeys.next()) {
                                        var7 = generatedKeys.getInt(1);
                                        break label92;
                                    }
                                } catch (Throwable var12) {
                                    if (generatedKeys != null) {
                                        try {
                                            generatedKeys.close();
                                        } catch (Throwable var11) {
                                            var12.addSuppressed(var11);
                                        }
                                    }

                                    throw var12;
                                }

                                if (generatedKeys != null) {
                                    generatedKeys.close();
                                }
                                break label109;
                            }

                            if (generatedKeys != null) {
                                generatedKeys.close();
                            }
                        } catch (Throwable var13) {
                            if (pstmt != null) {
                                try {
                                    pstmt.close();
                                } catch (Throwable var10) {
                                    var13.addSuppressed(var10);
                                }
                            }

                            throw var13;
                        }

                        if (pstmt != null) {
                            pstmt.close();
                        }
                        break label117;
                    }

                    if (pstmt != null) {
                        pstmt.close();
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

                return -1;
            }

            if (conn != null) {
                conn.close();
            }

            return var7;
        } catch (SQLException var15) {
            SQLException e = var15;
            e.printStackTrace();
            return -1;
        }
    }

    public static List<Integer> getAllAdopterIds() {
        List<Integer> adopterIds = new ArrayList();
        String sql = "SELECT id FROM adopters";

        try {
            Connection conn = connect();

            try {
                Statement stmt = conn.createStatement();

                try {
                    ResultSet rs = stmt.executeQuery(sql);

                    try {
                        while(rs.next()) {
                            adopterIds.add(rs.getInt("id"));
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

        return adopterIds;
    }
}

