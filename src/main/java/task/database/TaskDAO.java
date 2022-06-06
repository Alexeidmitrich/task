package task.database;

import task.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTask();
    Task getTaskById(int id);
    void save(Task task);
}
