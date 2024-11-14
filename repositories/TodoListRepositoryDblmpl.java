package repositories;

import config.Database;
import entities.TodoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryDblmpl implements TodoListRepository{
    private final Database database;

    public TodoListRepositoryDblmpl(final Database database){
        this.database = database;
    }

    @Override
    public TodoList[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM todos";
        List<TodoList> todoLists = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TodoList todoList = new TodoList();
                Integer id = resultSet.getInt(1);
                String todo = resultSet.getString(2);
                todoList.setTodo(todo);
                todoList.setId(id);
                todoLists.add(todoList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return todoLists.toArray(TodoList[]::new);
    }

    @Override
    public void add(TodoList todoList) {
        Connection connection = database.getConnection();
        String sqlStarment = "INSERT INTO todos(todo)VALUES(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStarment);
            preparedStatement.setString(1, todoList.getTodo());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Inser succes!!");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    @Override
    public Boolean edit(TodoList todoList) {
        return null;
    }
}
