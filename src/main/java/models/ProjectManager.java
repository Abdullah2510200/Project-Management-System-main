package models;
import jdk.internal.icu.text.UnicodeSet;

import java.util.List;
import java.util.Map;

public class ProjectManager extends Employee {
    // Key: Project Name, Value: Completion Percentage
    private Map<String, Float> projectCompletion = Map.of();
    private static final String EMPLOYEE_FILE = "ProjectManger.txt";

    // Constructor
    public ProjectManager(String name, int age, String id, String phoneNumber ) {
        super(name, age, id, phoneNumber);
        this.projectCompletion = projectCompletion;
    }

    // View Project Completion
    public String viewProjectCompletion() {
        if (projectCompletion.isEmpty()) {
            return "No projects available.";
        } else {
            return "Project Completion Status: " + projectCompletion.toString();
        }
    }

    // Update Project Completion
    public String updateProjectCompletion(String projectName, float percentage) {
        projectCompletion.put(projectName, percentage);
        if (percentage == 100) {
            return "The project '" + projectName + "' has been completed!";
        }
        return "Project '" + projectName + "' updated to " + percentage + "% completion.";
    }

    // Report Employee
    public String reportEmployee(String employeeName, String reportDetails) {
        return "Report on " + employeeName + ": " + reportDetails;
    }

    // Getter for Project Completion
    public Map<String, Float> getProjectCompletion() {
        return projectCompletion;
    }}


