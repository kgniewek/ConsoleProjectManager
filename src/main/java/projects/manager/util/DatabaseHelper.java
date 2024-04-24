package projects.manager.util;

import java.sql.*;

public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:sqlite:mydatabase.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }


    public void assignProjectToUser(String userId, String projectId) {
        String sql = "INSERT INTO user_projects (userId, projectId) VALUES (?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS projects (id TEXT PRIMARY KEY, name TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (id TEXT PRIMARY KEY, projectId TEXT NOT NULL, name TEXT NOT NULL, assignedUserId TEXT, FOREIGN KEY (projectId) REFERENCES projects(id))");
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id TEXT PRIMARY KEY, name TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS teams (id TEXT PRIMARY KEY, name TEXT NOT NULL, assignedProjectId TEXT, FOREIGN KEY (assignedProjectId) REFERENCES projects(id))");
            stmt.execute("CREATE TABLE IF NOT EXISTS team_users (teamId TEXT, userId TEXT, PRIMARY KEY (teamId, userId), FOREIGN KEY (teamId) REFERENCES teams(id), FOREIGN KEY (userId) REFERENCES users(id))");
            stmt.execute("CREATE TABLE IF NOT EXISTS user_projects (userId TEXT, projectId TEXT, PRIMARY KEY (userId, projectId), FOREIGN KEY (userId) REFERENCES users(id), FOREIGN KEY (projectId) REFERENCES projects(id))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
