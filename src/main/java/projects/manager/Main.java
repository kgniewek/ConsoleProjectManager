package projects.manager;

import projects.manager.model.Project;
import projects.manager.model.Task;
import projects.manager.model.User;
import projects.manager.model.Team;
import projects.manager.service.ProjectService;
import projects.manager.service.TaskService;
import projects.manager.service.UserService;
import projects.manager.service.TeamService;
import projects.manager.util.DatabaseHelper;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import projects.manager.AdminActions;
import projects.manager.UserActions;


public class Main {


    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witamy w aplikacji Project Manager!");
        System.out.println("Zaloguj się jako:");
        System.out.println("1. Admin");
        System.out.println("2. Użytkownik");
        System.out.print("Wybierz opcję (podaj numer): ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine();

        if (loginChoice == 1) {
            System.out.println("Logujesz się jako admin. Proszę wprowadzić hasło (admin):");
            String password = scanner.nextLine();
            if (!"admin".equals(password)) {
                System.out.println("Nieprawidłowe hasło. Wychodzenie...");
                return;
            }
            System.out.println("Zalogowano pomyślnie jako admin.");
            manageApp(scanner);
        } else if (loginChoice == 2) {
            handleUserLogin(scanner);
        } else {
            System.out.println("Nieprawidłowa opcja. Wychodzenie...");
        }

        scanner.close();
    }


    private static void handleUserLogin(Scanner scanner) {
        UserService userService = new UserService();

        System.out.println("Wpisz id użytkownika do zalogowania:");
        String userId = scanner.nextLine();
        Optional<User> user = userService.getUserById(userId);

        if (!user.isPresent()) {
            System.out.println("Nie ma użytkownika o tym id");
            return;
        }

        System.out.println("Pomyślnie zalogowano jako użytkownik o ID " + userId);
        boolean userSession = true;
        while (userSession) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Pokaż moje projekty");
            System.out.println("2. Pokaż moje zadania");
            System.out.println("3. Pokaż moje zespoły");
            System.out.println("4. Wyloguj / Wyjdź");
            System.out.print("Wybierz opcję: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    UserActions.showUserProjects(userId, userService);
                    break;
                case 2:
                    UserActions.showUserTasks(userId, new TaskService());
                    break;
                case 3:
                    UserActions.showUserTeams(userId, userService);
                    break;
                case 4:
                    userSession = false;
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }
    }


    private static void manageApp(Scanner scanner) {

        UserService userService = new UserService();
        ProjectService projectService = new ProjectService(userService);
        TaskService taskService = new TaskService();
        TeamService teamService = new TeamService();

        boolean running = true;
        while (running) {
            System.out.println("1. Zarządzaj projektami");
            System.out.println("2. Zarządzaj zadaniami");
            System.out.println("3. Zarządzaj użytkownikami");
            System.out.println("4. Zarządzaj zespołami");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję (podaj numer): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageProjects(projectService, scanner, userService, teamService);
                    break;
                case 2:
                    manageTasks(taskService, scanner);
                    break;
                case 3:
                    manageUsers(userService, scanner, projectService);
                    break;
                case 4:
                    manageTeams(teamService, scanner, projectService, userService);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }
        scanner.close();
    }
    private static void manageProjects(ProjectService projectService, Scanner scanner, UserService userService, TeamService teamService) {
        System.out.println("1. Dodaj projekt");
        System.out.println("2. Pokaż projekty");
        System.out.println("3. Zaktualizuj projekt");
        System.out.println("4. Usuń projekt");
        System.out.println("5. Przypisz projekt do użytkownika");
        System.out.println("6. Usuń projekt od użytkownika");
        System.out.println("7. Pokaż szczegóły projektu");
        System.out.print("Wybierz opcję: ");
        int projectChoice = scanner.nextInt();
        scanner.nextLine();

        switch (projectChoice) {
            case 1:
                AdminActions.addProject(scanner, projectService);
                break;
            case 2:
                AdminActions.showProjects(projectService);
                break;
            case 3:
                AdminActions.updateProject(scanner, projectService);
                break;
            case 4:
                AdminActions.deleteProject(scanner, projectService);
                break;
            case 5:
                AdminActions.assignProjectToUser(scanner, userService, projectService);
                break;
            case 6:
                AdminActions.removeProjectFromUser(scanner, userService, projectService);
                break;
            case 7:
                AdminActions.showProjectDetails(scanner, projectService, teamService, userService);
                break;
            default:
                System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
        }
    }


