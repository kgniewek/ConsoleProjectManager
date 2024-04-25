# Project Management Console Application

### Project Overview

The Project Management Console Application is a Java-based tool designed to simplify the management of projects, tasks, users, and teams within an organization. Built to run in a console environment, this application provides a straightforward interface for administrative and user-specific interactions, making it suitable for educational purposes or as a base for more complex project management solutions.

### Technologies Used

- **Java**: The application is written entirely in Java, emphasizing the language's object-oriented features for managing entities like projects and tasks.
- **SQLite**: Uses SQLite for database management, allowing for lightweight data handling and persistence without needing a server-based solution.
- **Gradle**: Utilizes Gradle for efficient project management and builds automation, enhancing the project's modularity and scalability.
- **Launch4j**: Enables packaging the Java application into a Windows executable with configuration for JVM parameters and application settings. This facilitates easy distribution and deployment of the console application on Windows systems, ensuring that end-users can start the application without needing to manually configure Java settings.

### Features

The application is divided into various functionalities, each designed to address different aspects of project management:

- **Project Management**: Admins can create, view, update, and delete projects. Each project can be associated with multiple tasks, users, and teams.
- **Task Management**: Tasks can be added to projects, with details such as task assignments to users. Admins can manage these tasks through creation, viewing, updating, and deletion.
- **User Management**: User profiles can be managed where admins have the ability to add, update, and remove users, as well as assign or unassign projects to them.
- **Team Management**: Similar to user management, teams can be created, modified, or deleted. Projects can be assigned to teams, fostering collaboration among group members.
- **Database Interaction**: Utilizes a robust database helper to interact with SQLite, ensuring all data is consistently stored and retrieved, reflecting changes in real-time.

### Login
- **Admin**:
  - Login with a password (`admin`).
  - Manage projects, tasks, users, and teams.
- **User**:
  - Login using a user ID.
  - View assigned projects, tasks, and teams.

### Project Management (Admin)
- Create, view, update, and delete projects.
- Assign and unassign projects to users.
- View project details including tasks, assigned users, and teams.

### Task Management (Admin)
- Add, view, update, and delete tasks.
- Assign tasks to users.

### User Management (Admin)
- Add, view, update, and remove users.
- Assign and unassign projects to users.
- View user details.

### Team Management (Admin)
- Create, view, update, and delete teams.
- Add and remove users from teams.
- Assign projects to teams.

### User Actions
- **View My Projects**: Displays all projects assigned to the user.
- **View My Tasks**: Displays tasks assigned to the user.
- **View My Teams**: Displays teams the user belongs to.

## Unit Testing

To ensure code quality, the application is equipped with unit tests that cover key parts of the business logic. These tests are implemented using JUnit and Mockito frameworks.

### Tests for User Actions
- Displaying projects, tasks, and teams assigned to a user.
- Mocking service responses and verifying interactions.

### Tests for Admin Actions
- Ensuring correct display of projects, tasks, users, and teams by admin.
- Interaction with services using mocked objects.

These tests verify the functionality of key features and are essential for maintaining high code quality.


### Usage

To run the application, users need to navigate through a menu-driven interface in the console. Admins and users log in with different credentials and access levels, allowing for control over what each can see or modify. Tasks, projects, users, and teams are managed through textual commands and guided inputs.

### Conclusion

This Java console application is an excellent starting point for understanding how to implement basic CRUD operations and manage relationships between different data entities in a software application. While primarily educational, it provides a solid foundation for expanding into a more comprehensive project management tool with GUI elements or web interfaces.

### Project Structure

The project is organized into several packages, each serving distinct roles:

- **model**: Contains classes that define the data structures for Projects, Tasks, Teams, and Users.
- **service**: Includes services that handle business logic and database operations for each of the entities.
- **util**: Provides utility classes, such as `DatabaseHelper`, which handles database connection and initialization.
- **main**: Contains the `Main.java`, which drives the application and handles user input and navigation.

Files:
- 📁 **root**
  - `build.gradle.kts`
  - `gradlew`
  - `gradlew.bat`
  - `settings.gradle.kts`
  - 📁 **gradle**
    - 📁 **wrapper**
      - `gradle-wrapper.jar`
      - `gradle-wrapper.properties`
  - 📁 **src**
    - 📁 **main**
      - 📁 **java**
        - 📁 **projects**
          - 📁 **manager**
            - `AdminActions.java`
            - `Main.java`
            - `UserActions.java`
            - 📁 **model**
              - `Project.java`
              - `Task.java`
              - `Team.java`
              - `User.java`
            - 📁 **service**
              - `ProjectService.java`
              - `TaskService.java`
              - `TeamService.java`
              - `UserService.java`
            - 📁 **util**
              - `DatabaseHelper.java`
    - 📁 **test**
      - 📁 **java**
        - 📁 **projects**
          - 📁 **manager**
            - `AdminActionsTest.java`
            - `UserActionsTest.java`
