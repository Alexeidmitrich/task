package task.database;

import task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl extends DBManager implements TaskDAO {

    @Override
    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM task.task");
            while (rs.next()){
               Task task = new Task(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4).toLocalDate());
                taskList.add(task);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Что-то пошло не так");
            ex.printStackTrace();
        }
        return taskList;
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public void save(Task task) {
        Connection connection = null;
        try {
            connection  = getConnection();
            String sql = "INSERT INTO task.task (id,title,description,data ) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setDate(4, Date.valueOf(task.getLocalDate()));
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

