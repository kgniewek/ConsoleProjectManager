package projects.manager.model;

import java.util.List;

public class Team {
    private String id;
    private String name;
    private List<User> members;
    private Project assignedProject;

    public Team() {
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
        // Inicjalizuj inne pola, jeśli jest to konieczne
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
}
