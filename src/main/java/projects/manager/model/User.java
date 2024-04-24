package projects.manager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Project> assignedProjects = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();


    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    public User() {}

    public User(String id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }


    public void addProject(Project project) {
        this.assignedProjects.add(project);
    }
    public void removeProject(Project project) {
        this.assignedProjects.remove(project);
    }

}
