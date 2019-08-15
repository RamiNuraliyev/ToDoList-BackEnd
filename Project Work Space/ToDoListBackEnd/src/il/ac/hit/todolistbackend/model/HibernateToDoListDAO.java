package il.ac.hit.todolistbackend.model;


import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.jupiter.api.Test;
/**
 * Data access object,
 * connecting to the data base by Hibernate   
 * @author rami
 *
 */

public class HibernateToDoListDAO implements IToDoListDAO{

	//creating factory for getting sessions
	private static  SessionFactory factory;
	private static HibernateToDoListDAO instance;
	
	/**
	 * Constructor 
	 * Creates only one factory variable for getting sessions
	 */
	private HibernateToDoListDAO() {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	/**
	 *Singleton
	 * return single instance of DAO
	 * @return
	 */
	public static HibernateToDoListDAO getInstance()
	{
		if(instance == null)
		{
			instance = new HibernateToDoListDAO();
		}
		return instance;
	}

	/**
	 * Return factory variable
	 * @return
	 */
	public SessionFactory getFactory()
	{
		return factory;
	}
	
	/**
	 * Add task to Tasks table
	 */
	//creating a new session for each action
	@Override
	public void addTask(Task ob) throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(ob); //Save object to database
			tx.commit();
		}
		catch (SessionException e) {
			if (tx!=null)
			{
				tx.rollback();
				throw new ToDoListDAOException("Can't save Task",e);
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction",e);
			}
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}
	}
	/**
	 * Delete task to Tasks table
	 */
	@Override
	public void deleteTask(int id) throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction(); 
			Task tmp = (Task) session.load(Task.class,id); //Load if task exist
			if(tmp == null) {
				throw new ToDoListDAOException("Can't find Task");
			}
			session.delete(tmp); //Delete on data base
			tx.commit();
		}
		catch (SessionException e) { 
			if (tx!=null)
			{
				tx.rollback();
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction",e);
			}
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}

	}
	/**
	 * Return list of all tasks from Tasks table
	 */
	@Override
	public List<Task> getTasks() throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		List<Task> Tasks;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Tasks = session.createQuery("from Task").list(); //Get all Tasks as list from tasks table
			tx.commit();
		}
		catch (SessionException e) { 
			if (tx!=null)
			{
				tx.rollback();
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction");
			}
			throw new ToDoListDAOException("Can't get Tasks list",e);
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}
		return Tasks;
	}
	/**
	 * Add user to Users table
	 */
	@Override
	public void addUser(User ob) throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(ob); //Save object to database
			tx.commit();
		}
		catch (SessionException e) { 
			if (tx!=null)
			{
				tx.rollback();
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction",e);
			}
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}

	}
	/**
	 * Delete user from Users table
	 */
	@Override
	public void deleteUser(int id) throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User tmp = (User) session.load(User.class,id); //Load if user exist 
			if(tmp == null) {
				throw new ToDoListDAOException("Can't find User");
			}
			session.delete(tmp);//Delete on data base
			tx.commit();
		}
		catch (SessionException e) {
			if (tx!=null)
			{
				tx.rollback();
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction",e);
			}
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}
	}
	/**
	 * Return users list from Users table
	 */
	@Override
	public List<User> getUsers() throws ToDoListDAOException {
		org.hibernate.classic.Session session = factory.openSession();
		List<User> Users;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Users = session.createQuery("from User").list(); //Get all Users as list from users table
			tx.commit();
		}
		catch (SessionException e) { 
			if (tx!=null)
			{
				tx.rollback();
			}
			else
			{
				throw new ToDoListDAOException("Null Transaction");
			}
			throw new ToDoListDAOException("Can't get Users list",e);
		}
		finally {
			try
			{
				session.close();
			}
			catch (SessionException e) {
			}
		}
		return Users;
	}

	/**
	 *Test method to see all methods output on the console
	 */
	public static void main(String[] args) 
	{	
		//Uncomment for test and print data

//		HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
//
//
//				// Add test 
//		try {
//			dao.addTask(new Task(101,"gym",1));
//			dao.addTask(new Task(102,"kkk",1));
//			dao.addTask(new Task(103,"gffym",1));
//			dao.addUser(new User(222, "xxx"));
//		} catch (ToDoListDAOException e) {
//			e.printStackTrace();
//		}

				/** Delete test 
		try {
			dao.deleteTask(1);
			dao.deleteTask(2);
			dao.deleteTask(3);
			dao.deleteUser(2);
		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
				 */
				/** Get list test 
		List<Task> Tasks = null;
		try {
			Tasks = dao.getTasks();
		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		System.out.println("There are " + Tasks.size());
		Iterator<Task> i = Tasks.iterator();
		while(i.hasNext()) 
		{
			System.out.println(i.next().getName());
		}

		List<User> users = null;
		try {
			users = dao.getUsers();
		} catch (ToDoListDAOException e) {
			e.printStackTrace();
		}
		System.out.println("There are " + users.size() + " users(s)");
		Iterator<User> j = users.iterator();
		while(j.hasNext()) 
		{
			System.out.println(j.next().getName() +"mainP");
		}*/


	}
}
