package task.database;

import task.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDAO {
    List<Task> getAllTask();
    Task getTaskById(int id);
    Task getTaskByDate(LocalDate date);
    void save(Task task);
}
