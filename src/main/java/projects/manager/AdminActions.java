package projects.manager;
import projects.manager.service.ProjectService;
import projects.manager.service.TaskService;
import projects.manager.service.UserService;
import projects.manager.service.TeamService;
import projects.manager.model.Project;
import projects.manager.model.Task;
import projects.manager.model.User;
import projects.manager.model.Team;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;
public class AdminActions {





    public static void addProject(Scanner scanner, ProjectService projectService) {
        System.out.print("Podaj ID projektu: ");
        String id = scanner.nextLine();
        System.out.print("Podaj nazwę projektu: ");
        String name = scanner.nextLine();

        Project project = new Project();
        project.setId(id);
        project.setName(name);
        projectService.addProject(project);
        System.out.println("Projekt dodany pomyślnie.");
    }

    public static void showProjects(ProjectService projectService) {
        List<Project> projects = projectService.getAllProjects();
        for (Project project : projects) {
            System.out.println("ID: " + project.getId() + ", Nazwa: " + project.getName());
        }
    }

    public static void updateProject(Scanner scanner, ProjectService projectService) {
        System.out.print("Podaj ID projektu do zaktualizowania: ");
        String id = scanner.nextLine();

        System.out.print("Podaj nową nazwę projektu: ");
        String newName = scanner.nextLine();

        Project project = new Project();
        project.setId(id);
        project.setName(newName);
        projectService.updateProject(project);

        System.out.println("Projekt został zaktualizowany.");
    }