    private static void manageTeams(TeamService teamService, Scanner scanner, ProjectService projectService, UserService userService) {
        boolean managingTeams = true;
        while (managingTeams) {
            System.out.println("\nZarządzanie zespołami:");
            System.out.println("1. Dodaj zespół");
            System.out.println("2. Pokaż zespoły");
            System.out.println("3. Pokaż szczegóły zespołu");
            System.out.println("4. Zaktualizuj zespół");
            System.out.println("5. Usuń zespół");
            System.out.println("6. Dodaj użytkownika do zespołu");
            System.out.println("7. Usuń użytkownika z zespołu");
            System.out.println("8. Przypisz projekt do zespołu");
            System.out.println("9. Wyjście");
            System.out.print("Wybierz opcję: ");
            int teamChoice = scanner.nextInt();
            scanner.nextLine();


            switch (teamChoice) {
                case 1:
                    AdminActions.addTeam(scanner, teamService);
                    break;
                case 2:
                    AdminActions.showTeams(teamService);
                    break;
                case 3:
                    AdminActions.showTeamDetails(scanner, teamService);
                    break;
                case 4:
                    AdminActions.updateTeam(scanner, teamService);
                    break;
                case 5:
                    AdminActions.deleteTeam(scanner, teamService);
                    break;
                case 6:
                    AdminActions.addUserToTeam(scanner, teamService, userService);
                    break;
                case 7:
                    AdminActions.removeUserFromTeam(scanner, teamService, userService);
                    break;
                case 8:
                    AdminActions.assignProjectToTeam(scanner, teamService, projectService);
                    break;
                case 9:
                    managingTeams = false;
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }
    }





    private static void manageTasks(TaskService taskService, Scanner scanner) {
        System.out.println("1. Dodaj zadanie");
        System.out.println("2. Pokaż zadania");
        System.out.println("3. Zaktualizuj zadanie");
        System.out.println("4. Usuń zadanie");
        System.out.print("Wybierz opcję: ");
        int taskChoice = scanner.nextInt();
        scanner.nextLine();

        switch (taskChoice) {
            case 1:
                AdminActions.addTask(scanner, taskService);
                break;
            case 2:
                AdminActions.showTasks(taskService);
                break;
            case 3:
                AdminActions.updateTask(scanner, taskService);
                break;
            case 4:
                AdminActions.deleteTask(scanner, taskService);
                break;
            default:
                System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
        }
    }

    private static void manageUsers(UserService userService, Scanner scanner, ProjectService projectService) {
        System.out.println("1. Dodaj użytkownika");
        System.out.println("2. Pokaż użytkowników");
        System.out.println("3. Zaktualizuj użytkownika");
        System.out.println("4. Usuń użytkownika");
        System.out.println("5. Przypisz projekt do użytkownika");
        System.out.println("6. Usuń projekt od użytkownika");
        System.out.println("7. Pokaż szczegóły użytkownika");
        System.out.print("Wybierz opcję: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                AdminActions.addUser(scanner, userService);
                break;
            case 2:
                AdminActions.showUsers(userService);
                break;
            case 3:
                AdminActions.updateUser(scanner, userService);
                break;
            case 4:
                AdminActions.deleteUser(scanner, userService);
                break;
            case 5:
                AdminActions.assignProjectToUser(scanner, userService, projectService);
                break;
            case 6:
                AdminActions.removeProjectFromUser(scanner, userService, projectService);
                break;
            case 7:
                AdminActions.showUserDetails(scanner, userService);
                break;
            default:
                System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
        }
    }
}
