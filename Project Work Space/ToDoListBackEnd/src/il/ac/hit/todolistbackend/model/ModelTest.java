package il.ac.hit.todolistbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

public class ModelTest {

	/** Tests for each method in the DAO
	 * All the tests clear the data base!!!
	 */
	HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
	/**
	 *Clear the data base tables
	 */
	public void clearDataBase()
	{
		org.hibernate.classic.Session session = dao.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery("delete from Task").executeUpdate(); //Delete tasks table
			session.createQuery("delete from User").executeUpdate(); //Delete users table
			tx.commit();
		}
		catch (Exception e) { 
			if (tx!=null)
			{
				tx.rollback();
			}
		}
		finally {
			session.close();
		}
	}
	/**
	 * test the add methods
	 */
	@Test
	public void addTest() {

		clearDataBase();
		try {
			
			dao.addTask(new Task(0,"gym",1));
			dao.addTask(new Task(0,"Home Work",1));
			dao.addTask(new Task(0,"test",1));
			dao.addUser(new User(1, "rami"));

			List<Task> tasks = dao.getTasks();
			List<User> users = dao.getUsers();
			assertEquals(tasks.size(),3);
			assertEquals(users.size(),1);

			assertEquals(tasks.get(0).getName(),"gym");
			assertEquals(tasks.get(1).getName(),"Home Work");
			assertEquals(tasks.get(2).getName(),"test");
			assertEquals(users.get(0).getName(),"rami");


		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		clearDataBase();
	}

	/**
	 * Test the get lists methods
	 */
	@Test
	public void getListTest()
	{
		clearDataBase();

		//Add content
		try {
			dao.addTask(new Task(101,"gym",222));
			dao.addTask(new Task(102,"Home Work",222));
			dao.addTask(new Task(103,"test",222));
			dao.addUser(new User(222, "rami"));
		} catch (ToDoListDAOException e1) {
			e1.printStackTrace();
		}

		//Get tasks list
		List<Task> tasks = null;
		try {
			tasks = dao.getTasks();
		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		assertEquals(tasks.get(0).getName(),"gym");
		assertEquals(tasks.get(1).getName(),"Home Work");
		assertEquals(tasks.get(2).getName(),"test");

		//Get users list
		List<User> users = null;
		try {
			users = dao.getUsers();
		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		assertEquals(users.get(0).getName(),"rami");
		
		clearDataBase();
	}

	/**
	 * Test the delete methods
	 */
	@Test
	public void deleteTest() {
		
		clearDataBase();
		try {
			//Add content to delete
			try {
				dao.addTask(new Task(101,"gym",222));
				dao.addTask(new Task(102,"Home Work",222));
				dao.addTask(new Task(103,"test",222));
				dao.addUser(new User(222, "rami"));
			} catch (ToDoListDAOException e1) {
				e1.printStackTrace();
			}
			
			//Get data as list
			List<Task> tasks = dao.getTasks();
			List<User> users = dao.getUsers();

			assertEquals(tasks.size(),3);
			assertEquals(users.size(),1);

			//Delete
			dao.deleteTask(tasks.get(0).getId());
			dao.deleteTask(tasks.get(1).getId());
			dao.deleteUser(users.get(0).getId());
			users = dao.getUsers();
			tasks = dao.getTasks();
			assertEquals(tasks.size(),1);
			assertEquals(users.size(),0);
			

		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		clearDataBase();
	}

}
