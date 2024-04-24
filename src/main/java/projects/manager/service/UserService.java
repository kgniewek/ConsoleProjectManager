package projects.manager.service;

import projects.manager.model.Team;
import projects.manager.model.User;
import projects.manager.util.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import projects.manager.model.Project;


public class UserService {
    public void assignProjectToUser(String userId, String projectId) {
        String sql = "INSERT INTO user_projects (userId, projectId) VALUES (?, ?) ON CONFLICT(userId, projectId) DO NOTHING";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (id, name) VALUES (?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getString("id"), rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public Optional<User> getUserById(String id) {
        Optional<User> result = Optional.empty();
        String sqlUser = "SELECT * FROM users WHERE id = ?";
        String sqlTeams = "SELECT teams.* FROM teams "
                + "JOIN team_users ON teams.id = team_users.teamId "
                + "WHERE team_users.userId = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmtUser = conn.prepareStatement(sqlUser);
             PreparedStatement pstmtTeams = conn.prepareStatement(sqlTeams)) {

            pstmtUser.setString(1, id);
            ResultSet rsUser = pstmtUser.executeQuery();
            if (rsUser.next()) {
                User user = new User(rsUser.getString("id"), rsUser.getString("name"));

                pstmtTeams.setString(1, id);
                ResultSet rsTeams = pstmtTeams.executeQuery();
                List<Team> teams = new ArrayList<>();
                while (rsTeams.next()) {
                    Team team = new Team();
                    team.setId(rsTeams.getString("id"));
                    team.setName(rsTeams.getString("name"));
                    teams.add(team);
                }
                user.setTeams(teams);

                result = Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<Project> getProjectsByUserId(String userId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.* FROM projects p JOIN user_projects up ON p.id = up.projectId WHERE up.userId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getString("id"));
                    project.setName(rs.getString("name"));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }



}
