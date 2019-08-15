package il.ac.hit.todolistbackend.model;
/**
 * Class for each user in Users table
 */
public class User {
	private int id;
	private String name;

	/**
	 * Default constructor 
	 */
	public User() {
		this.setId(0);
		this.setName(" ");
	}
	/**
	 * Constructor 
	 */
	public User(int id,String name) {
		this.setId(id);
		this.setName(name);
	}
	/**
	 * Return user id 
	 */
	public int getId() {
		return id;
	}
	/**
	 * Return user name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set user id 
	 */
	public void setId(int id) {
		if(id > 0)
		{
		this.id = id;
		}
		else
		{
			this.id = -1;
		}
	}
	/**
	 * Set user name 
	 */
	public void setName(String name) {
		this.name = name;
	}
}