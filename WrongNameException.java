//////////////////// WrongNameException ////////////////////
/**
 * WrongNameException represents an exception that is thrown
 * from an invalid category name.
 * @author Group 2
 */
@SuppressWarnings("serial")
public class WrongNameException extends Exception {
	
 /**
  * Constructor includes an error message for the user
  * @param msg 		The message to be displayed
  */
  public WrongNameException(String msg) {
    super(msg);
  }
  
} //////////////////// End of WrongNameException ////////////////////