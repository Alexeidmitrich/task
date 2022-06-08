package task.database;

import task.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDAO {
    List<Task> getAllTask();
    Task getTaskById(int id);
    Task getTaskByDate(LocalDate date);
    void getUpdateTask(String description, int id);
    Task getAllTaskByDate(LocalDate date, LocalDate date1);
    void getUpdateTitle(String title, int id);
    void save(Task task);
}
