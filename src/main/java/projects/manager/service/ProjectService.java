package projects.manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import projects.manager.model.Project;
import projects.manager.model.Task;
import projects.manager.model.Team;
import projects.manager.model.User;
import projects.manager.util.DatabaseHelper;

public class ProjectService {

    private final UserService userService;

    public ProjectService(UserService userService) {
        this.userService = userService;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()) {
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getString("id"));
                project.setName(rs.getString("name"));
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }

    public void addProject(Project project) {
        String sql = "INSERT INTO projects (id, name) VALUES (?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, project.getId());
            pstmt.setString(2, project.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProject(Project project) {
        String sql = "UPDATE projects SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProject(String id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Optional<Project> getProjectById(String id) {
        return getAllProjects().stream()
                .filter(project -> project.getId().equals(id))
                .findFirst();
    }

    public Optional<Project> getProjectDetailsById(String id) {
        Optional<Project> projectOpt = getProjectById(id);
        if (!projectOpt.isPresent()) {
            return Optional.empty();
        }

        Project project = projectOpt.get();

        String sqlTeams = "SELECT teams.* FROM teams WHERE assignedProjectId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmtTeams = conn.prepareStatement(sqlTeams)) {
            pstmtTeams.setString(1, id);
            try (ResultSet rsTeams = pstmtTeams.executeQuery()) {
                List<Team> teams = new ArrayList<>();
                while (rsTeams.next()) {
                    Team team = new Team();
                    team.setId(rsTeams.getString("id"));
                    team.setName(rsTeams.getString("name"));
                    teams.add(team);
                }
                project.setAssignedTeams(teams);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.of(project);
    }
    public List<User> getUsersByProjectId(String projectId) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.* FROM users u JOIN user_projects up ON u.id = up.userId WHERE up.projectId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

}
