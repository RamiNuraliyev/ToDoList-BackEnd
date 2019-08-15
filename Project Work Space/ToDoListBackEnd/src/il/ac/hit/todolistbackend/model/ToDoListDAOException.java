package il.ac.hit.todolistbackend.model;

/**
 * Exception for each error on the project   
 */
public class ToDoListDAOException extends Exception {
	/**
	 * Return message after error
	 */
	public ToDoListDAOException(String messege) {
		super(messege);
	}
	/**
	 * Return message and the cause after error
	 */
	public ToDoListDAOException(String messege, Throwable cause) {
		super(messege,cause);
	}

}
