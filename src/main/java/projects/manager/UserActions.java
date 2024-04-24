package projects.manager;

import projects.manager.model.Project;
import projects.manager.model.Task;
import projects.manager.model.User;
import projects.manager.model.Team;
import projects.manager.service.TaskService;
import projects.manager.service.UserService;
import java.util.List;

public class UserActions {


    public static void showUserProjects(String userId, UserService userService) {
        List<Project> projects = userService.getProjectsByUserId(userId);
        System.out.println("Projekty użytkownika:");
        for (Project project : projects) {
            System.out.println("ID Projektu: " + project.getId() + ", Nazwa: " + project.getName());
        }
    }

    public static void showUserTasks(String userId, TaskService taskService) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        System.out.println("Zadania użytkownika:");
        for (Task task : tasks) {
            System.out.println("ID Zadania: " + task.getId() + ", Projekt: " + task.getProjectId() + ", Nazwa: " + task.getName());
        }
    }

    public static void showUserTeams(String userId, UserService userService) {
        User user = userService.getUserById(userId).orElse(null);
        if (user != null) {
            System.out.println("Zespoły, do których należy użytkownik:");
            for (Team team : user.getTeams()) {
                System.out.println("ID Zespołu: " + team.getId() + ", Nazwa: " + team.getName());
            }
        }
    }
}
