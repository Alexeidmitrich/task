package task;

import task.database.*;
import java.time.LocalDate;
import java.util.List;

public class TaskRefactoring {
    private DBManager dbManager = new DBManager();
    private  TaskDAO taskDAO = new TaskDAOImpl();

    public void addTask(String title, String description, LocalDate date){
        Task task = new Task(title, description, date);
        taskDAO.save(task);
    }
    public void printAllTask(){
        List<Task> tasks = new TaskDAOImpl().getAllTask();
        for (int i = 0; i < tasks.size(); i++) {
         Task task = tasks.get(i);
         task.printInformation();
        }
    }
    public void printTaskById(int id){
        TaskDAOImpl taskDAOImpl = new TaskDAOImpl();
        Task t = taskDAOImpl.getTaskById(id);
        t.printInformation();
    }
    public void printTaskByDate(LocalDate date){
        TaskDAOImpl taskDAOImpl = new TaskDAOImpl();
        Task t = taskDAOImpl.getTaskByDate(date);
        t.printInformation();
    }
    public void updateTaskInfo(String description, int id){
        TaskDAOImpl taskDAOImpl = new TaskDAOImpl();
        Task t = taskDAOImpl.getUpdateTask(description,id);
        t.printInformation();
    }

}