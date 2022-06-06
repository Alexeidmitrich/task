package task;

import task.database.TaskDAO;
import task.database.TaskDAOImpl;

import java.time.LocalDate;

public class TaskRefactoring {

    TaskDAO taskDAO = new TaskDAOImpl();

    public void addTask(String title, String description, LocalDate localDate){
        Task task = new Task(title, description, localDate);
        taskDAO.save(task);
    }
}