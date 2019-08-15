package il.ac.hit.todolistbackend.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.Application;

import il.ac.hit.todolistbackend.model.HibernateToDoListDAO;
import il.ac.hit.todolistbackend.model.Task;
import il.ac.hit.todolistbackend.model.ToDoListDAOException;
import il.ac.hit.todolistbackend.model.User;
/**
 * Controller for each request from the users
 * @author rami
 *
 */
public class UserController {
	/**
	 * Constructor
	 */
	public UserController() {
	}
	/**
	 * Login or register action,
	 * if the user in the Users table it move to his TO-DO list 
	 * else its add the user to the table and move to the list.
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ToDoListDAOException, IOException
	{
		int id;
		List<User> users = HibernateToDoListDAO.getInstance().getUsers();
		if(request.getParameter("userId") != null)
		{
			try
			{
				id  = (int) Integer.parseInt(request.getParameter("userId"));
			}
			catch (NumberFormatException  e) {
				response.sendRedirect("http://localhost:8080/ToDoListBackEnd/errorPage.jsp");
				throw new ToDoListDAOException("Can't convert id to integer");

			}
			boolean newUser = true;
			for(User u : users)
			{
				if(u.getId() == id)
				{
					newUser  = false;
					break;
				}
			}
			if(newUser)
			{
				String name =  (String)request.getParameter("userName");
				HibernateToDoListDAO.getInstance().addUser(new User(id,name));
			}
			request.getSession().setAttribute("currentUserId",id);	
		}
		else
		{
			request.setAttribute("changeUser", "true");
			//RequestDispatcher  dispatcher = request.getRequestDispatcher("/login.jsp");
		}
	}
	/**
	 * Make attribute of all the tasks, from Tasks table,
	 * for the view.
	 * @param request
	 * @param response
	 * @throws ToDoListDAOException
	 * @throws IOException
	 */
	public void tasksList(HttpServletRequest request, HttpServletResponse response) throws ToDoListDAOException, IOException
	{
		List<Task> taskList = HibernateToDoListDAO.getInstance().getTasks();
		request.setAttribute("taskList", taskList);
	}
	/**
	 * Add new task to Tasks table
	 * @param request
	 * @param response
	 * @throws ToDoListDAOException
	 * @throws IOException
	 */
	public void addTask(HttpServletRequest request, HttpServletResponse response) throws ToDoListDAOException, IOException
	{
		if(!(request.getParameter("taskName") == null|| request.getParameter("taskId") == null))
		{
			int userId;
			try
			{
				userId =(int)(request.getSession().getAttribute("currentUserId"));
			}
			catch (NumberFormatException e) {
				response.sendRedirect("http://localhost:8080/ToDoListBackEnd/taskError.jsp");
				throw new ToDoListDAOException("Can't convert user id to integer");	
			}

			String name =  (String) request.getParameter("taskName");
			int  id;
			try
			{
				id =(int) Integer.parseInt(request.getParameter("taskId"));
			}
			catch (NumberFormatException e) {
				response.sendRedirect("http://localhost:8080/ToDoListBackEnd/taskError.jsp");
				throw new ToDoListDAOException("Can't convert id to integer");	
			}
			if(id < 0)
			{
				response.sendRedirect("http://localhost:8080/ToDoListBackEnd/taskError.jsp");
				throw new ToDoListDAOException("Task id should be greater then 0");	
			}
			HibernateToDoListDAO.getInstance().addTask(new Task(id,name,userId));
			request.getSession().setAttribute("taskId", id);
			request.getSession().setAttribute("taskName", name);
		}

	}
	/**
	 * Delete task from the Tasks table by id
	 * @param request
	 * @param response
	 * @throws ToDoListDAOException
	 * @throws IOException
	 */
	public void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ToDoListDAOException, IOException
	{
		if(request.getParameter("taskId") == null)
		{

		}
		else
		{
			int  id;
			try
			{
				id =(int) Integer.parseInt(request.getParameter("taskId"));
			}
			catch (NumberFormatException e) {
				response.sendRedirect("http://localhost:8080/ToDoListBackEnd/taskError.jsp");
				throw new ToDoListDAOException("Can't convert id to integer");	
			}
			request.getSession().setAttribute("taskId", id);
			HibernateToDoListDAO.getInstance().deleteTask(id);

		}	
	}

}
