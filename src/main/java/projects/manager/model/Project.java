package projects.manager.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String id;
    private String name;
    private List<Task> tasks;
    private List<User> assignedUsers;
    private List<Team> assignedTeams;

    public Project() {
        this.tasks = new ArrayList<>();
        this.assignedUsers = new ArrayList<>();
        this.assignedTeams = new ArrayList<>();
    }

    public Project(String id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = (tasks != null) ? tasks : new ArrayList<>();
        this.assignedUsers = new ArrayList<>();
        this.assignedTeams = new ArrayList<>();
    }

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
        this.assignedUsers = new ArrayList<>();
        this.assignedTeams = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public List<Team> getAssignedTeams() {
        return assignedTeams;
    }

    public void setAssignedTeams(List<Team> assignedTeams) {
        this.assignedTeams = assignedTeams;
    }
}
