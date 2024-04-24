package projects.manager.model;

public class Task {
    private String id;
    private String projectId;
    private String name;
    private String assignedUserId;


    public Task() {}

    public Task(String id, String projectId, String name, String assignedUserId) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.assignedUserId = assignedUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
