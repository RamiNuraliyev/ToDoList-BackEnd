package il.ac.hit.todolistbackend.model;

/**
 * Class for each task in Tasks table
 */
public class Task {
	private int id = 0;
	private String name = "";
	private int userId = 0;

	/**
	 * Default constructor
	 */
	public Task() {
		this.setId(0);
		this.setName(" ");
		this.setUserId(0);
	}
	/**
	 * Constructor
	 */
	public Task(int id, String name, int userId) {
		this.setId(id);
		this.setName(name);
		this.setUserId(userId);
	}
	/**
	 * Return the task id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Return the task name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the task id
	 */
	public void setId(int id) {
		if(id > 0 )
		{
			this.id = id;
		}
		else
		{
			this.id = -1;
		}

	}
	/**
	 * Set the task name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Return the user id that this his task   
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Set the user id that this his task   
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


}
