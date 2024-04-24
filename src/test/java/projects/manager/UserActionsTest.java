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
import projects.manager.service.TaskService;
import projects.manager.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UserActionsTest {

    @Mock
    private UserService userService;
    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showUserProjects() {
        String userId = "1";
        Project project1 = new Project("proj1", "Project 1");
        Project project2 = new Project("proj2", "Project 2");
        List<Project> projects = Arrays.asList(project1, project2);
        when(userService.getProjectsByUserId(userId)).thenReturn(projects);

        UserActions.showUserProjects(userId, userService);

        verify(userService).getProjectsByUserId(userId);
    }

    @Test
    void showUserTasks() {
        // Przygotowanie
        String userId = "1";
        Task task1 = new Task("task1", "proj1", "Task 1", "1");
        Task task2 = new Task("task2", "proj2", "Task 2", "1");
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskService.getTasksByUserId(userId)).thenReturn(tasks);

        UserActions.showUserTasks(userId, taskService);

        verify(taskService).getTasksByUserId(userId);
    }

    @Test
    void showUserTeams() {
        String userId = "1";
        User user = new User("1", "TestUser");
        Team team1 = new Team("team1", "Team 1");
        Team team2 = new Team("team2", "Team 2");
        user.setTeams(Arrays.asList(team1, team2));
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        UserActions.showUserTeams(userId, userService);

        verify(userService).getUserById(userId);
    }
}
