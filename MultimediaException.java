//////////////////// MultimediaException ////////////////////
/**
 * MultimediaException represents an exception that is thrown
 * from an invalid media file.
 * @author Group 2
 */
@SuppressWarnings("serial")
public class MultimediaException extends Exception {
  
  /**
   * Constructor includes an error message for the user
   * @param msg 	The message to be displayed
   */
  public MultimediaException(String msg){
    super(msg);
  }
  
} //////////////////// End of MultimediaException ////////////////////