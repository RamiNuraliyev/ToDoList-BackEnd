package il.ac.hit.todolistbackend.model;

import java.util.List;
/**
 *Interface for DAO 
 *contain all the action methods that should implement
 *for get data from the data base 
 */
public interface IToDoListDAO {
	/**Add task to Tasks table*/
    public void addTask(Task ob) throws ToDoListDAOException;
    /**Delete task from Tasks table*/
    public void deleteTask(int id) throws ToDoListDAOException;
    /**Return all tasks from Tasks table*/
    public List<Task> getTasks() throws ToDoListDAOException;
	/**Add user to Users table*/
    public void addUser(User ob) throws ToDoListDAOException;
	/**Delete user from Users table*/
    public void deleteUser(int id) throws ToDoListDAOException;
    /**Return all users from Users table*/
    public List<User> getUsers() throws ToDoListDAOException;
}
