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

public class TeamService {

    public void addTeam(Team team) {
        String sql = "INSERT INTO teams (id, name, assignedProjectId) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getId());
            pstmt.setString(2, team.getName());
            pstmt.setString(3, team.getAssignedProject() != null ? team.getAssignedProject().getId() : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Team> getTeamById(String id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Team team = new Team();
                team.setId(rs.getString("id"));
                team.setName(rs.getString("name"));
                return Optional.of(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Team team = new Team();
                team.setId(rs.getString("id"));
                team.setName(rs.getString("name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public void updateTeam(Team team) {
        String sql = "UPDATE teams SET name = ?, assignedProjectId = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getAssignedProject() != null ? team.getAssignedProject().getId() : null);
            pstmt.setString(3, team.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTeam(String id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUserToTeam(String teamId, String userId) {
        String sqlCheckTeam = "SELECT id FROM teams WHERE id = ?";
        String sqlCheckUser = "SELECT id FROM users WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmtCheckTeam = conn.prepareStatement(sqlCheckTeam);
             PreparedStatement pstmtCheckUser = conn.prepareStatement(sqlCheckUser)) {

            pstmtCheckTeam.setString(1, teamId);
            ResultSet rsTeam = pstmtCheckTeam.executeQuery();

            pstmtCheckUser.setString(1, userId);
            ResultSet rsUser = pstmtCheckUser.executeQuery();

            if (!rsTeam.next()) {
                System.out.println("Zespół o podanym ID nie istnieje.");
                return;
            }

            if (!rsUser.next()) {
                System.out.println("Użytkownik o podanym ID nie istnieje.");
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        String sql = "INSERT INTO team_users (teamId, userId) VALUES (?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeUserFromTeam(String teamId, String userId) {
        String sqlCheckTeam = "SELECT id FROM teams WHERE id = ?";
        String sqlCheckUser = "SELECT id FROM users WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmtCheckTeam = conn.prepareStatement(sqlCheckTeam);
             PreparedStatement pstmtCheckUser = conn.prepareStatement(sqlCheckUser)) {

            pstmtCheckTeam.setString(1, teamId);
            ResultSet rsTeam = pstmtCheckTeam.executeQuery();

            pstmtCheckUser.setString(1, userId);
            ResultSet rsUser = pstmtCheckUser.executeQuery();

            if (!rsTeam.next()) {
                System.out.println("Zespół o podanym ID nie istnieje.");
                return;
            }

            if (!rsUser.next()) {
                System.out.println("Użytkownik o podanym ID nie istnieje.");
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

        String sql = "DELETE FROM team_users WHERE teamId = ? AND userId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamId);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void assignProjectToTeam(String teamId, String projectId) {
        String sqlUpdateTeam = "UPDATE teams SET assignedProjectId = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdateTeam)) {
            pstmt.setString(1, projectId);
            pstmt.setString(2, teamId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public Optional<Team> getTeamDetailsById(String id) {
        Team team = null;
        String sqlTeam = "SELECT * FROM teams WHERE id = ?";
        String sqlUsers = "SELECT u.id, u.name FROM users u JOIN team_users tu ON u.id = tu.userId WHERE tu.teamId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmtTeam = conn.prepareStatement(sqlTeam);
             PreparedStatement pstmtUsers = conn.prepareStatement(sqlUsers)) {

            pstmtTeam.setString(1, id);
            ResultSet rsTeam = pstmtTeam.executeQuery();

            if (rsTeam.next()) {
                team = new Team();
                team.setId(rsTeam.getString("id"));
                team.setName(rsTeam.getString("name"));

                pstmtUsers.setString(1, id);
                ResultSet rsUsers = pstmtUsers.executeQuery();

                List<User> members = new ArrayList<>();
                while (rsUsers.next()) {
                    User user = new User();
                    user.setId(rsUsers.getString("id"));
                    user.setName(rsUsers.getString("name"));
                    members.add(user);
                }

                team.setMembers(members);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(team);
    }
}
