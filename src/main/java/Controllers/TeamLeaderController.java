package Controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import models.Employee;
import models.Task;
import models.TeamLeader;

import java.util.ArrayList;
import java.util.List;

public class TeamLeaderController {

    @FXML private TableView<Employee> teamTable;
    @FXML private TableColumn<Employee, String> colName;
    @FXML private TableColumn<Employee, String> colAge;
    @FXML private TableColumn<Employee, String> colId;
    @FXML private TableColumn<Employee, String> colHours;

    @FXML private TextField employeeIdField;
    @FXML private TextField taskField;

    @FXML private TableView<Task> completedTasksTable;
    @FXML private TableColumn<Task, String> colTask;

    private TeamLeader teamLeader;
    private ObservableList<Employee> teamList;
    private ObservableList<Task> completedTasksList;

    @FXML
    public void initialize() {
        // Mock data for testing
        List<Employee> team = new ArrayList<>();
        team.add(new Employee("Alice", 28, "E101", "password1", 40));
        team.add(new Employee("Bob", 32, "E102", "password2", 35));

        teamLeader = new TeamLeader("Team Leader", 35, "TL001", "leaderpass", 50, team);

        // Set up team TableView
        teamList = FXCollections.observableArrayList(teamLeader.manageEmployees());
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colAge.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAge())));
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colHours.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getHoursWorked())));
        teamTable.setItems(teamList);

        // Initialize Completed Tasks TableView
        completedTasksList = FXCollections.observableArrayList();
        colTask.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        completedTasksTable.setItems(completedTasksList);
    }

    @FXML
    private void assignTask() {
        String employeeId = employeeIdField.getText();
        String taskDescription = taskField.getText();

        if (employeeId.isEmpty() || taskDescription.isEmpty()) {
            showAlert("Error", "Please enter Employee ID and Task Description.");
            return;
        }

        for (Employee emp : teamLeader.manageEmployees()) {
            if (emp.getId().equals(employeeId)) {
                Task task = new Task(taskDescription);
                teamLeader.assignTask(emp, task);
                showAlert("Success", "Task assigned to " + emp.getName());
                completedTasksList.add(task);
                employeeIdField.clear();
                taskField.clear();
                return;
            }
        }
        showAlert("Error", "Employee not found.");
    }

    @FXML
    private void acceptVacation() {
        showAlert("Info", "Vacation request accepted!");
    }

    @FXML
    private void rejectVacation() {
        showAlert("Info", "Vacation request rejected!");
    }

    @FXML
    private void addPenalty() {
        showAlert("Info", "Penalty added to employee!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