    public static void deleteProject(Scanner scanner, ProjectService projectService) {
        System.out.print("Podaj ID projektu do usunięcia: ");
        String id = scanner.nextLine();
        projectService.deleteProject(id);
        System.out.println("Projekt usunięty pomyślnie.");
    }
    public static void showProjectDetails(Scanner scanner, ProjectService projectService, TeamService teamService, UserService userService) {
        System.out.print("Podaj ID projektu: ");
        String id = scanner.nextLine();
        try {
            Optional<Project> projectOpt = projectService.getProjectDetailsById(id);
            if (projectOpt.isPresent()) {
                Project project = projectOpt.get();
                System.out.println("ID Projektu: " + project.getId());
                System.out.println("Nazwa Projektu: " + project.getName());

                System.out.println("Zadania:");
                if (project.getTasks() != null) {
                    for (Task task : project.getTasks()) {
                        System.out.println("ID Zadania: " + task.getId() + ", Nazwa: " + task.getName() + ", Przypisany użytkownik: " + task.getAssignedUserId());
                    }
                }

                List<User> assignedUsers = projectService.getUsersByProjectId(project.getId());
                System.out.println("Przypisani użytkownicy:");
                for (User user : assignedUsers) {
                    System.out.println("ID Użytkownika: " + user.getId() + ", Nazwa: " + user.getName());
                }

                System.out.println("Przypisane zespoły:");
                if (project.getAssignedTeams() != null) {
                    for (Team team : project.getAssignedTeams()) {
                        System.out.println("ID Zespołu: " + team.getId() + ", Nazwa: " + team.getName());
                    }
                }
            } else {
                System.out.println("Projekt o podanym ID nie istnieje.");
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas wyświetlania szczegółów projektu: " + e.getMessage());
        }
    }



    public static void addTask(Scanner scanner, TaskService taskService) {
        System.out.print("Podaj ID zadania: ");
        String id = scanner.nextLine();
        System.out.print("Podaj ID projektu: ");
        String projectId = scanner.nextLine();
        System.out.print("Podaj nazwę zadania: ");
        String name = scanner.nextLine();
        System.out.print("Podaj ID przypisanego użytkownika: ");
        String userId = scanner.nextLine();

        Task task = new Task();
        task.setId(id);
        task.setProjectId(projectId);
        task.setName(name);
        task.setAssignedUserId(userId);

        taskService.addTask(task);
        System.out.println("Zadanie dodane pomyślnie.");
    }



    public static void showTasks(TaskService taskService) {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + ", Projekt: " + task.getProjectId() + ", Nazwa: " + task.getName() + ", Przypisany użytkownik: " + task.getAssignedUserId());
        }
    }

    public static void updateTask(Scanner scanner, TaskService taskService) {
        System.out.print("Podaj ID zadania do zaktualizowania: ");
        String id = scanner.nextLine();
        System.out.print("Podaj nową nazwę zadania: ");
        String name = scanner.nextLine();
        System.out.print("Podaj ID przypisanego użytkownika: ");
        String userId = scanner.nextLine();

        Task updatedTask = new Task();
        updatedTask.setId(id);
        updatedTask.setName(name);
        updatedTask.setAssignedUserId(userId);

        taskService.updateTask(updatedTask);
        System.out.println("Zadanie zostało zaktualizowane.");
    }



    public static void deleteTask(Scanner scanner, TaskService taskService) {
        System.out.print("Podaj ID zadania do usunięcia: ");
        String id = scanner.nextLine();
        taskService.deleteTask(id);
        System.out.println("Zadanie usunięte pomyślnie.");
    }

    public static void addUser(Scanner scanner, UserService userService) {
        System.out.print("Podaj ID użytkownika: ");
        String id = scanner.nextLine();
        System.out.print("Podaj nazwę użytkownika: ");
        String name = scanner.nextLine();

        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.addUser(user);
        System.out.println("Użytkownik dodany pomyślnie.");
    }

    public static void showUsers(UserService userService) {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Nazwa: " + user.getName());
        }
    }

    public static void updateUser(Scanner scanner, UserService userService) {
        System.out.print("Podaj ID użytkownika do zaktualizowania: ");
        String id = scanner.nextLine();
        System.out.print("Podaj nową nazwę użytkownika: ");
        String newName = scanner.nextLine();

        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            user.setName(newName);
            userService.updateUser(user);
            System.out.println("Użytkownik został zaktualizowany.");
        } else {
            System.out.println("Nie znaleziono użytkownika o podanym ID.");
        }
    }


    public static void deleteUser(Scanner scanner, UserService userService) {
        System.out.print("Podaj ID użytkownika do usunięcia: ");
        String id = scanner.nextLine();
        userService.deleteUser(id);
        System.out.println("Użytkownik usunięty pomyślnie.");
    }
    public static void assignProjectToUser(Scanner scanner, UserService userService, ProjectService projectService) {
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId).orElse(null);

        if (user == null) {
            System.out.println("Użytkownik nie istnieje.");
            return;
        }
        System.out.print("Podaj ID projektu do przypisania: ");
        String projectId = scanner.nextLine();
        Project project = projectService.getProjectById(projectId).orElse(null);
        if (project == null) {
            System.out.println("Projekt nie istnieje.");
            return;
        }
        userService.assignProjectToUser(userId, projectId);
        System.out.println("Projekt przypisany do użytkownika.");
    }


    public static void removeProjectFromUser(Scanner scanner, UserService userService, ProjectService projectService) {
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId).orElse(null);

        if (user == null) {
            System.out.println("Użytkownik nie istnieje.");
            return;
        }

        System.out.print("Podaj ID projektu do usunięcia: ");
        String projectId = scanner.nextLine();
        Project project = projectService.getProjectById(projectId).orElse(null);

        if (project == null || !user.getAssignedProjects().contains(project)) {
            System.out.println("Projekt nie istnieje lub nie jest przypisany do tego użytkownika.");
            return;
        }

        user.removeProject(project);
        System.out.println("Projekt usunięty od użytkownika.");
    }

    public static void showUserDetails(Scanner scanner, UserService userService) {
        System.out.print("Podaj ID użytkownika: ");
        String id = scanner.nextLine();
        Optional<User> userOpt = userService.getUserById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("ID Użytkownika: " + user.getId());
            System.out.println("Nazwa Użytkownika: " + user.getName());

            List<Project> assignedProjects = userService.getProjectsByUserId(user.getId());
            System.out.println("Przypisane projekty:");
            for (Project project : assignedProjects) {
                System.out.println("ID Projektu: " + project.getId() + ", Nazwa: " + project.getName());
            }

            System.out.println("Zespoły, do których należy użytkownik:");
            for (Team team : user.getTeams()) {
                System.out.println("ID Zespołu: " + team.getId() + ", Nazwa: " + team.getName());
            }
        } else {
            System.out.println("Użytkownik o podanym ID nie istnieje.");
        }
    }



    public static void addTeam(Scanner scanner, TeamService teamService) {
        System.out.print("Podaj ID zespołu: ");
        String id = scanner.nextLine();
        System.out.print("Podaj nazwę zespołu: ");
        String name = scanner.nextLine();

        Team team = new Team();
        team.setId(id);
        team.setName(name);

        teamService.addTeam(team);
        System.out.println("Zespół dodany pomyślnie.");
    }

    public static void showTeams(TeamService teamService) {
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Brak zespołów.");
        } else {
            for (Team team : teams) {
                System.out.println("ID Zespołu: " + team.getId() + ", Nazwa: " + team.getName());
            }
        }
    }
    public static void updateTeam(Scanner scanner, TeamService teamService) {
        System.out.print("Podaj ID zespołu do zaktualizowania: ");
        String id = scanner.nextLine();

        Optional<Team> teamOpt = teamService.getTeamById(id);
        if (!teamOpt.isPresent()) {
            System.out.println("Zespół o podanym ID nie istnieje.");
            return;
        }

        Team team = teamOpt.get();
        System.out.print("Podaj nową nazwę zespołu: ");
        String newName = scanner.nextLine();
        team.setName(newName);

        teamService.updateTeam(team);
        System.out.println("Zespół został zaktualizowany.");
    }

    public static void deleteTeam(Scanner scanner, TeamService teamService) {
        System.out.print("Podaj ID zespołu do usunięcia: ");
        String id = scanner.nextLine();

        if (!teamService.getTeamById(id).isPresent()) {
            System.out.println("Zespół o podanym ID nie istnieje.");
            return;
        }

        teamService.deleteTeam(id);
        System.out.println("Zespół usunięty pomyślnie.");
    }



    public static void assignProjectToTeam(Scanner scanner, TeamService teamService, ProjectService projectService) {
        System.out.print("Podaj ID zespołu: ");
        String teamId = scanner.nextLine();
        Optional<Team> teamOpt = teamService.getTeamById(teamId);

        if (!teamOpt.isPresent()) {
            System.out.println("Zespół o podanym ID nie istnieje.");
            return;
        }

        System.out.print("Podaj ID projektu do przypisania: ");
        String projectId = scanner.nextLine();
        Optional<Project> projectOpt = projectService.getProjectById(projectId);

        if (!projectOpt.isPresent()) {
            System.out.println("Projekt o podanym ID nie istnieje.");
            return;
        }

        teamService.assignProjectToTeam(teamId, projectId);
        System.out.println("Projekt przypisany do zespołu.");
    }




    public static void addUserToTeam(Scanner scanner, TeamService teamService, UserService userService) {
        System.out.print("Podaj ID zespołu: ");
        String teamId = scanner.nextLine();
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();

        if(userService.getUserById(userId).isPresent()) {
            teamService.addUserToTeam(teamId, userId);
            System.out.println("Użytkownik dodany do zespołu.");
        } else {
            System.out.println("Nie znaleziono użytkownika o podanym ID.");
        }
    }

    public static void removeUserFromTeam(Scanner scanner, TeamService teamService, UserService userService) {
        System.out.print("Podaj ID zespołu: ");
        String teamId = scanner.nextLine();
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();

        if(userService.getUserById(userId).isPresent()) {
            teamService.removeUserFromTeam(teamId, userId);
            System.out.println("Użytkownik usunięty z zespołu.");
        } else {
            System.out.println("Nie znaleziono użytkownika o podanym ID.");
        }
    }

    public static void showTeamDetails(Scanner scanner, TeamService teamService) {
        System.out.print("Podaj ID zespołu: ");
        String teamId = scanner.nextLine();

        Optional<Team> teamOpt = teamService.getTeamDetailsById(teamId);
        if (!teamOpt.isPresent()) {
            System.out.println("Zespół o podanym ID nie istnieje.");
            return;
        }

        Team team = teamOpt.get();
        System.out.println("ID Zespołu: " + team.getId());
        System.out.println("Nazwa Zespołu: " + team.getName());

        if (team.getMembers() != null) {
            System.out.println("Członkowie zespołu:");
            for (User member : team.getMembers()) {
                System.out.println("ID Użytkownika: " + member.getId() + ", Nazwa: " + member.getName());
            }
        }

        if (team.getAssignedProject() != null) {
            System.out.println("Przypisany projekt:");
            System.out.println("ID Projektu: " + team.getAssignedProject().getId() + ", Nazwa: " + team.getAssignedProject().getName());
        } else {
            System.out.println("Brak przypisanego projektu.");
        }
    }


}
