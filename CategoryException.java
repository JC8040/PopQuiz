//////////////////// CategoryException ////////////////////
/**
 * CategoryException represents an exception that is thrown
 * from an invalid category.
 * @author Group 2
 */
@SuppressWarnings("serial")
public class CategoryException extends Exception {
	
  /**
   * Constructor includes an error message for the user
   * @param msg 	The message to be displayed
   */
  public CategoryException(String msg) {
    super(msg);
  }
  
} //////////////////// End of CategoryException ////////////////////