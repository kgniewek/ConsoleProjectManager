package projects.manager.service;

import projects.manager.model.Task;
import projects.manager.util.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getString("id"));
                task.setProjectId(rs.getString("projectId"));
                task.setName(rs.getString("name"));
                task.setAssignedUserId(rs.getString("assignedUserId"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (id, projectId, name, assignedUserId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getId());
            pstmt.setString(2, task.getProjectId());
            pstmt.setString(3, task.getName());
            pstmt.setString(4, task.getAssignedUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET projectId = ?, name = ?, assignedUserId = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getProjectId());
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getAssignedUserId());
            pstmt.setString(4, task.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> getTasksByUserId(String userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE assignedUserId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getString("id"));
                    task.setProjectId(rs.getString("projectId"));
                    task.setName(rs.getString("name"));
                    task.setAssignedUserId(rs.getString("assignedUserId"));
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

}
