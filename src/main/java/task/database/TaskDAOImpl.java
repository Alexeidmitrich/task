package task.database;

import task.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl extends DBManager implements TaskDAO {

    @Override
    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        Connection connection = getConnection();
        try(Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM task");
            while (rs.next()) {
                Task task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
                taskList.add(task);
            }
        } catch (SQLException ex) {
            System.out.println("Что-то пошло не так");
            ex.printStackTrace();
        }
        return taskList;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = null;
        try(Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from task " +
                    " WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return task;
    }
    public Task getTaskByDate(LocalDate date) {
        Task task = null;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from task " +
                    " WHERE data = ?");
            statement.setDate(1,Date.valueOf(date));
            ResultSet rs = statement.executeQuery();
            rs.next();
            task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return task;
    }
    public void getUpdateTask(String description, int id) {
        try(Connection connection = getConnection()) {
            String sql = "UPDATE task.task  SET description = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, description);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getUpdateTitle(String title, int id) {
        try(Connection connection = getConnection()) {
            String sql = "UPDATE task.task  SET title = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Task getAllTaskByDate(LocalDate date, LocalDate date1) {
        Task task = null;
        try(Connection connection = getConnection()) {
            String sql = "SELECT * FROM task.task WHERE data between ? and ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(date));
            statement.setDate(2, Date.valueOf(date1));
            ResultSet rs = statement.executeQuery();
            rs.next();
            task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return task;
    }

    @Override
    public void getUpdateDate(LocalDate date, int id) {
        try(Connection connection = getConnection()) {
            String sql = "UPDATE task.task  SET data = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteOneTask(int id) {
        try(Connection connection = getConnection()) {
            String sql = "DELETE FROM task.task where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Task task) {
        Connection connection = getConnection();
        try {
            String sql = " INSERT INTO task (id, title,description,data) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setDate(4, Date.valueOf(task.getDate()));
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
