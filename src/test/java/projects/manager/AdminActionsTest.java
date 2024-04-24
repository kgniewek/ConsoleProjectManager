package projects.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import projects.manager.model.Project;
import projects.manager.model.Task;
import projects.manager.model.Team;
import projects.manager.model.User;
import projects.manager.service.ProjectService;
import projects.manager.service.TaskService;
import projects.manager.service.UserService;
import projects.manager.service.TeamService;

import java.util.Arrays;
import java.util.List;

class AdminActionsTest {

    @Mock
    private ProjectService projectService;
    @Mock
    private TaskService taskService;
    @Mock
    private UserService userService;
    @Mock
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showProjects() {
        List<Project> projects = Arrays.asList(new Project("1", "Project 1"), new Project("2", "Project 2"));
        when(projectService.getAllProjects()).thenReturn(projects);

        AdminActions.showProjects(projectService);

        verify(projectService).getAllProjects();
    }

    @Test
    void showTasks() {
        List<Task> tasks = Arrays.asList(new Task("1", "Project 1", "Task 1", "User 1"), new Task("2", "Project 2", "Task 2", "User 2"));
        when(taskService.getAllTasks()).thenReturn(tasks);

        AdminActions.showTasks(taskService);

        verify(taskService).getAllTasks();
    }

    @Test
    void showUsers() {
        List<User> users = Arrays.asList(new User("1", "User 1"), new User("2", "User 2"));
        when(userService.getAllUsers()).thenReturn(users);

        AdminActions.showUsers(userService);

        verify(userService).getAllUsers();
    }

    @Test
    void showTeams() {
        List<Team> teams = Arrays.asList(new Team("1", "Team 1"), new Team("2", "Team 2"));
        when(teamService.getAllTeams()).thenReturn(teams);

        AdminActions.showTeams(teamService);

        verify(teamService).getAllTeams();
    }
}
