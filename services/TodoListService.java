package services;
import entities.TodoList;
public interface TodoListService {
    TodoList[] getTodoList();
    void AddTodolist(String todo);
    Boolean removeTodoList(Integer number);
    Boolean editTodoList(Integer number, String todo);
}